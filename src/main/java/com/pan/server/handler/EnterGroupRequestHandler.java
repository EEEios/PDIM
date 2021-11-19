package com.pan.server.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import com.pan.codec.protocol.request.EnterGroupRequestPacket;
import com.pan.codec.protocol.response.ResponseAbstractPacket;
import com.pan.util.SessionUtil;

@ChannelHandler.Sharable
public class EnterGroupRequestHandler extends SimpleChannelInboundHandler<EnterGroupRequestPacket> {

    public static final EnterGroupRequestHandler INSTANCE = new EnterGroupRequestHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, EnterGroupRequestPacket msg) throws Exception {
        ChannelGroup group;
        if ((group = SessionUtil.getGroup(msg.getGroupName())) == null) {
            group = SessionUtil.createGroup(msg.getGroupName(), ctx.executor());
        }
        SessionUtil.addMember(msg.getGroupName(), ctx.channel());
        ResponseAbstractPacket responsePacket = new ResponseAbstractPacket();
        responsePacket.setSuccess(true);
        responsePacket.setMessage("用户 [ " + SessionUtil.getChannelUsername(ctx.channel()) + " ] " +
                "已加入群聊 [" + msg.getGroupName() + "]");
        group.writeAndFlush(responsePacket);
    }
}
