package server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import protocol.request.GroupMessageRequestPacket;
import protocol.response.ResponsePacket;
import util.SessionUtil;

public class GroupMessageRequestHandler extends SimpleChannelInboundHandler<GroupMessageRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupMessageRequestPacket msg) throws Exception {
        ChannelGroup group = SessionUtil.getGroup(msg.getToGroup());
        ResponsePacket responsePacket = new ResponsePacket();
        if (group == null) {
            responsePacket.setSuccess(false);
            responsePacket.setMessage("群组不存在");
        } else {
            responsePacket.setSuccess(true);
            responsePacket.setMessage("["+ SessionUtil.getChannelUsername(ctx.channel()) + "]: " +
                    msg.getMsg());
            group.writeAndFlush(responsePacket);
        }
    }
}
