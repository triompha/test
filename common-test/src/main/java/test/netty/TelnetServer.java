package test.netty;

import java.net.InetAddress;
import java.util.Date;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class TelnetServer {

    public void start() {
        ServerBootstrap bootstrap = new ServerBootstrap().channel(NioServerSocketChannel.class).group(new NioEventLoopGroup());
        bootstrap.childHandler(new ChannelInitializer<Channel>() {

            @Override
            protected void initChannel(Channel ch) throws Exception {
                ChannelPipeline pipeline = ch.pipeline();
                pipeline.addLast(new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()), new StringDecoder(),
                                 new StringEncoder());
                pipeline.addLast(new SimpleChannelInboundHandler<String>() {

                    @Override
                    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
                        String response;
                        boolean isClosed = false;
                        if (msg.isEmpty()) {
                            response = "please type something ! \r\n";
                        } else if ("bye".equals(msg)) {
                            response = "have a good day!\r\n";
                            isClosed = true;
                        } else {
                            response = "did you say :" + msg + "  ?\r\n";
                        }
                        ctx.writeAndFlush(response).addListener(isClosed ? ChannelFutureListener.CLOSE : ChannelFutureListener.CLOSE_ON_FAILURE);
                    }

                    @Override
                    public void channelActive(ChannelHandlerContext ctx) throws Exception {
                        ctx.write("you are welcome to" + InetAddress.getLocalHost().getHostAddress() + "!\r\n");
                        ctx.writeAndFlush("it is " + new Date() + " now!\r\n");
                    }
                });
            }

        });
        bootstrap.bind(9001);

    }

    public static void main(String[] args) {
//        new TelnetServer().start();
        System.out.println("wukong-app".hashCode()%256);
    }
}
