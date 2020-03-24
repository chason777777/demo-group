package cn.chason678.mqttClientPt.parser;

import io.netty.buffer.ByteBuf;
import cn.chason678.mqttClientPt.proto.messages.AbstractMessage;
import cn.chason678.mqttClientPt.proto.messages.PubRelMessage;

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