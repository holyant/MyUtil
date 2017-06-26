package db2Test.util;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import db2Test.entity.DbInfo;

/**
 * 获取数据库连接
 * 释放资源
 * @author fangyh
 */
public class DB2Connection {
	private DbInfo dbInfo;
	public DB2Connection(String driver,String url,String user,String password){
		dbInfo = new DbInfo(driver,url,user,password);
		//加载和注册数据库驱动
		try {
			dbInfo.setDriver(dbInfo.getDriver());
			Class.forName(dbInfo.getDriver());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获取数据库连接
	 */
	public  Connection getConn() throws SQLException{
		return DriverManager.getConnection(dbInfo.getUrl(),dbInfo.getUser(),dbInfo.getPassword());
	}
	/**
	 * 释放资源
	 */
	public static void close(ResultSet rs,Statement s,Connection conn){
		try{
			if(rs!=null)
				rs.close();
			if(s!=null)
				s.close();
			if(conn!=null)
				conn.close();
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}
	}
	public static void main(String[] args) throws SQLException {
		DB2Connection db2Connection = new DB2Connection("com.ibm.db2.jcc.DB2Driver","jdbc:db2://130.36.23.52:50000/zjolap",
				"biweb","ailk@2010");
		Connection conn = db2Connection.getConn();
		System.out.println(conn);
	}
}








