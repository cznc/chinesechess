package com.chinesechess.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.chinesechess.core.util.ResponseUtil;

/**
 * һ�ֶ���
 */
public class Scene  implements Serializable{
	private static final long serialVersionUID = 1L;
	
	int id;
	/**
	 * �������ۿ���,��������ṹ��������N���û�
	 */
	Map<Integer, Role> peopleMap = new HashMap<Integer, Role>();
	int[] players=new int[2];// �������
	byte turnIndex; // ��ǰ�ֵ�˭
//	int last; // ����ߵ�ʱ��,��λ��,�������㳬ʱ���
	byte status;// ״̬: 0δ���У�1���ڽ��У�2�ѽ���
	Pannel chess=new Pannel();
	public Pannel getChess() {return chess;}
	/**
	 * ����һ��,���㲥��ǰ������Է�;�Է���ʱ���ȡ
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
	 * ��ȡ�Է�������ָ���Լ�ϵͳ��Ϣ
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
	 * ��ѯ�Ƿ��ֵ���ǰ�û�����
	 */
	public boolean isMyTurn(int userID) {
		return players[turnIndex] == userID;
	}
	/**
	 * ��ǰ�û��������
	 */
	public void finishedMyTurn(int userID) {
		if( players[turnIndex] != userID) return;
		turnIndex++;
		turnIndex%=2;
	}
	/**
	 * �û���������
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
		//�㲥��Ϣ
	}
	/**
	 * �û��˳�����
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
		//�㲥��Ϣ
	}
//	/**
//	 * �ֵ��Է����ӣ����ǶԷ�û����������10�룬���㳬ʱ���Ƿ�ʱ��
//	 */
//	public boolean isTimeout() {
//		if (System.currentTimeMillis() / 1000 - last > 10) {
//			return true;
//		}
//		return false;
//	}

	/**
	 * ��ѯ�Լ��Ǻ�ɫ������ɫ
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