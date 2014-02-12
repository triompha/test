package test.BlockingQueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class TestBlockingQueue {
	public static void main(String[] args) {
		BlockingQueue<String> blockingQueue = new LinkedBlockingDeque<String>();
		blockingQueue.add("asdf");
		blockingQueue.add("asdf");
		blockingQueue.add("asdf");
		blockingQueue.add("asdf");
		blockingQueue.add("asdf");
		
		
		blockingQueue.offer("asdf");
		
		
	}
}
