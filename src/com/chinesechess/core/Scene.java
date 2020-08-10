package com.chinesechess.core;
/**
 * 一局对弈
 * */
public class Scene{
 int[] players;//玩家id
 int curr; //当前轮到谁
 int last; //最后走的时间,单位秒,用来计算超时与否
 byte status;//结果
 Command lastCmd;//最后的步子;留着是给对方用的;
 boolean newCmd;//最后的步子是否更新过
 Pannel myPannel;//棋盘
 
 /**
  * 查询是否轮到当前用户走子
  * */
 public boolean isMyTurn(int userID) {
	 return curr==userID;
 }
 /**
  * 轮到对方走子，但是对方没动静，超过10秒，就算超时；是否超时；
  * */
 public boolean isTimeout() {
	 if(System.currentTimeMillis()/1000 - last >10) {
		 return true;
	 }
	 return false;
 }
 /**
  * 查询自己是红色还是蓝色
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