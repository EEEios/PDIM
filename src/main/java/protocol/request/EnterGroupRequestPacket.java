package protocol.request;

import lombok.Data;
import protocol.Packet;

import static client.command.Command.ENTER_GROUP;

@Data
public class EnterGroupRequestPacket extends Packet {

    private String groupName;

    @Override
    public Byte getCommand() {
        return ENTER_GROUP;
    }
}
