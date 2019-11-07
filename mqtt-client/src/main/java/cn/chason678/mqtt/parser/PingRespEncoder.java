package cn.chason678.mqtt.parser;

import cn.chason678.mqtt.moquette.proto.messages.AbstractMessage;
import cn.chason678.mqtt.moquette.proto.messages.PingRespMessage;
import io.netty.buffer.ByteBuf;

/**
 *
 * @author andrea
 */
class PingRespEncoder extends DemuxEncoder<PingRespMessage> {

    @Override
    protected void encode(PingRespMessage msg, ByteBuf out) {
//        out.writeByte(AbstractMessage.PINGRESP << 4).writeByte(0);
        byte flags = Utils.encodeFlags(msg);
        out.writeByte(AbstractMessage.PINGRESP << 4 | flags).writeByte(0);
    }
}
