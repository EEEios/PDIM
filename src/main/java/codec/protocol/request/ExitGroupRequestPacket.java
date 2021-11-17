package codec.protocol.request;

import codec.protocol.Command;
import lombok.Data;
import codec.protocol.Packet;

@Data
@codec.Packet(Command.EXIT_GROUP)
public class ExitGroupRequestPacket extends Packet {

    private String groupName;

    @Override
    public Byte getCommand() {
        return Command.EXIT_GROUP;
    }
}
