package test.io.nio.compare;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
/****
 * 
 * 
 * 传统的Java 的IO，利用Socket建立服务器，接收客户端连接，一般都是为每一个连接建立一个线程，如果连接数巨大，那么服务器开销也将巨大。。
 * 
 * 
 * @author triompha
 *
 */
public class ServerMain {

	public static void main(String[] args) {

		//建立服务器socket，与客户端的socket进行通信。
		class SocketThread extends Thread {

			private Socket socket;
			private byte[] buf;
			private int len = 0;

			public SocketThread(Socket socket) {
				this.socket = socket;
				buf = new byte[1024];
			}

			@Override
			public void run() {
				try {
					DataInputStream dis = new DataInputStream(socket.getInputStream());
					DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

					// String command = dis.readUTF();
					len = dis.read(buf);
					String command = new String(buf, 0, len);

					System.out.println("command==" + command);

					String[] temp = command.split(" ");
					command = temp[0]; // 命令 是put还是get
					String filename = temp[1]; // 文件名

					File file = new File("/home/triompha/", filename);// 假设放在C盘
					//用户想get文件，也就是用户想下载文件，
					//那么对服务器文件进行检查，如果存在则传递给客户端的socket
					if (command.equals("get")) {
						if (!file.exists()) {
							// dos.writeUTF("notexists");
							dos.write("notexists".getBytes());
							dos.flush();
							System.out.println("没有这个文件，无法提供下载！");
							dis.close();
							dos.close();
							socket.close();
							return;
						}
						// dos.writeUTF("DownloadReady "+file.length());
						dos.write("准备下载".getBytes());
						dos.flush();

						System.out.println("正在接受文件下载...");
						DataInputStream fis = new DataInputStream(new BufferedInputStream(new FileInputStream(file)));

						while ((len = fis.read(buf)) != -1) {
							dos.write(buf, 0, len);
						}
						dos.flush();

						fis.close();
						System.out.println("文件传输完成");
					} else {   //用户想上传文件，服务器建立新文件，等待用户上传。
						// dos.writeUTF("UploadReady");
						dos.write("UploadReady".getBytes());
						dos.flush();

						System.out.println("正在接受文件上传...");
						DataOutputStream fileOut = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)));

						while ((len = dis.read(buf)) != -1) {
							fileOut.write(buf, 0, len);
						}
						System.out.println("上传完毕！");
						fileOut.close();
					}
					dis.close();
					dos.close();
					socket.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}

		System.out.println("等待客户端连接....");
		int index = 0;
		try {
			ServerSocket server = new ServerSocket(9527, 300); // 端口号9527
																// 允许最大连接数300
			while (true) {
				Socket socket = server.accept();  //阻塞，等待客户端链接。
				System.out.println("收到第" + (++index) + "个连接");
				new SocketThread(socket).start(); // 对每个连接创建一个线程
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}