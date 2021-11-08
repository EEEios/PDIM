package protocol.request;

import protocol.Command;
import protocol.Packet;

public class LogoutRequestPacket extends Packet {

    @Override
    public Byte getCommand() {
        return Command.LOGOUT_REQUEST;
    }
}
