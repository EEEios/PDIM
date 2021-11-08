package client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import protocol.response.MessageResponsePacket;

import java.util.Date;

public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageResponsePacket messageResponsePacket) throws Exception {

        if (messageResponsePacket.getFromUId() == null) {
            System.out.println(new Date() + "- 服务端响应：" + messageResponsePacket.getMessage());
        } else {
            System.out.println(new Date() + "- 收到用户 [" +
                    messageResponsePacket.getFromUsername() +
                    "] 的消息：" + messageResponsePacket.getMessage());
        }
    }
}
