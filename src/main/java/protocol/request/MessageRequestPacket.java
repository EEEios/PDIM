package protocol.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import protocol.Packet;

import static protocol.Command.MESSAGE_REQUEST;

@Data
@NoArgsConstructor
public class MessageRequestPacket extends Packet {

    public MessageRequestPacket(String msg) {
        this.msg = msg;
    }

    private String msg;

    private String toUId;

    @Override
    public Byte getCommand() {
        return MESSAGE_REQUEST;
    }
}
