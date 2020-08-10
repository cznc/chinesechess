package com.chinesechess.core;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/***
 * 棋盘中心
 * 一个用户只能进一个棋盘;可以作为红方、蓝方、观看者;
 * */
public class SceneCenter implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer sceneIdSeqence=0;
	private Integer userIdSequence=0;
	private Map<Integer, Scene> centerByUser=new HashMap<Integer, Scene>();
	private Map<Integer, Scene> centerById=new HashMap<Integer, Scene>();
	/**
	 * 获取棋盘
	 * */
	public Scene getByUserId(int userid) {
		return centerById.get(userid);
	}
	/**
	 * 获取棋盘
	 * */
	public Scene getById(int id) {
		return centerById.get(id);
	}
	/**
	 * 有人加入到棋盘
	 * */
	public Scene add2Scene(int id, int userid) {
		Scene scene=getById(id);
		if(scene==null)return null;
		if(centerByUser.containsKey(id))return scene;
//		if(scene.players[1]!=0)return scene;//已经人满,不能加入
//		scene.players[1]=userid;
		scene.addPeople(userid);
//		scene.last=(int)(System.currentTimeMillis()/1000);
		centerByUser.put(userid,scene);
		return scene;
	}
	/**
	 * 退出棋盘
	 * */
	public void quitScene(int userid) {
		Scene scene=getByUserId(userid);
		if(scene==null)return;
//		scene.status=status;
		scene.peopleMap.remove(userid);
		centerByUser.remove(userid);
		if(scene.peopleMap.size()==0) {
			centerById.remove(scene.id);
		}
	}
	/**
	 * 新建棋盘
	 * */
	public int createScene(int userid) {
		sceneIdSeqence++;
		Scene scene=new Scene();
		scene.id=sceneIdSeqence;
//		scene.players[0]=userid;
		scene.peopleMap.put(userid, new Role());
		centerByUser.put(userid, scene);
		centerById.put(sceneIdSeqence, scene);
		return sceneIdSeqence;
	}
}
