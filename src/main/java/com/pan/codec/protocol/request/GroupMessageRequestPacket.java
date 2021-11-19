package com.pan.codec.protocol.request;

import com.pan.codec.protocol.Packet;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.pan.codec.protocol.AbstractPacket;

import static com.pan.codec.protocol.Command.GROUP_MESSAGE;

@Data
@NoArgsConstructor
@Packet(GROUP_MESSAGE)
public class GroupMessageRequestPacket extends AbstractPacket {

    public GroupMessageRequestPacket(String msg) {
        this.msg = msg;
    }

    private String toGroup;

    private String msg;

    @Override
    public Byte getCommand() {
        return GROUP_MESSAGE;
    }
}
