package com.exp.hh.simpledemo.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

@ChannelHandler.Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = (ByteBuf) msg;
        System.out.println(
                "服务端接收到消息" + in.toString(CharsetUtil.UTF_8));

        // 向消息发送者写消息 此处将接收到的消息返回给消息发送者  注意此处不会将消息冲刷出站
        String out = "收到客户端的消息，将返回收到的消息" + ((ByteBuf) msg).toString(CharsetUtil.UTF_8);
        ByteBuf buf = Unpooled.copiedBuffer(out, CharsetUtil.UTF_8);
        ctx.write(buf);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)
                .addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // 发生异常的处理

        cause.printStackTrace();
        ctx.close();
    }
}
