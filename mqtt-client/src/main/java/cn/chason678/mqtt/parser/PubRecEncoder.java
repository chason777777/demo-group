package cn.chason678.mqtt.parser;

import cn.chason678.mqtt.moquette.proto.messages.AbstractMessage;
import cn.chason678.mqtt.moquette.proto.messages.PubRecMessage;
import io.netty.buffer.ByteBuf;

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