package 反射;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Demo1 {
  public static void main(String[] args) throws SecurityException, NoSuchMethodException, Exception, IllegalAccessException, InvocationTargetException {
////    a 获得class对象
//    Class<?> type = Koo.class;
////    b 获得Methed(方法)对象
////getMethod("方法名",参数.class列表):获得是public修饰的Method对象   
//    Method methodAdd = 
//          type.getMethod("add", int.class,int.class);
////    c 选择是否压制访问控制
////    d 通过反射调用对象的底层方法
//    Koo koo = new Koo();
////invoke(对象名,方法的参数列表):调用该对象的底层方法(通过
////method对象来确定调用哪个方法)    
//    int result = (Integer) methodAdd.invoke(koo,12,20);
//    System.out.println(result);
    
    Class<?> type = Koo.class;
//getDeclaredMethod(方法名,方法的参数.class):
//获得声明过的任何方法    
    Method methodSub = 
      type.getDeclaredMethod("sub", int.class,int.class);
//压制访问权限
    methodSub.setAccessible(true);
    int result = (Integer) methodSub.invoke(new Koo(), 20,12);
	//methodSub.invoke(new Koo(),new Object[]{ 20,12});
    System.out.println(result);
  }
}
class Koo{
  public int add(int num1,int num2){
    return num1+num2;
  }
  private int sub(int num1,int num2){
    return num1-num2;
  }
}
