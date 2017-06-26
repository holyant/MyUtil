package 反射;

public class Demo8 {
  public static void main(String[] args) throws InstantiationException, IllegalAccessException, Exception {
    Person person = new Person();
    System.out.println(person);
//动态来创建对象/动态来访问对象
//1 第1种   
//  1)先创建class对象  
    Class<?> type = Person.class;//创建Class对象type
//  2)type.newInstance():通过type来创建Preson对象
    Person person2 = (Person)type.newInstance(); 
    System.out.println("通过反射来创建Person对象"+person2);
//2 第2种:根据类的包名.类名来创建class对象
    Class<?> type2 = Class.forName("ref1.Person");
    Person person3 = (Person) type2.newInstance();
    System.out.println(person3);
//3 第3种:对象名.getClass()也可以获得class对象
    Class<?> type3 = person.getClass(); 
    Person person4 = (Person) type3.newInstance();
    System.out.println(person4);
  }

}
class Person{
  public String toString() {
    return "创建Person对象";
  }
  
}