package com.chinesechess.core;

/**
 * ����ָ��
 */
public class Command {
	byte sessionId;// �ĸ�����;֧��һ��ͬʱ����ֶ���,���256��
	byte action; // 0=����,1=��һ��,2=����,3=�˳�
	byte from; // Դλ��
	byte to; // Ŀ��λ��

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