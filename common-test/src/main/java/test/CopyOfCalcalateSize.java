package test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import org.apache.commons.lang.SerializationUtils;

public class CopyOfCalcalateSize {
	
	//取每个resin是200条数据
	
	
	//1M容量仅能存储7W多个，将近8W个Long行列表数据
	//经计算得出每个Long型对象有 14 Byte
	//List 对象占据 122 Byte
	public static void main(String[] args) throws IOException {
		
		TreeMap<Integer, Integer> ax = new TreeMap<Integer, Integer>();
		ax.put(1, 1);
		ax.put(3, 3);
		ax.put(5, 5);
		ax.put(7, 7);
		ax.put(8, 8);
		ax.put(9, 9);
		System.out.println(ax.tailMap(3));
		System.out.println(ax.tailMap(8));
		int size =  ax.tailMap(3).size() - ax.tailMap(8).size();
		System.out.println(size);
	}

}
