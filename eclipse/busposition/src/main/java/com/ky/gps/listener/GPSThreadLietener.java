package com.ky.gps.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ky.gps.sys.StartThread;


/**
 * 监听器
 * 
 * @author Rocky
 *
 */
public class GPSThreadLietener extends HttpServlet implements ServletContextListener {

	private final static Logger LOGGER = LoggerFactory.getLogger(GPSThreadLietener.class);
    
    public GPSThreadLietener() {
    	
    }

    @Override
    public void contextDestroyed(ServletContextEvent arg0)  { 
    	System.out.println("----------- GPS解析系统停止 -----------");
    }

	@Override
    public void contextInitialized(ServletContextEvent arg0)  { 
//    	System.out.println("----------- GPS解析系统启动 -----------");
		LOGGER.info("----------- GPS解析系统启动 -----------");
    	
    	Thread startThread = new Thread(new StartThread());	//创建主线程对象
    	startThread.setName("MyThread");
    	startThread.setDaemon(true);	//设置为守护线程
    	startThread.start();			//启动主线程
    	
//    	System.out.println("----------- GPS信号监听开始 -----------");
		LOGGER.info("----------- GPS信号监听开始 -----------");
    }
	
}
