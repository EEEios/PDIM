package com.pan.client.handler;

import com.pan.codec.protocol.request.HeartBeatRequestPacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.TimeUnit;

public class HeartBeatTimerHandler extends ChannelInboundHandlerAdapter {

    private static final int HEART_INTERNAL = 5;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("启动心跳");
        scheduleSendHeartBeat(ctx);
        super.channelActive(ctx);
    }

    // 定时发送心跳
    private void scheduleSendHeartBeat(ChannelHandlerContext ctx) {
        ctx.executor().schedule(() -> {
            if (ctx.channel().isActive()) {
                System.out.println("发送心跳");
                ctx.writeAndFlush(new HeartBeatRequestPacket());
                scheduleSendHeartBeat(ctx);
            }
        }, HEART_INTERNAL, TimeUnit.SECONDS);
    }
}
