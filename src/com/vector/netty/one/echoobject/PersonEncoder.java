package com.vector.netty.one.echoobject;

import java.nio.charset.Charset;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.serialization.ObjectEncoder;


/**
 * 2015年1月1日23:42:44
 * @author Vector
 *
 */
public class PersonEncoder  extends  ObjectEncoder{
	private final ChannelBuffer buffer = ChannelBuffers.dynamicBuffer();

	@Override
	protected Object encode(ChannelHandlerContext ctx, Channel channel,
			Object msg) throws Exception {
		// TODO Auto-generated method stub
		Person person = (Person) msg;
		buffer.writeInt(person.getId());
		buffer.writeInt(person.getName().getBytes(Charset.forName("UTF-8")).length);
		buffer.writeBytes(person.getName().getBytes(Charset.forName("UTF-8")));
		buffer.writeInt(person.getPasswd().getBytes(Charset.forName("UTF-8")).length);
		buffer.writeBytes(person.getPasswd().getBytes(Charset.forName("UTF-8")));
		return buffer;
	}

}
