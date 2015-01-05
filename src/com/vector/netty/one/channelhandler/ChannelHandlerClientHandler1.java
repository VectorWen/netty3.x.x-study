package com.vector.netty.one.channelhandler;

import java.nio.charset.Charset;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;


/**
 * @author vector
 *
 */
public class ChannelHandlerClientHandler1  extends SimpleChannelUpstreamHandler{
	
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e)
			throws Exception {
		super.channelConnected(ctx, e); 
		
		System.out.println("连接到了服务了1");
		
		ChannelBuffer channelBufferClient = ChannelBuffers.dynamicBuffer();
		String helloWorld = "I'm Client. HelloWorld.";
		
		channelBufferClient.writeBytes(helloWorld.getBytes());
		
		e.getChannel().write(channelBufferClient);
		
	}
	
	/**
	 * 接受完一个数据包事件
	 */
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
		
//		ChannelBuffer channelBufferServer = (ChannelBuffer) e.getMessage();
//		System.out.println("1 -- "+channelBufferServer.toString(Charset.forName("utf8"))+" -- end -- ");
		
		System.out.println((String)e.getMessage());
		
		e.getChannel().close();
		
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
