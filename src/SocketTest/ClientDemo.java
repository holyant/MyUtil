package SocketTest;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientDemo {

  public static void main(String[] args) throws UnknownHostException, IOException {
    ClientDemo colient = new ClientDemo();
    colient.open();
  }
  public void open() throws UnknownHostException, IOException{
//创建一个socket,标识到服务器的IP,端口号 
//     本机:localhost(127.0.0.1)  8000
//如果登录服务器失败,就会抛出异常    
    Socket socket = new Socket("localhost",8016);
//  in:代表从服务器端发送到客户端的流
    InputStream in = socket.getInputStream();
//  out:代表从客户端发送到服务器端的流  
    OutputStream out = socket.getOutputStream();
//  Reader线程负责将控制台读取的信息,发送到服务器端
    new Reader(out).start();
//  Writer线程负责将服务器端传输过来的信息,打印到控制台
    new Writer(in).start();
  }
  class Writer extends Thread{
    InputStream in;
    public Writer(InputStream in){
      this.in = in;
    }
    public void run(){
//Writer线程负责将服务器端传输过来的信息,打印到控制台
      try {
        int b;
        while((b=in.read())!=-1){
          System.out.write(b);
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
  class Reader extends Thread{
    OutputStream out;
    public Reader(OutputStream out){
      this.out = out;
    }
    public void run(){
//  Reader线程负责将控制台读取的信息,发送到服务器端      
      Scanner scanner = new Scanner(System.in);
      try {
        while(true){
          String str = scanner.nextLine();//读取控制台信息
        
          out.write(str.getBytes());//发送str到服务器端
          out.write('\n');
          out.flush();
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
  
}
