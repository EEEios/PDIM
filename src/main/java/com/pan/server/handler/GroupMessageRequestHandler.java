package com.pan.server.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import com.pan.codec.protocol.request.GroupMessageRequestPacket;
import com.pan.codec.protocol.response.ResponseAbstractPacket;
import com.pan.util.SessionUtil;

@ChannelHandler.Sharable
public class GroupMessageRequestHandler extends SimpleChannelInboundHandler<GroupMessageRequestPacket> {

    public static final GroupMessageRequestHandler INSTANCE = new GroupMessageRequestHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupMessageRequestPacket msg) throws Exception {
        ChannelGroup group;
        ResponseAbstractPacket responsePacket = new ResponseAbstractPacket();
        responsePacket.setSuccess(false);
        responsePacket.setMessage(
                (group = SessionUtil.getGroup(msg.getToGroup())) == null? "群组不存在" : "不在群组中");

        if (group != null && group.contains(ctx.channel())) {
            responsePacket.setSuccess(true);
            responsePacket.setMessage("["+ SessionUtil.getChannelUsername(ctx.channel()) + "]: " +
                    msg.getMsg());
            group.writeAndFlush(responsePacket);
            return;
        }
        ctx.channel().writeAndFlush(responsePacket);
    }
}
