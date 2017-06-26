package test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class EncoderTest {
	//编码解码测试
	public static void main(String[] args) {
		String str = "你好啊";
		try {
//			System.out.println(URLEncoder.encode(str,"UTF-8"));
			String strEn = URLEncoder.encode(str,"UTF-8");
			System.out.println(strEn);
			System.out.println(URLDecoder.decode(strEn,"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
