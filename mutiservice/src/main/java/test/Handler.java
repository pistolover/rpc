package test;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class Handler extends SimpleChannelInboundHandler<Request>{

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Request msg) throws Exception {
        // TODO Auto-generated method stub
        
    }

}
