package cn.chason678.mqtt.parser;

import cn.chason678.mqtt.moquette.proto.messages.AbstractMessage;
import cn.chason678.mqtt.moquette.proto.messages.ConnAckMessage;
import io.netty.buffer.ByteBuf;

/**
 *
 * @author andrea
 */
class ConnAckEncoder extends DemuxEncoder<ConnAckMessage> {

    @Override
    protected void encode(ConnAckMessage message, ByteBuf out) {
        out.writeByte(AbstractMessage.CONNACK << 4);
        out.writeBytes(Utils.encodeRemainingLength(2));
        out.writeByte(message.isSessionPresent() ? 0x01 : 0x00);
        out.writeByte(message.getReturnCode());
    }
    
}
