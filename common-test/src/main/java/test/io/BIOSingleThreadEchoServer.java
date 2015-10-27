package test.io;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.net.ServerSocketFactory;

public class BIOSingleThreadEchoServer  {

    public static void main(String[] args) throws IOException {
        new BIOSingleThreadEchoServer().start(9999);
    }

    public void start(int port) throws IOException {
        ServerSocket serverSocket = ServerSocketFactory.getDefault().createServerSocket(port);
        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("accepted socket from " + socket.getRemoteSocketAddress()+",local:"+socket.getLocalPort());
            service(socket);
        }
    }

    public void service(Socket socket) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    //����forѭ���ȴ����������߳�ֻ�ܴ�����һ��socket
        String readered = reader.readLine();
        writer.write(readered);
        writer.newLine();
        writer.flush();
        socket.close();
        System.out.println("readed from " + socket.getRemoteSocketAddress() + " , data:" + readered);
    }
}
