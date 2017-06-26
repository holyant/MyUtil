package test;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;

import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.JsonNodeFactory;
import org.junit.Test;

import test.model.Student;

public class Jackson4JsonTest {

	private JsonGenerator jsonGenerator = null;
    private ObjectMapper objectMapper = null;
    private Student bean = null;

    public static void main(String[] args) {
    	Jackson4JsonTest jt = new Jackson4JsonTest();
		jt.init();
//		jt.writeEntityJSON();
//		jt.readJson2List();
//		jt.readJson2Entity();
		jt.destory();
	}
    public void writeEntityJSON() {
        try {
            System.out.println("jsonGenerator");
            //writeObject可以转换java对象，eg:JavaBean/Map/List/Array等
            jsonGenerator.writeObject(bean);

            System.out.println("\nObjectMapper");
            //writeValue具有和writeObject相同的功能
            objectMapper.writeValue(System.out, bean);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void writeMapJSON() {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("name", bean.getName());
            map.put("account", bean);
            map.put("account2", bean);

            System.out.println("jsonGenerator");
            jsonGenerator.writeObject(map);
            System.out.println("");

            System.out.println("objectMapper");
            objectMapper.writeValue(System.out, map);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    

    public void readJson2Entity() {
        String json = "{\"id\":1,\"name\":\"holyant\",\"age\":18,\"birth\":1434356287837}";
        try {
            Student s = objectMapper.readValue(json, Student.class);
            System.out.println(s.getName());
            System.out.println(s);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
    * <b>function:</b>json字符串转换成list<map>
    * @author hoojo
    * @createDate 2010-11-23 下午06:12:01
    */
    @Test
    public void readJson2List() {
        String json = "[{\"address\": \"address2\",\"name\":\"haha2\",\"id\":2,\"email\":\"email2\"},"+
                    "{\"address\":\"address\",\"name\":\"haha\",\"id\":1,\"email\":\"email\"}]";
        try {
            List<LinkedHashMap<String, Object>> list = objectMapper.readValue(json, List.class);
//            readJson2Array
//            AccountBean[] arr = objectMapper.readValue(json, AccountBean[].class);
//            readJson2Map
//            Map<String, Map<String, Object>> maps = objectMapper.readValue(json, Map.class);
            System.out.println(list.size());
            for (int i = 0; i < list.size(); i++) {
            	System.out.println(list.get(i));
                Map<String, Object> map = list.get(i);
                Set<String> set = map.keySet();
//                for (Iterator<String> it = set.iterator();it.hasNext();) {
//                    String key = it.next();
//                    System.out.println(key + ":" + map.get(key));
//                }
            }
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void init() {
        bean = new Student();
        bean.setId(1);
        bean.setName("holyant");
        bean.setAge(18);
        bean.setBirth(new Date());

        objectMapper = new ObjectMapper();
        try {
            jsonGenerator = objectMapper.getJsonFactory().createJsonGenerator(System.out, JsonEncoding.UTF8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void destory() {
        try {
            if (jsonGenerator != null) {
                jsonGenerator.flush();
            }
            if (!jsonGenerator.isClosed()) {
                jsonGenerator.close();
            }
            jsonGenerator = null;
            objectMapper = null;
            bean = null;
            System.gc();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


	//对xml的支持
//	@Test
//	public void writeObject2Xml() {
//	    //stax2-api-3.0.2.jar
//	    System.out.println("XmlMapper");
//	    XmlMapper xml = new XmlMapper();
//
//	    try {
//	        //javaBean转换成xml
//	        //xml.writeValue(System.out, bean);
//	        StringWriter sw = new StringWriter();
//	        xml.writeValue(sw, bean);
//	        System.out.println(sw.toString());
//	        //List转换成xml
//	        List<AccountBean> list = new ArrayList<AccountBean>();
//	        list.add(bean);
//	        list.add(bean);
//	        System.out.println(xml.writeValueAsString(list));
//
//	        //Map转换xml文档
//	        Map<String, AccountBean> map = new HashMap<String, AccountBean>();
//	        map.put("A", bean);
//	        map.put("B", bean);
//	        System.out.println(xml.writeValueAsString(map));
//	    } catch (JsonGenerationException e) {
//	        e.printStackTrace();
//	    } catch (JsonMappingException e) {
//	        e.printStackTrace();
//	    } catch (IOException e) {
//	        e.printStackTrace();
//	    }
//	}

}
