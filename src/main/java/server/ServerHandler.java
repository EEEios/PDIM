package server;

import codec.PacketCodec;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import protocol.Packet;
import protocol.request.LoginRequestPacket;
import protocol.response.LoginResponsePacket;

import java.util.Date;

public class ServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(new Date() + "- 接受到客户端登录");
        ByteBuf byteBuf = (ByteBuf) msg;

        Packet packet = PacketCodec.INSTANCE.decode(byteBuf);

        if (packet instanceof LoginRequestPacket) {
            LoginRequestPacket requestPacket = (LoginRequestPacket) packet;

            LoginResponsePacket responsePacket = new LoginResponsePacket();
            responsePacket.setVersion(packet.getVersion());

            String username = requestPacket.getUsername();
            String password = requestPacket.getPassword();
            if ("user".equals(username) && "password".equals(password)) {
                responsePacket.setSuccess(true);
                System.out.println(new Date() + "- 用户登录成功 username [" + username +"]");
                responsePacket.setReason("登录成功");
            } else {
                responsePacket.setSuccess(true);
                System.out.println(new Date() + "- 用户身份校验失败 username [" + username +"]");
                responsePacket.setReason("用于校验失败");
            }

            ByteBuf resBuf = PacketCodec.INSTANCE.encode(ctx.alloc(), responsePacket);
            ctx.channel().writeAndFlush(resBuf);
        }
    }
}
