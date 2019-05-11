package com.ky.gps.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpServlet;

import com.ky.gps.sys.StartThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 监听器
 *
 * @author Alienware-Rocky
 */
public class GPSThreadLietener extends HttpServlet implements ServletContextListener {

    private final static Logger LOGGER = LoggerFactory.getLogger(GPSThreadLietener.class);

    public GPSThreadLietener() {

    }
    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        LOGGER.info("----------- GPS解析系统停止 -----------");
    }

    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        LOGGER.info("----------- GPS解析系统启动 -----------");
        //创建主线程对象
        Thread startThread = new Thread(new StartThread());
        startThread.setName("MyThread");
        //设置为守护线程
        startThread.setDaemon(true);
        //启动主线程
        startThread.start();

        LOGGER.info("----------- GPS信号监听开始 -----------");
    }

}
