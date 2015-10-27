package test.io;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;

public class NIO2EchoServer {

    public static void main(String[] args) {
        new NIO2EchoServer().start(9999);
    }

    public void start(int port) {
        try {
            final AsynchronousServerSocketChannel serverSocketChannel = AsynchronousServerSocketChannel.open();
            InetSocketAddress inetSocketAddress = new InetSocketAddress(port);
            serverSocketChannel.bind(inetSocketAddress);
            System.out.println("NIO2 server start begin to listen port:" + port);
            final CountDownLatch countDownLatch = new CountDownLatch(1);
            serverSocketChannel.accept(null,new CompletionHandler<AsynchronousSocketChannel, Object>() {
                @Override
                public void completed(AsynchronousSocketChannel result, Object attachment) {
                    serverSocketChannel.accept(null,this);
                    ByteBuffer buffer = ByteBuffer.allocate(100);
                    result.read(buffer, buffer,new EchoCompletionHandler(result));
                }
                @Override
                public void failed(Throwable exc, Object attachment) {
                    try {
                        serverSocketChannel.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }finally{
                        countDownLatch.countDown();
                    }
                }
            });
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private final class EchoCompletionHandler implements CompletionHandler<Integer, ByteBuffer>{
       private final AsynchronousSocketChannel channel;
        public EchoCompletionHandler(AsynchronousSocketChannel channel){
           this.channel = channel; 
        }
        
        @Override
        public void completed(Integer result, ByteBuffer attachment) {
            attachment.flip();
//            System.out.println("server read :"+Charset.forName("UTF-8").decode(attachment).toString());
            channel.write(attachment, attachment, new CompletionHandler<Integer, ByteBuffer>() {
                @Override
                public void completed(Integer result, ByteBuffer attachment) {
                    if(attachment.hasRemaining()){
                        channel.write(attachment, attachment, this);
                    }else {
                        attachment.compact();
                        channel.read(attachment,attachment,EchoCompletionHandler.this);
                    }
                }

                @Override
                public void failed(Throwable exc, ByteBuffer attachment) {
                    try {
                        channel.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        @Override
        public void failed(Throwable exc, ByteBuffer attachment) {
            try {
                channel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
