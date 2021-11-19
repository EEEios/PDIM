package com.pan.codec.protocol.request;

import com.pan.codec.protocol.Command;
import com.pan.codec.protocol.AbstractPacket;
import com.pan.codec.protocol.Packet;

@Packet(Command.LOGOUT_REQUEST)
public class LogoutRequestPacket extends AbstractPacket {

    @Override
    public Byte getCommand() {
        return Command.LOGOUT_REQUEST;
    }
}
