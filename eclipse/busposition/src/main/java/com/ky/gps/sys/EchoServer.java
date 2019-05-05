package com.ky.gps.sys;

import java.util.concurrent.TimeUnit;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;

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
		
			// 保持连接数
			.option(ChannelOption.SO_BACKLOG, 10)
			// 有数据立即发送
			.option(ChannelOption.TCP_NODELAY, true)
			// 设置通道类型NIO
			.channel(NioServerSocketChannel.class)
			// 保持连接
			.childOption(ChannelOption.SO_KEEPALIVE, true)
//			.localAddress(new InetSocketAddress(port))			// 设置监听端口
			// 处理新连接
			.childHandler(new ChannelInitializer<SocketChannel>() {
				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					//使用了netty自带的编码器和解码器
					//心跳检测，读超时，写超时，读写超时
					//ch.pipeline().addLast(new StringDecoder(),new StringEncoder(),new IdleStateHandler(5, 0, 0, TimeUnit.SECONDS));
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
