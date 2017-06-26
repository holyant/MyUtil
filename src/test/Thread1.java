package test;

public class Thread1 implements Runnable {
	private String hello;
	public Thread1(String hello){
		this.hello = hello;
	}
    public void run() {  
    	for (int i = 0; i < 5; i++) {  
            System.out.println(Thread.currentThread().getName() + "  loop " + i);  
       }  
    	
         synchronized(this) {  
              for (int i = 0; i < 5; i++) {  
                   System.out.println(Thread.currentThread().getName() + " synchronized loop " + i);  
              }  
         }  
         
         System.out.println(hello);
    }  
    public static void main(String[] args) {  
         Thread1 t1 = new Thread1("nihao");  
         Thread ta = new Thread(t1, "A");  
         Thread tb = new Thread(t1, "B");  
         ta.start();  
         tb.start();  
    } 
}