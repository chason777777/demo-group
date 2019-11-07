package cn.chason678.mqtt.client4vertx;

import cn.chason678.mqtt.moquette.proto.messages.ConnectMessage;
import cn.chason678.mqtt.parser.MQTTEncoder;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.impl.NetSocketInternal;
import io.vertx.core.json.JsonObject;
import io.vertx.core.net.NetClient;
import io.vertx.core.net.NetClientOptions;
import io.vertx.core.net.NetSocket;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;

import java.io.InputStream;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lichao
 * @version 1.0
 * @Description
 * @date 2019/11/4
 */
public class ClientStart {
    private static Logger logger = LogManager.getLogger(ClientStart.class);
    public static void main(String[] args) {

        /**加载log4j2配置*/
        ConfigurationSource source = null;
        try {
            //加载log4j2配置
            InputStream in = ClientStart.class.getResourceAsStream("/log4j2.xml");
            source = new ConfigurationSource(in);
            Configurator.initialize(null, source);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        String host = "127.0.0.1";
        int port = 1883;
        int count = 1;
        if (args.length == 3){
            host = args[0];
            port = Integer.parseInt(args[1]);
            count = Integer.parseInt(args[2]);
        }
        logger.info("host:" + host + " ,port:" + port + " ,count:" + count);

        int DEFAULT_IDLE_TIME = 60;//默认打idle最大超时时间
        Map<String, MQTTSession> sessions = new ConcurrentHashMap<>();
//        MQTTPacketTokenizer tokenizer = new MQTTPacketTokenizer();

        MQTTEncoder encoder = new MQTTEncoder();
        VertxOptions vertxOptions = new VertxOptions();
        vertxOptions.setEventLoopPoolSize(50);
        Vertx vertx = Vertx.vertx(vertxOptions);

        NetClientOptions options = new NetClientOptions().setConnectTimeout(60000);
        NetClient client = vertx.createNetClient(options);

        for (int i = 0; i < count; i++) {
            try {
                Thread.sleep(10);
            }catch (Exception e){
                e.printStackTrace();
            }

//            client.connect(2883, "47.106.83.60", res -> {
            client.connect(port, host, res -> {
                if (res.succeeded()) {
                    System.out.println("Connected!");
                    NetSocket netSocket = res.result();
                    NetSocketInternal soi = (NetSocketInternal) netSocket;
                    JsonObject config = new JsonObject();
                    ConfigParser c = new ConfigParser(config);

                    String clientID = "app:" + UUID.randomUUID().toString().replace("-","");
                    MQTTNetSocket mqttNetSocket = new MQTTNetSocket(DEFAULT_IDLE_TIME, soi, vertx, c, netSocket, sessions, clientID);
                    mqttNetSocket.start();

                    ConnectMessage connectMessage = new ConnectMessage();
                    connectMessage.setCleanSession(true);
                    connectMessage.setClientID(clientID);
                    connectMessage.setKeepAlive(60);
                    connectMessage.setPasswordFlag(false);
                    connectMessage.setProcotolVersion((byte)4);
                    connectMessage.setUsername("123321");
                    connectMessage.setWillFlag(false);
                    connectMessage.setDupFlag(false);
                    connectMessage.setRetainFlag(true);
                    connectMessage.setProtocolName("MQTT");
                    try {
                        netSocket.write(encoder.enc(connectMessage));
                    }catch (Throwable e){
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("Failed to connect: " + res.cause().getMessage());
                }
            });
        }

//        int id = 1;
//        for(;;) {
//            try {
//                Thread.sleep(5000);
//                PublishMessage publishMessage = new PublishMessage();
//                publishMessage.setTopicName("/aaa/result");
//                publishMessage.setPayload(ByteBuffer.wrap("{\"key\":\"hello\"}".getBytes("UTF-8")));
//                publishMessage.setQos(AbstractMessage.QOSType.LEAST_ONE);
//                publishMessage.setMessageID(id++);
//                Iterator<Map.Entry<String,MQTTSession>> iterator = sessions.entrySet().iterator();
//                while (iterator.hasNext()) {
//                    Map.Entry<String,MQTTSession> entry = iterator.next();
//                    entry.getValue().sendMessageToClient(publishMessage);
//                    System.out.println("---------sessions size:" + sessions.size());
//                }
//
//            }catch (Exception e ){
//                e.printStackTrace();
//            }
//        }
    }
}
