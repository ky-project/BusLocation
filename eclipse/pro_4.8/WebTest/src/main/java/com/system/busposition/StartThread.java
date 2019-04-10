package com.system.busposition;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;


/**
 * 主线程
 * 
 * @author Alienware-Rocky
 *
 */


public class StartThread implements Runnable {
	
	private ThreadPoolExecutor executor;	//声明线程池
	private static final int POOL_MAX = 20;
	private boolean flag = true;
	
	public StartThread() {
		
//		this.executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(POOL_MAX);	//初始化线程池设置最大线程数	
		
	}

	@Override
	public void run() {
		ServerSocket serverSocket = null;
		
		
		int GPSprot = 8001;
		System.out.println("-------------监听端口GPSport: "+GPSprot+"-------------");
		
		/*
		 * 创建服务器，开启守护线程 
		 */
		NIOServerSocket nss = new NIOServerSocket(GPSprot);
		EchoServer echoServer = new EchoServer();
		try {
			
//			serverSocket = new ServerSocket(GPSprot);	//设置守候端口
			//---------------------------------------------------------------//
//			echoServer.setPort(GPSprot);
//			echoServer.init();
			//---------------------------------------------------------------//

//			while(flag) {
							
				nss.start();
				
				
//				try {
//					echoServer.bind();
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
				
//				executor.setMaximumPoolSize(POOL_MAX);	//设置线程池最大任务数
//				executor.setCorePoolSize(4);	//设置线程池核心线程个数
				
				
				// 服务器一直等待客户端请求,无请求闲置;有请求返回请求的Socket连接
//				Socket clientSocket = serverSocket.accept();
				
//				System.out.println(executor.getMaximumPoolSize()+" "+executor.getCorePoolSize()+" "+executor.getQueue());

//				executor.submit(new ChildThread(clientSocket));	//创建新线程，并向线程池提交线程任务
//				ChildThread childThread = new ChildThread(clientSocket);
//				new Thread(childThread).start();
				
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (nss != null) {
				nss.shutdown();
			}
//			if (echoServer != null) {
//				echoServer.shutdown();
//			}
		}
	}
}
