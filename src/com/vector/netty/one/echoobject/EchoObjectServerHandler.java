package com.vector.netty.one.echoobject;

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
public class EchoObjectServerHandler extends SimpleChannelUpstreamHandler{
	
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e)
			throws Exception {
		super.channelConnected(ctx, e); 
			
		System.out.println("有人连接服务器了");
	}
	
	/**
	 * 接受完一个数据包事件
	 */
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
		
		Person person = (Person) e.getMessage();
		System.out.println(person);
	
		
		person.setId(9000);
		person.setName("Vector-Server");
		person.setPasswd("7890-+");
		
		e.getChannel().write(person);
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
