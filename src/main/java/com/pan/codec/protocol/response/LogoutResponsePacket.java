package com.pan.codec.protocol.response;

import com.pan.codec.protocol.Packet;
import lombok.Data;
import com.pan.codec.protocol.Command;
import com.pan.codec.protocol.AbstractPacket;

@Data
@Packet(Command.LOGOUT_RESPONSE)
public class LogoutResponsePacket extends AbstractPacket {

    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {
        return Command.LOGOUT_RESPONSE;
    }
}
