package com.ky.gps.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpServlet;

import com.ky.gps.sys.StartThread;


/**
 * 监听器
 * 
 * @author Alienware-Rocky
 *
 */

public class GPSThreadLietener extends HttpServlet implements ServletContextListener {

    
    public GPSThreadLietener() {
    	
    }

    public void contextDestroyed(ServletContextEvent arg0)  { 
    	System.out.println("----------- GPS解析系统停止 -----------");
    }

    public void contextInitialized(ServletContextEvent arg0)  { 
    	System.out.println("----------- GPS解析系统启动 -----------");
    	
    	Thread startThread = new Thread(new StartThread());	//创建主线程对象
    	startThread.setName("MyThread");
    	startThread.setDaemon(true);	//设置为守护线程
    	startThread.start();			//启动主线程
    	
    	System.out.println("----------- GPS信号监听开始 -----------");
    }
	
}
