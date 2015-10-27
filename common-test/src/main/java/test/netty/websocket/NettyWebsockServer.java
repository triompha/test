package test.netty.websocket;
import java.security.GeneralSecurityException;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;

import test.netty.spdy.SecureChatSslContextFactory;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.ssl.SslHandler;

public class NettyWebsockServer {

    public void start() throws GeneralSecurityException {
        
        final SSLContext context = SecureChatSslContextFactory.getServerContext();
        
        ServerBootstrap bootstrap = new ServerBootstrap().channel(NioServerSocketChannel.class).group(new NioEventLoopGroup());
        bootstrap.childHandler(new ChannelInitializer<Channel>() {

            @Override
            protected void initChannel(Channel ch) throws Exception {
                ChannelPipeline pipeline = ch.pipeline();
                SSLEngine engine = context.createSSLEngine();
                engine.setUseClientMode(false);
                pipeline.addLast("sslHandler",new SslHandler(engine));
                pipeline.addLast("httpCodec",new HttpServerCodec());
                pipeline.addLast("aggregator",new HttpObjectAggregator(65535));
                pipeline.addLast("websocket",new WebSocketServerProtocolHandler("/websocket"));
                pipeline.addLast("textFrame",new TextFrameHandler());
                pipeline.addLast("binaryFrame",new BinaryFrameHandler());
                pipeline.addLast("continuarionFrame",new ContinuationFrameHandler());
            }
        });

        ChannelFuture channelFuture = bootstrap.bind(9090);
        channelFuture.channel();
    }

    public static void main(String[] args) throws GeneralSecurityException {
        new NettyWebsockServer().start();
    }

}
