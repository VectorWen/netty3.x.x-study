package com.vector.netty.one.echoobject;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;


/**
 * 2015年1月1日21:31:53
 * @author vector
 *
 */
public class EchoObjectClientHandler  extends SimpleChannelUpstreamHandler{
	
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e)
			throws Exception {
		super.channelConnected(ctx, e); 
		
		System.out.println("连接到了服务了");
		
		Person person = new Person(110, "Vector-Client", "123456");
		
		//可以直接把一个对象发送出去，Pipeline里面有对应的编码Handler，当然这些都是自己写的
		e.getChannel().write(person);
	}
	
	/**
	 * 接受完一个数据包事件
	 */
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
		
		//接收到的数据会主动解码为Person对象
		Person person = (Person) e.getMessage();
		
		System.out.println(person);
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
