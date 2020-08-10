package com.chinesechess.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinesechess.core.Constant;
import com.chinesechess.core.ConstantEnv;
import com.chinesechess.core.Pannel;
import com.chinesechess.core.Response;
import com.chinesechess.core.Scene;
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
		User user= (User)request.getSession().getAttribute(ConstantEnv.SESSION_ATTR_USER);
		Scene scene=center.getByUserId(user.getId());
		Pannel chess = scene.getChess();
		
		String action = request.getParameter("action");
		if("quit".equals(action)) {
			center.quitScene(user.getId());
		}else if("add".equals(action)) {
			Integer id=Integer.parseInt(request.getParameter("id"));
			center.add2Scene(id, user.getId());
		}else if("create".equals(action)) {
			center.createScene(user.getId());
		}
		Response resp=new Response();
		resp.setMsg("ok.");
		resp.setStatus(Constant.RESPONSE_SUCCESS);
		String json = ResponseUtil.toJson(resp);
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
