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
import com.chinesechess.core.SceneCenter;
import com.chinesechess.core.User;
import com.chinesechess.core.util.ResponseUtil;

/**
 * 首次使用时新建自己帐号、注销自己等动作
 */
@WebServlet("/api/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SceneCenter center = (SceneCenter)request.getSession().getServletContext().getAttribute(ConstantEnv.APPLICATION_ATTR_SCENECENTER);
		
		String action = request.getParameter("action");
		if("create".equals(action)) {
			String nickname=request.getParameter("nickname");
			if(nickname==null||nickname.trim().equals(""))nickname="嚯嚯";
			User user= new User();
			user.setId(center.getUserIdSequence());
			user.setName(nickname);
			request.getSession().setAttribute(ConstantEnv.SESSION_ATTR_USER,user);

			Map<String,Object>data=new HashMap<String,Object>(){/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

			{
				put("user",user);
			}};
			String json = ResponseUtil.toJson(data);
			response.getWriter().append(json);
			return;
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
