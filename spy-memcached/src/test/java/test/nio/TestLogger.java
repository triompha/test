package test.nio;

import net.spy.memcached.compat.SpyObject;

public class TestLogger extends SpyObject {
    public static void main(String[] args) {
        new TestLogger().printLog();
        new CopyOfTestLogger().printLog();
    }

    public void printLog() {
        getLogger().info("xxxxxxx");
    }
}
