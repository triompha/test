package com.ucfgroup.zookeeper.common;

import java.util.List;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.IZkStateListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkNoNodeException;
import org.I0Itec.zkclient.exception.ZkNodeExistsException;
import org.apache.zookeeper.Watcher.Event.KeeperState;


public class ZKClient {

	private final ZkClient client;
	private volatile KeeperState state = KeeperState.SyncConnected;

	public static transient ZKClient instance;


	public static ZKClient getInstance(){
		if(instance==null){
			synchronized (ZKClient.class) {
				if(instance==null){
					instance = new ZKClient(Constant.ZOOKEEPER_SERVERS);
				}
			}
		}
		return instance;
	}

	private ZKClient(String address){

		client = new ZkClient(address);
	}

	public void subscribeStateChanges(IZkStateListener listener){
		client.subscribeStateChanges(listener);
	}

	public void subscribeChildChanges(String path , IZkChildListener listener){
		try {
			client.subscribeChildChanges(path, listener);
		} catch (ZkNodeExistsException e) {
		}

	}

	public void subscribeDataChanges(String path , IZkDataListener listener){

		try {
			client.subscribeDataChanges(path, listener);
		} catch (ZkNodeExistsException e) {
		}
	}

	public List<String> getChildren(String path){
		try {
			return client.getChildren(path);
		} catch (ZkNodeExistsException e) {
		}
		return null;
	}


	public void createPersistent(String path) {
		try {
			client.createPersistent(path, true);
		} catch (ZkNodeExistsException e) {
		}
	}
	public void createPersistent(String path , Object data) {
		try {
			client.createPersistent(path, data);
		} catch (ZkNodeExistsException e) {
		}
	}

	public void createEphemeral(String path) {
		try {
			client.createEphemeral(path);
		} catch (ZkNodeExistsException e) {
		}
	}

	public void delete(String path) {
		try {
			client.delete(path);
		} catch (ZkNoNodeException e) {
		}
	}

	public <T> T readData(String path){
		try {
			return client.readData(path);
		} catch (ZkNoNodeException e) {
		}
		return null;
	}

	public void writeData(String path , Object object){
		try {
			client.writeData(path, object);
		} catch (ZkNoNodeException e) {
		}
	}


	public boolean exist(String path){
		try {
			return client.exists(path);
		} catch (ZkNoNodeException e) {
		}
		return false;
	}


	public boolean isConnected() {
		return state == KeeperState.SyncConnected;
	}

	public void doClose() {
		client.close();
	}

}
