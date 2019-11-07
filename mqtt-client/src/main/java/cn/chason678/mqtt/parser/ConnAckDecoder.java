package cn.chason678.mqtt.parser;

import cn.chason678.mqtt.moquette.proto.messages.ConnAckMessage;
import io.netty.buffer.ByteBuf;

import java.util.List;

/**
 *
 * @author andrea
 */
class ConnAckDecoder extends DemuxDecoder {

    @Override
    void decode(ByteBuf in, List<Object> out) throws Exception {
        in.resetReaderIndex();
        //Common decoding part
        ConnAckMessage message = new ConnAckMessage();
        if (!decodeCommonHeader(message, 0x00, in)) {
            in.resetReaderIndex();
            return;
        }
        //skip reserved byte
        in.skipBytes(1);
        
        //read  return code
        message.setReturnCode(in.readByte());
        out.add(message);
    }
    
}
