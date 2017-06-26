package test;

import java.util.Date;

import activityRecords.model.Student;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class JsonTest {
	public static void main(String[] args) {
		JSONObject json = new JSONObject();
		Student s = new Student();
		s.setAge(1);
		s.setId(1);
		s.setName("12");
		s.setBirth(new Date());
		json.put("student", s);
		System.out.println(json.toString());
	}
	
}
