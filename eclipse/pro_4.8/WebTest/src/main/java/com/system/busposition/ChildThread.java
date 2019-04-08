package com.system.busposition;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 
 * 子线程模板
 * 		BIO框架
 * @author Alienware-Rocky
 *
 */

public class ChildThread implements Runnable {

	private Socket socket; // 声明通信服务器端
	private volatile static boolean flag = true;

	public ChildThread(Socket socket) {

		this.socket = socket;

	}

	@Override
	public void run() {

		InputStreamReader reader = null;
		PrintWriter writer = null;

		try {

			//OutputStream ops = socket.getOutputStream(); // 打开输出流通道
			//writer = new PrintWriter(new OutputStreamWriter(ops ,"UTF-8")); // 包装
			
			InputStream ips = socket.getInputStream(); // 打开输入流通道
			reader = new InputStreamReader(ips, "UTF-8");
//			reader = new BufferedReader(new InputStreamReader(ips, "UTF-8")); // 获取客户端的数据流，进行包装

			while (true) {
				
				// 日常延迟
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				/*
				 * 利用心跳检测 判断是否和远程客户端断开连接。
				 */
				try {
					socket.sendUrgentData(0xff);
				} catch (Exception e1) {
					System.out.println("线程" + Thread.currentThread().getName() + "结束工作");
					break;
				}
				
//				char[] rDataLen = new char[4];
//				reader.read(rDataLen, 0, 4);
//				int lenth = Integer.parseInt(new String(rDataLen));
//				char[] rContentTxt = new char[lenth];
//				reader.read(rContentTxt, 0, lenth);
//				new String(rDataLen) + new String(rContentTxt);
				String message = null;
				byte[] buf = new byte[1024];
	            int len=0;
	            len = ips.read(buf);
	            message = new String(buf, 0, len);
//	            while((len=reader.read(buf))!=-1){
//	                System.out.println(new String(buf,0,len) +" "+buf+" "+len);  //可用
//		            len=reader.read(buf);
//		            message = new String(buf,0,len);
//		            System.out.println(message);
//	                System.out.println(buf+" "+len);
//	            }
					
//				do {
//					message += reader.read();
//				} while (reader.read() != -1);
//				while ((line = reader.readLine()) != null) {
//					message += line;		//读取终端端消息
//				}
//				socket.setSoTimeout(3000);
//				message = reader.readLine();
				
				
				//-------------------------------------------------------------------------//
//				synchronized (this) {
				
//				WriteToMysql write = new WriteToMysql(message+"->"+Thread.currentThread().getName());
//				write.connect();
//				write.write();
				
//				}
				
				//-------------------------------------------------------------------------//
				System.out.println("客户端消息：" + message + "-->" + Thread.currentThread().getName() + ":" + socket.getPort());
				System.out.println(Thread.currentThread().getName() + "~socket.isConnected:" + socket.isConnected());
				
				//writer.println("接收");	// 向终端发送消息
				//writer.flush();				// 刷新输出流
				
			}

//			socket.shutdownOutput();	// 关闭输出流通道
			socket.shutdownInput();		// 关闭输入流通道

		} catch (IOException e) {
			e.printStackTrace();
		} finally {

			if (writer != null) {
				writer.close();
			}
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			System.out.println(socket);
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}

}
