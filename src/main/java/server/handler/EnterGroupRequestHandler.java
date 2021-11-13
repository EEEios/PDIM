package server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import protocol.request.EnterGroupRequestPacket;
import protocol.response.ResponsePacket;
import util.SessionUtil;

public class EnterGroupRequestHandler extends SimpleChannelInboundHandler<EnterGroupRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, EnterGroupRequestPacket msg) throws Exception {
        ChannelGroup group;
        if ((group = SessionUtil.getGroup(msg.getGroupName())) == null) {
            group = SessionUtil.createGroup(msg.getGroupName(), ctx.executor());
        }
        SessionUtil.addMember(msg.getGroupName(), ctx.channel());
        ResponsePacket responsePacket = new ResponsePacket();
        responsePacket.setSuccess(true);
        responsePacket.setMessage("用户 [ " + SessionUtil.getChannelUsername(ctx.channel()) + " ] " +
                "已加入群聊 [" + msg.getGroupName() + "]");
        group.writeAndFlush(responsePacket);
    }
}
