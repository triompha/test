package test.netty;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.handler.codec.serialization.ObjectDecoder;
import org.jboss.netty.handler.codec.serialization.ObjectEncoder;

public class Client {
    public static void main(String[] args) {
        ClientBootstrap client = new ClientBootstrap(new NioClientSocketChannelFactory(Executors.newCachedThreadPool(),
                Executors.newCachedThreadPool()));
        client.setPipelineFactory(new ChannelPipelineFactory() {
            public ChannelPipeline getPipeline() throws Exception {
                ChannelPipeline pipeLine = Channels.pipeline(new SimpleChannelUpstreamHandler() {
                    @Override
                    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) {
                        // 创建连接发送请求
                        Request r = new Request();
                        r.setVersion("1.0.0"); // 设置版本
                        r.setService(IAnimalService.class); // 设置服务类型
                        r.setMethod("getMoneyName"); // 调用服务方法名称
                        r.setParas(null); // 参数
                        e.getChannel().write(r);
                    }

                    @Override
                    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
                        // 监听消息到达
                        System.out.println(e.getMessage().toString());
                    }
                });
                pipeLine.addFirst("encoder", new ObjectEncoder()); // 对象编码器
                pipeLine.addFirst("decoder", new ObjectDecoder()); // 对象解码器
                return pipeLine;
            }
        });
        client.setOption("tcpNoDelay", true);
        client.setOption("keepAlive", true);
        ChannelFuture future = client.connect(new InetSocketAddress("127.0.0.1", 9990));
        future.getChannel().getCloseFuture().awaitUninterruptibly();
        client.releaseExternalResources(); // 释放外部资源
    }
}
