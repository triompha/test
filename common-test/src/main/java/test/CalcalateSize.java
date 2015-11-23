package test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.SerializationUtils;

public class CalcalateSize {
	
	//取每个resin是200条数据
	
	
	//1M容量仅能存储7W多个，将近8W个Long行列表数据
	//经计算得出每个Long型对象有 14 Byte
	//List 对象占据 122 Byte
	public static void main(String[] args) throws IOException {
		System.out.println(System.currentTimeMillis());
		
		
		List<Long> uids = new ArrayList<Long>();
		for(long i=0;i<3;i++){
			uids.add(i);
		}
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(SerializationUtils.serialize((Serializable) uids));
		int size =baos.size();
		System.out.println(size);
		
		
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject(uids);
		size =baos.size();
		
		System.out.println(size);
		
		
		
		
	}

}
