package com.chinesechess.core;

public interface Constant {
	public byte ACTION_STEP_DISCONVER=0;
	public byte ACTION_STEP_OTHERS=1;
	
//	0=指令被允许,1=指令不被允许,2=无效指令,3=我方输,4=对方输,5=对方退出
	public byte RESPONSE_SUCCESS=0;
	public byte RESPONSE_FAILED=1;
	public byte RESPONSE_INVALID=2;
	public byte RESPONSE_LOSS0=3;
	public byte RESPONSE_LOSS1=4;
	public byte RESPONSE_QUIT=5;
	/**未登录*/
	public byte RESPONSE_LOGIN=(byte)255;
	//byte status;// 状态: 0未进行，1正在进行，2已结束
	public byte SCENE_NEW=0;
	public byte SCENE_RUNNING=1;
	public byte SCENE_FINISHED=2;
	//未知,红方,蓝方
	public byte ROLE_COLORE_NONE=0;
	public byte ROLE_COLORE_RED=1;
	public byte ROLE_COLORE_BLUE=2;
}
