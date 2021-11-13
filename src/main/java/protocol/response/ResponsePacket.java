package protocol.response;

import lombok.Data;
import protocol.Command;
import protocol.Packet;

import static protocol.Command.LOGIN_RESPONSE;
import static protocol.Command.RESPONSE;

@Data
public class ResponsePacket extends Packet {
    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {
        return RESPONSE;
    }
}
