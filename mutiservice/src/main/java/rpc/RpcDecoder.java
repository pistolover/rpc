package rpc;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

/**
 *RPC 解码
 * @author zhangwei_david
 * @version $Id: RpcDecoder.java, v 0.1 2014年12月31日 下午8:53:16 zhangwei_david Exp $
 */
public class RpcDecoder extends ByteToMessageDecoder {
  private Class<?> genericClass;
  public RpcDecoder(Class<?> genericClass) {
    this.genericClass = genericClass;
  }
  @Override
  public final void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out)
                                           throws Exception {
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
    Object obj = SerializationUtil.deserializer(data, genericClass);
    out.add(obj);
  }
}

