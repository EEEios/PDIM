package com.pan.server.handler;

import com.pan.codec.protocol.request.HeartBeatRequestPacket;
import com.pan.codec.protocol.response.HeartBeatResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

public class HeartBeatRequestHandler extends SimpleChannelInboundHandler<HeartBeatRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HeartBeatRequestPacket msg) throws Exception {
//        System.out.println(new Date() + " - " + ctx.channel() + "存活");
        ctx.channel().writeAndFlush(new HeartBeatResponsePacket());
    }
}
