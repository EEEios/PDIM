package com.pan.codec.protocol.response;

import com.pan.codec.protocol.AbstractPacket;
import com.pan.codec.protocol.Packet;
import lombok.Data;

import static com.pan.codec.protocol.Command.HEART_BEAT_RESP;

@Data
@Packet(HEART_BEAT_RESP)
public class HeartBeatResponsePacket extends AbstractPacket {

    @Override
    public Byte getCommand() {
        return HEART_BEAT_RESP;
    }
}
