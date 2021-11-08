package client.command;

import client.command.handler.LogoutCommandHandler;
import client.command.handler.SendCommandHandler;
import io.netty.channel.Channel;

import java.util.HashMap;
import java.util.Map;

public class ConsoleCommandManager {

    private Map<String, CommandHandler> handlerMap;

    public ConsoleCommandManager() {
        this.handlerMap = new HashMap<>();
        handlerMap.put("logout", new LogoutCommandHandler(1));
        handlerMap.put("send", new SendCommandHandler(0));
    }

    public void exec(String[] strings, Channel channel) {
        CommandHandler handler = handlerMap.get(strings[0]);

        if (handler == null) {
            System.out.println("- 无法识别该指令");
            return;
        }

        handler.exec(strings, channel);
    }
}