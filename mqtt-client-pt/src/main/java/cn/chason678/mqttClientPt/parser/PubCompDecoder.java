package cn.chason678.mqttClientPt.parser;

import cn.chason678.mqttClientPt.proto.messages.MessageIDMessage;
import cn.chason678.mqttClientPt.proto.messages.PubCompMessage;

/**
 *
 * @author andrea
 */
class PubCompDecoder extends MessageIDDecoder {

    @Override
    protected MessageIDMessage createMessage() {
        return new PubCompMessage();
    }
}
