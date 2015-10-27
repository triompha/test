package test.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.net.ServerSocketFactory;

public class  BIOMultiThreadEchoServer{

    public static void main(String[] args) throws IOException {
        new BIOMultiThreadEchoServer().start(9999);
    }

    public void start(int port) throws IOException {
        ServerSocket serverSocket = ServerSocketFactory.getDefault().createServerSocket(port);
        int count = 0;

        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("accepted socket from " + socket.getRemoteSocketAddress() + ",local:"
                               + socket.getLocalPort());
            new ServiceThread("service-thread-" + count++, socket).start();
        }
    }

    public class ServiceThread extends Thread {

        private Socket socket;

        public ServiceThread(String name, Socket socket){
            super(name);
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                service(socket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void service(Socket socket) throws IOException {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            while (true) {
                String readered = reader.readLine();
                writer.write(readered);
                writer.newLine();
                writer.flush();
                System.out.println("readed from " + socket.getRemoteSocketAddress() + " , data:" + readered);
            }
        }
    }

}
