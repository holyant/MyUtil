package common.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class SystemInfoUtils {
	public static void main(String[] args) throws UnknownHostException {
		  
        System.out.println(getSysAccount());
	}
	//获得本机ip地址
	public static String getLocalHost(){
		String ipAddress = null;
		try {
			InetAddress address = InetAddress.getLocalHost();
			ipAddress = address.getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}    
		return ipAddress;
	}
	//获得系统账号
	public static String getSysAccount(){
		String osUser=System.getProperty("user.name");
		return osUser;
	}
	
}
