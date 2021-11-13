package client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import protocol.response.ResponsePacket;

import java.util.Date;

public class ResponseHandler extends SimpleChannelInboundHandler<ResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ResponsePacket msg) throws Exception {
        System.out.println(new Date() + "- 服务端响应：" + msg.getMessage());
    }
}
