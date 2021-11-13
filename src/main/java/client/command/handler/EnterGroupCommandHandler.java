package client.command.handler;

import client.command.AbstractCommandHandler;
import io.netty.channel.Channel;
import protocol.request.EnterGroupRequestPacket;

public class EnterGroupCommandHandler extends AbstractCommandHandler {

    public EnterGroupCommandHandler(int ARGS_NUMS) {
        super(ARGS_NUMS);
    }

    @Override
    public void execHandler(String[] strings, Channel channel) {
        EnterGroupRequestPacket packet = new EnterGroupRequestPacket();
        packet.setGroupName(strings[1]);
        channel.writeAndFlush(packet);
    }
}
