package cn.chason678.mqttClientPt.parser;

import io.netty.buffer.ByteBuf;
import io.vertx.core.buffer.Buffer;
import cn.chason678.mqttClientPt.proto.messages.AbstractMessage;
import cn.chason678.mqttClientPt.proto.messages.PublishMessage;

import java.util.Objects;

/**
 * @author andrea
 */
class PublishEncoder extends DemuxEncoder<PublishMessage> {

    @Override
    protected void encode(PublishMessage message, ByteBuf out) {
        if (message.getQos() == AbstractMessage.QOSType.RESERVED) {
            throw new IllegalArgumentException("Found a message with RESERVED Qos");
        }
        if (message.getTopicName() == null || message.getTopicName().isEmpty()) {
            throw new IllegalArgumentException("Found a message with empty or null topic name");
        }

        ByteBuf variableHeaderBuff = Buffer.buffer(2).getByteBuf();
        try {
            variableHeaderBuff.writeBytes(Utils.encodeString(message.getTopicName()));
            if (message.getQos() == AbstractMessage.QOSType.LEAST_ONE ||
                    message.getQos() == AbstractMessage.QOSType.EXACTLY_ONCE) {
                if (!Objects.nonNull(message.getMessageID())) {
                    throw new IllegalArgumentException("Found a message with QOS 1 or 2 and not MessageID setted");
                }
                variableHeaderBuff.writeShort(message.getMessageID());
            }

            variableHeaderBuff.writeBytes(message.getPayload().array());
            int variableHeaderSize = variableHeaderBuff.readableBytes();

            byte flags = Utils.encodeFlags(message);
            ByteBuf buff = Buffer.buffer(2 + variableHeaderSize).getByteBuf();
            buff.writeByte(AbstractMessage.PUBLISH << 4 | flags);
            buff.writeBytes(Utils.encodeRemainingLength(variableHeaderSize));
            buff.writeBytes(variableHeaderBuff);
            out.writeBytes(buff);
        } finally {
            variableHeaderBuff.release();
        }
    }

}
