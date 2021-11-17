package codec.protocol.request;

import codec.protocol.Command;
import codec.protocol.Packet;

@codec.Packet(Command.LOGOUT_REQUEST)
public class LogoutRequestPacket extends Packet {

    @Override
    public Byte getCommand() {
        return Command.LOGOUT_REQUEST;
    }
}
