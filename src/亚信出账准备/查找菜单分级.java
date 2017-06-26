package 亚信出账准备;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

import db2Test.util.DB2Connection;

public class 查找菜单分级 {
	private static String menuName = "通话用户";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DB2Connection db2Connection = new DB2Connection("com.ibm.db2.jcc.DB2Driver","jdbc:db2://130.36.23.52:50000/zjolap",
				"biweb","ailk@2010");
		try {
			Connection conn = db2Connection.getConn();
			String sql = "select parentid,menuitemtitle from sys_menu_item where MENUITEMTITLE like '%"+menuName+"%'";
			PreparedStatement ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			String currentId = null;
			while(rs.next()){
				currentId = rs.getString("parentid");
				System.out.println(rs.getString("menuitemtitle"));
			}
			while(true){
				if("0".equals(currentId)){
					break;
				}
				sql = "select parentid,menuitemtitle from sys_menu_item where menuitemid = "+currentId;
				rs=conn.prepareStatement(sql).executeQuery();
				while(rs.next()){
					currentId = rs.getString("parentid");
					System.out.println(rs.getString("menuitemtitle"));
				}
			}
			db2Connection.close(rs, null, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
