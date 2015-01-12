package com.vector.netty.one.codec;

import java.nio.charset.Charset;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.handler.codec.embedder.DecoderEmbedder;
import org.jboss.netty.handler.codec.embedder.EncoderEmbedder;
import org.jboss.netty.handler.codec.frame.FrameDecoder;

/**
 * 测试帧解码<p>
 * 任务<p>
 * 1, 把一个串字节码数据解码成一个对象，需要结合编码{@link #FrameEncoder}}一起测试<p>
 * 2, 把本该一串数据才分为两个传，之后解码成一个对象<p>
 * 3, 把两个对象的数据串成一个字节码串，之后解码成两个对象<p>
 * 4, 把10个对象的数据串成一个字节码串，其中第5个是错误的, 要求把前4 个成功解码出来，第5个之后的丢掉。<p>
 * 5, 在4 的10 对象数据解析完之后，接着解析下一个10 个对象数据，都是真确的，要求全部解析出来
 *@author vector
 **/
public class FrameDecoderTest extends FrameDecoder{

	ChannelBuffer buffer = null;
	
	@Override
	protected Object decode(ChannelHandlerContext ctx, Channel channel,
			ChannelBuffer buffer) throws Exception {
		this.buffer = buffer;
		
		/**
		 * 第一问的解码
		 */
		/*int nameLen = buffer.readInt() - 8;
		
		int id = buffer.readInt();
		
		String name = buffer.readBytes(nameLen).toString(Charset.defaultCharset());
		
		FrameObject obj = new FrameObject(id, name);*/
		
		
		/**
		 * 第二 三四问的解码
		 */
		/*buffer.markReaderIndex();
		
		//buffer 还不够一个数据
		if(buffer.readableBytes() < 4){
			buffer.resetReaderIndex();
			return null;
		}
		
		int nameLen = buffer.readInt() - 8;
		
		//buffer 还不够一个数据
		if(buffer.readableBytes() < 4){
			buffer.resetReaderIndex();
			return null;
		}
		int id = buffer.readInt();
		
		//buffer 还不够一个数据
		if(buffer.readableBytes() < nameLen){
			buffer.resetReaderIndex();
			return null;
		}
		
		String name = buffer.readBytes(nameLen).toString(Charset.defaultCharset());
		
		FrameObject obj = new FrameObject(id, name);*/
		
		
		/**
		 * 第五问的解码 -- 以不可能按一个Buffer发送的数据会接收成两个，所以，需要解析的数据至少有一帧，否则当做是错误帧丢掉
		 * 
		 * md,还是不行，错误消息的问题，还是留在心里吧，等结合Handler的再处理吧
		 */
		buffer.markReaderIndex();
		
		int nameLen = buffer.readInt() - 8;
		
		int id = buffer.readInt();
		
		String name = buffer.readBytes(nameLen).toString(Charset.defaultCharset());
		
		FrameObject obj = new FrameObject(id, name);
		return obj;
	}
	
	
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e)
			throws Exception {
		super.exceptionCaught(ctx, e);
		System.out.println("丢掉数据");
		buffer.clear();
	}



	public static class FrameObject{
		private int id;
		private String name;
		
		
		
		public int getId() {
			return id;
		}



		public void setId(int id) {
			this.id = id;
		}



		public FrameObject(int id, String name) {
			super();
			this.id = id;
			this.name = name;
		}



		public String getName() {
			return name;
		}



		public void setName(String name) {
			this.name = name;
		}



		public FrameObject() {
		}



		@Override
		public String toString() {
			return "Person [id=" + id + ", name=" + name + "]";
		}
	}
	
	public static void main(String[] args) {
		/**
		 * 第一问的测试
		 */
		/*//先把对象编码成Buffer
		EncoderEmbedder<ChannelBuffer> encoder = new EncoderEmbedder<ChannelBuffer>(
				new FrameEncoder());
		
		encoder.offer(new FrameObject(1,"wo ai ni"));
		
		//把Buffer 解码出来
		
		DecoderEmbedder<FrameObject> decoder = new DecoderEmbedder<FrameDecoderTest.FrameObject>(
				new FrameDecoderTest());
		
		decoder.offer(encoder.poll());
		
		FrameObject obj = decoder.poll();
		
		System.out.println(obj);*/
		
		/**
		 * 第二问的测试
		 */
		/*//先把对象编码成Buffer
		EncoderEmbedder<ChannelBuffer> encoder = new EncoderEmbedder<ChannelBuffer>(
				new FrameEncoder());
		
		encoder.offer(new FrameObject(1,"wo ai ni"));
		encoder.offer(new FrameObject(1,"wo ai ni"));
		
		//把Buffer 解码出来
		
		DecoderEmbedder<FrameObject> decoder = new DecoderEmbedder<FrameDecoderTest.FrameObject>(
				new FrameDecoderTest());
		//加入需要解码的两个buffer
		decoder.offer(encoder.poll());
		decoder.offer(encoder.poll());
		
		FrameObject obj = decoder.poll();
		
		System.out.println(obj);*/
		
		
		/**
		 * 第三问的测试
		 */
		/*//先把对象编码成Buffer
		EncoderEmbedder<ChannelBuffer> encoder = new EncoderEmbedder<ChannelBuffer>(
				new FrameEncoder());
		
		encoder.offer(new FrameObject(1,"wo ai ni"));
		encoder.offer(new FrameObject(1,"你爱我吗？"));
		
		//把Buffer 解码出来
		
		DecoderEmbedder<FrameObject> decoder = new DecoderEmbedder<FrameDecoderTest.FrameObject>(
				new FrameDecoderTest());
		
		decoder.offer(encoder.poll());
		
		FrameObject obj = decoder.poll();
		
		System.out.println(obj);
		 obj = decoder.poll();
		
		System.out.println(obj);*/
		/**
		 * 第四问的测试
		 */
		//先把对象编码成Buffer
		EncoderEmbedder<ChannelBuffer> encoder = new EncoderEmbedder<ChannelBuffer>(
				new FrameEncoder());
		
		FrameObject[] objs = new FrameObject[]{new FrameObject(1,"wo ai ni----------1"),new FrameObject(1,"wo ai ni----------2"),new FrameObject(1,"wo ai ni----------3"),
				new FrameObject(1,"wo ai ni----------1"),new FrameObject(1,"wo ai ni----------1"),new FrameObject(1,"wo ai ni----------1"),new FrameObject(1,"wo ai ni----------1"),new FrameObject(1,"wo ai ni----------1"),new FrameObject(1,"wo ai ni----------1"),new FrameObject(1,"wo ai ni----------1"),new FrameObject(1,"wo ai ni----------1"),new FrameObject(1,"wo ai ni----------1")};
		
		encoder.offer(objs);
		encoder.offer(objs);
		
		//把Buffer 解码出来
		
		DecoderEmbedder<FrameObject> decoder = new DecoderEmbedder<FrameDecoderTest.FrameObject>(
				new FrameDecoderTest());
		
		decoder.offer(encoder.poll());
		
		Object[] objss =  decoder.pollAll();
		
		for(int i=0;i<objss.length;i++){
			System.out.println(objss[i]);
		}
		
	}

}
