package com.pan.server.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import com.pan.codec.protocol.request.ExitGroupRequestPacket;
import com.pan.codec.protocol.response.ResponseAbstractPacket;
import com.pan.util.SessionUtil;

@ChannelHandler.Sharable
public class ExitGroupRequestHandler extends SimpleChannelInboundHandler<ExitGroupRequestPacket> {

    public static final ExitGroupRequestHandler INSTANCE = new ExitGroupRequestHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ExitGroupRequestPacket msg) throws Exception {
        ChannelGroup cg;
        ResponseAbstractPacket resp = new ResponseAbstractPacket();
        if ((cg = SessionUtil.getGroup(msg.getGroupName())) == null) {
            resp.setSuccess(false);
            resp.setMessage("群组 [" + msg.getGroupName() + "] 不存在");
            ctx.channel().writeAndFlush(resp);
            return;
        }

        cg.remove(ctx.channel());
        resp.setSuccess(true);
        resp.setMessage("已退出群组 [" + msg.getGroupName() + "]");
        ctx.channel().writeAndFlush(resp);
    }
}
