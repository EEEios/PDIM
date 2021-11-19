package com.pan.codec.protocol.response;

import com.pan.codec.protocol.Packet;
import lombok.Data;
import com.pan.codec.protocol.AbstractPacket;

import static com.pan.codec.protocol.Command.LOGIN_RESPONSE;

@Data
@Packet(LOGIN_RESPONSE)
public class LoginResponsePacket extends AbstractPacket {

    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {
        return LOGIN_RESPONSE;
    }
}
