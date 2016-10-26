package test.coder;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import test.Utils;

/**
 * 序列化
 * @author liqqc
 */
public class JsonEncoder extends MessageToByteEncoder {
    private static final ObjectMapper MAPPER = Utils.OBJECT_MAPPER;
    private Class<?> clazz;

    public JsonEncoder(Class<?> class1) {
        this.clazz = class1;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
        if (clazz.isInstance(msg)) {
            byte[] msgByte = MAPPER.writeValueAsBytes(msg);
            out.writeByte(msgByte.length);
            out.writeBytes(msgByte);
            System.err.println(msgByte.toString());
        }

    }

}
