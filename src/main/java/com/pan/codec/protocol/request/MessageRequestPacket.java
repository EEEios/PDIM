package com.pan.codec.protocol.request;

import com.pan.codec.protocol.Packet;
import lombok.Data;
import com.pan.codec.protocol.AbstractPacket;

import static com.pan.codec.protocol.Command.MESSAGE_REQUEST;

@Data
@Packet(MESSAGE_REQUEST)
public class MessageRequestPacket extends AbstractPacket {

    public MessageRequestPacket() {
    }

    public MessageRequestPacket(String msg) {
        this.msg = msg;
    }

    private String toUId;

    private String msg;

    private String toUsername;

    @Override
    public Byte getCommand() {
        return MESSAGE_REQUEST;
    }
}
