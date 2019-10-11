# netty-note

根据《netty实战》书籍进行`netty`的学习


书籍第三章大致讲了netty的组件：

`ChannelHandler` 和 `ChannelPipeline`
`EventLoop`
`ChannelFuture`

这里只是对这几个组件进行了大致描述并未详细说明。

学习过程中，现有几点疑问？

- `ChannelHandler`接口的有众多子接口，正如书籍所讲`ChannelHandlerInboundHandler`
是控制入站 `ChannelHandlerOutboundHandler` 是控制出站，然而在入站操作时即可进行写操作，那么
这里的出站接口是用来做什么呢？

`ChannelPipeline`调用链执行流程图： （。。。还未画）

