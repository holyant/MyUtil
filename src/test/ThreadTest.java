package test;

import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;
public class ThreadTest {
	@Test
	public void ttt() throws InterruptedException{
		Thread.sleep(1000);
		Map<Thread,StackTraceElement[]> threads =Thread.getAllStackTraces();
		
		for(Entry<Thread,StackTraceElement[]> en: threads.entrySet()){
			Thread temp = en.getKey();
			System.out.println(temp.getName());
		}
		
	}
}
