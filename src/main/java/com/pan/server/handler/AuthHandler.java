package com.pan.server.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import com.pan.util.SessionUtil;

import java.util.Date;

@ChannelHandler.Sharable
public class AuthHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (!SessionUtil.hasLogin(ctx.channel())) {
            ctx.channel().close();
        } else {
            System.out.println(new Date() + "- 当前连接登录验证完毕, AuthHandler 被移除");
            ctx.pipeline().remove(this);
            super.channelRead(ctx, msg);
        }
    }
}
