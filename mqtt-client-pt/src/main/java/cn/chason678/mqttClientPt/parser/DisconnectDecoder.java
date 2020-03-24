package cn.chason678.mqttClientPt.parser;

import io.netty.buffer.ByteBuf;
import cn.chason678.mqttClientPt.proto.messages.DisconnectMessage;

import java.util.List;

/**
 *
 * @author andrea
 */
class DisconnectDecoder extends DemuxDecoder {

    @Override
    void decode(ByteBuf in, List<Object> out) throws Exception {
        //Common decoding part
        in.resetReaderIndex();
        DisconnectMessage message = new DisconnectMessage();
        if (!decodeCommonHeader(message, 0x00, in)) {
            in.resetReaderIndex();
            return;
        }
        out.add(message);
    }
    
}
