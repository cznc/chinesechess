package com.chinesechess.core;

public class Response {// ����ָ����
	Command myCommand;// ָ��
	byte status; // 0=ָ�����,1=ָ�������,2=��Чָ��,3=�ҷ���,4=�Է���,5=�Է��˳�
	// final byte[][]table;//�������Ҫ,������ͬ����ǰ����,�������ܴ��ڵĲ�ͬ���쳣;
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