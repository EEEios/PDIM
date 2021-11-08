package protocol.response;

import lombok.Data;
import protocol.Packet;

import static protocol.Command.MESSAGE_RESPONSE;

@Data
public class MessageResponsePacket extends Packet {

    private String fromUId;

    private String fromUsername;

    private String message;

    @Override
    public Byte getCommand() {
        return MESSAGE_RESPONSE;
    }
}
