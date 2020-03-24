package cn.chason678.mqttClientPt.parser;

import io.netty.buffer.ByteBuf;
import cn.chason678.mqttClientPt.proto.messages.AbstractMessage;
import cn.chason678.mqttClientPt.proto.messages.PingRespMessage;

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
