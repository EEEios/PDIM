package server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import protocol.request.LogoutRequestPacket;
import protocol.response.LogoutResponsePacket;
import protocol.response.ResponsePacket;
import util.Session;
import util.SessionUtil;

import java.util.Date;

public class LogoutRequestHandler extends SimpleChannelInboundHandler<LogoutRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogoutRequestPacket msg) throws Exception {
        ResponsePacket resp = new ResponsePacket();

        Session session = SessionUtil.getSession(ctx.channel());
        System.out.println(new Date() + "- 用户登出 ID： [" + session.getUId() +"] " +
                "用户名：[" + session.getUsername() +"] ");
        resp.setSuccess(true);
        resp.setReason("登出成功");
        ctx.channel().writeAndFlush(resp);
    }
}
