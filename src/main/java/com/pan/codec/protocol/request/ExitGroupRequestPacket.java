package com.pan.codec.protocol.request;

import com.pan.codec.protocol.Command;
import com.pan.codec.protocol.Packet;
import lombok.Data;
import com.pan.codec.protocol.AbstractPacket;

@Data
@Packet(Command.EXIT_GROUP)
public class ExitGroupRequestPacket extends AbstractPacket {

    private String groupName;

    @Override
    public Byte getCommand() {
        return Command.EXIT_GROUP;
    }
}
