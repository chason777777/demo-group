package cn.chason678.mqtt.parser;

import cn.chason678.mqtt.moquette.proto.messages.AbstractMessage;
import cn.chason678.mqtt.moquette.proto.messages.PubAckMessage;
import io.netty.buffer.ByteBuf;
import io.vertx.core.buffer.Buffer;

/**
 *
 * @author andrea
 */
class PubAckEncoder extends DemuxEncoder<PubAckMessage> {

    @Override
    protected void encode(PubAckMessage msg, ByteBuf out) {
//        ByteBuf buff = chc.alloc().buffer(4);
        ByteBuf buff = Buffer.buffer(4).getByteBuf();
        try {
//            buff.writeByte(AbstractMessage.PUBACK << 4);
            byte flags = Utils.encodeFlags(msg);
            out.writeByte(AbstractMessage.PUBACK << 4 | flags);
            buff.writeBytes(Utils.encodeRemainingLength(2));
            buff.writeShort(msg.getMessageID());
            out.writeBytes(buff);
        } finally {
            buff.release();
        }
    }
    
}
