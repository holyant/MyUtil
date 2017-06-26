package test;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

public class ZipTest {
	public static void main(String[] args) {
		File file = new File("D:\\te.txt");
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(new FileOutputStream(file));
			// 写字符串
			writer.print("中华人民共和国万岁");
			writer.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				// 关闭输出文件流
				writer.close();
			}
		}
		try {
//			File[] file1 = new File("D:\\").listFiles();
			File zipFile = new File("D:\\", "清单(全省).zip");
			ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(
					zipFile));// 压缩包
			BufferedInputStream bis = new BufferedInputStream(
					new FileInputStream(new File("D:\\te.txt")));
			byte[] buf = new byte[1024];
			int len;
			ZipEntry ze = new ZipEntry("te.txt");// 这是压缩包名里的文件名
			zos.setEncoding("UTF-8");
			zos.putNextEntry(ze);
			while ((len = bis.read(buf)) != -1) {
				zos.write(buf, 0, len);
				zos.flush();
			}
			bis.close();
			bis = null;
			zos.close(); 
			zos = null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
