package server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import protocol.request.LoginRequestPacket;
import protocol.response.LoginResponsePacket;
import util.Session;
import util.SessionUtil;

import java.util.Date;
import java.util.HashMap;

/**
 * 接受客户端登录请求
 */
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

    private final HashMap<String, String> map = new HashMap<String, String>(){{
        put("root", "root");
        put("123","123");
    }};


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket loginRequestPacket) throws Exception {
        LoginResponsePacket responsePacket = new LoginResponsePacket();
        responsePacket.setVersion(loginRequestPacket.getVersion());

        String username = loginRequestPacket.getUsername();
        String password = loginRequestPacket.getPassword();
        if (map.containsKey(username) && map.get(username).equals(password)) {
            responsePacket.setSuccess(true);
            System.out.println(new Date() + "- 用户登录成功 用户名： [" + username +"]");
            responsePacket.setReason("登录成功");

            // 设置登录标志位
            SessionUtil.markAsLogin(ctx.channel());

            // UUID 绑定到 Channel 上
            SessionUtil.bindSession(new Session(loginRequestPacket.getUserId(), username), ctx.channel());
        } else {
            responsePacket.setSuccess(true);
            System.out.println(new Date() + "- 用户身份校验失败 用户名： [" + username +"]");
            responsePacket.setReason("用于校验失败");
        }

        ctx.channel().writeAndFlush(responsePacket);
    }
}
