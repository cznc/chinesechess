package com.chinesechess.core;
/**
 * һ�ֶ���
 * */
public class Scene{
 int[] players;//���id
 int curr; //��ǰ�ֵ�˭
 int last; //����ߵ�ʱ��,��λ��,�������㳬ʱ���
 byte status;//���
 Command lastCmd;//���Ĳ���;�����Ǹ��Է��õ�;
 boolean newCmd;//���Ĳ����Ƿ���¹�
 Pannel myPannel;//����
 
 /**
  * ��ѯ�Ƿ��ֵ���ǰ�û�����
  * */
 public boolean isMyTurn(int userID) {
	 return curr==userID;
 }
 /**
  * �ֵ��Է����ӣ����ǶԷ�û����������10�룬���㳬ʱ���Ƿ�ʱ��
  * */
 public boolean isTimeout() {
	 if(System.currentTimeMillis()/1000 - last >10) {
		 return true;
	 }
	 return false;
 }
 /**
  * ��ѯ�Լ��Ǻ�ɫ������ɫ
  * */
 public byte getUserColor(int userID) {
	 for (int i = 0; i < players.length; i++) {
		if(players[i]==userID)return (byte)i;
	 }
	 return -1;
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