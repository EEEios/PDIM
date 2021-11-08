package protocol.response;

import lombok.Data;
import protocol.Command;
import protocol.Packet;

@Data
public class LogoutResponsePacket extends Packet {

    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {
        return Command.LOGOUT_RESPONSE;
    }
}