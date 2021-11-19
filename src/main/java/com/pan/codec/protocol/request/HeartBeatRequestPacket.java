package com.pan.codec.protocol.request;


import com.pan.codec.protocol.AbstractPacket;
import com.pan.codec.protocol.Command;
import com.pan.codec.protocol.Packet;
import lombok.Data;

@Data
@Packet(Command.HEART_BEAT)
public class HeartBeatRequestPacket extends AbstractPacket {

    @Override
    public Byte getCommand() {
        return Command.HEART_BEAT;
    }
}
