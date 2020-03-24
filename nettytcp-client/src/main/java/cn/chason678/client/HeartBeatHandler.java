package cn.chason678.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;

import java.nio.Buffer;

/**
 * @author lichao
 * @version 1.0
 * @Description
 * @date 2019/11/7
 */
public class HeartBeatHandler extends SimpleChannelInboundHandler<Buffer> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Buffer msg) throws Exception {
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        System.err.println("=== " + ctx.channel().remoteAddress() + " is active ===");
        Clients.count.incrementAndGet();
        System.out.println("connect success, count:" + Clients.count.get());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
//        System.err.println("=== " + ctx.channel().remoteAddress() + " is active ===");
        Clients.count.decrementAndGet();
        System.out.println("disconnect success, count:" + Clients.count.get());
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        IdleStateEvent event = (IdleStateEvent)evt;
        ctx.channel().writeAndFlush("you are out");
    }
}
