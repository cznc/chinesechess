package com.chinesechess.core;

import java.io.Serializable;
/**
 * 旗手指令结果
 * */
public class Response implements Serializable {
	private static final long serialVersionUID = 1L;

	Command myCommand;// 指令
	byte status; // 0=指令被允许,1=指令不被允许,2=无效指令,3=我方输,4=对方输,5=对方退出
	// final byte[][]table;//如果有需要,不定期同步当前棋谱,矫正可能存在的不同步异常;
	String msg;
	public Command getMyCommand() {
		return myCommand;
	}

	public void setMyCommand(Command myCommand) {
		this.myCommand = myCommand;
	}

	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}