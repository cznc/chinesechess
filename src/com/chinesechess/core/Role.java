package com.chinesechess.core;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * �������������Ϊ��ɫ��������� <br/>
 * ��Ƴɵ�һ��������û�Ϊ��ɫ,�ڶ���Ϊ��ɫ, ֮������Ϊ�ۿ���;<br/> 
 * ���淭�Ӿ�����һ�����߼� �������<br/>
 */
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	public Role() {
		this.color=Constant.ROLE_COLORE_NONE;
	}

	public Role(byte color) {
		this.color=color;
	}

	byte color;
	// ���������ҵ�ָ��
	LinkedList<Response> cmdList = new LinkedList<Response>();
	
	public static void main(String[] args) {
		LinkedList<Integer> cmdList = new LinkedList<Integer>();
		cmdList.add(1);
		cmdList.add(2);
		System.out.println(cmdList.poll());
		System.out.println(cmdList.poll());
		System.out.println(cmdList.poll());
	}
}
