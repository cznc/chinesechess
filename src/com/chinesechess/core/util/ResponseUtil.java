package com.chinesechess.core.util;

import java.util.Map;

import com.chinesechess.core.Command;
import com.chinesechess.core.Constant;
import com.chinesechess.core.Response;
import com.google.gson.Gson;

public class ResponseUtil {
	public final static String success(Command cmd,String msg) {
		return build(cmd,Constant.RESPONSE_SUCCESS,msg);
	}
	public final static String failed(Command cmd,String msg) {
		return build(cmd,Constant.RESPONSE_FAILED,msg);
	}
	public final static String success(Command cmd) {
		return build(cmd,Constant.RESPONSE_SUCCESS,"");
	}
	public final static String failed(Command cmd) {
		return build(cmd,Constant.RESPONSE_FAILED,"");
	}
	public final static String build(Command cmd,byte status,String msg) {
		Response resp=new Response();
		resp.setMyCommand(cmd);
		resp.setStatus(status);
		resp.setMsg(msg);
		Gson gson = new Gson();
		String json = gson.toJson(resp);
		return json;
	}
	public final static String toJson(Map<String,Object>data) {
		Gson gson = new Gson();
		String json = gson.toJson(data);
		return json;
	}
}
