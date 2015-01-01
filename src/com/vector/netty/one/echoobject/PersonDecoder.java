package com.vector.netty.one.echoobject;

import java.nio.charset.Charset;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.frame.FrameDecoder;

/**
 * 2015年1月1日23:42:57
 * @author vector
 *
 */
public class PersonDecoder extends  FrameDecoder {

	private final ChannelBuffer buffer = ChannelBuffers.dynamicBuffer();
	
	@Override
	protected Object decode(ChannelHandlerContext arg0, Channel arg1,
			ChannelBuffer arg2) throws Exception {
		
		arg2.readBytes(buffer, arg2.readableBytes());
		
		int id = buffer.readInt();
		int nameLength = buffer.readInt();
		String name = buffer.readBytes(nameLength).toString(Charset.forName("UTF-8"));
		int passwdLength = buffer.readInt();
		String passwd =  buffer.readBytes(passwdLength).toString(Charset.forName("UTF-8"));
		
		return new Person(id, name, passwd);
	}
	
}
