package cn.chason678.mqttClientPt.parser;

import io.netty.buffer.ByteBuf;
import cn.chason678.mqttClientPt.proto.messages.AbstractMessage;
import cn.chason678.mqttClientPt.proto.messages.PubCompMessage;

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
