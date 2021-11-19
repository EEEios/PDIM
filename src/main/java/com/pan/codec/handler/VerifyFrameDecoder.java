package com.pan.codec.handler;

import com.pan.codec.PacketCodec;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * 使用基于长度的拆包器装配
 */
public class VerifyFrameDecoder extends LengthFieldBasedFrameDecoder {

    private static final int LENGTH_FILED_OFFSET = 7;
    private static final int LENGTH_FILED_LENGTH = 4;

    public VerifyFrameDecoder() {
        super(Integer.MAX_VALUE, LENGTH_FILED_OFFSET, LENGTH_FILED_LENGTH);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        if (in.getInt(in.readerIndex()) != PacketCodec.MAGIC_NUMBER) {
            ctx.channel().close();
            return null;
        }
        return super.decode(ctx, in);
    }
}
