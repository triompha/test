package test.netty.spdy;
import java.security.GeneralSecurityException;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;

import org.eclipse.jetty.npn.NextProtoNego;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.ssl.SslHandler;

public class NettySpdyServer {

    public void start() throws GeneralSecurityException {
        
        final SSLContext context = SecureChatSslContextFactory.getServerContext();
        
        ServerBootstrap bootstrap = new ServerBootstrap().channel(NioServerSocketChannel.class).group(new NioEventLoopGroup());
        bootstrap.childHandler(new ChannelInitializer<Channel>() {

            @Override
            protected void initChannel(Channel ch) throws Exception {
                ChannelPipeline pipeline = ch.pipeline();
                SSLEngine engine = context.createSSLEngine();
                engine.setUseClientMode(false);
                NextProtoNego.put(engine, new DefaultServerProvider());
                NextProtoNego.debug = true;
                pipeline.addLast("sslHandler",new SslHandler(engine));
                pipeline.addLast("chooser",new DefaultSpdyOrHttpChooser(1024*1024,1024*1024)); 
            }
        });

        ChannelFuture channelFuture = bootstrap.bind(9090);
        channelFuture.channel();
    }

    public static void main(String[] args) throws GeneralSecurityException {
        new NettySpdyServer().start();
    }

}
