package com.pan.client;

import com.pan.client.handler.HeartBeatTimerHandler;
import com.pan.client.handler.LoginResponseHandler;
import com.pan.client.handler.LogoutResponseHandler;
import com.pan.client.handler.ResponseHandler;
import com.pan.codec.PacketCodecHandler;
import com.pan.codec.handler.PacketDecoder;
import com.pan.codec.handler.PacketEncoder;
import com.pan.codec.handler.VerifyFrameDecoder;
import com.pan.server.handler.ServerIdleStateHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class NettyClient {

    private static final int MAX_RETRY = 5;
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 8080;

    public static void main(String[] args) {
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap
                .group(workerGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) {
                        socketChannel.pipeline()
                                .addLast(new ServerIdleStateHandler())
                                .addLast(new VerifyFrameDecoder())
                                .addLast(PacketCodecHandler.INSTANCE)
                                .addLast(new HeartBeatTimerHandler())
                                .addLast(new LoginResponseHandler())
                                .addLast(new ResponseHandler())
                                .addLast(new LogoutResponseHandler());
                    }
                });
        connect(bootstrap, HOST, PORT, MAX_RETRY);
    }

    private static void connect(Bootstrap bootstrap, String host, int port, int retry) {
        bootstrap.connect(host, port)
                .addListener(future -> {
                    if (future.isSuccess()) {
//                        System.out.println(new Date() + "- 连接成功!");
                    } else if (retry == 0) {
                        System.err.println(new Date() + "重新连接失败!");
                    } else {
                        int order = (MAX_RETRY - retry) + 1;
                        int delay = 1 << order;
                        System.err.println(new Date() + "- 连接失败，正在进行第" + order + "次重连...");
                        bootstrap.config().group()
                                .schedule(() -> connect(bootstrap, host, port, retry - 1), delay, TimeUnit.SECONDS);
                    }
                });
    }


}
