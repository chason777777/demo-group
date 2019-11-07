package cn.chason678.mqtt.util;

import cn.chason678.mqtt.moquette.proto.messages.PublishMessage;
import io.vertx.core.Vertx;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ConcurrentHashMap;

/**
 * handle QoS2 message.
 * use concurrenthashmap replace shareddata,because it's no need
 * Additional transformation operation.
 */
public enum CacheUtils {

    INSTANCE;

    private final static String SHARED_DATA = "shared_data_map";
    private final Logger logger = LogManager.getLogger(CacheUtils.class);

    private ConcurrentHashMap<String,PublishMessage> concurrentHashMap = new ConcurrentHashMap<>();

    /**
     * store publish msg to shared data
     * @param vertx
     * @param clientId
     * @param msgType
     * @param msgId
     */
    public void store(Vertx vertx,String clientId,int msgType,int msgId,PublishMessage publishMessage) {
//        SharedData sharedData = vertx.sharedData();
//        LocalMap<String, PublishMessage> localMap = sharedData.getLocalMap(SHARED_DATA);
        String key = convert(clientId,msgType,msgId);
        if(concurrentHashMap.containsKey(key)) {
            logger.info("duplicate key = {},value = {}.",key,publishMessage.toString());
        }
        concurrentHashMap.put(key,publishMessage);
    }

    /**
     * build the key({clientId,msgType,msgId})
     * @param clientId
     * @param msgType
     * @param msgId
     * @return
     */
    String convert(String clientId,int msgType,int msgId) {
        StringBuffer buffer = new StringBuffer(clientId);
        buffer.append(",").append(msgType).append(",").append(msgId);
        return buffer.toString();
    }

    /**
     * get publish from shareddata
     * @param vertx
     * @param clientId
     * @param msgType
     * @param msgId
     * @return
     */
    public PublishMessage get(Vertx vertx,String clientId,int msgType,int msgId) {
//        SharedData sharedData = vertx.sharedData();
//        LocalMap<String,PublishMessage> localMap = sharedData.getLocalMap(SHARED_DATA);
        String key = convert(clientId,msgType,msgId);
        if(concurrentHashMap.containsKey(key)) {
            return  concurrentHashMap.get(key);
        } else {
            logger.error("can't find the value by key({})",key);
            return null;
        }
    }

    /**
     * remove cache
     * @param clientId
     * @param msgType
     * @param msgId
     */
    public void remove(String clientId,int msgType,int msgId) {
        String key = convert(clientId,msgType,msgId);
        if(concurrentHashMap.containsKey(key)) {
            concurrentHashMap.remove(key);
        }
    }
}
