package cn.chason678.mqttClientPt.parser;

import cn.chason678.mqttClientPt.proto.messages.MessageIDMessage;
import cn.chason678.mqttClientPt.proto.messages.PubAckMessage;

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
