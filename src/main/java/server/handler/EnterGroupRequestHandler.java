package server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import protocol.request.EnterGroupRequestPacket;
import protocol.response.ResponsePacket;
import util.SessionUtil;

import java.util.HashMap;

public class EnterGroupRequestHandler extends SimpleChannelInboundHandler<EnterGroupRequestPacket> {

    // 保存群组和群组中的用户 Channel
    private static HashMap<String, ChannelGroup> groupMap = new HashMap<>();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, EnterGroupRequestPacket msg) throws Exception {

        if (!groupMap.containsKey(msg.getGroupName())) {
            groupMap.put(msg.getGroupName(), new DefaultChannelGroup(ctx.executor()){{add(ctx.channel());}});
        }
        ChannelGroup channels = groupMap.get(msg.getGroupName());
        ResponsePacket responsePacket = new ResponsePacket();
        responsePacket.setSuccess(true);
        responsePacket.setReason("用户 [ " + SessionUtil.getChannelUsername(ctx.channel()) + " ] " +
                "已加入群聊 [" + msg.getGroupName() + "]");
        channels.writeAndFlush(responsePacket);
    }
}
