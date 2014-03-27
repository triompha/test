package com.ucfgroup.zookeeper.test;


import org.apache.thrift.TException;

public class IndexNewsOperatorServicesImpl implements IndexNewsOperatorServices.Iface{

	public boolean deleteArtificiallyNews(int id) throws TException {
		// TODO Auto-generated method stub
		System.out.println("method success !!  id is :"+id);
		return true;
	}

	public boolean indexNews(NewsModel indexNews) throws TException {
		// TODO Auto-generated method stub
		System.out.println("method success !!  data  is :");
		System.out.println(indexNews);
		return true;
	}

}
