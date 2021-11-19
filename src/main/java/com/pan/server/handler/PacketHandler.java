package com.pan.server.handler;

import com.pan.codec.protocol.AbstractPacket;
import com.pan.codec.protocol.Command;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.HashMap;
import java.util.Map;

public class PacketHandler extends SimpleChannelInboundHandler<AbstractPacket> {

    public static final PacketHandler INSTANCE = new PacketHandler();

    private Map<Byte, SimpleChannelInboundHandler<? extends AbstractPacket>> handlerMap;

    public PacketHandler() {
        handlerMap = new HashMap<>();

        handlerMap.put(Command.ENTER_GROUP, EnterGroupRequestHandler.INSTANCE);
        handlerMap.put(Command.EXIT_GROUP, ExitGroupRequestHandler.INSTANCE);
        handlerMap.put(Command.GROUP_MESSAGE, GroupMessageRequestHandler.INSTANCE);
        handlerMap.put(Command.MESSAGE_REQUEST, MessageRequestHandler.INSTANCE);
        handlerMap.put(Command.LOGOUT_REQUEST, LogoutRequestHandler.INSTANCE);

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, AbstractPacket msg) throws Exception {
        handlerMap.get(msg.getCommand()).channelRead(ctx, msg);
    }
}
