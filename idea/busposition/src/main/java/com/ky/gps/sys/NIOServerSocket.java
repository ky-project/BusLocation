package com.ky.gps.sys;

import java.io.IOException;
import java.net.InetSocketAddress;
//import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

import com.ky.gps.util.ParseGPSUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * NIO框架
 * 
 * @author Alienware-Rocky
 *
 */

public class NIOServerSocket {
	private Selector selector;					// 选择器
	private ServerSocketChannel socketChannel;	// 通信管道
//	private static ServerSocket serverSocket;	// 通信服务
	private static ParseGPSUtil parseGPSUtil;
	private int port;
	private final static Logger LOGGER = LoggerFactory.getLogger(NIOServerSocket.class);
	

	public NIOServerSocket(int port) {
		this.port = port;
	}

	
	/*
	 * 	NIO服务器初始化
	 */
	public void init() throws IOException {
		
		selector = Selector.open();						// 创建通道选择器，并打开
		socketChannel = ServerSocketChannel.open();		// 创建ServerSocketChannel通道，并打开
		socketChannel.configureBlocking(false);			// 设置非阻塞模式，true为阻塞，
		parseGPSUtil = new ParseGPSUtil();
		parseGPSUtil.init();
	}
	
	/*
	 * 	监听服务开启
	 */
	public void start() {
		
		try {
			init();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			
//			serverSocket = socketChannel.socket();							// 
			socketChannel.bind(new InetSocketAddress(port));				// 将信道绑定到指定端口
			socketChannel.register(selector, SelectionKey.OP_ACCEPT);		// 把服务器通道注册到多路复用器上，并且监听阻塞事件
			
			while (true) {
				
				int channels = selector.select();		// 设置选择器开始监听端口，并获取可用通道
				if (channels <= 0) {					// 判断是否存在可用的通道
					continue;
				}
				
				Set<SelectionKey> selectionKeys = selector.selectedKeys();	// 获得所有的keys
				Iterator<SelectionKey> iterator = selectionKeys.iterator();	// 使用iterator遍历所有的keys
				SelectionKey key = null;
				
				try {
					
					while (iterator.hasNext()) {		// 循环遍历SelectionKeySet中的所有的SelectionKey
						
						key = iterator.next();			// 选择一个元素
						iterator.remove();				// 这里需要手动从键集中移除当前的key
						
						try {
							
							handleEvent(selector,key);	// 自定义事件处理
							
						} catch (Exception e) {
							if (key != null) {
								key.cancel();
								if (key.channel() != null)
									key.channel().close();
							}
						}
						
					}
					
				} catch (Exception e) {
//					System.out.println("通道开启失败！");
					LOGGER.info("通道开启失败！");
					e.printStackTrace();
				} finally {
					if (selectionKeys != null) {
						selectionKeys.clear();
					}
				}
			}
		} catch (IOException e) {
//			System.out.println("监听开启失败!");
			LOGGER.info("监听开启失败!");
			e.printStackTrace();
		} finally {
			shutdown();
		}
	}
	
	public void shutdown() {
		if (socketChannel != null) {
			try {
				socketChannel.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (selector != null) {
			try {
				selector.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void handleEvent(Selector selector, SelectionKey key) throws IOException {

		if (key.isValid()) {		// 如果是有效的，则执行下一步
			// 处理新接入的请求消息
			if (key.isAcceptable()) {
				
				ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
				SocketChannel sc = ssc.accept();		// 定义有新请求的通道
//				System.out.println("客户端请求连接...");
				LOGGER.info("客户端请求连接...");
				sc.configureBlocking(false);			// 设置非阻塞模式
				sc.register(selector, SelectionKey.OP_READ);	// 注册该通道为可读可写状态
//				System.out.println("Client: " + sc.getRemoteAddress() + " --> 连接成功! ");
				LOGGER.info("Client: " + sc.getRemoteAddress() + " --> 连接成功! ");
			}else if (key.isReadable()) {		// 判断通道是否可读
				
				SocketChannel sc = (SocketChannel) key.channel();		// 获取之前注册的socket通道对象
				ByteBuffer readBuffer = ByteBuffer.allocate(1024);		// 设置缓冲区
				int readBytes = sc.read(readBuffer);					// 将通道中的数据读到缓冲区
				
				if (readBytes > 0) {
					
					readBuffer.flip();									// 有数据则进行读取，读取之前需要进行复位方法(把position 和limit进行复位)
					byte[] bytes = new byte[readBuffer.remaining()];	// 根据缓冲区的数据长度创建相应大小的byte数组，接收缓冲区的数据
					readBuffer.get(bytes);		// 接收缓冲数据
					String request = new String(bytes, "UTF-8"); 		// 接收到的输入，并设置编码格式
//					System.out.println("Client: " + sc.getRemoteAddress() + " --> msg: " + request);
					LOGGER.info("Client: " + sc.getRemoteAddress() + " --> msg: " + request);
					//-------------------------------------------------------------------------//
					
					parseGPSUtil.setSbGPS("20001030");
					parseGPSUtil.parse(request);
					
					//-------------------------------------------------------------------------//
					
					String response = " ok! ";
					doWrite(sc, response);
					
				} else if (readBytes < 0) {
					// 对端链路关闭
					key.cancel();
					sc.close();
				} else
					; // 读到0字节，忽略
				
				readBuffer.clear();
			}
		}
	}

	public static void doWrite(SocketChannel channel, String response) throws IOException {
		
		if (response != null && response.trim().length() > 0) {
			
			byte[] bytes = response.getBytes();
			ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
			writeBuffer.put(bytes);
			writeBuffer.flip();
			channel.write(writeBuffer);
			writeBuffer.clear();
			writeBuffer.compact();		// 为读入腾出更多空间
		}
	}
}
