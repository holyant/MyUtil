package common.utils;

import java.io.File;    
import java.io.FileInputStream;    
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.net.ftp.*;

public class FtpUtil {      
    private static FtpUtil ftpUtil = null; 
    private  FTPClient ftpClient;      
    private String currentPath;
    
	public static FtpUtil getInstance(){
		if(null==ftpUtil){
			ftpUtil = new FtpUtil();
		}
		return ftpUtil;
	}
    /**  
     *   
     * @param path 上传到ftp服务器哪个路径下     
     * @param addr 地址  
     * @param port 端口号  
     * @param username 用户名  
     * @param password 密码  
     * @return  
     * @throws Exception  
     */    
    private  boolean connect(String addr,int port,String username,String password) throws Exception {      
        boolean result = false;      
        ftpClient = new FTPClient();      
        int reply;      
        ftpClient.connect(addr,port);      
        ftpClient.login(username,password);      
        ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);      
        reply = ftpClient.getReplyCode();      
        if (!FTPReply.isPositiveCompletion(reply)) {      
        	ftpClient.disconnect();      
            return result;      
        }      
//        ftpClient.changeWorkingDirectory(path);        
        return !result;      
    }      
    /**  
     *   
     * @param file 上传的文件或文件夹  
     * @throws Exception  
     */    
    private boolean upload(String fileName) throws Exception{
    	return this.upload(new File(fileName));
    }
    private boolean upload(File file) throws Exception{      
    	boolean result = false;
    	try{
    		if(file.isDirectory()){           
            	ftpClient.makeDirectory(file.getName());                
            	ftpClient.changeWorkingDirectory(file.getName());      
                String[] files = file.list();
                for (int i = 0; i < files.length; i++) {      
                    File file1 = new File(file.getPath()+"\\"+files[i] );      
                    if(file1.isDirectory()){      
                        upload(file1);      
                        ftpClient.changeToParentDirectory();      
                    }else{                    
                        File file2 = new File(file.getPath()+"\\"+files[i]);      
                        FileInputStream input = new FileInputStream(file2);      
                        if(!ftpClient.storeFile(file2.getName(), input)) {
                        	return result;
                        }
                        input.close();                            
                    }                 
                }      
            }else{      
                File file3 = new File(file.getPath());      
                FileInputStream input = new FileInputStream(file3);      
                if(!ftpClient.storeFile(file3.getName(), input)){
                	return result;
                }
                input.close();   
            }      
    		 ftpClient.logout();
    	}catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftpClient.isConnected()) {
                try {
                	ftpClient.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
        
        return !result;
    }      
    /**
     * Description: 从FTP服务器下载文件
     */
    public boolean download(String path,String fileName) {
        boolean result = false;
        try {
            int reply;
            // 获取文件列表
            for (FTPFile ff : ftpClient.listFiles()) {
                if (ff.getName().equals(fileName)) {
                	this.downloadFileOrDirectory(path,ff);
                }
            }
            ftpClient.logout();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftpClient.isConnected()) {
                try {
                	ftpClient.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
        return !result;
    }
    
    public void downloadFileOrDirectory(String path,FTPFile file) throws IOException{
    	File localFile = new File(path);
    	if(!localFile.exists()){
    		localFile.mkdir();
    	}
    	
    	if(null!=file&&file.isDirectory()){
    		ftpClient.changeWorkingDirectory(file.getName());
    		downloadFileOrDirectory(path+"/"+file.getName(), null);
    	}else{
    		FTPFile[] ffs = ftpClient.listFiles();
    		for(FTPFile ff : ffs){
    			if(ff.isDirectory()){
    				downloadFileOrDirectory(path+"/"+ff.getName(), ff);
    			}else{
    				OutputStream is = new FileOutputStream(new File(file.getName()));
    	            ftpClient.retrieveFile(ff.getName(), is);
    	            is.close();
    			}
    		}
            
    	}
    }
    public void download1(String remotePath,String localPath,String fileName){
    	try {
    		this.changeWorkingDirectory(remotePath);
			FTPFile[] ff = this.ftpClient.listFiles();
			for(FTPFile file1 : ff){
				if(file1.getName().equals(fileName)){
					this.download2(file1);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    }

   private void changeWorkingDirectory(String remotePath) throws IOException {
	   this.currentPath = remotePath;
	   this.ftpClient.changeWorkingDirectory(remotePath);
	}
   
   private void download2(FTPFile ftpFile) {
	   try {
			if(ftpFile.isDirectory()){
				this.changeWorkingDirectory(this.currentPath+"/"+ftpFile.getName());
				FTPFile[] ftpFiles = ftpClient.listFiles();
				
			}
	   } catch (IOException e) {
			e.printStackTrace();
		}
	}
public static void main(String[] args) throws Exception{    
      FtpUtil ftpUtil = FtpUtil.getInstance();
      String remotePath = "/uniapp/users/pafop01/applications/BIP/official/ZJServer/share/";
      
      ftpUtil.connect("114.215.210.32", 21, "pwftp", "fanghao");    
//      ftpUtil.upload("d:\\test.txt");    
      ftpUtil.download("d:\\","000010000001.ppt");
   }    
}   













