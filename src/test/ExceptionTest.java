package test;

public class ExceptionTest {
	public static void main(String[] args) {
		ExceptionTest et = new ExceptionTest();
		System.out.println(et.test1());
	}
	public String test1(){
		try {
			System.out.println("1");
			throw new Exception("hello");
		} catch (Exception e) {
			System.out.println("2");
			return "123";
		}finally{
			System.out.println("3");
		}
	}
}
