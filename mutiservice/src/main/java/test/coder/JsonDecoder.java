package test.coder;

import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import test.Utils;

/**
 * 反序列化
 * @author liqqc
 */
public class JsonDecoder extends ByteToMessageDecoder {

    private static final ObjectMapper MAPPER = Utils.OBJECT_MAPPER;
    private Class<?> clazz;

    public JsonDecoder(Class<?> class1) {
        this.clazz = class1;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (in.readableBytes() < 4) {
            return;
        }
        in.markReaderIndex();
        int dataLength = in.readInt();
        if (dataLength < 0) {
            ctx.close();
        }
        if (in.readableBytes() < dataLength) {
            in.resetReaderIndex();
        }
        byte[] data = new byte[dataLength];
        in.readBytes(data);
        Object obj = MAPPER.readValue(data, clazz);
        out.add(obj);
    }
}
