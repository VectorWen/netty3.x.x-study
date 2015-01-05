package com.vector.netty.one.channel;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import org.jboss.netty.handler.codec.http.DefaultHttpRequest;
import org.jboss.netty.handler.codec.http.HttpResponse;
import org.jboss.netty.handler.codec.http.HttpVersion;


/**
 * 2015年1月1日21:31:53
 * @author vector
 *
 */
public class HttpClientHandler  extends SimpleChannelUpstreamHandler{
	
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e)
			throws Exception {
		super.channelConnected(ctx, e); 
		
		System.out.println("连接好了");
		
		DefaultHttpRequest request = new DefaultHttpRequest(new HttpVersion("1.1", false), null, "www.baidu.com");
		
		
		e.getChannel().write(request);
	}
	
	/**
	 * 接受完一个数据包事件
	 */
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
		
		
		HttpResponse response = (HttpResponse) e.getMessage();
		
		System.out.println(response);
		
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
