package common;

/**
 *<p>Title: DateFormat.java </p>
 * <p>Description:
 *		Java日期格式模板
  * </p>
 * <p>Copyright: Copyright (c) Asiainfo 2010</p>
 * <p>Author: zhaoguoxingyrkl</p>
 * <p>E-mail: zhaoguoxingyrkl@126.com</p>
 * <p>MSN: zhaoguoxingyrkl@126.com</p>
 * <p>Version 1.0</p>
 * <p>Mar 17, 2011 10:47:09 AM</p>
 * </p>
 */

public class DateFormat
{
	/**
	 * 年-月 日期格式,如 2010-05
	 */
	public static final String YM = "yyyy-MM";
	
	/**
	 * 年-月-日 日期格式,如 2010-05-01
	 */
	public static final String YMD = "yyyy-MM-dd";
	
	/**
	 * 年-月-日 时:分:秒 日期格式,如 2010-05-01 13:05:30
	 */
	public static final String YMDHMS = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * 年月 日期格式,如 201005
	 */
	public static final String YM_SIMPLE = "yyyyMM";
	
	/**
	 * 年月日 日期格式,如 20100501
	 */
	public static final String YMD_SIMPLE = "yyyyMMdd";
	
	/**
	 * 年月日 时:分:秒 日期格式,如 20100501 13:05:30
	 */
	public static final String YMDHMS_SIMPLE = "yyyyMMdd HH:mm:ss";
	
	/**
	 * 年月 日期格式,如 2010年05月
	 */
	public static final String YM_CHINA = "yyyy年MM月";
	
	/**
	 * 年月日 日期格式,如 2010年05月01日
	 */
	public static final String YMD_CHINA = "yyyy年MM月dd日";
	
	/**
	 * 年月日 时分秒 日期格式,如 2010年05月01日 13时05分30秒
	 */
	public static final String YMDHMS_CHINA = "yyyy年MM月dd日 HH时mm分ss秒";
}
