package cn.chason678.mqtt.parser;

import cn.chason678.mqtt.moquette.proto.messages.AbstractMessage;
import io.netty.buffer.ByteBuf;

/**
 *
 * @author andrea
 */
abstract class DemuxEncoder<T extends AbstractMessage> {
    abstract protected void encode(T msg, ByteBuf bb);
}
