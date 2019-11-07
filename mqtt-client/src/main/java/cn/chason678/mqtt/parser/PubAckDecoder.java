package cn.chason678.mqtt.parser;

import cn.chason678.mqtt.moquette.proto.messages.MessageIDMessage;
import cn.chason678.mqtt.moquette.proto.messages.PubAckMessage;

/**
 *
 * @author andrea
 */
class PubAckDecoder extends MessageIDDecoder {

    @Override
    protected MessageIDMessage createMessage() {
        return new PubAckMessage();
    }
    
}
