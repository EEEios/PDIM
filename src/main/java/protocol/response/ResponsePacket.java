package protocol.response;

import lombok.Data;
import protocol.Packet;

import static client.command.Command.RESPONSE;

@Data
public class ResponsePacket extends Packet {
    private boolean success;

    private String message;

    @Override
    public Byte getCommand() {
        return RESPONSE;
    }
}
