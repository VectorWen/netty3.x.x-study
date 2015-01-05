package com.vector.netty.one.channelhandler;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;

/**
 * @author vector
 *
 */
public class ChannelHandlerClient {
	public void run(String host,int port){

		/**
		 * 客户端需要的是ClientChannelFactory
		 */
		ClientBootstrap bootstrap = new ClientBootstrap(
				new NioClientSocketChannelFactory(
						Executors.newCachedThreadPool(),
						Executors.newCachedThreadPool()));
		// 设置一个处理服务端消息和各种消息事件的类(Handler)
		bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
				public ChannelPipeline getPipeline()throws Exception { 
					
					ChannelPipeline pipeline = Channels.pipeline();
					
					pipeline.addLast("decoder2",new Stringdecoder2());
					
					pipeline.addAfter("decoder2", "decoder1",new Stringdecoder1());
					
					pipeline.addLast("handler1",new ChannelHandlerClientHandler1());
					
					return pipeline;
				}
		});
		// 连接到本地的8000端口的服务端
		bootstrap.setOption("tcpNoDelay", true);
		bootstrap.connect(new InetSocketAddress(host, port));
	}
	
	public static void main(String[] args) {
		new ChannelHandlerClient().run("127.0.0.1", 8080);
	}
}
