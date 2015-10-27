package test.io;
import java.util.ArrayList;
import java.util.List;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

public class ObjectEchoClient {

    public void start(int port, final int size) {

        final List<Integer> firstObj = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            firstObj.add(Integer.valueOf(i));
        }
        Bootstrap bootstrap = new Bootstrap().group(new NioEventLoopGroup()).channel(NioSocketChannel.class);
        bootstrap.handler(new ChannelInitializer<Channel>() {

            @Override
            protected void initChannel(Channel ch) throws Exception {
                ChannelPipeline pipeline = ch.pipeline();
                pipeline.addLast(new ObjectEncoder());
                pipeline.addLast(new ObjectDecoder(ClassResolvers.cacheDisabled(null)));
                pipeline.addLast(new SimpleChannelInboundHandler<Object>() {

                    @Override
                    public void channelActive(ChannelHandlerContext ctx) throws Exception {
                        ctx.writeAndFlush(firstObj);
                    }

                    @Override
                    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
                        System.out.println("client got return msg:"+msg);
                        ctx.writeAndFlush(msg);
                    }
                });

            }
        });
        bootstrap.connect("127.0.0.1",port);
    }

    public static void main(String[] args) {
        new ObjectEchoClient().start(9002, 10);
    }
}
