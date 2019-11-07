package cn.chason678.mqtt.parser;

import cn.chason678.mqtt.moquette.proto.messages.AbstractMessage;
import cn.chason678.mqtt.moquette.proto.messages.PubRelMessage;
import io.netty.buffer.ByteBuf;

/**
 *
 * @author andrea
 */
class PubRelEncoder extends DemuxEncoder<PubRelMessage> {

    @Override
    protected void encode(PubRelMessage msg, ByteBuf out) {
//        out.writeByte(AbstractMessage.PUBREL << 4);
        byte flags = Utils.encodeFlags(msg);
        out.writeByte(AbstractMessage.PUBREL << 4 | flags);
        out.writeBytes(Utils.encodeRemainingLength(2));
        out.writeShort(msg.getMessageID());
    }
}