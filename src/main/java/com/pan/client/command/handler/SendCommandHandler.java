package com.pan.client.command.handler;

import com.pan.client.command.AbstractCommandHandler;
import io.netty.channel.Channel;
import com.pan.codec.protocol.request.MessageRequestPacket;

public class SendCommandHandler extends AbstractCommandHandler {

    public SendCommandHandler(int ARGS_NUMS) {
        super(ARGS_NUMS);
    }

    @Override
    public void execHandler(String[] strings, Channel channel) {
        MessageRequestPacket requestPacket  = new MessageRequestPacket();
        requestPacket.setToUsername(strings[1]);
        requestPacket.setMsg(strings[2]);
        channel.writeAndFlush(requestPacket);
    }
}
