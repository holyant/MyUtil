package 亚信出账准备;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.utils.DateConvertor;



import db2Test.util.DB2Connection2;
import excel.poiTest.ExcelReader;

public class UserHitrateCount {
	public static void main(String[] args) {
		//1取出excel中所有用户
		InputStream is = null;
		ExcelReader excelReader = new ExcelReader();
		Map<Integer, UserHitrateModel> resultFromExcel = new HashMap<Integer, UserHitrateModel>();
		Map<Integer, String> map = null;
		try {
			is = new FileInputStream("C:\\workspace\\经分表清理及系统访问情况(201506).xls");
			map = excelReader.readExcelContent(is,2);
			String str = null;
			for (int i = 1; i <= map.size(); i++) {
				str = map.get(i);
				UserHitrateModel model = new UserHitrateModel();
				model.setRowNum(i-1);
				model.setUserId1(str.split("=")[2]);
				model.setUserName(str.split("=")[3]);
				resultFromExcel.put(i-1, model);
//				System.out.println(i+","+model);
			}
		} catch (FileNotFoundException e) {
			System.out.println("未找到指定路径的文件!");
			e.printStackTrace();
		}

		//2取出sql中所有用户
		Map<Integer,UserHitrateModel> resultFromSql = new HashMap<Integer, UserHitrateModel>();
		try {
			Connection conn = DB2Connection2.getConn();
			String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			String theLastMonthFirstDate = DateConvertor.getLastMonthFirstDate(today, "yyyy-MM-dd");
			String theLastDayByPreviousMonthString = DateConvertor.getLastMonthLastDate(today, "yyyy-MM-dd");
			
			String sql = "select a.rownum,a.userid userId1,b.* from  user_hitrate_photo_201403  a full join (select c.cityname,a.userid userId2, a.username, nvl(b.cnt, 0) hitCount    from user_user a    left join (select user_id, count(*) cnt                 from user_hitrate_total b                " +
					"where hit_time between to_date('"+theLastMonthFirstDate+"', 'yyyy-mm-dd') " +
					"and to_date('"+theLastDayByPreviousMonthString+" 23:59:59', 'yyyy-mm-dd hh24:mi:ss')                group by user_id) b on a.userid = b.user_id   inner join user_city c on a.cityid = c.cityid   where a.status = 0 and a.notes not like '%test%' order by nvl(b.cnt, 0) desc,a.username) b on a.userid=b.userId2 order by a.rownum";
			System.out.println(sql);
			PreparedStatement ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				UserHitrateModel model = new UserHitrateModel(rs.getRow(),rs.getString("userId1"), rs.getString("userId2"),
						rs.getString("cityname"), rs.getString("username"), rs.getString("hitCount"));
				resultFromSql.put(rs.getRow()-1, model);
//				System.out.println(model+","+rs.getRow());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//3 同3月份拍照表的直接赋值
		List<UserHitrateModel> result = new ArrayList<UserHitrateModel>();
		for (int i = 0; i < resultFromSql.size(); i++) {
			UserHitrateModel model1 = resultFromSql.get(i);
			if (null != model1.getUserId1()) {
				for (int j = i; j < resultFromExcel.size(); j++) {
					UserHitrateModel model2 = resultFromExcel.get(j);

					if (model2.getUserId1().equals(model1.getUserId2())) {
						model2.setHitCount(model1.getHitCount());
						break;
					} else if (null == model2.getCityName()) {
						model2.setHitCount("-");
						break;
					}
				}
			}else{
					result.add(model1);
			}
			// System.out.println(result.get(i));

		}
		
		
		//4 不同的比较 然后赋值
		
		for(int i=0;i<resultFromExcel.size();i++){
			UserHitrateModel model = resultFromExcel.get(i);
			if(null==model.getHitCount()){
				for(int j=0;j<result.size();j++){
		        	UserHitrateModel model2 = result.get(j);
					if(model.getUserId1().equals(model2.getUserId2())){
						model.setHitCount(model2.getHitCount());
					}
		        }
//				System.out.println(model);
			}
			
		}
		//如果还是result中hitcount仍为null，那么设置为-
		for(int i=0;i<resultFromExcel.size();i++){
			UserHitrateModel model = resultFromExcel.get(i);
			if(null==model.getHitCount()){
				model.setHitCount("-");
			}
		}
		System.out.println("原有部分");
		for(int i=0;i<resultFromExcel.size();i++){
			UserHitrateModel model = resultFromExcel.get(i);
			System.out.println(model.getUserId1()+"	"+model.getUserName()+"	"+model.getHitCount());
		}
		System.out.println("---------------------------------------------------------------------");
		System.out.println("新增人员部分");
		List<UserHitrateModel> result3 = new ArrayList<UserHitrateModel>();
		for(int i=1;i<result.size();i++){
			UserHitrateModel model2 = result.get(i);
			for(int j=1;j<resultFromExcel.size();j++){
				UserHitrateModel model = resultFromExcel.get(j);
				if(model2.getUserId2().toUpperCase().equals(model.getUserId1().toUpperCase())){
					break;
				}
				if(j==resultFromExcel.size()-1){
					result3.add(model2);
					System.out.println(model2.getCityName()+","+ model2.getUserId2()+","+model2.getUserName()+","+model2.getHitCount());
				}
			}
		}
	}
}
























