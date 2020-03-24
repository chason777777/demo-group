package cn.chason678.mqttClientPt.parser;

import io.netty.buffer.ByteBuf;
import cn.chason678.mqttClientPt.proto.messages.AbstractMessage;
import cn.chason678.mqttClientPt.proto.messages.PingReqMessage;

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
