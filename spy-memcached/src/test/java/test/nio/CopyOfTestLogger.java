package test.nio;

import net.spy.memcached.compat.SpyObject;

public class CopyOfTestLogger extends SpyObject {
    public static void main(String[] args) {
        new CopyOfTestLogger().printLog();
    }

    public void printLog() {
        getLogger().info("xxxxxxx CopyOfTestLogger");
    }
}
