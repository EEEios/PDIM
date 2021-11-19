package com.pan.codec.protocol.response;

import com.pan.codec.protocol.Packet;
import lombok.Data;
import com.pan.codec.protocol.Command;
import com.pan.codec.protocol.AbstractPacket;


@Data
@Packet(Command.RESPONSE)
public class ResponseAbstractPacket extends AbstractPacket {
    private boolean success;

    private String message;

    @Override
    public Byte getCommand() {
        return Command.RESPONSE;
    }
}
