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
import java.util.List;
import java.util.Map;

import common.utils.DateConvertor;



import db2Test.util.DB2Connection2;
import excel.poiTest.ExcelReader;

public class MenuHitrateCount {
	public static void main(String[] args) {
		//1取出excel中所有用户
		InputStream is = null;
		ExcelReader excelReader = new ExcelReader();
		List<MenuHitrateModel> resultFromExcel = new ArrayList<MenuHitrateModel>();
		
		Map<Integer, String> map = null;
		
		try {
			is = new FileInputStream("C:\\workspace\\经分表清理及系统访问情况(201506).xls");
			map = excelReader.readExcelContent(is,3);
			String str = null;
			for (int i = 1; i <= map.size(); i++) {
				str = map.get(i);
				MenuHitrateModel model = new MenuHitrateModel();
				model.setRowNum(i-1);
				model.setTitle(str.split("=")[1]);
				resultFromExcel.add(model);
//				System.out.println(model.getTitle());
			}
		} catch (FileNotFoundException e) {
			System.out.println("未找到指定路径的文件!");
			e.printStackTrace();
		}

		//2取出sql中所有用户
		List<MenuHitrateModel> resultFromSql = new ArrayList<MenuHitrateModel>();
		try {
			Connection conn = DB2Connection2.getConn();
			String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			String theLastMonthFirstDate = DateConvertor.getLastMonthFirstDate(today, "yyyy-MM-dd");
			String theLastDayByPreviousMonthString = DateConvertor.getLastMonthLastDate(today, "yyyy-MM-dd");
			
			String sql = "select a.rownum,a.MENUITEMID as menuitemida,b.title,b.menuitemid as menuitemidb,b.cnt from  menu_hitrate_photo_201403  a full join (select *   from (select nvl(c5.MENUITEMtitle, '') || '-' || nvl(c4.MENUITEMtitle, '') || '-' ||                nvl(c3.MENUITEMtitle, '') || '-' || nvl(c2.MENUITEMtitle, '') || '-' ||                nvl(c1.MENUITEMtitle, '') || '-' || nvl(a.MENUITEMtitle, '') title,a.MENUITEMID,                nvl(b.cnt, 0) cnt           from SYS_MENU_ITEM a           left join SYS_MENU_ITEM c1 on c1.MENUITEMID = a.parentid           left join SYS_MENU_ITEM c2 on c2.MENUITEMID = c1.parentid           left join SYS_MENU_ITEM c3 on c3.MENUITEMID = c2.parentid           left join SYS_MENU_ITEM c4 on c4.MENUITEMID = c3.parentid           left join SYS_MENU_ITEM c5 on c5.MENUITEMID = c4.parentid                  left join (select MENU_ITEM_ID, count(*) cnt                       from user_hitrate_total b                      where hit_time between                            to_date('"+theLastMonthFirstDate+"', 'yyyy-mm-dd') and                            to_date('"+theLastDayByPreviousMonthString+" 23:59:59',                                    'yyyy-mm-dd hh24:mi:ss')                      group by MENU_ITEM_ID) b on a.MENUITEMID =                                                  int(b.MENU_ITEM_ID)          where trim(nvl(a.url, '')) <> '') where title not like '%测试%'    and title not like '%系统监控%'    and title not like '%系统管理%'    and title not like '需求进度查询'    and title not like '%报表维护%'    and title not like '%报表类型%'    and title not like '%自助报表%'    and title not like '%报表批量导出%'    and title not like '%KPI配置%'    and title not like '%KPI-每月聚焦-增值业务'    and title not like '%客户细分%'    and title not like '%KPI-每月聚焦'    and title not like '%KPI-今日视点'    and title not like '%经营盘点-收入一览' order by cnt desc,title ) b on a.MENUITEMID=b.MENUITEMID order by a.rownum";
			System.out.println("holyant:"+sql);
			PreparedStatement ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				if(rs.getString("cnt")!=null){
					MenuHitrateModel model = new MenuHitrateModel(rs.getRow(),rs.getString("menuitemida"), rs.getString("title"),
							rs.getString("menuitemidb"), Integer.parseInt(rs.getString("cnt")));
					resultFromSql.add(model);
				}else{
					resultFromSql.add(null);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//2.1 取出sql中不在3月拍照表中的数据
		List<MenuHitrateModel> resultFromSqlNotInMonth3 = new ArrayList<MenuHitrateModel>();
		for(int i=0;i<resultFromSql.size();i++){
			MenuHitrateModel model = resultFromSql.get(i);
			
			if(model==null||model.getMenuItemIdA()!=null){
				continue;
			}
			model.cleanTitle();
//			System.out.println(model.getTitle());
			resultFromSqlNotInMonth3.add(model);
		}
//		System.out.println(resultFromExcel.size());
		//2.2 对比
		List<MenuHitrateModel> result = new ArrayList<MenuHitrateModel>();
		for (int i = 0; i < resultFromExcel.size(); i++) {
			MenuHitrateModel model = resultFromExcel.get(i);
			System.out.print(""+i+"\t");
			for(int j=0;j<resultFromSqlNotInMonth3.size();j++){
				MenuHitrateModel model2 = resultFromSqlNotInMonth3.get(j);
				if(model2 == null||model2.getTitle()==null){
					continue;
				}
				if(model2.getTitle().equals(model.getTitle())){
					model2.setFlag(false);
					System.out.print(model.getTitle()+"\t"+model2.getCnt());
				}
			}
			System.out.println("");

		}
		
		//2.3列出没有匹配的新增菜单
		System.out.println("---------------------------------------------------------------------------------------------");
		System.out.println("---------------------------------------------------------------------------------------------");
		for(int i=0;i<resultFromSqlNotInMonth3.size();i++){
			MenuHitrateModel model = resultFromSqlNotInMonth3.get(i);
			if(model.isFlag()){
				System.out.println(model.getTitle()+"\t"+model.getCnt());
			}
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
