package designMode;

public class 单子模式test {
	public static void main(String[] args) {
		单子模式 a1 = 单子模式.getAceing();
		单子模式 a2 = 单子模式.getAceing();
		if (a1 == a2) {
			System.out.println("创建的两个对象引用指向的是一个对象");
		} else {
			System.out.println("创建的两个对象引用指向的不是一个对象");
		}
	}

}
