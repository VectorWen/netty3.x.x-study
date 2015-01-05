package com.vector.netty.one.channelhandler;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
/**
 * @author vector
 *
 */
public class ChannelHandlerServer {

	public void run(int port) {
		// Server服务启动器
		ServerBootstrap bootstrap = new ServerBootstrap(
				
				//ChannelFactory 是用来创建Channel的，现在不明白什么是Channel 没有关系，我们现在才写HelloWorld
				//了解有这个东西，这个东西怎么来就算了解很多了
				new NioServerSocketChannelFactory(
						//需要两个线程池，第一个是Boss 线程池，第二个是Worker线程池，帮Channel做功的
						Executors.newCachedThreadPool(),
						Executors.newCachedThreadPool()));
		
		//PipelineFactory 是用来创建ChannelPipeline，是一个被Channel 包裹的一条通道，用来接收Channel发来的事件
		//同样如果你不明白Channel与ChannelPipeline的关系没有问题，你可以先记下《是一个被Channel 包裹的一条通道》，以后你会说：哇，原来是这样的
		bootstrap.setPipelineFactory(
			new ChannelPipelineFactory() {
				//重写一个方法，用来get到ChannelPipeline的
				public ChannelPipeline getPipeline() throws Exception {
					//使用Channels 拿到一个没有Handler 的Pipeline
					ChannelPipeline pipeline = Channels.pipeline();
					//添加Handler，ChannelPipeline拿到Channel传来的事件就会交给Handler处理，这里就是添加这些Handler
					//所以可以说，ChannelPipeline是包裹Handler的
					pipeline.addLast("handler", new ChannelHandlerServerHandler());
					return pipeline;
				}
		});
		bootstrap.setOption("tcpNoDelay", true);
		// 最后绑定端口，就开始接受客户端的连接了
		bootstrap.bind(new InetSocketAddress(port));
	}

	public static void main(String[] args) {
		new ChannelHandlerServer().run(8080);
	}
	
}
