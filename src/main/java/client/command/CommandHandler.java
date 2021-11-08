package client.command;

import io.netty.channel.Channel;

public interface CommandHandler {

    void exec(String[] strings, Channel channel);
}
