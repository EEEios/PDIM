package codec.protocol.request;

import lombok.Data;
import codec.protocol.Packet;

import static codec.protocol.Command.ENTER_GROUP;

@Data
@codec.Packet(ENTER_GROUP)
public class EnterGroupRequestPacket extends Packet {

    private String groupName;

    @Override
    public Byte getCommand() {
        return ENTER_GROUP;
    }
}
