package client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import protocol.response.LogoutResponsePacket;

import java.util.Date;

public class LogoutResponseHandler extends SimpleChannelInboundHandler<LogoutResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogoutResponsePacket logoutResponsePacket) throws Exception {
        if (logoutResponsePacket.isSuccess()) {
            System.out.println(new Date() + "- 登出成功");
            ctx.channel().close();
        } else {
            System.out.println(new Date() + "- 登出失败，原因：" + logoutResponsePacket.getReason());
        }
    }
}
