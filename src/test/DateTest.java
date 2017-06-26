package test;

import java.io.UnsupportedEncodingException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

//import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang.xwork.time.DateUtils;
import org.junit.Test;

import activityRecords.util.DateUtil;

public class DateTest {
	@Test
	/**
	 * 日期格式转换
	 */
	public void test1(){
		Date date = new Date();
		DateUtil df = new DateUtil();
		System.out.println(df.getFormatDate(date, "yyyyMMdd"));
		//天数+1
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, 1);
		System.out.println(df.getFormatDate(c.getTime(), "yyyyMMdd"));
	}
	@Test
	/**
	 * 日期add方法
	 */
	public void test2(){
		DateUtils du = new DateUtils();
		System.out.println(du.addDays(new Date(), 4).toString());
	}
	@Test
	public void test5(){
		String str = "013-1-22 12:12:121";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//指定日期/时间解析是否不严格
		sdf.setLenient(false);
		Date d = null;
		try {
			d = sdf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println(sdf.format(d));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
