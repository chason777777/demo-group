package cn.chason678.mqtt.parser;

import cn.chason678.mqtt.moquette.proto.messages.MessageIDMessage;
import cn.chason678.mqtt.moquette.proto.messages.PubRecMessage;

/**
 *
 * @author andrea
 */
class PubRecDecoder extends MessageIDDecoder {

    @Override
    protected MessageIDMessage createMessage() {
        return new PubRecMessage();
    }
}
