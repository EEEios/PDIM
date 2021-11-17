package server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import codec.protocol.request.GroupMessageRequestPacket;
import codec.protocol.response.ResponsePacket;
import util.SessionUtil;

public class GroupMessageRequestHandler extends SimpleChannelInboundHandler<GroupMessageRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupMessageRequestPacket msg) throws Exception {
        ChannelGroup group;
        ResponsePacket responsePacket = new ResponsePacket();
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
