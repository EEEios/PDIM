package com.pan.server.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import com.pan.codec.protocol.request.LogoutRequestPacket;
import com.pan.codec.protocol.response.LogoutResponsePacket;
import com.pan.util.SessionUtil;

@ChannelHandler.Sharable
public class LogoutRequestHandler extends SimpleChannelInboundHandler<LogoutRequestPacket> {

    public static final LogoutRequestHandler INSTANCE = new LogoutRequestHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogoutRequestPacket msg) throws Exception {
        LogoutResponsePacket resp = new LogoutResponsePacket();
        SessionUtil.removeChannel(SessionUtil.getChannelUsername(ctx.channel()));
        resp.setSuccess(true);
        resp.setReason("登出成功");
        ctx.channel().writeAndFlush(resp);
    }
}
