package cn.chason678.mqttClientPt.parser;

import io.netty.buffer.ByteBuf;
import cn.chason678.mqttClientPt.proto.messages.AbstractMessage;
import cn.chason678.mqttClientPt.proto.messages.UnsubAckMessage;

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

