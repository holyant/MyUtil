package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;

public class Test {
	public void fun1(ArrayList<String> datas){
		datas.add("hello");
	}
	public void fun2(ArrayList<String> datas){
		for(int i=0;i<datas.size();i++){
			System.out.println(datas.get(i));
		}
	}
	public static void main(String[] args) {
//		for(int i=0;i<args.length;i++){
//			System.out.println("holyant"+args[i]);
//		}
//		Test test = new Test();
//		test.lambda();
		String a = "半光切片";
		String b = "半光切片2";
		
		System.out.println(a.indexOf(b));
	}
	
	public void h1(int i){
		System.out.println("int");
	}
	public void h1(String i){
		System.out.println("String");
		
	}
	public void lambda(){
//		Arrays.asList( "a", "b", "d" ).forEach( e -> {
//		    System.out.print( e );
//		    System.out.print( e );
//		} );
//		Arrays.asList( "a", "b", "d" ).sort( ( e1, e2 ) -> e1.compareTo( e2 ) );
//		String a = "a";
//		List<String> strings = Arrays.asList(a,"b","c");
//		strings.stream().filter(e->!"a".equals(e));
//		strings.forEach(e->System.out.println(e));
		
		List<String> userNames = Arrays.asList("a","b","c").stream().collect(Collectors.toCollection(ArrayList::new));
		String[] kks = {"1","2","3"};
		StringUtils.join(kks,",");
	}
	
	
}
