package com.chinesechess.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.chinesechess.core.util.ResponseUtil;

/**
 * 一局对弈
 */
public class Scene  implements Serializable{
	private static final long serialVersionUID = 1L;
	
	int id;
	/**
	 * 还包括观看者,所以这个结构中允许超过N个用户
	 */
	Map<Integer, Role> peopleMap = new HashMap<Integer, Role>();
	int[] players=new int[2];// 两个玩家
	byte turnIndex; // 当前轮到谁
//	int last; // 最后走的时间,单位秒,用来计算超时与否
	byte status;// 状态: 0未进行，1正在进行，2已结束
	Pannel chess=new Pannel();
	public Pannel getChess() {return chess;}
	/**
	 * 下完一步,即广播当前命令给对方;对方定时会获取
	 * **/
	public void broadcast(Integer userid, Response cmd) {
		if(!ResponseUtil.isCommandPermit(cmd))return;
		for(Map.Entry<Integer, Role>ent:peopleMap.entrySet()) {
			if(userid!=ent.getKey()) {
				ent.getValue().cmdList.add(cmd);
			}
		}
	}
	/**
	 * 获取对方的走子指令以及系统消息
	 * */
	public List<Response>popCommad(Integer userid){
		List<Response>list=new ArrayList<Response>();
		LinkedList <Response>queue=peopleMap.get(userid).cmdList;
		Response resp=queue.poll();
		while(resp!=null) {
			list.add(resp);
		}
		return list;
	}
	/**
	 * 查询是否轮到当前用户走子
	 */
	public boolean isMyTurn(int userID) {
		return players[turnIndex] == userID;
	}
	/**
	 * 当前用户走子完毕
	 */
	public void finishedMyTurn(int userID) {
		if( players[turnIndex] != userID) return;
		turnIndex++;
		turnIndex%=2;
	}
	/**
	 * 用户加入棋盘
	 * */
	public void addPeople(int userid) {
		if(peopleMap.containsKey(userid))return;
		if(players[0]==0) {
			peopleMap.put(userid, new Role(Constant.ROLE_COLORE_RED));
			players[0]=userid;
		}else if(players[1]==0) {
			peopleMap.put(userid, new Role(Constant.ROLE_COLORE_BLUE));
			players[1]=userid;
		} else {
			peopleMap.put(userid, new Role());
		}
		if(players[0]!=0&&players[1]!=0) {
			status = Constant.SCENE_RUNNING;
		}
		//广播消息
	}
	/**
	 * 用户退出棋盘
	 * */
	public void quitPeople(int userid) {
		Role role = peopleMap.get(userid);
		if(role==null)return ;
		if(role.color==Constant.ROLE_COLORE_RED) {
			this.players[0]=0;
			status = Constant.SCENE_NEW;
		}else if(role.color==Constant.ROLE_COLORE_BLUE) {
			this.players[1]=1;
			status = Constant.SCENE_NEW;
		}
		peopleMap.remove(userid);
		//广播消息
	}
//	/**
//	 * 轮到对方走子，但是对方没动静，超过10秒，就算超时；是否超时；
//	 */
//	public boolean isTimeout() {
//		if (System.currentTimeMillis() / 1000 - last > 10) {
//			return true;
//		}
//		return false;
//	}

	/**
	 * 查询自己是红色还是蓝色
	 */
	public byte getUserColor(int userid) {
		return peopleMap.get(userid).color;
	}

	/**
	 * 
	 * */
	public Command getCommand() {
//	 if()
		return null;
	}

	public void setCommand(Command cmd) {
//	 if()

	}
}