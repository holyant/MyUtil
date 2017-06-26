package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

public class ColectionTest {
	//Iterator的简单使用
	@Test
	public void test1(){
		List<String> list = new ArrayList<String>();
		list.add("a");
		list.add("b");
		list.add("c");
		
		Iterator<String> iterator = list.iterator();
		while(iterator.hasNext()){
			String s = iterator.next();
			System.out.println(s);
		}
	}
	@Test
	//comparator的简单操作
	public void test2(){
		List<String> list = new ArrayList<String>();
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("b");
		
		Collections.sort(list, new MyComparator());
		
		Iterator<String> iterator = list.iterator();
		while(iterator.hasNext()){
			String s = iterator.next();
			System.out.println(s);
		}
	}
	
	//Array工具类使用
	@Test
	public void test3(){
		int[] intArray = new int[]{1, 2, 3, 4};
	    List<Integer> list = new ArrayList<Integer>();
	    list.add(1);
	    list.add(2);
	    list.add(3);
	    list.add(4);
	    List listFromArray = Arrays.asList(intArray);
	    System.out.println(listFromArray);
	    System.out.println(list);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
