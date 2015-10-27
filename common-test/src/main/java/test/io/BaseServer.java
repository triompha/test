package test.io;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class BaseServer {

    private ChannelInitializer<Channel> initializer;
    private int                         port;

    public BaseServer(ChannelInitializer<Channel> initializer, int port){
        super();
        this.initializer = initializer;
        this.port = port;
    }

    public void start() {
        ServerBootstrap serverBootstrap = new ServerBootstrap().group(new NioEventLoopGroup(1), new NioEventLoopGroup());
        serverBootstrap.channel(NioServerSocketChannel.class);
        serverBootstrap.childHandler(initializer);
        serverBootstrap.bind(port);
    }

}
