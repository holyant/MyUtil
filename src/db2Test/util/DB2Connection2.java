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
public class DB2Connection2 {
	private static DbInfo dbInfo;
	static{
		try{
			//加载属性文件
			Properties prop=MyProperties.getProperties("/db.properties");
//			dbInfo = new DbInfo(prop.getProperty("db2zjbidriver"),
//					prop.getProperty("db2zjbiurl"),
//					prop.getProperty("db2zjbiuser"),
//					prop.getProperty("db2zjbipassword"));
//			dbInfo.setDriver(prop.getProperty("db2zjbidriver"));
			
//			Properties prop=MyProperties.getProperties("/db.properties");
//			dbInfo = new DbInfo(prop.getProperty("db2driver"),
//					prop.getProperty("db2url"),
//					prop.getProperty("db2user"),
//					prop.getProperty("db2password"));
//			dbInfo.setDriver(prop.getProperty("db2driver"));
			
			dbInfo = new DbInfo(prop.getProperty("db2driver"),
					prop.getProperty("db2url"),
					prop.getProperty("db2user"),
					prop.getProperty("db2password"));
			dbInfo.setDriver(prop.getProperty("db2driver"));
			//加载和注册数据库驱动
			Class.forName(dbInfo.getDriver());
		}catch(ClassNotFoundException cnfe){
			cnfe.printStackTrace();
		}catch(IOException ioe){
			ioe.printStackTrace();
		}
	}
	/**
	 * 获取数据库连接
	 */
	public static Connection getConn() throws SQLException{
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
		Connection conn = DB2Connection2.getConn();
		System.out.println(conn);
	}
}








