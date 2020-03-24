package cn.chason678.mqttClientPt.parser;

import cn.chason678.mqttClientPt.proto.messages.MessageIDMessage;
import cn.chason678.mqttClientPt.proto.messages.PubRecMessage;

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
