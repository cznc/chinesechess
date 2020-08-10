package com.chinesechess.core;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * 玩家在棋盘中作为角色的相关属性 <br/>
 * 设计成第一个进入的用户为红色,第二个为蓝色, 之后进入的为观看者;<br/> 
 * 代替翻子决定哪一方的逻辑 简化了设计<br/>
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
	// 存放其他玩家的指令
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
