package test;
import java.io.UnsupportedEncodingException;


public class EncodingTest {

    public static void main(String[] args) throws UnsupportedEncodingException {
        String s = "c";
        char c = 'c';
        System.out.println(s.getBytes("UTF-8").length);
        

    }
}
