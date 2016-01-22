package com.tagding.application;

import java.text.SimpleDateFormat;
import java.util.Date;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.ReferenceCountUtil;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component; 

import com.mchange.v2.c3p0.impl.NewPooledConnection;

@Component
@Qualifier("serverHandler")
@Sharable
public class ServerHandler extends SimpleChannelInboundHandler<String> {

	Logger logger = Logger.getLogger(ServerHandler.class);
	
	@Override
	public void channelRead0(ChannelHandlerContext ctx, String msg)
			throws Exception {
		System.out.print("received data :" + msg);
		//logger.info("received data :" + msg); 
		ctx.channel().writeAndFlush(msg);  
	}
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("Channel is active\n");
		super.channelActive(ctx);
	}

	public void userEventTriggered(
			ChannelHandlerContext ctx, Object evt)
			throws Exception {
		if(IdleStateEvent.class.isAssignableFrom(evt.getClass())){
			IdleStateEvent event = (IdleStateEvent) evt;
			if(event.state() == IdleState.READER_IDLE)
				{System.out.println("read idle");
			ctx.close();}
			else if(event.state() == IdleState.WRITER_IDLE)
				System.out.println("write idle");
			else if(event.state() == IdleState.ALL_IDLE)
				System.out.println("all idle");
		}
	}
	
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("\nChannel is disconnected");
		super.channelInactive(ctx);
	}

}
