package protocol.request;

import lombok.Data;
import protocol.Packet;

import static protocol.Command.MESSAGE_REQUEST;

@Data
public class MessageRequestPacket extends Packet {

    public MessageRequestPacket(String msg) {
        this.msg = msg;
    }

    public MessageRequestPacket() {
    }

    private String msg;

    @Override
    public Byte getCommand() {
        return MESSAGE_REQUEST;
    }
}
