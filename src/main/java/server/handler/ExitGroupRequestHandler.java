package server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import codec.protocol.request.ExitGroupRequestPacket;
import codec.protocol.response.ResponsePacket;
import util.SessionUtil;

public class ExitGroupRequestHandler extends SimpleChannelInboundHandler<ExitGroupRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ExitGroupRequestPacket msg) throws Exception {
        ChannelGroup cg;
        ResponsePacket resp = new ResponsePacket();
        if ((cg = SessionUtil.getGroup(msg.getGroupName())) == null) {
            resp.setSuccess(false);
            resp.setMessage("群组 [" + msg.getGroupName() + "] 不存在");
            ctx.channel().writeAndFlush(resp);
            return;
        }

        cg.remove(ctx.channel());
        resp.setSuccess(true);
        resp.setMessage("已退出群组 [" + msg.getGroupName() + "]");
        ctx.channel().writeAndFlush(resp);
    }
}
