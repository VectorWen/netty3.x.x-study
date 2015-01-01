package com.vector.netty.one.echoobject;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;

/**
 * 2015年1月1日23:41:21
 * @author vector
 *
 */
public class EchoObjectClient {
	public void run(String host,int port){
		
		ClientBootstrap bootstrap = new ClientBootstrap(
				new NioClientSocketChannelFactory(
						Executors.newCachedThreadPool(),
						Executors.newCachedThreadPool()));
		
		bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
				public ChannelPipeline getPipeline()throws Exception { 
					
					ChannelPipeline pipeline = Channels.pipeline();
					
					//发送一个对象，需要编码解码的Handler，
					//当发送数据就会发出一个编码事件，ChannelPipeline就会回调encoder处理的encoder方法，拿到一个ChannelBuffer，之后底层发送出去
					//当接收数据就会发出一个解码事件，ChannelPipeline就会回调decoder处理器的decoder方法，拿到一个对象，之后产生messageReceived 事件
					pipeline.addLast("encoder",new PersonDecoder());
					pipeline.addLast("decoder",new PersonEncoder());
					pipeline.addLast("handler",new EchoObjectClientHandler());
					return pipeline;
				}
		});
		
		bootstrap.connect(new InetSocketAddress(host, port));
	}
	
	public static void main(String[] args) {
		new EchoObjectClient().run("127.0.0.1", 8080);
	}
}
