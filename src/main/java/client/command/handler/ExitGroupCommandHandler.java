package client.command.handler;

import client.command.AbstractCommandHandler;
import io.netty.channel.Channel;
import codec.protocol.request.ExitGroupRequestPacket;

public class ExitGroupCommandHandler extends AbstractCommandHandler {

    public ExitGroupCommandHandler(int ARGS_NUMS) {
        super(ARGS_NUMS);
    }

    @Override
    public void execHandler(String[] strings, Channel channel) {
        ExitGroupRequestPacket packet = new ExitGroupRequestPacket();
        packet.setGroupName(strings[1]);
        channel.writeAndFlush(packet);
    }
}
