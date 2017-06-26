package common.utils;

/**
 *
 * <p>Title: </p>
 * <p>Description: 数字格式化显示工具类</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author weilin.wu   wuwl2@asiainfo.com
 * @version 1.0
 *
 */

import java.text.*;
import java.util.Scanner;
import java.lang.Math;

public class NumberConvertor {
	public static String DEFAULT_FORMAT_PATTERN = "#,##0.00";
	public static String DEFAULT_NO_VALID_DATA = "-";

	/**
	 * 
	 */
	public NumberConvertor() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 按给定的模式格式化给定的对象（一般是数字，如：Integer，Double，Long或String）
	 * 
	 * @param obj
	 *            要被格式化的数字对象
	 * @param formatPattern
	 *            格式化模式
	 * @param fractionLen
	 *            要保留的小数位数（小数点往后推算，四舍五入）
	 * @param integerLen
	 *            要保留的整数位数（小数点往前推算）
	 * @return 格式化后的字串
	 */
	public static String format(Object obj, String formatPattern,
			int fractionLen, int integerLen) {
		String res = "";
		DecimalFormat theFormat = null;
		try {
			if ((formatPattern == null) || (formatPattern.length() < 1))
				theFormat = new DecimalFormat(DEFAULT_FORMAT_PATTERN);
			else
				theFormat = new DecimalFormat(formatPattern);
			if (fractionLen >= 0)
				theFormat.setMaximumFractionDigits(fractionLen);
			if (integerLen > 0)
				theFormat.setMaximumIntegerDigits(integerLen);
			if (obj instanceof String)
				obj = new Double((String) obj);
			// 如果要格式化的值为Double.MIN_VALUE，则不格式化，直接返回"无数据"
			if (obj.equals(new Double(Double.MIN_VALUE)))
				return DEFAULT_NO_VALID_DATA;
			res = theFormat.format(obj);
		} catch (Exception e) {
			// e.printStackTrace();
			res = (String) obj;
		}
		return res;
	}

	/**
	 * 
	 * @param obj
	 * @param formatPattern
	 * @param fractionLen
	 * @param integerLen
	 * @param dataUnit
	 * @return
	 */
	public static String format(Object obj, String formatPattern,
			int fractionLen, int integerLen, int dataUnit) {
		String res = "";
		if (obj instanceof String)
			obj = new Double((String) obj);
		// 如果要格式化的值为Double.MIN_VALUE，则不格式化，直接返回"无数据"
		if (obj.equals(new Double(Double.MIN_VALUE)))
			return DEFAULT_NO_VALID_DATA;

		obj = new Double(convertValue(((Double) obj).doubleValue(), dataUnit));
		// 如果数据为0，直接返回0
		if (((Double) obj).doubleValue() == 0)
			return "0";
		res = format(obj, formatPattern, fractionLen, integerLen)
				+ getUnit(dataUnit);
		return res;
	}

	/**
	 * 
	 * @param unit
	 * @return
	 */
	public static String getUnit(int unit) {
		float dataUnit = (float) Math.pow(10, unit);
		if (dataUnit == (float) 0.01) {
			return "%";
		}
		if (dataUnit == 1)
			return "";
		if (dataUnit == 10)
			return "十";
		if (dataUnit == 100)
			return "百";
		if (dataUnit == 1000)
			return "千";
		if (dataUnit == 10000)
			return "万";
		if (dataUnit == 100000)
			return "十万";
		if (dataUnit == 1000000)
			return "百万";
		if (dataUnit == 10000000)
			return "千万";
		if (dataUnit == 100000000)
			return "亿";
		return "";
	}

	/**
	 * 将value值转换为大单位值
	 * 
	 * @param value
	 * @param dataUnit
	 * @return
	 */
	public static double convertValue(double value, int dataUnit) {
		return value / Math.pow(10, dataUnit);
	}

	public static int NumberToTen(int beforeConversion, String number) {// 其它进制转成十进制
		double result = 0;// 声明转换后的数值
		String subString;
		for (int i = 0; i < number.length(); i++) {// 根据字符串的长度循环获得单个元素
			subString = number.substring(i, i + 1);// 将字符串按循环截取
			if (beforeConversion == 16) {// 判断传入的是否是十六进制
				subString = sixteenCharToNumber(subString);// 将字母转换成数字
			}
			result += Integer.parseInt(subString)// 返回转换的结果
					* Math.pow(beforeConversion, number.length() - i - 1);
		}
		return (int) result;
	}

