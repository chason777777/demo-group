package cn.chason678.mqtt.parser;

import cn.chason678.mqtt.moquette.proto.messages.AbstractMessage;
import cn.chason678.mqtt.moquette.proto.messages.PingReqMessage;
import io.netty.buffer.ByteBuf;

/**
 *
 * @author andrea
 */
class PingReqEncoder  extends DemuxEncoder<PingReqMessage> {

    @Override
    protected void encode(PingReqMessage msg, ByteBuf out) {
//        out.writeByte(AbstractMessage.PINGREQ << 4).writeByte(0);
        byte flags = Utils.encodeFlags(msg);
        out.writeByte(AbstractMessage.PINGREQ << 4 | flags).writeByte(0);

    }
}
