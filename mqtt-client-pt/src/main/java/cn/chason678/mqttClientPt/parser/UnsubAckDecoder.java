package cn.chason678.mqttClientPt.parser;


import cn.chason678.mqttClientPt.proto.messages.MessageIDMessage;
import cn.chason678.mqttClientPt.proto.messages.UnsubAckMessage;

/**
 *
 * @author andrea
 */
class UnsubAckDecoder extends MessageIDDecoder {

    @Override
    protected MessageIDMessage createMessage() {
        return new UnsubAckMessage();
    }
}

