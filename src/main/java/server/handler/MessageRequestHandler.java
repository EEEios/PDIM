package server.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import protocol.request.MessageRequestPacket;
import protocol.forward.MessageResponsePacket;
import util.Session;
import util.SessionUtil;

import java.util.Date;

public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket messageRequestPacket) throws Exception {
        System.out.println(new Date() + "- 接受到消息：" + messageRequestPacket.getMsg());
        Channel c;
        String toUsername = messageRequestPacket.getToUsername();
        if ((c = SessionUtil.getChannel(messageRequestPacket.getToUsername())) == null) {
            MessageResponsePacket msgResPacket = new MessageResponsePacket();
            msgResPacket.setMessage("用户：[ " + toUsername + " ] 未上线");
            ctx.channel().writeAndFlush(msgResPacket);
        } else {
            // 获取 Channel 绑定的 Session 信息
            Session session = SessionUtil.getSession(ctx.channel());

            // 构造消息体
            MessageResponsePacket toResponse = new MessageResponsePacket();
            toResponse.setFromUId(session.getUId());
            toResponse.setFromUsername(session.getUsername());
            toResponse.setMessage(messageRequestPacket.getMsg());

            // 获取对方 channel
            c.writeAndFlush(toResponse);

            MessageResponsePacket responsePacket = new MessageResponsePacket();
            responsePacket.setMessage("发送成功");
            ctx.channel().writeAndFlush(responsePacket);
        }
    }
}
