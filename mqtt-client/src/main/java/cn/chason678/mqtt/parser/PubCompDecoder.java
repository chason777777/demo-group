package cn.chason678.mqtt.parser;

import cn.chason678.mqtt.moquette.proto.messages.MessageIDMessage;
import cn.chason678.mqtt.moquette.proto.messages.PubCompMessage;


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
