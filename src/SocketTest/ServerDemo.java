package SocketTest;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;

public class ServerDemo {
	 public static void main(String[] args) throws Exception {
		    ServerDemo serverDemo = new ServerDemo();
		    serverDemo.start();
		  }
		  public void start() throws IOException{
		//先创建ServerSocket,确定端口    
		    ServerSocket serverSocket = new ServerSocket(8016);
		    while(true){
		      System.out.println("等待用户的访问");
		//当客户端登录到服务器,会发送过来Socket,接收到Socket,启动
		//一个线程来处理客户端的请求
		//serverSocket继续等待下个客户端的登录      
		      Socket s = serverSocket.accept();
		      System.out.println("用户连接成功,创建服务的线程");
//		    创建服务的线程  
		      new Service(s).start();
		    }
		  }
		  class Service extends Thread{
		    Socket s  ;
		    public Service(Socket s){
		      this.s =s;
		    }
		    public void run(){
		      try {
//		      in :客户端传输到服务器端的信息
		        InputStream in = s.getInputStream();
//		      out:服务器端传输到客户端的信息
		        OutputStream out = s.getOutputStream();
		        out.write("欢迎您登录,你想要点什么?\n".getBytes());
		        out.flush();
		//用scanner来接收客户端传输过来的信息(String)"Scanner(in)"     
		        Scanner scanner = new Scanner(in);
		        while(true){
		          String str = scanner.nextLine().trim();
		          
		          System.out.println(Arrays.toString(str.getBytes()));
		          if(str.equals("liushishi")){
		            out.write("没有,别指望了\n".getBytes());
		            out.flush();
		          } else if(str.equals("chengxuyuan")){
		            out.write("给你,一片...\n".getBytes());
		            out.flush();
		            break;
		          } else {
		            out.write("再说一遍\n".getBytes());
		            out.flush();
		          }
		        }
		      } catch (Exception e) {
		        e.printStackTrace();
		      }
		    } 
		  }
}
