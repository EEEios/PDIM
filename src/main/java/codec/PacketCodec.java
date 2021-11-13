package codec;

import client.command.Command;
import io.netty.buffer.ByteBuf;
import protocol.Packet;
import protocol.request.*;
import protocol.response.LoginResponsePacket;
import protocol.response.LogoutResponsePacket;
import protocol.response.ResponsePacket;
import serialize.Serializer;
import serialize.SerializerAlgorithm;
import serialize.impl.JSONSerializer;

import java.util.HashMap;
import java.util.Map;

public class PacketCodec {

    public static final int MAGIC_NUMBER_LENGTH = 4;
    public static final int VERSION_LENGTH = 1;

    public static final int MAGIC_NUMBER = 0x4d3c2b1a;

    public static final PacketCodec INSTANCE = new PacketCodec();

    private final Map<Byte, Class<? extends Packet>> packetMap;

    private final Map<Byte, Serializer> serializerMap;

    public PacketCodec() {
        // TODO 命令解析
        packetMap = new HashMap<>();
        packetMap.put(Command.LOGIN_REQUEST, LoginRequestPacket.class);
        packetMap.put(Command.LOGIN_RESPONSE, LoginResponsePacket.class);
        packetMap.put(Command.MESSAGE_REQUEST, MessageRequestPacket.class);
//        packetMap.put(Command.MESSAGE_RESPONSE, MessageResponsePacket.class);
        packetMap.put(Command.ENTER_GROUP, EnterGroupRequestPacket.class);
        packetMap.put(Command.RESPONSE, ResponsePacket.class);
        packetMap.put(Command.GROUP_MESSAGE, GroupMessageRequestPacket.class);
        packetMap.put(Command.LOGOUT_REQUEST, LogoutRequestPacket.class);
        packetMap.put(Command.LOGOUT_RESPONSE, LogoutResponsePacket.class);
        serializerMap = new HashMap<>();
        serializerMap.put(SerializerAlgorithm.JSON, new JSONSerializer());
    }

    public ByteBuf encode(ByteBuf byteBuf, Packet packet) {

        byte[] bytes = Serializer.DEFAULT.serialize(packet);

        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlogrithm());
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);

        return byteBuf;
    }

    public Packet decode(ByteBuf byteBuf) {
        byteBuf.skipBytes(MAGIC_NUMBER_LENGTH);
        byteBuf.skipBytes(VERSION_LENGTH);

        byte serializeAlgorithm = byteBuf.readByte();
        byte command = byteBuf.readByte();

        int length = byteBuf.readInt();

        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);

        Class<? extends Packet> requestType = getType(command);
        Serializer serializer = getSerializer(serializeAlgorithm);

        if (requestType != null && serializer != null) {
            return serializer.deserialize(requestType, bytes);
        }
        return null;
    }

    private Serializer getSerializer(byte serializeAlgorithm) {
        return serializerMap.get(serializeAlgorithm);
    }

    private Class<? extends Packet> getType(byte command) {
        return packetMap.get(command);
    }
}
