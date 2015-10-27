package test.netty.spdy;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.CharsetUtil;

public class HttpRequestHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest msg) throws Exception {
        if (HttpHeaders.is100ContinueExpected(msg)) {  
            send100Continue(ctx);  
        }  
        FullHttpResponse response = new DefaultFullHttpResponse(msg.getProtocolVersion(), HttpResponseStatus.OK);
        response.content().writeBytes(getContent().getBytes(CharsetUtil.UTF_8));
        response.headers().set(HttpHeaders.Names.CONTENT_TYPE, "text/plain;charset=UTF-8");
        boolean keepAlive = HttpHeaders.isKeepAlive(msg);
        if (keepAlive) {
            response.headers().set(HttpHeaders.Names.CONTENT_LENGTH, response.content().readableBytes());
            response.headers().set(HttpHeaders.Names.CONNECTION, HttpHeaders.Values.KEEP_ALIVE);
        }
        ChannelFuture channelFuture = ctx.writeAndFlush(response);
        if (!keepAlive) {
            channelFuture.addListener(ChannelFutureListener.CLOSE);
        }
    }

    private static void send100Continue(ChannelHandlerContext ctx) {  
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,  
                HttpResponseStatus.CONTINUE);  
        ctx.writeAndFlush(response);  
    }  
    
    protected String getContent() {
        return "This content is transfer via HTTP\r\n";
    }
}
