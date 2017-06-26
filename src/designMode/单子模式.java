package designMode;

public class 单子模式 {
	private static 单子模式 ace;

	public 单子模式() {
		super();
		System.out.println("单子模式类中的无参构造器调用了");
	}

	public static 单子模式 getAceing() {
		if (ace == null) {
			ace = new 单子模式();
		}
		return ace;
	}
	
	

}
