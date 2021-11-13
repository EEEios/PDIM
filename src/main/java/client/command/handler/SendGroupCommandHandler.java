package client.command.handler;

import client.command.AbstractCommandHandler;
import io.netty.channel.Channel;
import protocol.request.GroupMessageRequestPacket;
import protocol.request.MessageRequestPacket;

public class SendGroupCommandHandler extends AbstractCommandHandler {

    public SendGroupCommandHandler(int ARGS_NUMS) {
        super(ARGS_NUMS);
    }

    @Override
    public void execHandler(String[] strings, Channel channel) {
        GroupMessageRequestPacket requestPacket  = new GroupMessageRequestPacket();
        requestPacket.setToGroup(strings[1]);
        requestPacket.setMsg(strings[2]);
        channel.writeAndFlush(requestPacket);
    }
}
