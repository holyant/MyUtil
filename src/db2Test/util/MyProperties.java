package db2Test.util;
import java.util.*;
import java.io.*;
/**
 * 工具类
 * @author fangyh
 *
 */
public class MyProperties {
	/**
	 * 通过属性文件的路径和名字返回Properties对象
	 */
	public static Properties getProperties(String filename)
			throws IOException{
		//构建properties对象
		Properties prop=new Properties();
		//通过类对象的getResourceAsStream()方法
		//获取类路径下某一文件所对应的InputStream
		InputStream inputStream=
			MyProperties.class.getResourceAsStream(filename);
		//通过load方法加载属性文件
		prop.load(inputStream);
		return prop;
	}
	public static void main(String[] args) {
		java.net.URL url = MyProperties.class.getResource("/db.properties");
		System.out.println(url.toString());
	}
}






