package test;

public class Thread2 {  
    public void m4t1() {  
         synchronized(this) {  
              int i = 5;  
              while( i-- > 0) {  
                   System.out.println(Thread.currentThread().getName() + " : " + i);  
                   try {  
                        Thread.sleep(500);  
                   } catch (InterruptedException ie) {  
                   }  
              }  
         }  
    }  
    public void m4t2() {  
         int i = 5;  
         while( i-- > 0) {  
              System.out.println(Thread.currentThread().getName() + " : " + i);  
              try {  
                   Thread.sleep(500);  
              } catch (InterruptedException ie) {  
              }  
         }  
    }  
  //修改Thread2.m4t2()方法：  
//    public void m4t2() {  
//         synchronized(this) {  
//              int i = 5;  
//              while( i-- > 0) {  
//                   System.out.println(Thread.currentThread().getName() + " : " + i);  
//                   try {  
//                        Thread.sleep(500);  
//                   } catch (InterruptedException ie) {  
//                   }  
//              }  
//         }
//
//    }
    public static void main(String[] args) {  
         final Thread2 myt2 = new Thread2();  
         Thread t1 = new Thread(  new Runnable() {  public void run() {  myt2.m4t1();  }  }, "t1"  );  
         Thread t2 = new Thread(  new Runnable() {  public void run() { myt2.m4t2();   }  }, "t2"  );  
         t1.start();  
         t2.start();  
    } 
}