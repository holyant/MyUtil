package security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

public class MD5Utils {
	public static void main(String[] args) {
		System.out.println(getMd5ByStr("hello"));

	}
	public static String getMd5ByStr(String source){
		//获得一个摘要加密工具,
		//该工具可以使用指定的加密算法来进行加密。
		String str = null;
		try {
			MessageDigest md = MessageDigest.getInstance("md5");
			//对指定的数据进行加密,加密之后生成的是一个字节数组
			byte[] buf = md.digest(source.getBytes());
			//字节数组不方便使用，所以将字节数据转换成一个字符串
			BASE64Encoder coder = new BASE64Encoder();
			str = coder.encode(buf);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return str;
	}
}
