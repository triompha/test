package test.zookeeper;

import java.io.IOException;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.Watcher.Event.EventType;

public class Test2 implements Runnable, Watcher {
    ZooKeeper zk;
    byte[] prevData;

    public Test2(String s) throws IOException, KeeperException, InterruptedException {
        zk = new ZooKeeper("10.15.9.33", 3000, this);
    }

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        new Test2("aa").run();
    }

    public void run() {
        try {
            synchronized (this) {
                wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void process(WatchedEvent arg0) {
        System.out.println("process");
        if (arg0.getType() == EventType.NodeDeleted) {
            System.out.println("node has bean deleted");
        } else if (arg0.getType() == EventType.NodeDataChanged) {
            System.out.println(arg0.getPath());
            System.out.println("node data has bean change");
        }
        // 每次处理完信息后都要检测一下节点是否存在。
        try {
            zk.exists("*", true);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
