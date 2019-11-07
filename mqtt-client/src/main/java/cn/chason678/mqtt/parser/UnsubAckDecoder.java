package cn.chason678.mqtt.parser;

import cn.chason678.mqtt.moquette.proto.messages.MessageIDMessage;
import cn.chason678.mqtt.moquette.proto.messages.UnsubAckMessage;

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

