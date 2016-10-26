package rpc;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import io.netty.handler.codec.MessageToByteEncoder;

/**
*
* @author zhangwei_david
* @version $Id: RpcEncoder.java, v 0.1 2014年12月31日 下午8:55:25 zhangwei_david Exp $
*/
@SuppressWarnings("rawtypes")
public class RpcEncoder extends MessageToByteEncoder {
 private Class<?> genericClass;
 public RpcEncoder(Class<?> genericClass) {
   this.genericClass = genericClass;
 }
 @Override
 public void encode(ChannelHandlerContext ctx, Object in, ByteBuf out) throws Exception {
   if (genericClass.isInstance(in)) {
     byte[] data = SerializationUtil.serializer(in);
     out.writeInt(data.length);
     out.writeBytes(data);
   }
 }
}

