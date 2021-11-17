package codec.protocol.request;

import lombok.Data;
import codec.protocol.Packet;

import static codec.protocol.Command.MESSAGE_REQUEST;

@Data
@codec.Packet(MESSAGE_REQUEST)
public class MessageRequestPacket extends Packet {

    public MessageRequestPacket() {
    }

    public MessageRequestPacket(String msg) {
        this.msg = msg;
    }

    private String toUId;

    private String msg;

    private String toUsername;

    @Override
    public Byte getCommand() {
        return MESSAGE_REQUEST;
    }
}
