package com.system.busposition;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class EchoServer {
	
	private EventLoopGroup group ;
	private ServerBootstrap bootstrap;
	private int port ;
	
	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public void init() {
		group = new NioEventLoopGroup();
		bootstrap = new ServerBootstrap();
		bootstrap.group(group)
			.option(ChannelOption.SO_BACKLOG, 4)
			.channel(NioServerSocketChannel.class)				//设置通道类型NIO
//			.localAddress(new InetSocketAddress(port))			//设置监听端口
			.childHandler(new ChannelInitializer<SocketChannel>() {
				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(new ServerHandler());
				}
			});
	}
	
	
	public void bind() throws InterruptedException {
		
		ChannelFuture channelFuture = bootstrap.bind(port).sync().channel().closeFuture().sync();
//		channelFuture.channel().closeFuture().sync();
	}
	
	public void shutdown() {
		if (group != null) {
			group.shutdownGracefully();
		}
	}
	
}
