package cn.chason678.mqtt.client;

import cn.chason678.mqtt.moquette.proto.messages.ConnectMessage;
import cn.chason678.mqtt.parser.MQTTEncoder;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import io.vertx.core.buffer.Buffer;

import java.util.List;
import java.util.UUID;

/**
 * @author lichao
 * @version 1.0
 * @Description
 * @date 2019/11/4
 */
public class ClientHandler extends SimpleChannelInboundHandler<ByteBuf> {
    private List<ChannelHandlerContext> list;
    private MQTTEncoder encoder;

    public ClientHandler(List<ChannelHandlerContext> list, MQTTEncoder encoder){
        this.list = list;
        this.encoder = encoder;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx){
        list.add(ctx);
        System.out.println("66666666666:" + list.size());
//        connection
        ConnectMessage connectMessage = new ConnectMessage();
        connectMessage.setCleanSession(false);
        connectMessage.setClientID(UUID.randomUUID().toString().replace("-",""));
        connectMessage.setKeepAlive(60);
        connectMessage.setPasswordFlag(false);
        connectMessage.setProcotolVersion((byte)4);
        connectMessage.setUsername("123321");
        connectMessage.setWillFlag(false);
        connectMessage.setDupFlag(false);
        connectMessage.setRetainFlag(true);
        connectMessage.setProtocolName("MQTT");

        try {
            Buffer b1 = encoder.enc(connectMessage);
            ctx.writeAndFlush(b1);
//            ctx.writeAndFlush(Unpooled.copiedBuffer(connectMessage.toString() , CharsetUtil.UTF_8));
        }catch (Exception e ){
            e.printStackTrace();
        }

    }
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
//        System.out.println("66666666666666");
        System.out.println("Client received: " + msg.toString(CharsetUtil.UTF_8));
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause){
//        System.out.println("7777777777777");
        cause.printStackTrace();
        ctx.close();
    }
}
