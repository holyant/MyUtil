package activityRecords.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

/**
 *<p>Title: FileHelp.java </p>
 * <p>Description:
 *		鏂囦欢澶勭悊甯姪绫?
 * </p>
 * <p>Copyright: Copyright (c) Asiainfo 2010</p>
 * <p>Author: Gong Jingcai</p>
 * <p>E-mail: gongjc@asiainfo.com</p>
 * <p>MSN: willbekiller@hotmail.com</p>
 * <p>Version 1.0</p>
 * <p>Apr 19, 2010 8:19:56 PM</p>
 * </p>
 */

 
public class FileHelp
{
	/**
     * 灏嗕笂浼犵殑鏂囦欢copy鍒版寚瀹氱殑璺緞涓?
     * @param String path , 灏嗕笂浼犵殑鏂囦欢杞埌鎿嶄綔绯荤粺鎸囧畾鐨勭粷瀵硅矾寰?
     * @param String fileFileName , 涓婁紶鐨勬枃浠跺師濮嬫枃浠跺悕
     * @param java.io.File file , 涓婁紶鐨勬枃浠?
     * @return String newFileName , 鍙﹀瓨鐨勬柊鏂囦欢鍚嶇О
     */
	public String copyFile(String path,String fileFileName,File file) throws Exception
	{
		String newFileName = null;

		// 寰楀埌褰撳墠鏃堕棿鑷?1970骞?1鏈?1鏃?0鏃?0鍒?0绉掑紑濮嬫祦閫濈殑姣鏁帮紝灏嗚繖涓绉掓暟浣滀负涓婁紶鏂囦欢鏂扮殑鏂囦欢鍚嶃??
		long now = new Date().getTime();

		File dir = new File(path);
		// 濡傛灉杩欎釜鐩綍涓嶅瓨鍦紝鍒欏垱寤哄畠銆?
		if (!dir.exists())
			dir.mkdir();

		int index = fileFileName.lastIndexOf('.');
		// 鍒ゆ柇涓婁紶鏂囦欢鍚嶆槸鍚︽湁鎵╁睍鍚?
		if (index != -1)
			newFileName = now + fileFileName.substring(index);
		else
			newFileName = Long.toString(now);

		BufferedOutputStream bos = null;
		BufferedInputStream bis = null;
		// 璇诲彇淇濆瓨鍦ㄤ复鏃剁洰褰曚笅鐨勪笂浼犳枃浠讹紝鍐欏叆鍒版柊鐨勬枃浠朵腑銆?
		try
		{
			FileInputStream fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);

			FileOutputStream fos = new FileOutputStream(new File(dir,newFileName));
			bos = new BufferedOutputStream(fos);
			
			int fileLength = (int)file.length()+1024;
			byte[] buf = new byte[fileLength];

			int len = -1;
			while ((len = bis.read(buf)) != -1)
			{
				bos.write(buf, 0, len);
			}
		} finally
		{
			try
			{
				if (null != bis)
					bis.close();
			} catch (IOException e)
			{
				e.printStackTrace();
			}

			try
			{
				if (null != bos)
					bos.close();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		return newFileName;
	}
	
	/**
     * 灏嗘寚瀹氱殑鍐呭鍐欏埌鎸囧畾鐨勬枃浠?
     * @param String  , 瑕佸啓鍏ョ殑鏂囦欢鍚?
	 * @param List<String> , 瑕佸啓鍏ョ殑鏂囦欢鍐呭鍒楄〃
     */
	public void writerFile(String fileName,List<String> lines)
	{
		BufferedWriter writer = null;
		try{
			writer = new BufferedWriter(new FileWriter(fileName,true));
			for(int i=0;i<lines.size();i++)
			{
				writer.write(lines.get(i));
				writer.write("\r\n");		//榛樿涓篧indows鎿嶄綔绯荤粺鎹㈣绗?
			}
			writer.close();
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
     * 濡傛灉鎸囧畾鐨勭洰褰曚笉瀛樺湪锛屽垯鍒涘缓
     * @param String  , 瑕佸垱寤虹殑鐩綍
     */
	public void createPath(String path)
	{
		File f = new File(path);  
		if(!f.exists())
		{
			f.mkdirs();
		}		
	}
	
	public String copyFileZgx(String path,String fileFileName,File file,String newFileName) throws Exception
	{ 
		File dir = new File(path);
		// 如果这个目录不存在，则创建它。
		if (!dir.exists()) 
			dir.mkdir();  
		int index = fileFileName.lastIndexOf('.');
		// 判断上传文件名是否有扩展名
//		if (index != -1)
//			newFileName = now + fileFileName.substring(index);
//		else
//			newFileName = Long.toString(now); 
		BufferedOutputStream bos = null;
		BufferedInputStream bis = null;
		// 读取保存在临时目录下的上传文件，写入到新的文件中。
		try
		{
			FileInputStream fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);

			FileOutputStream fos = new FileOutputStream(new File(dir,newFileName));
			bos = new BufferedOutputStream(fos);
			
			int fileLength = (int)file.length()+1024;
			byte[] buf = new byte[fileLength];

			int len = -1;
			while ((len = bis.read(buf)) != -1)
			{
				bos.write(buf, 0, len);
			}
		} finally
		{
			try
			{
				if (null != bis)
					bis.close();
			} catch (IOException e)
			{
				e.printStackTrace();
			}

			try
			{
				if (null != bos)
					bos.close();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		return newFileName;
	}
}
