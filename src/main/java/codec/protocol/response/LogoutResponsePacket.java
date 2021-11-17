package codec.protocol.response;

import lombok.Data;
import codec.protocol.Command;
import codec.protocol.Packet;

@Data
@codec.Packet(Command.LOGOUT_RESPONSE)
public class LogoutResponsePacket extends Packet {

    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {
        return Command.LOGOUT_RESPONSE;
    }
}
