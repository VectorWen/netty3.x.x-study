package com.vector.netty.one.channelhandler;

import java.nio.charset.Charset;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneDecoder;


/**
 * @author Vector
 *
 */
public class Stringdecoder2  extends  OneToOneDecoder{

	@Override
	protected Object decode(ChannelHandlerContext ctx, Channel channel,
			Object msg) throws Exception {
		
		ChannelBuffer buffer = (ChannelBuffer) msg;
		
		String s = buffer.toString(Charset.defaultCharset());
		System.out.println("Stringdecoder2");
		return s;
	}

}
