package 反射;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Demo3 {
  public static void main(String[] args) {
    Class<?> type = Person.class;
    Field[] fields = type.getDeclaredFields();
    for(Field field :fields){
      System.out.println(field.getName());
    }
    Method[] methods = type.getDeclaredMethods();
    for(Method method :methods){
      System.out.println(method.getName());
      System.out.println(method.getReturnType());
      System.out.println(Arrays.toString(
          method.getParameterTypes()));
    }
    Constructor[] constructors =
      type.getDeclaredConstructors();
    for(Constructor constructor:constructors){
      System.out.println(constructor.getName());
      System.out.println(Arrays.toString(
          constructor.getParameterTypes()));
    }
  }
}
class Person{
  int age;
  String name;
  public Person(int age,String name){
    this.age = age;
    this.name = name;
  }
  public Person(int age){
    this.age = age;
  }
  public void show1(int a){
    System.out.println(a);
  }
  private int add(int a,int b){
    return a+b;
  }
}