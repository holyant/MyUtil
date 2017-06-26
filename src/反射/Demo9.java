package 反射;

import java.lang.reflect.Constructor;

public class Demo9 {
	public static void main(String[] args) throws Exception, IllegalAccessException {
		// Class<?> type = Koo.class;
		// 如果Koo构造方法带参数的话,不能使用type.newInstance()
		// Koo koo =(Koo)type.newInstance();
		// Constructor constructor =
		// type.getConstructor(int.class,int.class);
		//// 返回一个 Constructor对象它反映此 Class 对象所表示的类的
		//// 指定公共构造方法。
		//// parameterTypes - 参数数组
		// Koo koo = (Koo) constructor.newInstance(12,14);

		// Koo koo = new Koo(12);
		// 1 创建class对象
		Class<?> type = Koo.class;
		// 2 创建Constructor对象
		// Constructor construtor = type.getConstructor(Class)
		// type.getConstructor:只能找到用public修饰的构造方法的
		// Constructor对象
		// type.getDeclaredConstructor(参数.Class):
		// 根据参数来生成对应Constructor对象
		Constructor constructor = type.getDeclaredConstructor(int.class);
		// 3 压制访问权限
		// setAccessible(boolean)
		// 值为 true 则指示反射的对象在使用时应该取消 Java语言访问检查。
		// 值为 false 则指示反射的对象应该实施 Java 语言访问检查。
		constructor.setAccessible(true);
		// 4 创建private修饰的构造方法的类的对象
		Koo koo = (Koo) constructor.newInstance(12);

	}
}

class Koo {
	int a;

	private Koo(int a) {
		System.out.println("创建private修饰的构造方法的类的对象");
	}

	public Koo(int a, int b) {
		System.out.println("创建带参数a的Koo对象" + (a + b));
	}

}