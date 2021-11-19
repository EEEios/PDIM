package com.pan.codec.protocol.request;

import com.pan.codec.protocol.Packet;
import lombok.Data;
import com.pan.codec.protocol.AbstractPacket;

import static com.pan.codec.protocol.Command.LOGIN_REQUEST;

@Data
@Packet(LOGIN_REQUEST)
public class LoginRequestPacket extends AbstractPacket {

    private String userId;

    private String username;

    private String password;

    @Override
    public Byte getCommand() {
        return LOGIN_REQUEST;
    }
}
