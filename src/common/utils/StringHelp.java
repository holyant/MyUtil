package common.utils;

import java.io.UnsupportedEncodingException;

/**
 *<p>Title: StringHelp.java </p>
 * <p>Description:
 *		字符串处理类
* </p>
 * <p>Copyright: Copyright (c) Asiainfo 2010</p>
 * <p>Author: zhaoguoxingyrkl</p>
 * <p>E-mail: zhaoguoxingyrkl@126.com</p>
 * <p>MSN: zhaoguoxingyrkl@126.com</p>
 * <p>Version 1.0</p>
 * <p>Feb 26, 2011 11:43:13 AM</p>
 * </p>
 */

public class StringHelp
{
	/**
	 * 通过GET方式传入的默认字符串重新编码成UTF-8
	 * @param String 原始参数
	 * @return 处理结果
	 */
	public static String chinaString(String str)
	{
		if (str == null)
		{
			str = "";
		} else
		{
			try
			{
				str = (new String(str.getBytes("iso-8859-1"), "UTF-8")).trim();
			} catch (Exception e)
			{
				e.printStackTrace(System.err);
			}
		}
		return str;
	}
	
	/**
	 * 解析URL编码
	 * @param String 原始参数
	 * @return 处理结果
	 */
	public static String urlDecoder(String str)
	{
		String result = null;
		try
		{
			result = java.net.URLDecoder.decode(str,"UTF-8");
		} catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * URL编码
	 * @param String 原始参数
	 * @return 处理结果
	 */
	public static String urlEncoder(String str)
	{
		String result = null;
		try
		{
			result = java.net.URLEncoder.encode(str,"UTF-8");
		} catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		return result;
	}
}
