package test;
import java.sql.Connection;    
import java.sql.DriverManager;    
import java.sql.ResultSet;    
import java.sql.SQLException;
import java.sql.Statement;   
public class SqlServerTest {
	 public static void main(String args[]) {    
		 String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";  
	        String dbURL = "jdbc:sqlserver://192.122.111.60:1433;databaseName=province"; // 1433是端口，"USCSecondhandMarketDB"是数据库名称  
	        String userName = "infodep"; // 用户名  
	        String userPwd = "infodep"; // 密码  
	  
	        Connection dbConn = null;  
	        try {  
	  
	            Class.forName(driverName).newInstance();  
	        } catch (Exception ex) {  
	            System.out.println("驱动加载失败");  
	            ex.printStackTrace();  
	        }  
	        try {  
	            dbConn = DriverManager.getConnection(dbURL, userName, userPwd);  
	            System.out.println("成功连接数据库！"+dbConn);  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        } finally {  
	  
	            try {  
	                if (dbConn != null)  
	                    dbConn.close();  
	            } catch (SQLException e) {  
	                e.printStackTrace();  
	            }  
	        }  
	    }    
}
