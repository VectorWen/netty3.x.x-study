package com.vector.netty.one.channelhandler;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;


/**
 * @author Vector
 *
 */
public class StringEncoder2  extends  OneToOneEncoder{

		protected Object encode(ChannelHandlerContext ctx, Channel channel,
				Object msg) throws Exception {
			System.out.println("StringEncoder2");
			
			ChannelBuffer buffer = ChannelBuffers.dynamicBuffer();
			String s = (String)msg;
			buffer.writeBytes(s.getBytes());
			return buffer;
		}

}
