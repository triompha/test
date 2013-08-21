package com.memcached.test;

public class NodeStatusItem {
    private int slabId;
    private long evicted_time;
    private long outofmemory;
    private long number;
    private long evicted_nonzero;
    private long age;
    private long tailrepairs;
    private long evicted;

    public long getEvicted() {
        return evicted;
    }

    public void setEvicted(long evicted) {
        this.evicted = evicted;
    }

    public int getSlabId() {
        return slabId;
    }

    public void setSlabId(int slabId) {
        this.slabId = slabId;
    }

    public long getEvicted_time() {
        return evicted_time;
    }

    public void setEvicted_time(long evictedTime) {
        evicted_time = evictedTime;
    }

    public long getOutofmemory() {
        return outofmemory;
    }

    public void setOutofmemory(long outofmemory) {
        this.outofmemory = outofmemory;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public long getEvicted_nonzero() {
        return evicted_nonzero;
    }

    public void setEvicted_nonzero(long evictedNonzero) {
        evicted_nonzero = evictedNonzero;
    }

    public long getAge() {
        return age;
    }

    public void setAge(long age) {
        this.age = age;
    }

    public long getTailrepairs() {
        return tailrepairs;
    }

    public void setTailrepairs(long tailrepairs) {
        this.tailrepairs = tailrepairs;
    }

    public NodeStatusItem(int slabId) {
        super();
        this.slabId = slabId;
    }

    @Override
    public String toString() {
        return "NodeStatusItem [age=" + age + ", evicted_nonzero=" + evicted_nonzero + ", evicted_time=" + evicted_time
                + ", number=" + number + ", outofmemory=" + outofmemory + ", slabId=" + slabId + ", tailrepairs="
                + tailrepairs + "]";
    }
}
