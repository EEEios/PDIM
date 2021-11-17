package server.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import codec.protocol.request.MessageRequestPacket;
import codec.protocol.response.ResponsePacket;
import util.Session;
import util.SessionUtil;

public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket messageRequestPacket) throws Exception {
//        System.out.println(new Date() + "- 接受到消息：" + messageRequestPacket.getMsg());
        Channel c;
        String toUsername = messageRequestPacket.getToUsername();
        if ((c = SessionUtil.getChannel(messageRequestPacket.getToUsername())) == null) {
            ResponsePacket responsePacket = new ResponsePacket();
            responsePacket.setSuccess(false);
            responsePacket.setMessage("用户：[ " + toUsername + " ] 未上线");
            ctx.channel().writeAndFlush(responsePacket);
        } else {
            // 获取 Channel 绑定的 Session 信息
            Session session = SessionUtil.getSession(ctx.channel());

            // 构造消息体
            ResponsePacket forwardPacket = new ResponsePacket();
            forwardPacket.setMessage("[ " + session.getUsername() + " ]: " + messageRequestPacket.getMsg());

            // 获取对方 channel
            c.writeAndFlush(forwardPacket);

            ResponsePacket responsePacket = new ResponsePacket();
            responsePacket.setMessage("发送成功");
            ctx.channel().writeAndFlush(responsePacket);
        }
    }
}
