package com.pan.server.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import com.pan.codec.protocol.request.MessageRequestPacket;
import com.pan.codec.protocol.response.ResponseAbstractPacket;
import com.pan.util.Session;
import com.pan.util.SessionUtil;

@ChannelHandler.Sharable
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {

    public static final MessageRequestHandler INSTANCE = new MessageRequestHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket messageRequestPacket) throws Exception {
//        System.out.println(new Date() + "- 接受到消息：" + messageRequestPacket.getMsg());
        Channel c;
        String toUsername = messageRequestPacket.getToUsername();
        if ((c = SessionUtil.getChannel(messageRequestPacket.getToUsername())) == null) {
            ResponseAbstractPacket responsePacket = new ResponseAbstractPacket();
            responsePacket.setSuccess(false);
            responsePacket.setMessage("用户：[ " + toUsername + " ] 未上线");
            ctx.channel().writeAndFlush(responsePacket);
        } else {
            // 获取 Channel 绑定的 Session 信息
            Session session = SessionUtil.getSession(ctx.channel());

            // 构造消息体
            ResponseAbstractPacket forwardPacket = new ResponseAbstractPacket();
            forwardPacket.setMessage("[ " + session.getUsername() + " ]: " + messageRequestPacket.getMsg());

            // 获取对方 channel
            c.writeAndFlush(forwardPacket);

            ResponseAbstractPacket responsePacket = new ResponseAbstractPacket();
            responsePacket.setMessage("发送成功");
            ctx.channel().writeAndFlush(responsePacket);
        }
    }
}
