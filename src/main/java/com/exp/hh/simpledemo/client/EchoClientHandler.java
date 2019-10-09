package com.exp.hh.simpledemo.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

@ChannelHandler.Sharable
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {


    /**
     * 该方法是当客户端和服务端连接之后被调用
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        // 当客户端和服务端连接之后发送消息
        ctx.writeAndFlush(Unpooled.copiedBuffer("this is a message ",
                CharsetUtil.UTF_8));
    }
    // 获取服务端返回的消息
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        System.out.println("服务端返回消息为:"+ msg.toString(CharsetUtil.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // 发生异常时关闭channel

        cause.printStackTrace();
        ctx.close();
    }
}
