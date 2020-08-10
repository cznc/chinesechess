package com.chinesechess.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinesechess.core.Command;
import com.chinesechess.core.Constant;
import com.chinesechess.core.ConstantEnv;
import com.chinesechess.core.Pannel;
import com.chinesechess.core.Response;
import com.chinesechess.core.util.CommandUtil;
import com.chinesechess.core.util.ResponseUtil;
import com.google.gson.Gson;

/**
 * Servlet implementation class StepServlet
 */
@WebServlet("/step")
public class StepServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StepServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/////////////////build Command from parameter =>
		String actionStr=request.getParameter("action");
		String fromStr=request.getParameter("from");
		String toStr=request.getParameter("to");
		byte action=0;
		byte from=0;
		byte to=0;
		try {
			action=new Byte(actionStr);
			from=new Byte(fromStr);
			to=new Byte(toStr);
		}catch(Exception e) {
			String json = ResponseUtil.failed(null);
			response.getWriter().append(json);
			return;
		}
		Command cmd=CommandUtil.build(action,from,to);
		///////////////// <= build Command from parameter
		
		Pannel chess = (Pannel)request.getSession().getAttribute(ConstantEnv.SESSION_ATTR_PANNEL);//一人可以多局,此处简化，演示用
		if(chess==null) {
			chess = new Pannel();
			request.getSession().setAttribute(ConstantEnv.SESSION_ATTR_PANNEL,chess);
		}
		
		///////////////// if ACTION_DISCONVER  
		if(Constant.ACTION_STEP_DISCONVER==action) {
			byte item = chess.discover(from);
			cmd.setTo(item);
			String json = ResponseUtil.success(cmd);
			response.getWriter().append(json);
			return;
		}else if(Constant.ACTION_STEP_OTHERS==action) {
			byte result = chess.other(from,to,true);
			cmd.setAction(result);
			String json = ResponseUtil.success(cmd);
			response.getWriter().append(json);
			return;
		}
		///////////////// fi ACTION_DISCONVER
		
		String json = ResponseUtil.failed(null);
		response.getWriter().append(json);
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
