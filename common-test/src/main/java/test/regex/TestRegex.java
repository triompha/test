package test.regex;

public class TestRegex {

	
	public static void main(String[] args) {
		
		String s = "asdfsdfffwException";
		System.out.println(s.matches("((?!(.*SocketTimeout)|(.*Document)|(.*IllegalArgumentException))^).*Exception$"));
		
		
	}
}
