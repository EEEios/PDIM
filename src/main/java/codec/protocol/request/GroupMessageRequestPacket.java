package codec.protocol.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import codec.protocol.Packet;

import static codec.protocol.Command.GROUP_MESSAGE;

@Data
@NoArgsConstructor
@codec.Packet(GROUP_MESSAGE)
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
