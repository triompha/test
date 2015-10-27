package test.io;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.AbstractSelector;
import java.nio.channels.spi.SelectorProvider;
import java.util.Iterator;
import java.util.Set;

import sun.nio.ch.SelectorProviderImpl;


public class NIOEchoServer {

    public static void main(String[] args) {
        new NIOEchoServer().start(9999);
    }

    public void start(int port) {
        try {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            ServerSocket serverSocket = serverSocketChannel.socket();
            InetSocketAddress inetSocketAddress = new InetSocketAddress(port);
            serverSocket.bind(inetSocketAddress, 100);
            System.out.println("NIO server start begin to listen port:" + port);
            Selector selector = Selector.open();
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            while (true) {
                selector.select();
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectedKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    iterator.remove();
                    if (key.isAcceptable()) {
                        ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
                        SocketChannel socketChannel = serverChannel.accept();
                        System.out.println("Accept connection from :" + socketChannel);
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE,
                                               ByteBuffer.allocate(100));
                    }
                    if (key.isReadable()) {
                        SocketChannel client = (SocketChannel) key.channel();
                        ByteBuffer buffer = (ByteBuffer) key.attachment();
                        client.read(buffer);
                    }
                    if (key.isWritable()) {
                        SocketChannel client = (SocketChannel) key.channel();
                        ByteBuffer clientInfo = (ByteBuffer) key.attachment();
                        clientInfo.flip();
                        client.write(clientInfo);
                        clientInfo.compact();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
