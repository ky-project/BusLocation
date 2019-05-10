package com.ky.gps.sys;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ServerHandler extends ChannelInboundHandlerAdapter {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(StartThread.class);
	private ParseGPS parseGPS;
	
	/**
	 * 连接时调用
	 */
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		LOGGER.info("Client: " + ctx.channel() + " --> 连接成功! ");
		parseGPS = new ParseGPS();
		parseGPS.init();
		
	}
	
	/**
	 * 客户端与服务端断开连接时调用
	 */
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		ctx.channel().closeFuture();
		LOGGER.info("客户端与服务端连接关闭...");
	}
	
	/**
	 * 服务端接收客户端发送过来的数据时调用
	 */
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

		LOGGER.info("接收: "+ctx.name()+"-->msg: "+msg);
		ByteBuf buf = (ByteBuf) msg;
		byte[] req = new byte[buf.readableBytes()];
		buf.readBytes(req);
		String body = new String(req, "UTF-8");
		LOGGER.info("接收客户端数据:" + body);

		/*
		 * 解析信息
		 */
		String response = parseGPS.parse(body);
		byte[] resp = response.getBytes();
		ByteBuf pingMessage = Unpooled.buffer();
		pingMessage.writeBytes(resp);
		ctx.writeAndFlush(pingMessage);
	}
	
	/**
	 * 服务端接收客户端发送过来的数据结束之后调用
	 */
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
		LOGGER.info("信息接收完毕...");
	}
	
	/**
     * 工程出现异常的时候调用
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    	LOGGER.error("",cause);
        ctx.close();
    }
}
