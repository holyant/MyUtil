package ExtFileTest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import org.junit.Test;

public class TxtTest {
	public static String src = "d:/11.txt";
	public static void main(String[] args) throws Exception {
		String str = new String("这是一段测试语句好啊 jkkk");
		File file = new File(src);
		OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(src),"UTF-8");  
		TxtTest t = new TxtTest();
		//b[0]==-17&&b[1]==-69&&b[2]==-65
		out.write("这是一段测试语句好啊 jkkk");
		int fg = 0x1b;
		int hh = 0x0a;
		
		out.write(hh);
		out.write("这");
		out.write(fg);
		out.write("那");
		out.flush();
		out.close();
	}
	@Test
	public void read() throws Exception{
//		File file = new File(src);
//		FileInputStream in = new FileInputStream(file);
//		byte[] buff = new byte[1024];
//		int count;
//		while((count=in.read(buff))!=-1){
//	      for(int i=0;i<buff.length;i++){
//	    	  System.out.print(buff[i]);
//	      }
//	    }
		java.io.File f=new java.io.File(src);  
		try{  
		 java.io.InputStream ios=new java.io.FileInputStream(f);  
		byte[] b=new byte[3];  
		 ios.read(b);  
		 ios.close();  
		if(b[0]==-17&&b[1]==-69&&b[2]==-65)  
		    System.out.println(f.getName()+"编码为UTF-8");  
		else System.out.println(f.getName()+"可能是GBK");  
		}catch(Exception e){  
		  e.printStackTrace();  
		}  
	}
		/**  
	     * 字符串转换成十六进制字符串 
	     * @param String str 待转换的ASCII字符串 
	     * @return String 每个Byte之间空格分隔，如: [61 6C 6B] 
	     */    
	    public static String str2HexStr(String str)  
	    {    
	  
	        char[] chars = "0123456789ABCDEF".toCharArray();    
	        StringBuilder sb = new StringBuilder("");  
	        byte[] bs = str.getBytes();    
	        int bit;    
	          
	        for (int i = 0; i < bs.length; i++)  
	        {    
	            bit = (bs[i] & 0x0f0) >> 4;    
	            sb.append(chars[bit]);    
	            bit = bs[i] & 0x0f;    
	            sb.append(chars[bit]);  
	            sb.append(' ');  
	        }    
	        return sb.toString().trim();    
    }  
	    /**  
	     * 十六进制转换字符串 
	     * @param String str Byte字符串(Byte之间无分隔符 如:[616C6B]) 
	     * @return String 对应的字符串 
	     */    
	    public static String hexStr2Str(String hexStr)  
	    {    
	        String str = "0123456789ABCDEF";    
	        char[] hexs = hexStr.toCharArray();    
	        byte[] bytes = new byte[hexStr.length() / 2];    
	        int n;    
	  
	        for (int i = 0; i < bytes.length; i++)  
	        {    
	            n = str.indexOf(hexs[2 * i]) * 16;    
	            n += str.indexOf(hexs[2 * i + 1]);    
	            bytes[i] = (byte) (n & 0xff);    
	        }    
	        return new String(bytes);    
	    }  
}
