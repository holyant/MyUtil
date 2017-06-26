package test.web.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;

import db2Test.util.DB2Connection;
import db2Test.util.DB2Connection2;

public class ExportExcelAction extends ActionSupport implements ServletResponseAware,ServletRequestAware{
	public static Logger logger=Logger.getLogger(ExportExcelAction.class);
	private HttpServletRequest request;
	private HttpServletResponse response;
	public static void main(String[] args) {
		ExportExcelAction eea = new ExportExcelAction();
		eea.getExcelFile();
	}
	
	public String getExcelFile(){
		String column = "";
		try {
			Connection conn = DB2Connection2.getConn();
			StringBuffer sb = new StringBuffer("select count(1) count from BIWEB.OUTPUT_TABLE_META ");
			PreparedStatement ps = conn.prepareStatement(sb.toString());
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				System.out.println(rs.getString("count"));
			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		return SUCCESS;
	}
	
	
	
	
	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
	}

	public void setServletResponse(HttpServletResponse arg0) {
		this.response = arg0;
	}
}
