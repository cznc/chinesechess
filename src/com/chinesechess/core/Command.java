package com.chinesechess.core;

/**
 * 棋手指令
 */
public class Command {
	byte sessionId;// 哪个对弈;支持一人同时开多局对弈,最多256局
	byte action; // 0=翻棋,1=走一步,2=认输,3=退出
	byte from; // 源位置
	byte to; // 目标位置

	public byte getSessionId() {
		return sessionId;
	}

	public void setSessionId(byte sessionId) {
		this.sessionId = sessionId;
	}

	public byte getAction() {
		return action;
	}

	public void setAction(byte action) {
		this.action = action;
	}

	public byte getFrom() {
		return from;
	}

	public void setFrom(byte from) {
		this.from = from;
	}

	public byte getTo() {
		return to;
	}

	public void setTo(byte to) {
		this.to = to;
	}

}