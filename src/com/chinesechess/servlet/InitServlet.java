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
import com.chinesechess.core.Scene;
import com.chinesechess.core.SceneCenter;
import com.chinesechess.core.User;
import com.chinesechess.core.util.ResponseUtil;

/**
 * Servlet implementation class StepServlet
 */
@WebServlet("/api/init")
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
		SceneCenter center = (SceneCenter)request.getSession().getServletContext().getAttribute(ConstantEnv.APPLICATION_ATTR_SCENECENTER);
		User user= (User)request.getSession().getAttribute(ConstantEnv.SESSION_ATTR_USER);
		Scene scene=center.getByUserId(user.getId());
		Pannel chess = scene.getChess();
		
		byte[] table=chess.getTable();
		Map<String,Object>data=new HashMap<String,Object>();
		data.put("table", table);
		data.put("color", scene.getUserColor(user.getId()));//我的是哪个颜色
		data.put("turn", scene.isMyTurn(user.getId()));//是不是该我走棋了
		
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
