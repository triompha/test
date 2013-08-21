package com.netty.test;

import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.serialization.ObjectDecoder;
import org.jboss.netty.handler.codec.serialization.ObjectEncoder;

public class Server {
    public static void main(String[] args) {
        final int port = 9990;
        ServerBootstrap bootstrap = new ServerBootstrap(new NioServerSocketChannelFactory(Executors
                .newCachedThreadPool(), Executors.newCachedThreadPool()));
        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            public ChannelPipeline getPipeline() throws Exception {
                ChannelPipeline pipeLine = Channels.pipeline(new SimpleChannelUpstreamHandler() {
                    @Override
                    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
                        // 监听消息到达
                        Request obj = (Request) e.getMessage();
                        if (obj.getService().equals(IAnimalService.class)) {
                            Method targetMethod = obj.getService().getMethod(obj.getMethod(), new Class[0]);
                            Object result = targetMethod.invoke(new AnimalServiceImp(), obj.getParas());
                            e.getChannel().write(result);
                        }
                    }
                });
                pipeLine.addFirst("encoder", new ObjectEncoder()); // 对象编码器
                pipeLine.addFirst("decoder", new ObjectDecoder()); // 对象解码器
                return pipeLine;
            }
        });
        bootstrap.bind(new InetSocketAddress(port)); // 启动服务并绑定端口
    }
}
