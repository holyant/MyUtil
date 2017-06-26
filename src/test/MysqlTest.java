package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import common.utils.PropertiesUtil;

public class MysqlTest {
	public static Logger logger=Logger.getLogger(MysqlTest.class);
	
	public static void main(String[] args) {
		PropertiesUtil util=new PropertiesUtil("src/db.properties");  
		
		String driverName = util.getProperty("mysqldriver");  
		
        Connection dbConn = null;  
//        driverName = "COM.ibm.db2.jdbc.app.DB2Driver";
        try {
            Class.forName(driverName).newInstance();  
        } catch (Exception ex) {  
            ex.printStackTrace();
            logger.debug(ex.toString());
//            ex.printStackTrace(System.out);//堆栈信息导出到控制台
        }  
        
        String dbURL = util.getProperty("mysqlurl");
        String userName = util.getProperty("mysqluser");
        String userPwd = util.getProperty("mysqlpassword");
        
        
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
