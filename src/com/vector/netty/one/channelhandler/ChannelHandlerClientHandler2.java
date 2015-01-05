package com.vector.netty.one.channelhandler;

import org.jboss.netty.channel.ChannelEvent;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelDownstreamHandler;


/**
 * @author vector
 *
 */
public class ChannelHandlerClientHandler2  extends SimpleChannelDownstreamHandler{

	@Override
	public void handleDownstream(ChannelHandlerContext ctx, ChannelEvent e)
			throws Exception {
		super.handleDownstream(ctx, e);
		
		System.out.println("handleDownstream");
		
	}

	@Override
	public void writeRequested(ChannelHandlerContext ctx, MessageEvent e)
			throws Exception {
		super.writeRequested(ctx, e);
		System.out.println("writeRequested");
	}

	@Override
	public void bindRequested(ChannelHandlerContext ctx, ChannelStateEvent e)
			throws Exception {
		super.bindRequested(ctx, e);
		System.out.println("bindRequested");
	}

	@Override
	public void connectRequested(ChannelHandlerContext ctx, ChannelStateEvent e)
			throws Exception {
		super.connectRequested(ctx, e);
		System.out.println("connectRequested");
	}

	@Override
	public void setInterestOpsRequested(ChannelHandlerContext ctx,
			ChannelStateEvent e) throws Exception {
		// TODO Auto-generated method stub
		super.setInterestOpsRequested(ctx, e);
		System.out.println("setInterestOpsRequested");
	}

	@Override
	public void disconnectRequested(ChannelHandlerContext ctx,
			ChannelStateEvent e) throws Exception {
		// TODO Auto-generated method stub
		super.disconnectRequested(ctx, e);
		System.out.println("disconnectRequested");
	}

	@Override
	public void unbindRequested(ChannelHandlerContext ctx, ChannelStateEvent e)
			throws Exception {
		super.unbindRequested(ctx, e);
		System.out.println("unbindRequested");
	}

	@Override
	public void closeRequested(ChannelHandlerContext ctx, ChannelStateEvent e)
			throws Exception {
		super.closeRequested(ctx, e);
		System.out.println("closeRequested");
	}
	

}
