package test;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import test.model.ListBean;
import test.model.Student;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class XStreamTest {
	private XStream xstream = null;
    private ObjectOutputStream  out = null;
    private ObjectInputStream in = null;
    
    private Student bean = null;
    
    public static void main(String[] args) {
    	XStreamTest xsTest = new XStreamTest();
    	xsTest.init();
//    	xsTest.writeBean2XML();
//    	xsTest.writeList2XML();
//    	xsTest.readXml2Object();
    	xsTest.readXML4InputStream();
    	xsTest.destory();
	}
    public void init() {
        try {
            xstream = new XStream(new DomDriver());
        } catch (Exception e) {
            e.printStackTrace();
        }
        bean = new Student();
        bean.setId(1);
        bean.setName("holyant");
        bean.setAge(18);
        bean.setBirth(new Date());

    }
    public void destory() {
        xstream = null;
        bean = null;
        try {
            if (out != null) {
                out.flush();
                out.close();
            }
            if (in != null) {
                in.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.gc();
    }
    public void writeBean2XML() {
        try {
            fail("------------Bean->XML------------");
            fail(xstream.toXML(bean));
            fail("重命名后的XML");
            //类重命名
            //xstream.alias("account", Student.class);
            //xstream.alias("生日", Birthday.class);
            //xstream.aliasField("生日", Student.class, "birthday");
            //xstream.aliasField("生日", Birthday.class, "birthday");
            //fail(xstream.toXML(bean));
            //属性重命名
            xstream.aliasField("名字", Student.class, "name");
            //包重命名
//            xstream.aliasPackage("hoo", "com.hoo.entity");
            fail(xstream.toXML(bean));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void writeList2XML() {
        try {
            //修改元素名称
            xstream.alias("beans", ListBean.class);
            xstream.alias("student", Student.class);
            fail("----------List-->XML----------");
            ListBean listBean = new ListBean();
            listBean.setName("this is a List Collection");
            
            List<Object> list = new ArrayList<Object>();
            list.add(bean);
            list.add(bean);//引用bean
            //list.add(listBean);//引用listBean，父元素
            
            bean.setId(2);
            bean.setName("holyant");
            bean.setAge(18);
            bean.setBirth(new Date());
            
            list.add(bean);
            listBean.setList(list);
            
            //将ListBean中的集合设置空元素，即不显示集合元素标签
            //xstream.addImplicitCollection(ListBean.class, "list");
            
            //设置reference模型
            //xstream.setMode(XStream.NO_REFERENCES);//不引用
//            xstream.setMode(XStream.ID_REFERENCES);//id引用
            //xstream.setMode(XStream.XPATH_ABSOLUTE_REFERENCES);//绝对路径引用
              
            //将name设置为父类（Student）的元素的属性
            xstream.useAttributeFor(Student.class, "name");
//            xstream.useAttributeFor(Birthday.class, "birthday");
            //修改属性的name
            xstream.aliasAttribute("姓名", "name");
//            xstream.aliasField("生日", Birthday.class, "birthday");
          
            fail(xstream.toXML(listBean));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void readXml2Object() {
        try {
        	
            failRed("-----------Xml >>> Bean--------------");
            Student stu = (Student) xstream.fromXML(xstream.toXML(bean));
            fail(stu.toString());
            
            List<Student> list = new ArrayList<Student>();
            list.add(bean);//add
            
            Map<String, Student> map = new HashMap<String, Student>();
            map.put("No.1", bean);//put
            
            bean = new Student();
            bean.setAddress("china");
            bean.setEmail("tom@125.com");
            bean.setId(2);
            bean.setName("tom");
            list.add(bean);//add
            map.put("No.2", bean);//put
            
            bean = new Student();
            bean.setName("jack");
            list.add(bean);//add
            map.put("No.3", bean);//put
            
            failRed("==========XML >>> List===========");
            List<Student> studetns = (List<Student>) xstream.fromXML(xstream.toXML(list));
            fail("size:" + studetns.size());//3
            for (Student s : studetns) {
                fail(s.toString());
            }
//            
            failRed("==========XML >>> Map===========");
            Map<String, Student> maps = (Map<String, Student>) xstream.fromXML(xstream.toXML(map));
            fail("size:" + maps.size());//3
            Set<String> key = maps.keySet();
            Iterator<String> iter = key.iterator();
            while (iter.hasNext()) {
                String k = iter.next();
                fail(k + ":" + map.get(k));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void readXML4InputStream() {
        try {
            String s = "<object-stream><com.hoo.entity.Student><id>0</id><name>jack</name>" +
              "</com.hoo.entity.Student><com.hoo.entity.Birthday><birthday>2010-05-33</birthday>" +
              "</com.hoo.entity.Birthday><byte>22</byte><boolean>true</boolean><float>22.0</float>" +
              "<string>hello</string></object-stream>";
            failRed("---------ObjectInputStream## XML --> javaObject---------");
            StringReader reader = new StringReader(s);
            in = xstream.createObjectInputStream(reader);
            Student stu = (Student) in.readObject();
            byte i = in.readByte();
            boolean bo = in.readBoolean();
            float f = in.readFloat();
            String str = in.readUTF();
            System.out.println(stu);
            System.out.println(i);
            System.out.println(bo);
            System.out.println(f);
            System.out.println(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public final void fail(String string) {
        System.out.println(string);
    }
    
    public final void failRed(String string) {
        System.err.println(string);
    }
    
}
