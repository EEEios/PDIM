package server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import protocol.request.MessageRequestPacket;
import protocol.response.MessageResponsePacket;

import java.util.Date;

public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket messageRequestPacket) throws Exception {
        System.out.println(new Date() + "- 接受到消息：" + messageRequestPacket.getMsg());

        MessageResponsePacket msgResPacket = new MessageResponsePacket();
        msgResPacket.setMessage("【消息发送成功】");
        ctx.channel().writeAndFlush(msgResPacket);
    }
}
