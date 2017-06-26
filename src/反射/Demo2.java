package 反射;

import java.lang.reflect.Field;

public class Demo2 {
  public static void main(String[] args) throws SecurityException, NoSuchFieldException, Exception, IllegalAccessException {
//    a 获得class对象
//    b 获得Field(属性)对象
//    c 选择是否压制访问控制
//    d 通过反射来访问对象的属性
    Class<?> type = Foo.class;
    Field fieldA = type.getDeclaredField("a");
    Foo foo = new Foo();
    System.out.println(foo.a);
//set(目标对象,目标对象的要赋的属性值):给目标对象的属性赋值    
    fieldA.set(foo, 30);
    System.out.println(foo.a);
    
    Field fieldB = type.getDeclaredField("b");
    System.out.println(foo.getB());
// 压制访问控制
    fieldB.setAccessible(true);
    fieldB.set(foo, 47);
    System.out.println(foo.getB());
    
    
    
    
    
    
    
    
  }
}
class Foo{
  int a = 12;
  private int b=12;
  public int getB(){
    return this.b;
  }
}