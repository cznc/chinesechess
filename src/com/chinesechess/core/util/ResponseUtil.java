package com.chinesechess.core.util;

import java.util.Map;

import com.chinesechess.core.Command;
import com.chinesechess.core.Constant;
import com.chinesechess.core.Response;
import com.google.gson.Gson;

public class ResponseUtil {
	
	public final static Response success(Command cmd,String msg) {
		return build(cmd,Constant.RESPONSE_SUCCESS,msg);
	}
	public final static Response failed(Command cmd,String msg) {
		return build(cmd,Constant.RESPONSE_FAILED,msg);
	}
	public final static Response success(Command cmd) {
		return build(cmd,Constant.RESPONSE_SUCCESS,"");
	}
	public final static Response failed(Command cmd) {
		return build(cmd,Constant.RESPONSE_FAILED,"");
	}
	public final static Response build(Command cmd,byte status,String msg) {
		Response resp=new Response();
		resp.setMyCommand(cmd);
		resp.setStatus(status);
		resp.setMsg(msg);
		return resp;
	}
	public final static String toJson(Map<String,Object>data) {
		Gson gson = new Gson();
		String json = gson.toJson(data);
		return json;
	}
	public final static String toJson(Response data) {
		Gson gson = new Gson();
		String json = gson.toJson(data);
		return json;
	}
	/**
	 * ���ָ���Ƿ�������ִ��; ����ִ�е�ָ��, ����Ҫ��֪�������ҲҪ�ط�
	 * */
	public static final boolean isCommandPermit(Response resp) {
		if(resp.getStatus()==Constant.RESPONSE_FAILED)return false;
		if(resp.getStatus()==Constant.RESPONSE_INVALID)return false;
		return true;
	}
}
