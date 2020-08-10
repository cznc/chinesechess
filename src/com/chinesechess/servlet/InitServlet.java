package com.chinesechess.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinesechess.core.ConstantEnv;
import com.chinesechess.core.Pannel;
import com.chinesechess.core.util.ResponseUtil;

/**
 * Servlet implementation class StepServlet
 */
@WebServlet("/init")
public class InitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InitServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Pannel chess = (Pannel)request.getSession().getAttribute(ConstantEnv.SESSION_ATTR_PANNEL);
		if(chess==null) {
			chess = new Pannel();
			request.getSession().setAttribute(ConstantEnv.SESSION_ATTR_PANNEL,chess);
		}
		byte[] table=chess.getTable();
		Map<String,Object>data=new HashMap<String,Object>();
		data.put("table", table);
		data.put("color", 1);//我的是哪个颜色
		data.put("turn", 1);//是不是该我走棋了
		
		String json = ResponseUtil.toJson(data);
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
