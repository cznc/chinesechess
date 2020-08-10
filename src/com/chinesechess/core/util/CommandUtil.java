package com.chinesechess.core.util;

import com.chinesechess.core.Command;

public class CommandUtil {
	public final static Command build(byte action,byte from,byte to) {
		
		Command cmd=new Command();
		cmd.setAction(action);
		cmd.setFrom(from);
		cmd.setTo(to);
		return cmd; 
	}
}
