package server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import protocol.request.LoginRequestPacket;
import protocol.response.LoginResponsePacket;

import java.util.Date;

/**
 * 接受客户端登录请求
 */
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket loginRequestPacket) throws Exception {
        LoginResponsePacket responsePacket = new LoginResponsePacket();
        responsePacket.setVersion(loginRequestPacket.getVersion());

        String username = loginRequestPacket.getUsername();
        String password = loginRequestPacket.getPassword();
        if ("user".equals(username) && "password".equals(password)) {
            responsePacket.setSuccess(true);
            System.out.println(new Date() + "- 用户登录成功 username [" + username +"]");
            responsePacket.setReason("登录成功");
        } else {
            responsePacket.setSuccess(true);
            System.out.println(new Date() + "- 用户身份校验失败 username [" + username +"]");
            responsePacket.setReason("用于校验失败");
        }

        ctx.channel().writeAndFlush(responsePacket);
    }
}
