package cn.chason678.mqttClientPt.parser;

import io.netty.buffer.ByteBuf;
import cn.chason678.mqttClientPt.proto.messages.AbstractMessage;

/**
 *
 * @author andrea
 */
abstract class DemuxEncoder<T extends AbstractMessage> {
    abstract protected void encode(T msg, ByteBuf bb);
}
