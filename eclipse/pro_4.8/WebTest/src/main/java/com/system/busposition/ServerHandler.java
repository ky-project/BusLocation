package com.system.busposition;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ServerHandler extends ChannelInboundHandlerAdapter {
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		System.out.println("接收到了："+msg);
		ByteBuf buf = (ByteBuf) msg;
		byte[] req = new byte[buf.readableBytes()];
		buf.readBytes(req);
		String body = new String(req, "UTF-8");
		System.out.println("接收客户端数据:" + body);
		ByteBuf pingMessage = Unpooled.buffer();
		pingMessage.writeBytes(req);
		ctx.writeAndFlush(pingMessage);
	}
	
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		ctx.channel().closeFuture();
		System.out.println("客户端与服务端连接关闭...");
	}
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
		System.out.println("信息接收完毕...");
	}
}
