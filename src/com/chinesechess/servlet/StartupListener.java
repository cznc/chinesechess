package com.chinesechess.servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.chinesechess.core.ConstantEnv;
import com.chinesechess.core.SceneCenter;

/**
 * Application Lifecycle Listener implementation class InitListener
 * 初始化
 */
@WebListener
public class StartupListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public StartupListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    		SceneCenter center = (SceneCenter)arg0.getServletContext().getAttribute(ConstantEnv.APPLICATION_ATTR_SCENECENTER);
    	if(center==null) {
    			center=new SceneCenter();
    			arg0.getServletContext().setAttribute(ConstantEnv.APPLICATION_ATTR_SCENECENTER,center);
    	}
    }
	
}
