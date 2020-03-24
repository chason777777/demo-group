package cn.chason678.mqttClientPt.parser;

import io.netty.buffer.ByteBuf;
import cn.chason678.mqttClientPt.proto.messages.AbstractMessage;
import cn.chason678.mqttClientPt.proto.messages.PubRecMessage;

/**
 *
 * @author andrea
 */
class PubRecEncoder extends DemuxEncoder<PubRecMessage> {

    @Override
    protected void encode(PubRecMessage msg, ByteBuf out) {
//        out.writeByte(AbstractMessage.PUBREC << 4);
        byte flags = Utils.encodeFlags(msg);
        out.writeByte(AbstractMessage.PUBREC << 4 | flags);
        out.writeBytes(Utils.encodeRemainingLength(2));
        out.writeShort(msg.getMessageID());
    }
}