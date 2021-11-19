package com.pan.serialize;

import com.pan.serialize.impl.JSONSerializer;

// 各JSON算法的接口
public interface Serializer {

    Serializer DEFAULT = new JSONSerializer();

    byte getSerializerAlogrithm();

    byte[] serialize(Object o);

    <T>T deserialize(Class<T> clazz, byte[] bytes);
}
