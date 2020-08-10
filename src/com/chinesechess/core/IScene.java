package com.chinesechess.core;
/**
 * 两人对弈的场景
 * 
 * */
public interface IScene {
	/**
	 * 用户进来,要找到自己的那盘
	 * */
	Pannel getPannel(int userid);
	/**
	 *  怎么确定谁的顺序
	 * */
	Pannel getPannel0(int userid);
	
}
