package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import org.junit.Test;

public class IOTest {
	@Test
	//获得当前路径
	public void test1(){
		File file = new File("D:\\workspace\\project\\zjubisms\\WebRoot\\WEB-INF\\conf\\base_config.txt");
		System.out.println(file.getAbsolutePath());
		System.out.println(System.getProperty("user.dir"));
		try {
			InputStreamReader in = new InputStreamReader(new FileInputStream(file));
			int c;
			char[] cbuf = new char[1024];
			in.read(cbuf);
			while((c=in.read(cbuf))!=-1){
		      System.out.println(cbuf);
		    }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
public static void main(String[] args) {
		
		IOTest myFile = new IOTest();
		File filew = new File("D:/");
		if (!filew.exists()) {
			filew.mkdir();
		}
//		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
//		Date date = new Date();
//		String formateDate = format.format(date);
		String remotefilename = "logs";
		// String fileStr=remotefilename;
		try {
			myFile.creatTxtFile(remotefilename);
//			for (int i = 0; i < args.length; i++) {
				myFile.writeTxtFile(new String("利好啊".getBytes(),"GBK"));
//			}

			// myFile.writeTxtFile("显示的是追加的信息");
			String str = myFile.readDate();
			// System.out.println("*********\n" + str);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static String path = "d:/";
	private static String filenameTemp;

	/**
	 * 创建文件
	 * 
	 * @throws IOException
	 */
	public static boolean creatTxtFile(String name) throws IOException {
		boolean flag = false;
		filenameTemp = path + name + ".txt";
		File filename = new File(filenameTemp);
		if (!filename.exists()) {
			filename.createNewFile();
			flag = true;
		}
		return flag;
	}

	/**
	 * 写文件
	 * 
	 * @param newStr
	 *            新内容
	 * @throws IOException
	 */
	public static boolean writeTxtFile(String newStr) throws IOException {
		// 先读取原有文件内容，然后进行写入操作
		boolean flag = false;
		String filein = newStr + "\r\n";
		String temp = "";

		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;

		FileOutputStream fos = null;
		PrintWriter pw = null;
		try {
			// 文件路径
			File file = new File(filenameTemp);
			// 将文件读入输入流
			fis = new FileInputStream(file);
			isr = new InputStreamReader(fis);
			br = new BufferedReader(isr);
			StringBuffer buf = new StringBuffer();

			// 保存该文件原有的内容
			for (int j = 1; (temp = br.readLine()) != null; j++) {
				buf = buf.append(temp);
				// System.getProperty("line.separator")
				// 行与行之间的分隔符 相当于“\n”
				buf = buf.append(System.getProperty("line.separator"));
			}
			buf.append(filein);

			fos = new FileOutputStream(file);
			pw = new PrintWriter(fos);
			pw.write(buf.toString().toCharArray());
			pw.flush();
			flag = true;
		} catch (IOException e1) {
			// TODO 自动生成 catch 块
			throw e1;
		} finally {
			if (pw != null) {
				pw.close();
			}
			if (fos != null) {
				fos.close();
			}
			if (br != null) {
				br.close();
			}
			if (isr != null) {
				isr.close();
			}
			if (fis != null) {
				fis.close();
			}
		}
		return flag;
	}

	/**
	 * 读取数据
	 */
	public void readData1() {
		try {
			FileReader read = new FileReader(filenameTemp);
			BufferedReader br = new BufferedReader(read);
			String row;
			while ((row = br.readLine()) != null) {
				// System.out.println(row);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String readDate() {
		// 定义一个待返回的空字符串
		String strs = "";
		try {
			FileReader read = new FileReader(new File(filenameTemp));
			StringBuffer sb = new StringBuffer();
			char ch[] = new char[1024];
			int d = read.read(ch);
			while (d != -1) {
				String str = new String(ch, 0, d);
				sb.append(str);
				d = read.read(ch);
			}
			// System.out.print(sb.toString());
			String a = sb.toString().replaceAll("@@@@@", ",");
			strs = a.substring(0, a.length() - 1);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return strs;
	}
}
