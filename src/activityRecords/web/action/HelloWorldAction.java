package activityRecords.web.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;

import activityRecords.model.Student;
import activityRecords.service.HelloService;
import net.sf.json.JSONObject;

public class HelloWorldAction extends ActionSupport implements
		ServletRequestAware, ServletResponseAware {
	public static Logger logger = Logger.getLogger(HelloWorldAction.class);
	private HelloService helloService;
	private String exptStatus;
	private HttpServletRequest request;
	private HttpServletResponse response;

	public static void main(String[] args) throws IOException {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:comp/env");

			if (envContext == null) {
				throw new Exception("Boom - No Context");
			}
			String strDbJndi = "java:comp/env/jdbc/BIDB";
			if (strDbJndi.startsWith("java:comp/env/"))
				strDbJndi = strDbJndi.substring(14);
			DataSource ds = (DataSource) envContext.lookup(strDbJndi);
			System.out.println(ds);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String hello() throws Exception {

		
		return "success";
	}

	public void setHelloService(HelloService helloService) {
		this.helloService = helloService;
	}

	public String test() {
		try {
			this.helloService.deleteEmp(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "success";
	}

	public String json() {
		try {
			PrintWriter writer = ServletActionContext.getResponse().getWriter();
			JSONObject json = new JSONObject();
			Student s = new Student();
			s.setAge(1);
			s.setId(1);
			s.setName("12");
			s.setBirth(new Date());
			json.put("student", s);
			writer.write(json.toString());
			writer.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public String test404() {
		System.out.println("hello");
		return "error";
	}

	public String getExptStatus() {
		return exptStatus;
	}

	public void setExptStatus(String exptStatus) {
		this.exptStatus = exptStatus;
	}

	public HelloService getHelloService() {
		return helloService;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

}
