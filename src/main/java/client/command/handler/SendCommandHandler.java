package client.command.handler;

import client.command.AbstractCommandHandler;
import io.netty.channel.Channel;

public class SendCommandHandler extends AbstractCommandHandler {

    public SendCommandHandler(int ARGS_NUMS) {
        super(ARGS_NUMS);
    }

    @Override
    public void execHandler(String[] strings, Channel channel) {

    }
}
