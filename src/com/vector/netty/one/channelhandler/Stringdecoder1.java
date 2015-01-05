package com.vector.netty.one.channelhandler;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneDecoder;


/**
 * @author Vector
 *
 */
public class Stringdecoder1  extends  OneToOneDecoder{

	@Override
	protected Object decode(ChannelHandlerContext ctx, Channel channel,
			Object msg) throws Exception {
		
		System.out.println("Stringdecoder1");
		
		return msg;
	}

}
