package cn.chason678.mqtt.parser;

import cn.chason678.mqtt.moquette.proto.messages.AbstractMessage;
import cn.chason678.mqtt.moquette.proto.messages.PubCompMessage;
import io.netty.buffer.ByteBuf;

/**
 * @author andrea
 */
class PubCompEncoder extends DemuxEncoder<PubCompMessage> {

    @Override
    protected void encode(PubCompMessage msg, ByteBuf out) {
//        out.writeByte(AbstractMessage.PUBCOMP << 4);
        byte flags = Utils.encodeFlags(msg);
        out.writeByte(AbstractMessage.PUBCOMP << 4 | flags);
        out.writeBytes(Utils.encodeRemainingLength(2));
        out.writeShort(msg.getMessageID());
    }
}
