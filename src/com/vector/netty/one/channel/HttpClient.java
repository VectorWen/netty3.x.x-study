package com.vector.netty.one.channel;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.http.HttpTunnelingClientSocketChannelFactory;
import org.jboss.netty.channel.socket.oio.OioClientSocketChannelFactory;
import org.jboss.netty.handler.codec.http.HttpRequestEncoder;
import org.jboss.netty.handler.codec.http.HttpResponseDecoder;

/**
 * 失败之作，迟点回来完成
 * @author vector
 *
 */
public class HttpClient {
	public void run(String host,int port){
		
		ClientBootstrap bootstrap = new ClientBootstrap(
				new HttpTunnelingClientSocketChannelFactory(
                        new OioClientSocketChannelFactory(Executors.newCachedThreadPool())));
		bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
				public ChannelPipeline getPipeline()throws Exception { 
					
					ChannelPipeline pipeline = Channels.pipeline();
					pipeline.addLast("encoder",new HttpRequestEncoder());
					pipeline.addLast("decoder",new HttpResponseDecoder());
					pipeline.addLast("handler",new HttpClientHandler());
					return pipeline;
				}
		});
		bootstrap.connect(new InetSocketAddress(host, port));
	}
	
	public static void main(String[] args) {
		new HttpClient().run("www.baidu.com", 80);
	}
}
