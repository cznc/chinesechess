package com.chinesechess.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinesechess.core.ConstantEnv;
import com.chinesechess.core.Response;
import com.chinesechess.core.Scene;
import com.chinesechess.core.SceneCenter;
import com.chinesechess.core.User;
import com.chinesechess.core.util.ResponseUtil;

/**
 * 获取异步指令,对方的执行，系统消息
 */
@WebServlet("/api/hb")
public class HeartBeatingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HeartBeatingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SceneCenter center = (SceneCenter)request.getSession().getServletContext().getAttribute(ConstantEnv.APPLICATION_ATTR_SCENECENTER);
		User user= (User)request.getSession().getAttribute(ConstantEnv.SESSION_ATTR_USER);
		Scene scene=center.getByUserId(user.getId());
		
		List<Response> resp = scene.popCommad(user.getId());
		Map<String,Object>data=new HashMap<String,Object>(){/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

		{
			put("cmd",resp);
		}};
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
