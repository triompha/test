package test;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ScanSource {
	
	public static void main(String[] args) {
		findArgsList(" protected void doBind(SocketAddress localAddress) ");
	}
	
	
	
	static void findArgsList(Object targetSrc) {
		/*
		 * 正则匹配串基本上是这样子分组情况(A(B(c(d))))
		 * 串是:(\\w+\\s+\\w+\\s*\\(((\\s*\\w+\\s*(\\[
		 * \\])*\\s*\\s+(\\[\\])*\\s*\\w+\\s*(\\[\\])*,?)+)\\)\\s*\\{) 比如public
		 * static void findArgsList(Object targetSrc,int []a){
		 * A是匹配整个方法定义行:这里是:static void findArgsList(Object targetSrc,int []a){
		 * B是匹配匹配参数列表:这里是Object targetSrc,int []a
		 * C是匹配一个参数,包括类型和类型名称和逗号:这里是Object targetSrc, D是匹配数组标识符:这里是[]
		 * 这个串有点bt,水平有限,只能这样
		 */
		Pattern p = Pattern
				.compile("(\\w+\\s+\\w+\\s*\\(((\\s*\\w+\\s*(\\[\\])*\\s*\\s+(\\[\\])*\\s*\\w+\\s*(\\[\\])*,?)+)\\)\\s*)");
		Matcher m = p.matcher((CharSequence) targetSrc);

		// locate the all methord defination
		while (m.find()) {
			String methodName = m.group(0);
			String methodArgName = m.group(1);
			String strArgs = m.group(2);
			String fourArgs = m.group(3);
			System.out.println(methodName + "\n" + methodArgName + "\n" + strArgs + "\n" + fourArgs + "\n");
		}

	}
	
}