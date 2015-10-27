package test.io;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.CharsetUtil;

public class NettyEchoServer {

    public void start() {
        ServerBootstrap bootstrap = new ServerBootstrap().channel(NioServerSocketChannel.class).group(new NioEventLoopGroup());
        bootstrap.childHandler(new ChannelInitializer<Channel>() {

            @Override
            protected void initChannel(Channel ch) throws Exception {
                ChannelPipeline pipeline = ch.pipeline();
                pipeline.addLast(new ChannelInboundHandlerAdapter() {

                    @Override
                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                        String msgS = ((ByteBuf) msg).toString(CharsetUtil.UTF_8);
                        System.out.print("echo server got  msg :" + msgS);
                        ctx.writeAndFlush(msg);// ��������ͨ���ı�����bytebuf
                    }

                    @Override
                    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
                        ctx.flush();
                    }

                    @Override
                    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                        cause.printStackTrace();
                    }
                });
            }
        });

        ChannelFuture channelFuture = bootstrap.bind(9090);
        channelFuture.channel();
    }

    public static void main(String[] args) {
        new NettyEchoServer().start();
    }

}
