package com.chinesechess.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinesechess.core.Constant;
import com.chinesechess.core.ConstantEnv;
import com.chinesechess.core.Response;
import com.chinesechess.core.User;
import com.chinesechess.core.util.ResponseUtil;

/**
 * Servlet Filter implementation class AuthorizationFilter
 */
@WebFilter(filterName="/AuthorizationFilter", urlPatterns = { "/api/*" })
public class AuthorizationFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AuthorizationFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest request=(HttpServletRequest)req;
		HttpServletResponse response=(HttpServletResponse)resp;
		String path=request.getServletPath();
		User user= (User)request.getSession().getAttribute(ConstantEnv.SESSION_ATTR_USER);
		if(user==null && !"/login".equals(path)) {
			Response cmd=ResponseUtil.build(null,Constant.RESPONSE_LOGIN,"未登录请先登录");
			String json = ResponseUtil.toJson(cmd);
			response.getWriter().append(json);
			return;
		}
		// pass the request along the filter chain
		chain.doFilter(req, resp);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
