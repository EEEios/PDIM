package codec.protocol.response;

import lombok.Data;
import codec.protocol.Packet;

import static codec.protocol.Command.LOGIN_RESPONSE;

@Data
@codec.Packet(LOGIN_RESPONSE)
public class LoginResponsePacket extends Packet {

    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {
        return LOGIN_RESPONSE;
    }
}
