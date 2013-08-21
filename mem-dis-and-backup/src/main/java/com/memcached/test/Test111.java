package com.memcached.test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Test111 {
    public static void main(String[] args) throws UnknownHostException, IOException {
        // SocketChannel socketChannel = SocketChannel.open();
        // socketChannel.connect(new InetSocketAddress("10.15.9.33", 11213));
        // Socket socket = socketChannel.socket();
        // ByteBuffer bufferIn = ByteBuffer.allocateDirect(15);
        // ByteBuffer bufferOut = ByteBuffer.allocateDirect(124);
        // bufferOut.put("version \r\n".getBytes());
        // bufferOut.flip();
        // int rst = socket.getChannel().write(bufferOut);
        // bufferOut.flip();
        // System.out.println(rst);
        // socket.getChannel().read(bufferIn);
        // bufferIn.flip();
        // byte[] bb = new byte[15];
        // bufferIn.get(bb);
        // System.out.println(new String(bb));
        // // while (true) {
        // // System.out.println(bufferIn.get());
        // // }
    }
}
