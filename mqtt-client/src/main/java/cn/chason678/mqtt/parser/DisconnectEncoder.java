package cn.chason678.mqtt.parser;

import cn.chason678.mqtt.moquette.proto.messages.AbstractMessage;
import cn.chason678.mqtt.moquette.proto.messages.DisconnectMessage;
import io.netty.buffer.ByteBuf;

/**
 *
 * @author andrea
 */
public class DisconnectEncoder extends DemuxEncoder<DisconnectMessage> {

    @Override
    protected void encode(DisconnectMessage msg, ByteBuf out) {
        byte flags = Utils.encodeFlags(msg);
        out.writeByte(AbstractMessage.DISCONNECT << 4 | flags).writeByte(0);
    }
    
}
