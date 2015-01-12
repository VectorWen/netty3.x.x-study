package com.vector.netty.one.codec;

import java.nio.charset.Charset;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.handler.codec.base64.Base64Decoder;
import org.jboss.netty.handler.codec.base64.Base64Encoder;
import org.jboss.netty.handler.codec.embedder.DecoderEmbedder;
import org.jboss.netty.handler.codec.embedder.EncoderEmbedder;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;

/**
 * 测试编解码器
 *@author vector
 **/
public class Emdedder {
	public static void main(String[] args) {
		String data = "123456";
		
		//生成一个编码器测试器，把需要测试的编码器传入
		 EncoderEmbedder<ChannelBuffer> encoder = new EncoderEmbedder<ChannelBuffer>(
		         new Base64Encoder(), new StringEncoder());

		 //添加测试数据，添加之后，就会自动进过全部的编码器编码，保存到一个队里中，之后我们就可以从这个队列拿到编码好的数据
		 encoder.offer(data);

		 //拿队列的第一个编码好的数据
		 ChannelBuffer encoded = encoder.poll();
		// assert encoded.toString(CharsetUtil.US_ASCII).equals("Zm9vYmFy");
		 
		 //打印：MTIzNDU2
		 System.out.println(encoded.toString(Charset.defaultCharset()));
		
		 
		 DecoderEmbedder<String> decoder = new DecoderEmbedder<String>(
				 new Base64Decoder(),new StringDecoder());
		 decoder.offer("MTIzNDU2");
		 //打印：123456
		 System.out.println(decoder.poll());
		 
		 
	}
}