	public static String TenToNumber(int afterConversion, String number) {// 十进制转成其他进制
		int current = Integer.parseInt(number);// 将字符串转换成整数
		String opResult = "";
		if (afterConversion == 16) {// 判断转换后的数制是否是16进制
			while (current >= afterConversion) {// 判断传入的数是否大于16，大于则逢16进一
				opResult += sixteenNumberToChar(current % afterConversion);// 将数字转换成字母
				current /= afterConversion;
			}
			if (current != 0)
				opResult += sixteenNumberToChar(current);// 最终余数
		} else {
			while (current >= afterConversion) {// 判断传入的值是否大于转换后的数制
				opResult += current % afterConversion;
				current /= afterConversion;
			}
			if (current != 0)
				opResult += current;// 最终余数
		}
		String riResult = "";// 倒序二进制字符串
		for (int i = opResult.length() - 1; i >= 0; i--) {// 根据二进制的转换方式进行循环输出
			riResult = riResult + opResult.substring(i, i + 1);
		}
		return riResult;
	}
	
	public static String sixteenCharToNumber(String s){// 十六进制字母对应数字
		String num="";
		if(s.equals("A") || s.equals("a"))
		num="10";
		else if(s.equals("B") || s.equals("b"))
		num="11";
		else if(s.equals("C") || s.equals("c"))
		num="12";
		else if(s.equals("D") || s.equals("d"))
		num="13";
		else if(s.equals("E") || s.equals("E"))
		num="14";
		else if(s.equals("F") || s.equals("f"))
		num="15";
		else
		num=s;
		return num;
		}

	public static String sixteenNumberToChar(int num){// 十六进制数字对应字母
		String c="";
		if(num==10) c="A";
		else if(num==11) c="B";
		else if(num==12) c="C";
		else if(num==13) c="D";
		else if(num==14) c="E";
		else if(num==15) c="F";
		else c=String.valueOf(num);
		return c;
		}

		public  void ncTest(){
			String number;// 要转换的数
			int beforeConversion,afterConversion;// 转换前的数制，转换后的数制
			String result="";// 经过数制转换后的结果
			String stop="";
			Scanner read=new Scanner(System.in);// 得到用户输入的值
	
			do{
				System.out.println("请输入三个参数（整数）：待转换的数据   转换前的数制  转换后的数制");
				number=read.next();
				beforeConversion=read.nextInt();
				afterConversion=read.nextInt();
				stop="Q";
			}while(stop!="Q");// 跳出循环
			try {
				if(beforeConversion!=10){// 判断转换前的数制是否是十进制
					String temp=String.valueOf(NumberToTen(beforeConversion,number));// 获得转换成十进制的数
					result=String.valueOf(TenToNumber(afterConversion, temp));// 十进制转换成其它进制
				}else{
					result=String.valueOf(TenToNumber(afterConversion, number));// 十进制转换成其它进制
				}
				System.out.println(beforeConversion+"进制的数:"+number+",转换成"+afterConversion+"进制的数为："+result);
			} catch (Exception e) {
				System.out.print("转换失败，请输入合法数据！");
				System.exit(-1);// 所有程序（方法，类等）停止，系统停止运行
			}
		}
	/**
	 * 例子
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		NumberConvertor nc = new NumberConvertor();
		nc.ncTest();
		System.out.println("-----------------------------------------------");
		// 取0.2356的百分比数（不保留小数）
		String res = NumberConvertor.format(new Double("0.2356"), "#%", 0, 0);
		System.out.println(res);
		// 取0.2356的百分比数（保留1位有效小数）
		res = NumberConvertor.format(new Double("0.2356"), "#%", 2, 0);
		System.out.println(res);

		// 取1234123.2356的格式化显示（保留2位有效小数）
		res = NumberConvertor.format(new Double("1234123.2356"), "#", 2, 0);
		System.out.println(res);
		// 取1234123.2356的格式化显示（保留2位有效小数）
		res = NumberConvertor.format(new Double("27.0783"), "", 0, 0);
		System.out.println(res);

		// 取1234123.2356的格式化显示（保留2位整数，2位有效小数）
		res = NumberConvertor.format(new Double("1234123.2356"), "", 2, 0);
		System.out.println(res);

		// 取1234123.2356的格式化显示（保留2位整数，2位有效小数）
		res = NumberConvertor.format("1234123.2356", "", 2, 2);
		System.out.println(res);

		// 取1234123.2356的格式化显示（保留2位整数，2位有效小数）
		res = NumberConvertor.format("1234123.20", "", 0, 0);
		System.out.println(res);

		res = NumberConvertor.format(new Double(Double.MIN_VALUE), "", 0, 0);
		System.out.println(res);

		// 更多帮助参看Java2 java.util.DecimalFormat 类的帮助文件
	}
}
