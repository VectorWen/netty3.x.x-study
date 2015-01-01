package com.vector.netty.one.echoobject;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
/**
 * 2015年1月1日20:56:52
 * @author vector
 *
 */
public class EchoObjectServer {

	public void run(int port) {
		ServerBootstrap bootstrap = new ServerBootstrap(
				
				new NioServerSocketChannelFactory(
						Executors.newCachedThreadPool(),
						Executors.newCachedThreadPool()));
		
		bootstrap.setPipelineFactory(
			new ChannelPipelineFactory() {
				public ChannelPipeline getPipeline() throws Exception {
					ChannelPipeline pipeline = Channels.pipeline();
					pipeline.addLast("encoder",new PersonDecoder());
					pipeline.addLast("decoder",new PersonEncoder());
					pipeline.addLast("handler", new EchoObjectServerHandler());
					return pipeline;
				}
		});
		// 最后绑定端口，就开始接受客户端的连接了
		bootstrap.bind(new InetSocketAddress(port));
	}

	public static void main(String[] args) {
		new EchoObjectServer().run(8080);
	}
	
}
