package com.vector.netty.one.channelhandler;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;


/**
 * @author Vector
 *
 */
public class StringEncoder1  extends  OneToOneEncoder{

	@Override
	protected Object encode(ChannelHandlerContext ctx, Channel channel,
			Object msg) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("StringEncoder1");
		return "String";
	}

}
