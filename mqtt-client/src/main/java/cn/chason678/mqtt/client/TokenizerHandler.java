package cn.chason678.mqtt.client;

import cn.chason678.mqtt.moquette.proto.messages.AbstractMessage;
import cn.chason678.mqtt.parser.MQTTDecoder;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.vertx.core.buffer.Buffer;

import java.nio.ByteBuffer;

/**
 * @author lichao
 * @version 1.0
 * @Description
 * @date 2019/11/4
 */
public class TokenizerHandler extends SimpleChannelInboundHandler<ByteBuffer> {
    private MQTTDecoder decoder;

    public TokenizerHandler(MQTTDecoder decoder){
        this.decoder = decoder;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuffer msg) throws Exception {
        try {
            AbstractMessage message = decoder.dec(Buffer.buffer(msg.array()));
            System.out.println(message.toString());
        }catch (Exception e   ){
            e.printStackTrace();
        }
    }
}
