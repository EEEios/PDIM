package protocol.request;

import client.command.Command;
import protocol.Packet;

public class LogoutRequestPacket extends Packet {

    @Override
    public Byte getCommand() {
        return Command.LOGOUT_REQUEST;
    }
}
