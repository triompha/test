package test;

import com.alibaba.fastjson.JSON;

public class TestFastJson {
	
	public static void main(String[] args) {
		AB ab = new AB();
		Object json = JSON.toJSON(ab);
		System.out.println(json);
	}
	
	
	public static class AB{
		String AbCd ="xxxxx";
		int ef  =1000;

		public String get_AbCd() {
			return AbCd;
		}
		public void setAbCd(String abCd) {
			AbCd = abCd;
		}
		public int getEf() {
			return ef;
		}
		public void setEf(int ef) {
			this.ef = ef;
		}

	}

}
