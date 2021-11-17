package codec;

import com.sun.xml.internal.bind.v2.runtime.reflect.Lister;
import org.reflections.Reflections;
import codec.protocol.Command;
import io.netty.buffer.ByteBuf;
import codec.protocol.Packet;
import codec.protocol.request.*;
import codec.protocol.response.LoginResponsePacket;
import codec.protocol.response.LogoutResponsePacket;
import codec.protocol.response.ResponsePacket;
import serialize.Serializer;
import serialize.SerializerAlgorithm;
import serialize.impl.JSONSerializer;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class PacketCodec {

    public static final int MAGIC_NUMBER_LENGTH = 4;
    public static final int VERSION_LENGTH = 1;

    public static final int MAGIC_NUMBER = 0x4d3c2b1a;

    public static final PacketCodec INSTANCE = new PacketCodec();

    private final Map<Byte, Class<? extends Packet>> packetMap;

    private final Map<Byte, Serializer> serializerMap;

    public PacketCodec() {
        packetMap = new HashMap<>();
//        packetMap.put(Command.LOGIN_REQUEST, LoginRequestPacket.class);
//        packetMap.put(Command.LOGIN_RESPONSE, LoginResponsePacket.class);
//        packetMap.put(Command.MESSAGE_REQUEST, MessageRequestPacket.class);
//        packetMap.put(Command.ENTER_GROUP, EnterGroupRequestPacket.class);
//        packetMap.put(Command.RESPONSE, ResponsePacket.class);
//        packetMap.put(Command.GROUP_MESSAGE, GroupMessageRequestPacket.class);
//        packetMap.put(Command.LOGOUT_REQUEST, LogoutRequestPacket.class);
//        packetMap.put(Command.LOGOUT_RESPONSE, LogoutResponsePacket.class);
//        packetMap.put(Command.EXIT_GROUP, ExitGroupRequestPacket.class);
        serializerMap = new HashMap<>();
        serializerMap.put(SerializerAlgorithm.JSON, new JSONSerializer());

        Reflections reflections = new Reflections(this.getClass().getPackage().getName() + ".protocol");
        Set<Class<?>> packets = reflections.getTypesAnnotatedWith(codec.Packet.class);
        for (Class<?> pClazz : packets) {
            packetMap.put(pClazz.getAnnotation(codec.Packet.class).value(), (Class<? extends Packet>) pClazz);
        }
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
