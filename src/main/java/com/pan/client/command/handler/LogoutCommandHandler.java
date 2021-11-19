package com.pan.client.command.handler;

import com.pan.client.command.AbstractCommandHandler;
import io.netty.channel.Channel;
import com.pan.codec.protocol.request.LogoutRequestPacket;

public class LogoutCommandHandler extends AbstractCommandHandler {

    public LogoutCommandHandler(int ARGS_NUMS) {
        super(ARGS_NUMS);
    }

    @Override
    public void execHandler(String[] strings, Channel channel) {
        LogoutRequestPacket logoutRequestPacket = new LogoutRequestPacket();
        channel.writeAndFlush(logoutRequestPacket);
    }
}
