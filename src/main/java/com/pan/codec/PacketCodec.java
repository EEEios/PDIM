package com.pan.codec;

import com.pan.codec.protocol.Packet;
import org.reflections.Reflections;
import io.netty.buffer.ByteBuf;
import com.pan.codec.protocol.AbstractPacket;
import com.pan.serialize.Serializer;
import com.pan.serialize.SerializerAlgorithm;
import com.pan.serialize.impl.JSONSerializer;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class PacketCodec {

    public static final PacketCodec INSTANCE = new PacketCodec();

    public static final int MAGIC_NUMBER_LENGTH = 4;
    public static final int VERSION_LENGTH = 1;

    public static final int MAGIC_NUMBER = 0x4d3c2b1a;

    private final Map<Byte, Class<? extends AbstractPacket>> packetMap;

    private final Map<Byte, Serializer> serializerMap;

    public PacketCodec() {
        packetMap = new HashMap<>();
        serializerMap = new HashMap<>();
        serializerMap.put(SerializerAlgorithm.JSON, new JSONSerializer());

        Reflections reflections = new Reflections(this.getClass().getPackage().getName() + ".protocol");
        Set<Class<?>> packets = reflections.getTypesAnnotatedWith(Packet.class);
        for (Class<?> pClazz : packets) {
            packetMap.put(pClazz.getAnnotation(Packet.class).value(), (Class<? extends AbstractPacket>) pClazz);
        }
    }


    public ByteBuf encode(ByteBuf byteBuf, AbstractPacket abstractPacket) {

        byte[] bytes = Serializer.DEFAULT.serialize(abstractPacket);

        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(abstractPacket.getVersion());
        byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlogrithm());
        byteBuf.writeByte(abstractPacket.getCommand());
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);

        return byteBuf;
    }

    public AbstractPacket decode(ByteBuf byteBuf) {
        byteBuf.skipBytes(MAGIC_NUMBER_LENGTH);
        byteBuf.skipBytes(VERSION_LENGTH);

        byte serializeAlgorithm = byteBuf.readByte();
        byte command = byteBuf.readByte();

        int length = byteBuf.readInt();

        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);

        Class<? extends AbstractPacket> requestType = getType(command);
        Serializer serializer = getSerializer(serializeAlgorithm);

        if (requestType != null && serializer != null) {
            return serializer.deserialize(requestType, bytes);
        }
        return null;
    }

    private Serializer getSerializer(byte serializeAlgorithm) {
        return serializerMap.get(serializeAlgorithm);
    }

    private Class<? extends AbstractPacket> getType(byte command) {
        return packetMap.get(command);
    }
}
