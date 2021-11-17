package client.command;

import client.command.handler.*;
import io.netty.channel.Channel;
import server.handler.ExitGroupRequestHandler;

import java.util.HashMap;
import java.util.Map;

public class ConsoleCommandManager {

    private Map<String, CommandHandler> handlerMap;

    public ConsoleCommandManager() {
        this.handlerMap = new HashMap<>();
        handlerMap.put("logout", new LogoutCommandHandler(1));
        handlerMap.put("send", new SendCommandHandler(3));
        handlerMap.put("eng", new EnterGroupCommandHandler(2));
        handlerMap.put("sendg", new SendGroupCommandHandler(3));
        handlerMap.put("exg", new ExitGroupCommandHandler(2));
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
