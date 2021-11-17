package codec.protocol.response;

import lombok.Data;
import codec.protocol.Command;
import codec.protocol.Packet;


@Data
@codec.Packet(Command.RESPONSE)
public class ResponsePacket extends Packet {
    private boolean success;

    private String message;

    @Override
    public Byte getCommand() {
        return Command.RESPONSE;
    }
}
