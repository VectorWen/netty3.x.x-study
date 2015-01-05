package com.vector.netty.one.channelfuture;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;

/**
 * 客户端连接好服务端后，在控制台读取一行数据后就发送给服务端<p>
 * 与HelloWorldServer 一起调试
 *@author vector
 **/
public class ChannelFutureClient {
	
	public void run(String host,int port){
		ClientBootstrap bootstrap = new ClientBootstrap(
				new NioClientSocketChannelFactory(
						Executors.newCachedThreadPool(), 
						Executors.newCachedThreadPool()));
		
		bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
			
			public ChannelPipeline getPipeline() throws Exception {
				ChannelPipeline pipeline = Channels.pipeline();
				pipeline.addLast("encoder", new StringEncoder());
				pipeline.addLast("decoder", new StringDecoder());
				pipeline.addLast("handler", new ChannelFureClientHandler());
				return pipeline;
			}
		});
		
		ChannelFuture future = bootstrap.connect(new InetSocketAddress(host, port));
		
		future.awaitUninterruptibly();
		
		if(!future.getChannel().isConnected()){
			System.out.println("connect error");
			return;
		}
		
		String line = "";
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		while(true){
			try {
				line = reader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			if(line == null||line.equals("")||line.equals("exit"))
				break;
			
			future.getChannel().write(line);
			
		}
		
		bootstrap.shutdown();
		bootstrap.releaseExternalResources();
		
	}
	
	public static void main(String[] args) {
		new ChannelFutureClient().run("127.0.0.1", 8080);
	}
}
