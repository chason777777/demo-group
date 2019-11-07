package cn.chason678.mqtt.parser;

import cn.chason678.mqtt.moquette.proto.messages.AbstractMessage;
import cn.chason678.mqtt.moquette.proto.messages.UnsubAckMessage;
import io.netty.buffer.ByteBuf;

/**
 *
 * @author andrea
 */
class UnsubAckEncoder extends DemuxEncoder<UnsubAckMessage> {

    @Override
    protected void encode(UnsubAckMessage msg, ByteBuf out) {
        out.writeByte(AbstractMessage.UNSUBACK << 4).
                writeBytes(Utils.encodeRemainingLength(2)).
                writeShort(msg.getMessageID());
    }
}

