package com.pan.codec.protocol.request;

import com.pan.codec.protocol.Packet;
import lombok.Data;
import com.pan.codec.protocol.AbstractPacket;

import static com.pan.codec.protocol.Command.ENTER_GROUP;

@Data
@Packet(ENTER_GROUP)
public class EnterGroupRequestPacket extends AbstractPacket {

    private String groupName;

    @Override
    public Byte getCommand() {
        return ENTER_GROUP;
    }
}
