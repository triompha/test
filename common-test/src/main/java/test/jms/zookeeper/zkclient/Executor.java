package test.jms.zookeeper.zkclient;

/**
 * A simple example program to use DataMonitor to start and
 * stop executables based on a znode. The program watches the
 * specified znode and saves the data that corresponds to the
 * znode in the filesystem. It also starts the specified program
 * with the specified arguments when the znode exists and kills
 * the program if the znode goes away.
 */
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

public class Executor implements Watcher, Runnable, DataMonitor.DataMonitorListener {
    String znode;
    DataMonitor dm;
    ZooKeeper zk;
    String filename;
    String exec[];
    Process child;

    public Executor(String hostPort, String znode, String filename, String exec[]) throws KeeperException, IOException {
        this.filename = filename;
        this.exec = exec;
        zk = new ZooKeeper(hostPort, 3000, this);
        dm = new DataMonitor(zk, znode, null, this);
    }

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        // if (args.length < 4) {
        // System.err.println("USAGE: Executor hostPort znode filename program [args ...]");
        // System.exit(2);
        // }
        String hostPort = "10.15.9.33";
        String znode = "/sohu/test1";
        String filename = "D:\\out.file";
        String exec[] = new String[] { "explorer.exe" };
        // System.arraycopy(args, 3, exec, 0, exec.length);
        try {
            new Executor(hostPort, znode, filename, exec).run();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Runtime.getRuntime().exec("explorer.exe");
    }

    /***************************************************************************
     * We do process any events ourselves, we just need to forward them on.
     * 
     * @see org.apache.zookeeper.Watcher#process(org.apache.zookeeper.proto.WatcherEvent)
     */
    // 继承自watcher
    // new
    // zookeeper时将自己穿进去，连接或数据有任何变动的时候都出动此方法，但，出动之后需要调用zookeeper.exit()检测数据的有效性。
    public void process(WatchedEvent event) {
        dm.process(event);
    }

    public void run() {
        try {
            synchronized (this) {
                while (!dm.dead) {
                    wait();
                }
            }
        } catch (InterruptedException e) {
        }
    }

    public void closing(int rc) {
        synchronized (this) {
            notifyAll();
        }
    }

    // 子进程的数据记录
    static class StreamWriter extends Thread {
        OutputStream os;
        InputStream is;

        StreamWriter(InputStream is, OutputStream os) {
            this.is = is;
            this.os = os;
            start();
        }

        public void run() {
            byte b[] = new byte[80];
            int rc;
            try {
                while ((rc = is.read(b)) > 0) {
                    os.write(b, 0, rc);
                }
            } catch (IOException e) {
            }
        }
    }

    // 处理数据更改的逻辑
    public void exists(byte[] data) {
        if (data == null) { // 数据数据为NULL，标示此节点已经被删除。
            if (child != null) {
                System.out.println("Killing process");
                child.destroy();
                try {
                    child.waitFor();
                } catch (InterruptedException e) {
                }
            }
            child = null;
        } else { // 不为NULL，节点数据更新
            if (child != null) { // 子进程仍在进行。
                System.out.println("Stopping child");
                child.destroy();
                try {
                    child.waitFor();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 向文件内输出新值
            try {
                FileOutputStream fos = new FileOutputStream(filename);
                fos.write(data);
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // 打开子进程
            try {
                System.out.println("Starting child");
                child = Runtime.getRuntime().exec(exec);
                new StreamWriter(child.getInputStream(), System.out);
                // new StreamWriter(child.getErrorStream(), System.err);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}