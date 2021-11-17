package server;

import client.handler.LoginResponseHandler;
import client.handler.LogoutResponseHandler;
import codec.handler.PacketDecoder;
import codec.handler.PacketEncoder;
import codec.handler.VerifyFrameDecoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import server.handler.*;

import java.util.Date;

public class NettyServer {

    private static int PORT = 8080;

    public static void main(String[] args) {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        final ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap
                .group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline()
                                .addLast(new VerifyFrameDecoder())
                                .addLast(new PacketDecoder())
                                .addLast(new LoginRequestHandler())
                                .addLast(new AuthHandler())
                                .addLast(new MessageRequestHandler())
                                .addLast(new EnterGroupRequestHandler())
                                .addLast(new LogoutRequestHandler())
                                .addLast(new GroupMessageRequestHandler())
                                .addLast(new ExitGroupRequestHandler())
                                .addLast(new PacketEncoder());
                    }
                });
        bind(serverBootstrap, PORT);
    }

    private static void bind(final ServerBootstrap serverBootstrap, final int port) {
        serverBootstrap.bind(port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println(new Date() + "- 绑定端口成功 端口号: [" + port + "]");
            } else {
                System.out.println(new Date() + "- 绑定端口失败 端口号: [" + port + "]");
            }
        });
    }
}
