package com.pan.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import com.pan.codec.protocol.response.ResponseAbstractPacket;

import java.util.Date;

public class ResponseHandler extends SimpleChannelInboundHandler<ResponseAbstractPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ResponseAbstractPacket msg) throws Exception {
        System.out.println(new Date() + "- 服务端响应：" + msg.getMessage());
    }
}
