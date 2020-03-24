package cn.chason678.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lichao
 * @version 1.0
 * @Description
 * @date 2019/7/18
 */
public class Client {
    private final String host;
    private final int port;
    public static List<Channel> channelList = new ArrayList<Channel>();

    public static ChannelFactory factory = null;

    public Client(String host, int port){
        this.host = host;
        this.port = port;
    }

    public void start() throws Exception{
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress(host, port))
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new ClientHandler());
                        }
                    });
            ChannelFuture f = b.connect().sync();
            channelList.add(f.channel());
            System.out.println("链接成功");
            f.channel().closeFuture().sync();
        }finally {
            group.shutdownGracefully().sync();
        }
    }

    public static void main(String[] args) throws Exception{
//        if (args.length != 2) {
//            System.out.println("Usage: " + EchoClientHandler.class.getSimpleName() + "<host> <port>");
//        }
//        String host = args[0];
//        int port = Integer.parseInt(args[1]);

        for (int i = 0; i < 100; i++) {
            new Client("127.0.0.1", 8888).start();
        }

    }
}
