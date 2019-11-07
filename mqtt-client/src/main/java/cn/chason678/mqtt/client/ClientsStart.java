package cn.chason678.mqtt.client;

import cn.chason678.mqtt.moquette.proto.messages.PublishMessage;
import cn.chason678.mqtt.parser.MQTTDecoder;
import cn.chason678.mqtt.parser.MQTTEncoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.vertx.core.buffer.Buffer;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author lichao
 * @version 1.0
 * @Description
 * @date 2019/11/4
 */
public class ClientsStart {
    public static void main(String[] args) {
        List<ChannelHandlerContext> list = new CopyOnWriteArrayList<ChannelHandlerContext>();
        MQTTDecoder decoder = new MQTTDecoder();
        MQTTEncoder encoder = new MQTTEncoder();

        EventLoopGroup group = new NioEventLoopGroup(10);
        Bootstrap b = new Bootstrap();
        b.group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.SO_REUSEADDR, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast("clientHandler", new ClientHandler(list, encoder));
//                        ch.pipeline().addLast("tokenizerHandler",new TokenizerHandler(decoder));
                    }
                });


        for (int i = 0; i < 3; i++) {
            ChannelFuture f = b.connect("127.0.0.1", 1883);
            if (f.isSuccess()) {
                System.out.println("index: " + i + "链接成功");
            }
        }
        try {
            Thread.sleep(20);
        }catch (Exception e ){
            e.printStackTrace();
        }
        System.out.println("--------------------------");
        System.out.println("---------------------->" + list.size());
        int count = 0;
        try {
            for (; ; ) {
                int index = (int) (Math.random() * 1);
                ChannelHandlerContext ctx = list.get(index);
                PublishMessage publishMessage = new PublishMessage();
                publishMessage.setPayload("test:" + UUID.randomUUID().toString().replace("-", ""));
                publishMessage.setTopicName("/orange/123321");
                try {
                    Buffer b1 = encoder.enc(publishMessage);
                    ctx.writeAndFlush(b1);
//                    ctx.writeAndFlush(Unpooled.copiedBuffer(publishMessage.toString() , CharsetUtil.UTF_8));
//                    ctx.writeAndFlush(Unpooled.copiedBuffer("recive client message,index:" + index + ",count:" + count++, CharsetUtil.UTF_8));
                } catch (Exception e) {
                    e.printStackTrace();
                }

                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            group.shutdownGracefully();
        }
    }
}
