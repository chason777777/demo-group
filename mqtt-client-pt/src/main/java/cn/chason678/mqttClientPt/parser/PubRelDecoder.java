package cn.chason678.mqttClientPt.parser;

import io.netty.buffer.ByteBuf;
import cn.chason678.mqttClientPt.proto.messages.MessageIDMessage;
import cn.chason678.mqttClientPt.proto.messages.PubRelMessage;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 *
 * @author andrea
 */
class PubRelDecoder extends DemuxDecoder {

//    @Override
//    protected MessageIDMessage createMessage() {
//        return new PubRelMessage();
//    }

    @Override
    void decode(ByteBuf in, List<Object> out) throws UnsupportedEncodingException {
        in.resetReaderIndex();
        //Common decoding part
        MessageIDMessage message = new PubRelMessage();
        if (!decodeCommonHeader(message, 0x02, in)) {
            in.resetReaderIndex();
            return;
        }

        //read  messageIDs
        message.setMessageID( in.readUnsignedShort());
        out.add(message);
    }

}

