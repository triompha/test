package test.jms.zookeeper.zkclient.barrier.download;

import java.io.File;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

public class MulThreadDownloader {
    public static void main(String[] args) throws Exception {
        String path = "http://119.75.217.56/img/0308_c3d9a429ad71647eb5f59623e7ec60a0.gif";
        int threadSize = 3; // 定义线程数
        new MulThreadDownloader().download(path, threadSize);
    }

    private void download(String path, int threadSize) throws Exception {
        URL downloadPath = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) downloadPath.openConnection();
        conn.setReadTimeout(5000);
        conn.setRequestMethod("GET");
        if (conn.getResponseCode() == 200) {
            int length = conn.getContentLength();// 获取文件长度
            File file = new File(getFileName(path));
            RandomAccessFile accessFile = new RandomAccessFile(file, "rws");// 生产本地文件
            accessFile.setLength(length);
            accessFile.close();
            // 计算每条线程负责下载的数据量
            int block = length % threadSize == 0 ? length / threadSize : length / threadSize + 1;
            for (int threadid = 0; threadid < threadSize; threadid++) {
                new DownloadThread(threadid, downloadPath, block, file).start();
            }
        }
    }

    // 负责下载操作
    private final class DownloadThread extends Thread {
        private final int threadid;
        private final URL downloadPath;
        private final int block;
        private final File file;

        public DownloadThread(int threadid, URL downloadPath, int block, File file) {
            this.threadid = threadid;
            this.downloadPath = downloadPath;
            this.block = block;
            this.file = file;
        }

        public void run() { // 重写run方法
            int startposition = threadid * block; // 从网络文件的什么位置开始下载
            int endposition = (threadid + 1) * block - 1; // 下载到网络的什么位置结束
            // Range: bytes = startposition - endposition
            try {
                RandomAccessFile accessFile = new RandomAccessFile(file, "rwd");
                accessFile.seek(startposition); // 移动指针到文件的某个位置
                HttpURLConnection conn = (HttpURLConnection) downloadPath.openConnection();
                conn.setReadTimeout(5000);
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Range", "bytes =" + startposition + "-" + endposition);// 设置头字段
                System.out.println("%%%%%%%%%%%%%%");
                InputStream inStream = conn.getInputStream();
                System.out.println("&&&&&&&&&&&&&&&&");
                byte[] buffer = new byte[1024];
                int len = 0;
                while ((len = inStream.read(buffer)) != -1) {
                    accessFile.write(buffer, 0, len);
                }
                accessFile.close();
                inStream.close();
                System.out.println("第" + (threadid + 1) + "线程下载完成");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取文件名称
     * 
     * @param path 下载路径
     * @return
     */
    private String getFileName(String path) {
        return path.substring(path.lastIndexOf("/") + 1);
    }
}