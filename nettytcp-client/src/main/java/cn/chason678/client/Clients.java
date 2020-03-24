package cn.chason678.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author lichao
 * @version 1.0
 * @Description
 * @date 2019/10/21
 */
public class Clients {
    public static AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) throws Exception {
        String host = "127.0.0.1";
        int port = 1883;
        int count = 1;
        if (args.length == 3) {
            host = args[0];
            port = Integer.parseInt(args[1]);
            count = Integer.parseInt(args[2]);
        }
        System.out.println("host:" + host + " ,port:" + port + " ,count:" + count);
        EventLoopGroup group = new NioEventLoopGroup(10);
        Bootstrap b = new Bootstrap();
        b.group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.SO_REUSEADDR, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast("decoder", new StringDecoder());
                        ch.pipeline().addLast("encoder", new StringEncoder());
                        ch.pipeline().addLast(new IdleStateHandler(0,30,0, TimeUnit.SECONDS));
                        ch.pipeline().addLast(new HeartBeatHandler());
                    }
                });
        try {
            for (int i = 0; i < count; i++) {
                Thread.sleep(2);
                ChannelFuture f = b.connect(host, port);
                if (f.isSuccess())
                    System.out.println("index: " + (i + 1) + "链接成功");
//                f.channel().closeFuture().sync();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            group.shutdownGracefully().sync();
        }

    }
}
