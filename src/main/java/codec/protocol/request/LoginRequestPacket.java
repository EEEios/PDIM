package codec.protocol.request;

import lombok.Data;
import codec.protocol.Packet;

import static codec.protocol.Command.LOGIN_REQUEST;

@Data
@codec.Packet(LOGIN_REQUEST)
public class LoginRequestPacket extends Packet {

    private String userId;

    private String username;

    private String password;

    @Override
    public Byte getCommand() {
        return LOGIN_REQUEST;
    }
}
