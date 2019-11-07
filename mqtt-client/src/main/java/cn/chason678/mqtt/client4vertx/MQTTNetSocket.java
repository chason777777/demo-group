package cn.chason678.mqtt.client4vertx;

import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.impl.NetSocketInternal;
import io.vertx.core.json.JsonObject;
import io.vertx.core.net.NetSocket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;
import java.util.Objects;

import static io.netty.handler.codec.mqtt.MqttMessageType.DISCONNECT;


/**
 * Created by giovanni on 07/05/2014.
 */
public class MQTTNetSocket extends MQTTSocket {

    private static Logger logger = LogManager.getLogger(MQTTNetSocket.class);

    private NetSocket netSocket;


    public MQTTNetSocket(int timeout, NetSocketInternal soi, Vertx vertx, ConfigParser config, NetSocket netSocket, Map<String, MQTTSession> sessions, String clientID) {
        super(timeout, soi, vertx, config, sessions, netSocket, clientID);
        this.netSocket = netSocket;
    }

    public void start() {
//        netSocket.setWriteQueueMaxSize(1000);
        netSocket.handler(this);
        netSocket.exceptionHandler(event -> {
            String clientInfo = getClientInfo();
            logger.error(clientInfo + ", net-socket closed ... " + netSocket.writeHandlerID() + " error: " + event.getMessage(), event.getCause());
            // 日志埋点
            logger.info("mqtt-process:" + new JsonObject().put("to","server").put("clientId",session.getClientID()).put("msgType", DISCONNECT).put("ip",netSocket.remoteAddress().host()).put("mqttMsg","net-socket closed for exception:" + event.getMessage()).toString());

            handleWillMessage();
            if (Objects.nonNull(session) && Objects.nonNull(session.getClientID())) {
                checkDevice(session.getClientID(), "offline");//离线狀態

                session.closeState();
                session.release();
            }
            shutdown();
        });
        netSocket.closeHandler(aVoid -> {
            String clientInfo = getClientInfo();
//            logger.debug(clientInfo + ", net-socket closed ... " + netSocket.writeHandlerID());
            // 日志埋点
            logger.info("mqtt-process:" + new JsonObject().put("to","server").put("clientId","").put("msgType", DISCONNECT).put("ip",netSocket.remoteAddress().host()).put("mqttMsg","net-socket closed for socket:").toString());

            handleWillMessage();
            if (Objects.nonNull(session) && Objects.nonNull(session.getClientID())) {
                checkDevice(session.getClientID(), "offline");//离线狀態

                session.closeState();
                session.release();
            }
            shutdown();
        });
    }


    @SuppressWarnings("Duplicates")
    @Override
    protected void sendMessageToClient(Buffer bytes) {
        try {
            if (netSocket != null) {
                netSocket.write(bytes);
                if (netSocket.writeQueueFull()) {
                    netSocket.pause();
                    netSocket.drainHandler(done -> netSocket.resume());
                }
            }
        } catch (Throwable e) {
            logger.error(e.getMessage());
        }
    }

    protected void closeConnection() {
        logger.debug("net-socket will be closed ... " + netSocket.writeHandlerID());
        netSocket.close();
    }

}
