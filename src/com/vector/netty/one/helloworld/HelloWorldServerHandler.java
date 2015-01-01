package com.vector.netty.one.helloworld;

import java.nio.charset.Charset;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
/**
 * 
 * 2015年1月1日21:10:56
 * @author vector
 *
 */
public class HelloWorldServerHandler extends SimpleChannelUpstreamHandler{
	
	/**
	 * 当客户端成功连接上来了，就会产生一个事件，Channel把事件传递给ChannelPipeline，ChannelPipeline
	 * 找到相应的Handler，之后就回调了这个方法
	 */
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e)
			throws Exception {
		super.channelConnected(ctx, e); 
			
		System.out.println("有人连接服务器了");
	}
	
	/**
	 * 接受完一个数据包事件
	 */
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
		
		//接受到客户端发来的数据，之后打印，ChannelBuffer 是在NIO 里面发送一个数据包使用的缓冲区
		//服务端和客户端之间发送接收数据一定是使用ChannelBuffer封装，没有write(7) 这样的了
		ChannelBuffer channelBufferClient = (ChannelBuffer) e.getMessage();
		System.out.println(channelBufferClient.toString(Charset.forName("utf8")));
	
		//使用ChannelBuffers 拿到ChannelBuffer，这里拿到一个动态的ChannelBuffer，
		ChannelBuffer channelBufferServer = ChannelBuffers.dynamicBuffer();
		String helloWorld = "I'm Server. HelloWorld.";
		
		channelBufferServer.writeBytes(helloWorld.getBytes());
		
		//回送一个Buffer给客户端
		e.getChannel().write(channelBufferServer);
	}
	
	    
	public void channelDisconnected(ChannelHandlerContext ctx,
			ChannelStateEvent e) throws Exception {
		super.channelDisconnected(ctx, e);
		
		System.out.println("客户端关闭了");
	}

	/**
	 * 发生异常时候的回调方法
	 */
	public void exceptionCaught(
	            ChannelHandlerContext ctx, ExceptionEvent e) {
		e.getChannel().close();
		System.out.println("exception");
	}

}
