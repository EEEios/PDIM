package client.command.handler;

import client.command.AbstractCommandHandler;
import io.netty.channel.Channel;
import codec.protocol.request.LogoutRequestPacket;

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
