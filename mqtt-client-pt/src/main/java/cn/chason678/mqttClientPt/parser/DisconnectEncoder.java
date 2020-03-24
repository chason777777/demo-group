package cn.chason678.mqttClientPt.parser;

import io.netty.buffer.ByteBuf;
import cn.chason678.mqttClientPt.proto.messages.AbstractMessage;
import cn.chason678.mqttClientPt.proto.messages.DisconnectMessage;

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
