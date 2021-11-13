package protocol.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import protocol.Packet;

import static client.command.Command.GROUP_MESSAGE;

@Data
@NoArgsConstructor
public class GroupMessageRequestPacket extends Packet {

    public GroupMessageRequestPacket(String msg) {
        this.msg = msg;
    }

    private String toGroup;

    private String msg;

    @Override
    public Byte getCommand() {
        return GROUP_MESSAGE;
    }
}
