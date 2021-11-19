package com.pan.codec.handler;

import com.pan.codec.PacketCodec;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class PacketDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        Object o;
        // 收到客户端断开请求时，会有 accept，但是缓冲区没有收到数据
        if ((o = PacketCodec.INSTANCE.decode(in) ) != null) {
            out.add(o);
        } else {
            ctx.channel().close();
        }
    }
}
