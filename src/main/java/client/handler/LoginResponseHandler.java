package client.handler;

import client.command.ConsoleCommandManager;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import protocol.request.LoginRequestPacket;
import protocol.response.LoginResponsePacket;

import java.util.Date;
import java.util.Scanner;
import java.util.UUID;

/**
 * SimpleChannelInboundHandler 能够根据传入的类型对消息进行解析
 */
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {

    /**
     * 客户端激活后，向服务端发起登录请求
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.print("用户: ");
        String username = sc.nextLine();
        System.out.print("密码: ");
        String password = sc.nextLine();
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();

        loginRequestPacket.setUserId(UUID.randomUUID().toString());
        loginRequestPacket.setUsername(username);
        loginRequestPacket.setPassword(password);
        ctx.channel().writeAndFlush(loginRequestPacket);
    }

    /**
     * 接受登录响应
     * @param ctx
     * @param loginResponsePacket
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket loginResponsePacket) throws Exception {
        if (loginResponsePacket.isSuccess()) {
            System.out.println(new Date() + "- 客户端登录成功");
            System.out.println(new Date() + "- 正在启动控制台输入线程...");
            startConsoleThread(ctx.channel());
        } else {
            System.out.println(new Date() + "- 客户端登录失败，原因：" + loginResponsePacket.getReason());
        }
    }


    /**
     * 启动控制台线程
     * @param channel
     */
    private static void startConsoleThread(Channel channel) {
        ConsoleCommandManager commandManager = new ConsoleCommandManager();
        new Thread(() -> {
            while (!Thread.interrupted()) {
                Scanner sc = new Scanner(System.in);
                String[] line = sc.nextLine().split("\\s");
                if (line.length > 0) {
                    commandManager.exec(line, channel);
                }
            }
        }).start();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(new Date() + "- 客户端链接被断开");
    }
}
