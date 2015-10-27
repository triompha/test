package test.io;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

public class ObjectEchoServer {

    public static void main(String[] args) {
        new BaseServer(new ChannelInitializer<Channel>() {

            @Override
            protected void initChannel(Channel ch) throws Exception {
                ChannelPipeline pipeline = ch.pipeline();
                pipeline.addLast(new ObjectEncoder(), new ObjectDecoder(ClassResolvers.cacheDisabled(null)));
                pipeline.addLast(new SimpleChannelInboundHandler<Object>() {

                    @Override
                    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
                        System.out.println("got object : "+ msg);
                        ctx.writeAndFlush(msg);
                    }
                });
            }
        }, 9002).start();
    }
}
