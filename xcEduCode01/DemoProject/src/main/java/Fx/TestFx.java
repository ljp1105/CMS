package Fx;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

public class TestFx {
    public static void main(String[] args) throws MalformedURLException {
        String str="http://www.xuecheng.com/img/widget‐bannerA.jpg";

        String response = str.replaceAll("[[\\-]]","");
        String s = str.toUpperCase();
        System.out.println(s.toLowerCase().indexOf("-"));
        char[] chars = str.toCharArray();
        System.out.println(Arrays.toString(chars));
        String s1 = Arrays.toString(chars);
        int i = s1.indexOf(" \\-");
        System.out.println(response);

    }
}
