package server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import codec.protocol.request.LogoutRequestPacket;
import codec.protocol.response.LogoutResponsePacket;
import util.SessionUtil;

public class LogoutRequestHandler extends SimpleChannelInboundHandler<LogoutRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogoutRequestPacket msg) throws Exception {
        LogoutResponsePacket resp = new LogoutResponsePacket();
        SessionUtil.removeChannel(SessionUtil.getChannelUsername(ctx.channel()));
        resp.setSuccess(true);
        resp.setReason("登出成功");
        ctx.channel().writeAndFlush(resp);
    }
}
