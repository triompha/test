package test.io;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;



public class SimpleNettyClient {

    private void connectWrite(){
       Bootstrap bootstrap = new Bootstrap().channel(NioSocketChannel.class).group(new NioEventLoopGroup());
       bootstrap.handler(new ChannelInboundHandlerAdapter(){
         @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
             String msgS = ((ByteBuf)msg).toString(CharsetUtil.UTF_8);
             System.out.print("get msg :" + msgS);
         }  
       });
      ChannelFuture future = bootstrap.connect("127.0.0.1", 9090) ;
       future.channel().writeAndFlush(Unpooled.copiedBuffer("xxxxxxxxxbbbb".getBytes())); 
       
    }
    public static void main(String[] args) {
        new SimpleNettyClient().connectWrite();
    }
}
