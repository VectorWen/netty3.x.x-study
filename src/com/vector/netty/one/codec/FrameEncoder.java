package com.vector.netty.one.codec;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;

import com.vector.netty.one.codec.FrameDecoderTest.FrameObject;

/**
 *
 *@author vector
 **/
public class FrameEncoder extends OneToOneEncoder {

	private static int count = 1;
	
	private static ChannelBuffer cumulation= ChannelBuffers.dynamicBuffer();
	
	/**
	 * 把一个对象编码成 length+content
	 */
	@Override
	protected Object encode(ChannelHandlerContext ctx, Channel channel,
			Object msg) throws Exception {
		ChannelBuffer buffer = ChannelBuffers.dynamicBuffer();
		
		
		FrameObject obj = null;
		if(!(msg instanceof Object[]))
			obj = (FrameObject) msg;
		
		
		/**
		 * 第一问的编码
		 */
		/*buffer.writeInt(obj.getName().getBytes().length + 8); //写入数据的长度
		
		buffer.writeInt(obj.getId());
		
		buffer.writeBytes(obj.getName().getBytes());*/
		
		/**
		 * 第二问的编码, 需要在编码的时候，传入两个相同的对象来测试
		 */
	/*	if(count == 1){
			//编码id 进去
			buffer.writeInt(obj.getName().getBytes().length + 8); //写入数据的长度
			
			buffer.writeInt(obj.getId());
			count ++;
		}else{
			buffer.writeBytes(obj.getName().getBytes());
		}*/
		
		/**
		 * 第三问的编码
		 */
		/*if(count == 1){
			//编码id 进去
			cumulation.writeInt(obj.getName().getBytes().length + 8); //写入数据的长度
			
			cumulation.writeInt(obj.getId());
			
			cumulation.writeBytes(obj.getName().getBytes());
			count ++;
			return null;
		}else{
			cumulation.writeInt(obj.getName().getBytes().length + 8); //写入数据的长度
			
			cumulation.writeInt(obj.getId());
			
			cumulation.writeBytes(obj.getName().getBytes());
			return cumulation;
		}*/
		
		
		/**
		 * 第四问的编码
		 */
		
		if(msg instanceof Object[]){
			FrameObject objs[] = (FrameObject[]) msg;
			
			for(int i=0;i<objs.length;i++){
				if(i==4){
					buffer.writeInt(objs[i].getName().getBytes().length + 2); //写入数据的长度
					
					buffer.writeInt(objs[i].getId());
					
					buffer.writeBytes(objs[i].getName().getBytes());
					continue;
				}
				
				buffer.writeInt(objs[i].getName().getBytes().length + 8); //写入数据的长度
				
				buffer.writeInt(objs[i].getId());
				
				buffer.writeBytes(objs[i].getName().getBytes());
			}
			
		}else{
			buffer.writeInt(obj.getName().getBytes().length + 8); //写入数据的长度
			
			buffer.writeInt(obj.getId());
			
			buffer.writeBytes(obj.getName().getBytes());
		}
		
		
		return buffer;
	}

}
