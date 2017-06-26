package db2Test;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import db2Test.entity.UserCity;
import db2Test.util.DB2Connection;
import db2Test.util.DB2Connection2;


public class Db2Test {
	public static void main(String[] args) {
		List<UserCity> userCitys = new ArrayList<UserCity>();
		try {
			Connection conn = DB2Connection2.getConn();
			String sql = "select * from USER_CITY where rownum<=10";
			PreparedStatement ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				UserCity userCity = new UserCity();
				userCity.setCityid(rs.getString("CITYID"));
				userCity.setCityname(rs.getString("CITYNAME"));
				userCitys.add(userCity);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for(int i=0;i<userCitys.size();i++){
			System.out.println(userCitys.get(i).getCityid());
			System.out.println(userCitys.get(i).getCityname());
		}

	}
	public List<UserCity> getInfos(){
		List<UserCity> userCitys = new ArrayList<UserCity>();
		try {
			Connection conn = DB2Connection2.getConn();
			String sql = "select * from USER_CITY where rownum<=10";
			PreparedStatement ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				UserCity userCity = new UserCity();
				userCity.setCityid(rs.getString("CITYID"));
				userCity.setCityname(rs.getString("CITYNAME"));
				userCitys.add(userCity);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userCitys;
	}
	@Test
	public void test1(){
		List<UserCity> userCitys = new ArrayList<UserCity>();
		try {
			Connection conn = DB2Connection2.getConn();
//			String sql = "select * from USER_CITY where rownum<=10";
//			PreparedStatement ps=conn.prepareStatement(sql);
			ResultSet rs=null;
//			while(rs.next()){
//				UserCity userCity = new UserCity();
//				userCity.setCityid(rs.getString("CITYID"));
//				userCity.setCityname(rs.getString("CITYNAME"));
//				userCitys.add(userCity);
//			}
            DatabaseMetaData metaData = conn.getMetaData();  
           	System.out.println( metaData.getDatabaseProductName() );
//            while(rs.next()){  
//                String tableName = rs.getString("TABLE_NAME");  
//                System.out.println(tableName);
//            }  
		} catch (SQLException e) {
			e.printStackTrace();
		}
//		for(int i=0;i<userCitys.size();i++){
//			System.out.println(userCitys.get(i).getCityid());
//			System.out.println(userCitys.get(i).getCityname());
//		}
	}
}
