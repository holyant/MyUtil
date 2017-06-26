package common.utils;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author lummyliao
 * @version 1.0
 */
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.Vector;

public class DateConvertor
{
    public DateConvertor()
    {
    }

    /**
     Function: 将日期字符串按照指定格式转为日期形对象
     @param sDate
     @param sFormat
     This is to convert a string into date with given format
     Format can be "yyyy-MM-dd HH:mm:ss"
    */
  public static Date stringToDate( String sDate, String sFormat )
  {
    Date dDate = null;
    try
    {
      ParsePosition pos = new ParsePosition(0);
      SimpleDateFormat sdf = new SimpleDateFormat( sFormat );
      dDate = sdf.parse( sDate, pos );
    }
    catch ( Exception e )
    {
      return dDate;
    }
    return dDate;
  }

  /**
    Function: stringToDate
    @param sDate
    This is to convert a string into date with default format "yyyy-MM-dd"
  */
  public static Date stringToDate( String sDate )
  {
    return stringToDate( sDate, "yyyy-MM-dd" );
  }

  /**
    Function: stringToDateTime
    @param sDate
    This is to convert a string into date time with default format "yyyy-MM-dd HH:mm:ss"
  */
  public static Date stringToDateTime( String sDate )
  {
    return stringToDate( sDate, "yyyy-MM-dd HH:mm:ss" );
  }

  /**
    Function: dateToString
    @param dDate
    @param sFormat
    This is to convert a date into string with given format
    Format can be "yyyy-MM-dd HH:mm:ss"
  */
  public static String dateToString( Date dDate, String sFormat )
  {
    if ( dDate == null )
    {
      return "";
    }
    else
    {
      SimpleDateFormat sdf = new SimpleDateFormat( sFormat );
      return sdf.format( dDate );
    }
  }
  public static String dateToOracleStr( java.sql.Date dDate)
  {
  	String result = "";
    SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
    result = "To_date('" + sdf.format( dDate ) + "','yyyy-MM-dd')";
    return result;
  }

  public static java.sql.Date Str2SqlDate(String str){
  	Date date = stringToDate(str);
  	java.sql.Date sqlDate = new java.sql.Date(date.getTime());
  	return sqlDate;
  }
  /**
    Function: dateToString
    @param dDate
    This is to convert a date into string with default format "yyyy-MM-dd"
  */
  public static String dateToString( Date dDate )
  {
    return dateToString( dDate, "yyyy-MM-dd" );
  }

  /**
    Function: dateTimeToString
    @param dDate
    This is to convert a date into string with default format "yyyy-MM-dd HH:mm"
  */
  public static String dateTimeToString( Date dDate )
  {
    return dateToString( dDate, "yyyy-MM-dd HH:mm" );
  }

  /**
    Function: dateTimeSecToString
    @param dDate
    This is to convert a date into string with default format "yyyy-MM-dd HH:mm:ss"
  */
  public static String dateTimeSecToString( Date dDate )
  {
    return dateToString( dDate, "yyyy-MM-dd HH:mm:ss" );
  }
  public static String getNotNull(String sr){
	if ( sr != null)
		return sr.trim();
	else 
		return "";
  }
	public static String getNotNull(BigDecimal sr){
		if ( sr != null)
			return sr.toString();
		else 
			return "0.0";
	}

 /**
    Function: returnDate
    @param day
    @param month
    @param year
    This is to convert integer day, month,year to a java Date
  */
  public static Date returnDate (int day, int month, int year)
  {
      int mon=month-1;
      int ye;
      Date db;
      Calendar rightNow = Calendar.getInstance();
      if (year >=0 && year <80)
          ye=year+2000;
      else if (year >100)
          ye=year;
      else
          ye=year+1900;
      rightNow.set( Calendar.HOUR_OF_DAY, 0 );
      rightNow.set( Calendar.MINUTE, 0 );
      rightNow.set( Calendar.SECOND, 0 );
      rightNow.set(ye,mon,day);
      db=rightNow.getTime();
      return db;
  }

    /**
    * Function: leapYear find out the whether the input year is a leap year
    * @param theYear year going to test
    * @return boolean return true if the input year is a leap year
    */
      public static boolean leapYear (int theYear) {
          if (theYear%4==0)
              return true;
          else
              return false;
      }

  /**
  * Function: 比较两个日期中某个时间单位的间隔数
  *
  * @param type the type of diff go to find out like Year, Day or Month
  * @param toDate the more Recently day
  * @param fromDate the From day
  * @return int the different of the the day in integer
  */
    public static int dateDiff(String type, Date toDate , Date fromDate)
    {
        Calendar fromCalendar = Calendar.getInstance();
        fromCalendar.setTime(fromDate);

        Calendar toCalendar = Calendar.getInstance();
        toCalendar.setTime(toDate);

        int fromDay = fromCalendar.get(Calendar.DAY_OF_YEAR);
        int toDay = toCalendar.get(Calendar.DAY_OF_YEAR);
        int fromMonth = fromCalendar.get(Calendar.MONTH);
        int toMonth = toCalendar.get(Calendar.MONTH);
        int fromYear = fromCalendar.get(Calendar.YEAR);
        int toYear = toCalendar.get(Calendar.YEAR);
        int fromHour = fromCalendar.get(Calendar.HOUR_OF_DAY);
        int toHour = toCalendar.get(Calendar.HOUR_OF_DAY);
        int fromMinute = fromCalendar.get(Calendar.MINUTE);
        int toMinute = toCalendar.get(Calendar.MINUTE);
        int fromSecond = fromCalendar.get(Calendar.SECOND);
        int toSecond = toCalendar.get(Calendar.SECOND);

        int day = 0;
        int month = 0;
        int minute = 0;
        int second = 0;
        int hour = 0;

        for (int i = fromYear; i < toYear; i++)
        {
            int noOfDay;
            if (leapYear(i))
                noOfDay = 366;
            else
                noOfDay = 365;
            day = day + noOfDay;
            hour = hour + (noOfDay * 24);
            minute = minute + (noOfDay * 24 * 60);
            second = second + (noOfDay * 24 * 60 * 60);
            month = month + 12;
        }

        if (type.equalsIgnoreCase("yyyy"))
            return toYear - fromYear;
        else if (type.equalsIgnoreCase("m"))
            return toMonth - (fromMonth - month);
        else if (type.equalsIgnoreCase("d"))
            return toDay - (fromDay - day);
        else if (type.equalsIgnoreCase("h"))
            return toHour - (fromHour - hour);
        else if (type.equalsIgnoreCase("n"))
            return toMinute - (fromMinute - minute);
        else if (type.equalsIgnoreCase("s"))
            return toSecond - (fromSecond - second);
        else
            return 0;

    }

  /**
  * Function: 比较两个日期的大小
  *						no-time format
  *
  * @param toDate the more Recently day
  * @param fromDate the From day
  * @return >0 if 1st date after 2nd date
  *					=0 if 1st date equals 2nd date
  *					<0 if 1st date before 2nd date
  */
    public static int dateDiffNoTime(Date fromDate, Date toDate)
    {
        Calendar fromCalendar = Calendar.getInstance();
        fromCalendar.setTime(fromDate);

        Calendar toCalendar = Calendar.getInstance();
        toCalendar.setTime(toDate);

        int fromDay = fromCalendar.get(Calendar.DAY_OF_YEAR);
        int toDay = toCalendar.get(Calendar.DAY_OF_YEAR);
        int fromMonth = fromCalendar.get(Calendar.MONTH);
        int toMonth = toCalendar.get(Calendar.MONTH);
        int fromYear = fromCalendar.get(Calendar.YEAR);
        int toYear = toCalendar.get(Calendar.YEAR);

        //	Calculate from value
        int fromDateVal = fromYear * 10000 + fromMonth * 100 + fromDay * 1;

        //	Calculate to value
        int toDateVal = toYear * 10000 + toMonth * 100 + toDay * 1;

        return (fromDateVal - toDateVal);
    }

  /**
   *  Function: dateTimeToDBString
   *  @param dDate
   *  This is to convert a date into string with given format
   */
  public static String dateTimeToDBString( Date dDate )
  {
    if ( dDate == null )
    {
      return "";
    }
    else
    {
      SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
      sdf.setTimeZone( TimeZone.getTimeZone( "GMT" ) );
      return "TO_DATE( '" + sdf.format( dDate ) + "', 'YYYY-MM-DD HH24:MI:SS' )";
    }
  }

  /**
   *  Function: dateTimeToDBStringNoTime
   *  @param dDate
   *  This is to convert a date into string with given format
   */
  public static String dateTimeToDBStringNoTime( Date dDate)
  {
    if ( dDate == null )
    {
      return "";
    }
    else
    {
      SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
      sdf.setTimeZone( TimeZone.getTimeZone( "GMT" ) );
      return "TO_DATE( '" + sdf.format( dDate ) + "', 'YYYY-MM-DD' )";
    }
  }

  /**
   *  Function: dbDateToJavaDate
   *  @param dDate
   *  This is to add 8 hours to java date to offset oracle java time discrepancy
   */
  public static Date dbDateToJavaDate( Date dDate)
  {
    if ( dDate == null )
    {
      return null;
    }
    else
    {
      Calendar cal = Calendar.getInstance();
      cal.setTime(dDate);
      cal.add(Calendar.HOUR, 8);
      return cal.getTime();
    }
  }

  /**
   * Function: 取得指定日期所属week，周一的日期
   * 注：此处返回周一，不是返回周日
   * @param strYYYYMMDD 日期字符串
   * @return String 处理后的日期字符串
   */
  public static String getThisWeekMonday(String strYYYYMMDD)
  {
      Calendar caldTmp = Calendar.getInstance();
      //注意：月份的起始值为０而不是１，所以要设置八月时，我们用７而不是8 -> calendar.set(Calendar.MONTH, 7);
      caldTmp.set(DateConvertor.getIntDateYear(strYYYYMMDD),
                  DateConvertor.getIntDateMonth(strYYYYMMDD) - 1,
                  DateConvertor.getIntDateDay(strYYYYMMDD));
      int nDayOfWeek = caldTmp.get(Calendar.DAY_OF_WEEK);
      //System.out.println("  "+strYYYYMMDD+"||   Calendar.DAY_OF_WEEK="+Calendar.DAY_OF_WEEK+"; caldTmp.get(Calendar.DAY_OF_WEEK)="+caldTmp.get(Calendar.DAY_OF_WEEK));
      caldTmp.add(Calendar.DATE, -(caldTmp.get(Calendar.DAY_OF_WEEK) -1));
      //区别此处不同于西方：周日为每周的第一天，周六为每周最后一天
      // 周一为每周的第一天，周日为每周最后一天
      // 此处得到的caldTmp为周日，必须将其做相应修改
      // 此处得到的caldTmp为周日故需加一天；注意：若指定日期刚好是周日，则需减6天
      if(nDayOfWeek == 1)
      {
          caldTmp.add(Calendar.DATE,-6);
      }else{
          caldTmp.add(Calendar.DATE,1);
      }
       return DateConvertor.dateToString(caldTmp.getTime(),"yyyy-MM-dd");


  }

  /**
   * Function: 取得指定日期所属week，周日的日期
   * 注：此处返回周日，不是返回周六(按照中国工作周习惯，不同于西方将周六作为周末)
   * @param strYYYYMMDD 日期字符串
   * @return String 处理后的日期字符串
   */
  public static String getThisWeekSunday(String strYYYYMMDD)
  {
      String strThisWeekFirstDate = DateConvertor.getThisWeekMonday(strYYYYMMDD);
      return DateConvertor.offsetDate(strThisWeekFirstDate,6,"day");
  }

  /**
   * Function : 取得给定日期为所属月份第几周
   * @param strYYYYMMDD
   * @return
   */
  public static int getNumWeekOfMonth(String strYYYYMMDD)
  {
      Calendar caldTmp = Calendar.getInstance();
      //按照西方周算出的结果，一周：周日->周六
      //中国人习惯，一周：周一->周日
      caldTmp.setFirstDayOfWeek(Calendar.MONDAY);
      caldTmp.set(DateConvertor.getIntDateYear(strYYYYMMDD),
                  DateConvertor.getIntDateMonth(strYYYYMMDD)-1 ,
                  DateConvertor.getIntDateDay(strYYYYMMDD));
      int nWeekOfMonth = caldTmp.get(Calendar.WEEK_OF_MONTH);
      //注意以下几种情况:
      //  先判断上月最后一天对应的周末
      //   	1.若当前日期<=上月最后一天对应的周末，则返回0(给定日期属于上月)
      //	2.若当前日期>=上月最后一天对应的周末
      //		判断该月一号是否为周日？
      //       2.1 是周日， return nWeekOfMonth ;
      //	   2.2 否则  return nWeekOfMonth -1 ;
      // 如：2004-7-1~4都是属上个月最后一周；而2004-8-1为上月最后一周sunday, 2004-8-2属于8月份第一周
      int nDateDiffNoTime = 0;
      //本月一号
      String strFirstDayOfThisMonth = DateConvertor.getThisMonthFirstDate(strYYYYMMDD);
      //取得上月最后一周的sunday
      //上月最后一天
      String strLastDateOfPreMonth = DateConvertor.offsetDate(
          strFirstDayOfThisMonth, -1, "day");
      //   上月最后一天的sunday
      String strSundayOfLastWeekOfPreMonth = DateConvertor.getThisWeekSunday(strLastDateOfPreMonth);
      //  判断给定日期是否小于上月最后一天对应的周末
      //    注：此处返回的日期字符串可能同传入参数不完全一样2004-08-01 与 2004-8-1;所以必须转为date比较
      Date dParam = DateConvertor.stringToDate(strYYYYMMDD);
      Date dSundayOfLastWeekOfPreMonth = DateConvertor.stringToDate(strSundayOfLastWeekOfPreMonth);
      nDateDiffNoTime = DateConvertor.dateDiffNoTime(dParam,dSundayOfLastWeekOfPreMonth);
      //System.out.println("--  nWeekOfMonth="+nWeekOfMonth+"; nDateDiffNoTime="+nDateDiffNoTime+"; strYYYYMMDD="+strYYYYMMDD+" ;strSundayOfLastWeekOfPreMonth="+strSundayOfLastWeekOfPreMonth);
      if(nDateDiffNoTime<=0){
      	return 0;
      }else{
      	//判断该月一号是否为周日？
      	// 本月1号对应的sunday
        String strSundayOfFirstDayOfThisMonth = DateConvertor.getThisWeekSunday(strFirstDayOfThisMonth);
        // 将给定日期参数规整为标准格式 如：2004-8-1 -> 2004-08-01
        Date dTmp = DateConvertor.stringToDate(strYYYYMMDD,"yyyy-MM-dd");
        String strStdYYYYMMDD = DateConvertor.dateToString(dTmp,"yyyy-MM-dd");
        if(strStdYYYYMMDD.compareToIgnoreCase(strSundayOfFirstDayOfThisMonth)==0){
        	//本月1号为周日
        	return nWeekOfMonth;
        }else{
        	//本月1号不为周日
        	//判断本月一号是否大于上月最后一天对应的周日
        	Date dFistDayOfThisMonth = DateConvertor.stringToDate(strFirstDayOfThisMonth,"yyyy-MM-dd");
        	nDateDiffNoTime = DateConvertor.dateDiffNoTime(dFistDayOfThisMonth,dSundayOfLastWeekOfPreMonth);
        	if(nDateDiffNoTime>0){
        		return nWeekOfMonth;
        	}else{
        		return nWeekOfMonth-1;
        	}

        }
      }

  }

  /**
   * Function: 取得指定日期对应月的第一天日期
   * @param strYYYYMMDD 日期字符串
   * @return String 处理后的日期字符串
   */
  public static String getThisYearFirstDate(String strYYYYMMDD,String sFormat)
  {
      Calendar caldTmp = Calendar.getInstance();
      //取得该年第一天日期
      caldTmp.set(DateConvertor.getIntDateYear(strYYYYMMDD),
                  0, 1);
      return DateConvertor.dateToString(caldTmp.getTime(),sFormat);
  }

  /**
   * Function: 取得指定日期对应月的第一天日期
   * @param strYYYYMMDD 日期字符串
   * @return String 处理后的日期字符串
   */
  public static String getThisMonthFirstDate(String strYYYYMMDD)
  {
      Calendar caldTmp = Calendar.getInstance();
      //取得该月第一天日期
      caldTmp.set(DateConvertor.getIntDateYear(strYYYYMMDD),
                  DateConvertor.getIntDateMonth(strYYYYMMDD) - 1, 1);
      return DateConvertor.dateToString(caldTmp.getTime(),"yyyy-MM-dd");
  }

  /**
   * Function: 取得指定日期对应月的最后一天日期
   * @param strYYYYMMDD 日期字符串
   * @return String 处理后的日期字符串
   */
  public static String getThisMonthLastDate(String strYYYYMMDD)
  {
      Calendar caldTmp = Calendar.getInstance();
      //取得本月第一天日期
      caldTmp.set(DateConvertor.getIntDateYear(strYYYYMMDD),
                  (DateConvertor.getIntDateMonth(strYYYYMMDD) - 1), 1);
      //取得该月的下一个月第一天日期
      caldTmp.add(Calendar.MONTH,1);
      //下月第一天减一天即为本月最后一天
      caldTmp.add(Calendar.DATE,-1);
      return DateConvertor.dateToString(caldTmp.getTime(),"yyyy-MM-dd");
  }
  /**
   * Function: 取得指定日期上月的当天
   * @param strYYYYMMDD 日期字符串
   * @return String 处理后的日期字符串
   */
  public static String getLastMonthCurrDate(String strYYYYMMDD)
  {
      Calendar caldTmp = Calendar.getInstance();
      //取得本月第一天日期
      caldTmp.set(DateConvertor.getIntDateYear(strYYYYMMDD),
                  (DateConvertor.getIntDateMonth(strYYYYMMDD) - 1), DateConvertor.getIntDateDay(strYYYYMMDD));
      //取得该月的下一个月第一天日期
      caldTmp.add(Calendar.MONTH,-1);
      return DateConvertor.dateToString(caldTmp.getTime(),"yyyy-MM-dd");
  }
  /**
   * Function: 取得指定日期上月的当天
   * @param Date 日期类型
   * @return Date 日期类型
   */
  public static Date getLastMonthCurrDate(Date now)
  {
	  SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	  String strYYYYMMDD = sdf.format(now);
      Calendar caldTmp = Calendar.getInstance();
      //取得本月第一天日期
      caldTmp.set(DateConvertor.getIntDateYear(strYYYYMMDD),
                  (DateConvertor.getIntDateMonth(strYYYYMMDD) - 1), DateConvertor.getIntDateDay(strYYYYMMDD));
      //取得该月的下一个月第一天日期
      caldTmp.add(Calendar.MONTH,-1);
      return caldTmp.getTime();
  }
  
  /**
   * Function: 取得指定日期上月的最后一天
   * @param strYYYYMMDD 日期字符串
   * @return String 处理后的日期字符串
   */
  public static String getLastMonthFirstDate(String strYYYYMMDD,String patten){
      Calendar caldTmp = Calendar.getInstance();
      caldTmp.set(DateConvertor.getIntDateYear(strYYYYMMDD),
                  (DateConvertor.getIntDateMonth(strYYYYMMDD) - 2), 1);
      return DateConvertor.dateToString(caldTmp.getTime(),patten);
  }
  
  /**
   * Function: 取得指定日期上月的最后一天
   * @param strYYYYMMDD 日期字符串
   * @return String 处理后的日期字符串
   */
  public static String getLastMonthLastDate(String strYYYYMMDD,String patten){
	  String theFirstDateCurrMonthString = getFirstDateCurrMonth(strYYYYMMDD);
      Calendar caldTmp = Calendar.getInstance();
      caldTmp.set(DateConvertor.getIntDateYear(theFirstDateCurrMonthString),
                  (DateConvertor.getIntDateMonth(theFirstDateCurrMonthString) - 1), DateConvertor.getIntDateDay(theFirstDateCurrMonthString)-1);
      return DateConvertor.dateToString(caldTmp.getTime(),patten);
  }
  
  /**
   * Function: 取得指定日期的当月的第一天
   * @param strYYYYMMDD 日期字符串
   * @return String 处理后的日期字符串
   */
  public static String getFirstDateCurrMonth(String strYYYYMMDD){
      Calendar caldTmp = Calendar.getInstance();
      //取得本月第一天日期
      caldTmp.set(DateConvertor.getIntDateYear(strYYYYMMDD),
                  (DateConvertor.getIntDateMonth(strYYYYMMDD) - 1), 1);
      return DateConvertor.dateToString(caldTmp.getTime(),"yyyyMMdd");
  }

  /**
   *  Function: getSmallestDate
   *  @param Vector - date
   *  @return Date
   *  This is to get the smallest date from a vector
   */
  public static Date getSmallestDate( Vector vDate )
  {
    int nDeleteIndex = -1;
    Date dDate = returnDate( 31,12,2999 );
    for ( int i = 0; i < vDate.size(); i++ )
    {
      Date dPrevDate = dDate;
      Date dCurrDate = ( Date ) vDate.elementAt( i );
      if ( dCurrDate.before( dPrevDate ) )
      {
        dDate = dCurrDate;
        nDeleteIndex = i;
      }
    }
    if ( nDeleteIndex > -1 )
    {
      return ( Date ) vDate.remove( nDeleteIndex );
    }
    return null;
  }

  /**
   * getDateYear 取得日期字符串中的年字符串
   * @param strYYYYMMDD
   * @return
   */
  public  static  String getStrDateYear(String strYYYYMMDD)
  {
      return strYYYYMMDD.substring(0,4);
  }

  /**
   * getIntDateYear 取得日期字符串中的年数值
   * @param strYYYYMMDD
   * @return
   */
  public static int getIntDateYear(String strYYYYMMDD)
  {
      return Integer.parseInt(getStrDateYear(strYYYYMMDD));
  }

  /**
   * getStrDateMonth 取得日期字符串中的月份字符串
   * @param strYYYYMMDD
   * @return
   */
  public  static  String getStrDateMonth(String strYYYYMMDD)
  {
      //确定日期分割符号
      String strIntervalMark = "";
      if(strYYYYMMDD.indexOf("/")>0){
          strIntervalMark = "/";
      }else if(strYYYYMMDD.indexOf("-")>0){
          strIntervalMark = "-";
      }

      String strMonth = "";
      String strTmp = "";
      int nFirstMarkNum = 0;
      int nSecondMarkNum = 0;
      nFirstMarkNum = strYYYYMMDD.indexOf(strIntervalMark);
      strTmp = strYYYYMMDD.substring(nFirstMarkNum+1);
      nSecondMarkNum = nFirstMarkNum + strTmp.indexOf(strIntervalMark);
      if( strIntervalMark.compareTo("") == 0)
      {
          //YYYYMMDD
          strMonth = strYYYYMMDD.substring(4,6);
      }else{
          strMonth = strYYYYMMDD.substring(nFirstMarkNum+1,nSecondMarkNum+1);
      }
      return strMonth;
  }

  /**
   * getIntDateMonth 取得日期字符串中月份的数值
   * @param strYYYYMMDD
   * @return
   */
  public static int getIntDateMonth(String strYYYYMMDD)
  {
      return Integer.parseInt(getStrDateMonth(strYYYYMMDD));
  }

  /**
   * getDateYear 取得日期字符串中天的字符串
   * @param strYYYYMMDD
   * @return
   */
  public  static  String getStrDateDay(String strYYYYMMDD)
  {
      //确定日期分割符号
      String strIntervalMark = "";
      if (strYYYYMMDD.indexOf(" ")>0)
          strYYYYMMDD = strYYYYMMDD.substring(0,strYYYYMMDD.indexOf(" "));
      if(strYYYYMMDD.indexOf("/")>0){
          strIntervalMark = "/";
      }else if(strYYYYMMDD.indexOf("-")>0){
          strIntervalMark = "-";
      }

      String strDay = "";
      int nLastMarkNum = 0;
      nLastMarkNum = strYYYYMMDD.lastIndexOf(strIntervalMark);

      if( strIntervalMark.compareTo("")==0 )
      {
          //YYYYMMDD
          strDay = strYYYYMMDD.substring(6);
      }else{
          strDay = strYYYYMMDD.substring(nLastMarkNum+1);
      }
      return strDay;
  }

  /**
   * getIntDateDay 取得日期字符串中的天数值
   * @param strYYYYMMDD
   * @return
   */
  public static int getIntDateDay(String strYYYYMMDD)
  {
      return Integer.parseInt(getStrDateDay(strYYYYMMDD));
  }

  /**
   *  Function: sortDateAsc
   *  @param Vector - date vector
   *  @return Vector - sorted date vector in ascending order
   *  This is to sort the input date vector in ascending order
   */
  public static Vector sortStrDateAsc( Vector vDate )
  {
    Vector vSortedDate = new Vector();

    int nSmallestIndex = 0;
    while ( vDate.size() > 0 )
    {
      Date dDate = getSmallestDate( vDate );
      if ( dDate != null )
      {
        vSortedDate.addElement( dDate );
      }
    }
    return vSortedDate;
  }

  /**
   *  Function: add days to a Date object
   *  @param date - Date
   *  @param nNumberOfDay - number of day
   *  @return Date
   *  Function to add number of days to a Date object
   */
  public static Date addDays(Date dInput, int nNumberOfDay)
  {
    if(dInput==null)
    {
      return null ;
    }
    java.util.Calendar c = java.util.Calendar.getInstance() ;
    c.setTime(dInput) ;
    c.add(java.util.Calendar.DATE,nNumberOfDay) ;
    return c.getTime() ;
  }

  /**
   *  Function: dateNoTimeToDBStringNoTime
   *  @param dDate
   *  This is to convert a java date into string with given format for DB search
   */
  /*
  public static String dateNoTimeToDBStringNoTime( Date dDate)
  {
    if ( dDate == null )
    {
      return "";
    }
    else
    {
      Date dDateNoTime = DateConvertor.returnDate( dDate.getDate(), dDate.getMonth()+1 ,dDate.getYear() +1900);
      SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
      sdf.setTimeZone( TimeZone.getTimeZone( "GMT" ) );
      return "TO_DATE( '" + sdf.format( dDateNoTime ) + "', 'YYYY-MM-DD' )";
    }
  }
*/

  /**
   *  Function: trimMillis
   *  @param date - Date
   *  @return Date
   *  Function to trim millisecond (set ms to 0)
   */
  public static Date trimMillis( Date dDate )
  {
    if ( dDate == null )
    {
      return null;
    }
    Calendar cal = Calendar.getInstance();
    cal.setTime(dDate);
    cal.set(Calendar.MILLISECOND, 0);

    return cal.getTime();
  }

  /**
   *  Function: add second to a Date object
   *  @param date - Date
   *  @param nNumberOfSecond - number of second (can be negative )
   *  @return Date
   *  Function to add number of second to a Date object
   */
  public static Date addSecond(Date dInput, int nNumberOfSecond)
  {
    if(dInput==null)
    {
      return null ;
    }
    java.util.Calendar c = java.util.Calendar.getInstance() ;
    c.setTime(dInput) ;
    c.add(java.util.Calendar.SECOND,nNumberOfSecond) ;
    return c.getTime() ;
  }

  /**
   * 将指定日期偏移一段时间
   * @param strYYYYMMDD yyyy/mm/dd 结束日期
   * @param nOffsetNum 前/后推数量
   * @param strOffsetUnit 前/后推周期单位 day,week,month,year
   * @return String YYYY-MM-DD
   */
  public static String offsetDate(String strYYYYMMDD, int nOffsetNum, String strOffsetUnit)
  {
      int nYear = 0;
      int nMonth = 0;
      int nDay = 0;
      Calendar caldTmp = Calendar.getInstance();
      nYear = DateConvertor.getIntDateYear(strYYYYMMDD);
      nMonth = DateConvertor.getIntDateMonth(strYYYYMMDD);
      nDay = DateConvertor.getIntDateDay(strYYYYMMDD);
      caldTmp.set(nYear,nMonth-1,nDay);

      if(strOffsetUnit.compareToIgnoreCase("day") == 0)
      {
          caldTmp.add(Calendar.DATE, nOffsetNum);
      }else if(strOffsetUnit.compareToIgnoreCase("week") == 0){
          caldTmp.add(Calendar.DATE, nOffsetNum*7);
      }else if(strOffsetUnit.compareToIgnoreCase("month") == 0){
          caldTmp.add(Calendar.MONTH,nOffsetNum);
      }else if(strOffsetUnit.compareToIgnoreCase("year") == 0){
          caldTmp.add(Calendar.YEAR,nOffsetNum);
      }

      return DateConvertor.dateToString(caldTmp.getTime(),"yyyy-MM-dd");
  }

  /**
   * 将指定日期偏移一段时间
   * @param strYYYYMMDD yyyy/mm/dd 结束日期
   * @param nOffsetNum 前/后推数量
   * @param strOffsetUnit 前/后推周期单位 day,week,month,year
   * @return Date
   */
  public static Date offsetDate(Date date, int nOffsetNum, String strOffsetUnit)
  {
      int nYear = 0;
      int nMonth = 0;
      int nDay = 0;
      Calendar caldTmp = Calendar.getInstance();
      caldTmp.setTime(date);

      if(strOffsetUnit.compareToIgnoreCase("day") == 0)
      {
      	  if (nOffsetNum == 0)
  	               return date;
          caldTmp.add(Calendar.DATE, nOffsetNum);
      }else if(strOffsetUnit.compareToIgnoreCase("week") == 0){
          caldTmp.add(Calendar.DATE, nOffsetNum*7);
      }else if(strOffsetUnit.compareToIgnoreCase("month") == 0){
          caldTmp.add(Calendar.MONTH,nOffsetNum);
      }else if(strOffsetUnit.compareToIgnoreCase("year") == 0){
          caldTmp.add(Calendar.YEAR,nOffsetNum);
      }
      Date dNewDate = caldTmp.getTime();
      return dNewDate;
  }

	  /**
	   * 判断给定日期是所属星期的第几天
	   * added by wwl 2004-8-4
	   * @param strYYYYMMDD
	   * @return
	   */
	  public static int getDayOfWeek(String strYYYYMMDD) {
	      int res = 1;
	      int nYear = 0;
	      int nMonth = 0;
	      int nDay = 0;
	      Calendar caldTmp = Calendar.getInstance();
	      nYear = DateConvertor.getIntDateYear(strYYYYMMDD);
	      nMonth = DateConvertor.getIntDateMonth(strYYYYMMDD);
	      nDay = DateConvertor.getIntDateDay(strYYYYMMDD);
	      caldTmp.set(nYear,nMonth-1,nDay);

	      res = caldTmp.get(Calendar.DAY_OF_WEEK) - 1;
	      //如果为0，说明当天是周日，按中国人的习惯应该是一周的第7天
	      if (res == 0)
	          res = 7;
	      return res;
	  }

	  /**
	   * 获得给定日期所在月共有多少个星期（判断规则：星期一的日期是几月份，则此周属于几月份）
	   * added by wwl 2004-8-5
	   * @param strYYYYMMDD
	   * @return
	   */
	  public static int getWeekCountOfMonth(String strYYYYMMDD) {
	      int res = 1;
	      String lastDate;
	      //取本月的最后一天日期
	      lastDate = DateConvertor.getThisMonthLastDate(strYYYYMMDD);
	      //取本月最后一天是本月的第几个星期
	      res = DateConvertor.getNumWeekOfMonth(lastDate);
	      return res;
	  }

	  /**
	   * 取给定日期（strYYYYMMDD）所在月的第n(weekIndex)个星期的周一日期
	   * added by wwl 2004-8-4
	   * @param strYYYYMMDD
	   * @param weekIndex
	   * @return
	   */
	  public static String getMondayOfWeek(String strYYYYMMDD,int weekIndex) {
	      int nYear = 0;
	      int nMonth = 0;
	      int nDay = 0;
	      Calendar caldTmp = Calendar.getInstance();
	      nYear = DateConvertor.getIntDateYear(strYYYYMMDD);
	      nMonth = DateConvertor.getIntDateMonth(strYYYYMMDD);
	      nDay = DateConvertor.getIntDateDay(strYYYYMMDD);
	      caldTmp.set(nYear,nMonth-1,1);

	      //下面为什么这样做，我也不知道，只是这样做就能得到我想要的结果，所以就这样做了……
	      //得到这个月1日是星期几
	      int dayOfWeek = caldTmp.get(Calendar.DAY_OF_WEEK);
	      //如果是周日或周一，则按正常情况处理
	      if ( (dayOfWeek == Calendar.SUNDAY) || (dayOfWeek == Calendar.MONDAY) )
	          caldTmp.set(Calendar.WEEK_OF_MONTH,weekIndex);
	      //否则，当前周数加一
	      else
	          caldTmp.set(Calendar.WEEK_OF_MONTH,weekIndex + 1);

	      //设置日期为当周的第二天（即周一）
	      caldTmp.set(Calendar.DAY_OF_WEEK,2);

	      String tmpDate = DateConvertor.dateToString(caldTmp.getTime(),"yyyy-MM-dd");

	      //System.out.println("tmpDate: " + tmpDate + " " + DateConvertor.getNumWeekOfMonth(tmpDate));

	      return tmpDate;
	  }

	  /**
	   * 获得从开始日期到截止日期间所有有效日期的字符数组
	   * added by wwl 2004-8-5
	   * @param beginDate
	   * @param endDate
	   * @return String[n] = "YYYY-MM-DD"
	   */
	  public static String[] getDayList(String beginDate,String endDate) {
	      ArrayList theList = new ArrayList();
	      int beginYear,beginMonth,beginDay,endYear,endMonth,endDay;
	      int intBeginDate,intEndDate;
	      beginYear = DateConvertor.getIntDateYear(beginDate);
	      endYear = DateConvertor.getIntDateYear(endDate);
	      //java中的月份从0-11，所以正常的月份需要 - 1
	      beginMonth = DateConvertor.getIntDateMonth(beginDate) - 1;
	      endMonth = DateConvertor.getIntDateMonth(endDate) - 1;
	      beginDay = DateConvertor.getIntDateDay(beginDate);
	      endDay = DateConvertor.getIntDateDay(endDate);

	      GregorianCalendar bCal = new GregorianCalendar(beginYear,beginMonth,beginDay);
	      GregorianCalendar eCal = new GregorianCalendar(endYear,endMonth,endDay);
	      Date eDate = eCal.getTime();
	      Date bDate = bCal.getTime();
	      String tmpDate;
	      //对比开始日期与截止日期的大小
	      while (bDate.compareTo(eDate) <= 0) {
	          tmpDate = DateConvertor.dateToString(bDate,"yyyy-MM-dd");
	          //tmpDate = DateConvertor.formatDate(tmpDate.substring(5));
	          //System.out.println(tmpDate);
	          theList.add(tmpDate);
	          //开始日期加1天
	          bCal.add(Calendar.DATE,1);
	          bDate = bCal.getTime();
	      }
	      String[] res = new String[theList.size()];
	      res = (String[])theList.toArray(res);
	      return res;
	  }

	  /**
	   * 获得从开始月份的开始星期到截至月份的截至星期间的所有有效星期的字符数组
	   * added by wwl 2004-8-5
	   * @param beginDate YYYY-MM
	   * @param endDate YYYY-MM
	   * @param beginWeek
	   * @param endWeek
	   * @return String[n] = "YYYY-MM|第几周" （年月 与 第几周之间用'|'分隔）
	   */
	  public static String[] getWeekList(String beginDate,String endDate,
	          						int beginWeek,int endWeek) {
	      ArrayList theList = new ArrayList();
	      int beginYear,beginMonth,beginDay,endYear,endMonth,endDay;
	      int intBeginDate,intEndDate,weekCntOfMonth,tmpInt;
	      beginYear = DateConvertor.getIntDateYear(beginDate);
	      endYear = DateConvertor.getIntDateYear(endDate);
	      //java中的月份从0-11，所以正常的月份需要 - 1
	      beginMonth = DateConvertor.getIntDateMonth(beginDate) - 1;
	      endMonth = DateConvertor.getIntDateMonth(endDate) - 1;
	      //日期对于判断当前月有几个星期没有关系，所以日期可以为1-31的任何值，我们取一个较中间的值
	      beginDay = 10;
	      endDay = 10;

	      GregorianCalendar bCal = new GregorianCalendar(beginYear,beginMonth,beginDay);
	      GregorianCalendar eCal = new GregorianCalendar(endYear,endMonth,endDay);
	      Date eDate = eCal.getTime();
	      Date bDate = bCal.getTime();
	      String tmpDate,tmpStr;
	      //如果开始日期比截至日期小，则不断循环
	      while (bDate.compareTo(eDate) < 0) {
	          tmpDate = DateConvertor.dateToString(bDate,"yyyy-MM-dd");
	          //获得当前月共有多少星期
	          weekCntOfMonth = DateConvertor.getWeekCountOfMonth(tmpDate);
	          //从本月起始星期 一直 循环到 截至星期
	          for(tmpInt = beginWeek; tmpInt <= weekCntOfMonth; tmpInt++) {
	              tmpStr = tmpDate.substring(0,7) + "|" + tmpInt;
	              //System.out.println(tmpStr);
	              theList.add(tmpStr);
	          }

	          //起始星期回复到第一周
	          beginWeek = 1;

	          //开始日期加1个月
	          bCal.add(Calendar.MONTH,1);
	          bDate = bCal.getTime();
	      }

	      //如果起始日期与截至日期相同
	      if (bDate.compareTo(eDate) == 0) {
	          tmpDate = DateConvertor.dateToString(bDate,"yyyy-MM-dd");
	          //获得当前月共有多少星期
	          weekCntOfMonth = DateConvertor.getWeekCountOfMonth(tmpDate);
	          //判断当月的星期总数与截至星期参数的大小（截至星期不能大于星期总数）
	          if (endWeek > weekCntOfMonth)
	              endWeek = weekCntOfMonth;
	          for(tmpInt = beginWeek; tmpInt <= endWeek; tmpInt++) {
	              tmpStr = tmpDate.substring(0,7) + "|" + tmpInt;
	              //System.out.println(tmpStr);
	              theList.add(tmpStr);
	          }
	      }

	      String[] res = new String[theList.size()];
	      res = (String[])theList.toArray(res);
	      return res;
	  }

	  /**
	   * 获取从开始月份到截至月份间所有有效月份的字符数组
	   * added by wwl 2004-8-5
	   * @param beginDate
	   * @param endDate
	   * @return String[m] = "YYYY-MM"
	   */
	  public static String[] getMonthList(String beginDate,String endDate) {
	      ArrayList theList = new ArrayList();
	      int beginYear,beginMonth,beginDay,endYear,endMonth,endDay;
	      int intBeginDate,intEndDate,weekCntOfMonth,tmpInt;
	      beginYear = DateConvertor.getIntDateYear(beginDate);
	      endYear = DateConvertor.getIntDateYear(endDate);
	      //java中的月份从0-11，所以正常的月份需要 - 1
	      beginMonth = DateConvertor.getIntDateMonth(beginDate) - 1;
	      endMonth = DateConvertor.getIntDateMonth(endDate) - 1;
	      //我们只关心年月的大小，但日期也能影响对比，所以我们把日期设置为相同
	      beginDay = 10;
	      endDay = 10;

	      GregorianCalendar bCal = new GregorianCalendar(beginYear,beginMonth,beginDay);
	      GregorianCalendar eCal = new GregorianCalendar(endYear,endMonth,endDay);
	      Date eDate = eCal.getTime();
	      Date bDate = bCal.getTime();
	      String tmpDate,tmpStr;
	      //如果开始日期比截至日期小，则不断循环
	      while (bDate.compareTo(eDate) <= 0) {
	          tmpDate = DateConvertor.dateToString(bDate,"yyyy-MM-dd");
	          tmpStr = tmpDate.substring(0,7);
	          //System.out.println(tmpStr);
	          theList.add(tmpStr);

	          //开始日期加1个月
	          bCal.add(Calendar.MONTH,1);
	          bDate = bCal.getTime();
	      }

	      String[] res = new String[theList.size()];
	      res = (String[])theList.toArray(res);
	      return res;
	  }

	  /**
	   * 获取从开始日期到截至日期间所有有效年份的字符数组
	   * added by wwl 2004-8-5
	   * @param beginDate
	   * @param endDate
	   * @return String[m] = "YYYY"
	   */
	  public static String[] getYearList(String beginDate,String endDate) {
	      ArrayList theList = new ArrayList();
	      int beginYear,beginMonth,beginDay,endYear,endMonth,endDay;
	      int intBeginDate,intEndDate,weekCntOfMonth,tmpInt;
	      beginYear = DateConvertor.getIntDateYear(beginDate);
	      endYear = DateConvertor.getIntDateYear(endDate);
	      //我们只关心年的大小，但月份日期也能影响对比，所以我们把月份日期设置为相同
	      beginMonth = 10;
	      endMonth = 10;
	      beginDay = 10;
	      endDay = 10;

	      GregorianCalendar bCal = new GregorianCalendar(beginYear,beginMonth,beginDay);
	      GregorianCalendar eCal = new GregorianCalendar(endYear,endMonth,endDay);
	      Date eDate = eCal.getTime();
	      Date bDate = bCal.getTime();
	      String tmpDate,tmpStr;
	      //如果开始日期比截至日期小，则不断循环
	      while (bDate.compareTo(eDate) <= 0) {
	          tmpDate = DateConvertor.dateToString(bDate,"yyyy-MM-dd");
	          tmpStr = tmpDate.substring(0,4);
	          //System.out.println(tmpStr);
	          theList.add(tmpStr);

	          //开始日期加1个年
	          bCal.add(Calendar.YEAR,1);
	          bDate = bCal.getTime();
	      }

	      String[] res = new String[theList.size()];
	      res = (String[])theList.toArray(res);
	      return res;
	  }

	  /**
	   * 格式化"MM-dd"或"yyyy-MM"格式的字串，去除字串中月份或日期数字中的"0"
	   * added by wwl 2004-8-19
	   * @param date "MM-dd"或"yyyy-MM"格式的字串
	   * @return
	   */
	  public static String formatDate(String date) {
	      String res = "";
	      if (date == null)
	          return res;
	      int year,month,day;
	      try {
	          //"MM-dd"格式的字串
	          if (date.length() == 5) {
		          //去除月份和日期前面的"0"
		          month = Integer.parseInt(date.substring(0,2));
		          day = Integer.parseInt(date.substring(3));
		          res = month + "-" + day;
	          }
	          //"yyyy-MM"格式的字串
	          else if (date.length() == 7) {
	              year = Integer.parseInt(date.substring(0,4));
		          month = Integer.parseInt(date.substring(5));
		          res = year + "-" + month;
	          }
	      } catch (Exception e) {
	          res = date;
	      }
	      return res;
	  }

	  /**
	   * 把日期字符转换为中文含义的日期字符
	   * added by wwl 2004-8-19
	   * @param date "yyyy-MM-dd"或"MM-dd"或"yyyy-MM"格式的字串
	   * @return
	   */
	  public static String formatDateToCN(String date) {
	      String res = "";
	      if (date == null)
	          return res;
	      int year,month,day;
	      try {
		      //是"MM-dd"格式的字串
		      if (date.length() == 5) {
		          month = Integer.parseInt(date.substring(0,2));
		          day = Integer.parseInt(date.substring(3));
		          res = month + "月" + day + "日";
		      }
		      //"yyyy-MM"格式的字串
		      else if (date.length() == 7) {
		          year = Integer.parseInt(date.substring(0,4));
		          month = Integer.parseInt(date.substring(5,7));
		          res = year + "年" + month + "月";
		      }
		      //是"yyyy-MM-dd"格式的字串
		      else if (date.length() == 10) {
		          year = Integer.parseInt(date.substring(0,4));
		          month = Integer.parseInt(date.substring(5,7));
		          day = Integer.parseInt(date.substring(8));
		          res = year + "年" + month + "月" + day + "日";
		      }
		      else
		          res = date;
	      } catch (Exception e) {
	          res = date;
	      }
	      return res;
	  }
	  /**
	   * 把日期字符串去掉时间，返回日期字符串
	   * added by chenzq 2005-7-26
	   * @param strDate "yyyy-MM-dd"或"yyyy-MM-dd hh:mm:ss"格式的字串
	   * @return res "yyyy-mm-dd"
	   */
	  public static String formatDateStr(String strDate) {
	      String res = "";
	      if (strDate == null)
	          return res;

	      try {
		      if(strDate.length() > 10 && strDate.indexOf(" ") >= 0) 
		      	res = strDate.substring(0,strDate.indexOf(" "));
		      else
		      	res = strDate;
		      	  
	      } catch (Exception e) {
	          res = strDate;
	      }
	      return res;
	  }
	   

	  /**
	   * 返回当天所在的年月
	   * added by wwl 2004-9-27
	   * @return "yyyyMM"
	   */
	  public static String getTodayYearMonth() {
	  	String res = "";
		Calendar caldTmp = Calendar.getInstance();
		caldTmp.setTime(new Date());
		if (caldTmp.get(Calendar.MONTH) + 1 < 10)
			res = caldTmp.get(Calendar.YEAR) + "0" + (caldTmp.get(Calendar.MONTH) + 1);
		else
			res = caldTmp.get(Calendar.YEAR) + "" + (caldTmp.get(Calendar.MONTH) + 1);
		return res;
	  }

	  /**
	   * 返回当天的日期
	   * added by wwl 2004-11-18
	   * @return "YYYY-MM-DD"
	   */
	  public static String getToday() {
		String res = DateConvertor.dateToString(new Date());
		return res;
	  }

	  /**
	   * 把长整形时间秒数转换为年月日格式
	   * @param longMills
	   * @return YYYY-MM-DD
	   */
	  public static String convertLongMillsToYYYYMMDD(long longMills) {
		Calendar caldTmp = Calendar.getInstance();
		caldTmp.setTimeInMillis(longMills);
		String res = caldTmp.get(Calendar.YEAR) + "-" + (caldTmp.get(Calendar.MONTH) + 1) + "-" + caldTmp.get(Calendar.DAY_OF_MONTH);
	  	return res;
	  }
	  /**
	   * 把日期字符串转换成年月日星期格式
	   * @param yyyy-MM-dd
	   * @return 年-月-日 星期
	   */
	  public static String converStringToYYYYMMDDEE(String date){
			Date tempdate= new Date();
			date = date.replace("-", "").substring(0,4)+"-"+date.replace("-", "").substring(4,6)+"-"+date.replace("-", "").substring(6,8);
			SimpleDateFormat d= new SimpleDateFormat("yyyy-MM-dd");
			String mydate = "";
			String temp = date.substring(0,4)+"年"+date.substring(5,7)+"月"+date.substring(8,10)+"日";
			try {
				tempdate= d.parse(date);
				
				 SimpleDateFormat sf = new SimpleDateFormat("E",Locale.CHINA);
				 mydate=temp+" "+sf.format(tempdate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  return mydate;
	  }
	  /**
	   * 获取某个时间段的天数
	   * 
	   * @param dateStr1 :yyyy-MM-dd    dateStr2:yyyy-MM-dd 
	   * @return int 
	   */
	  public static int getDaysBySub(String dateStr1, String dateStr2){
		  SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		  SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		  try {
		   dateStr1 = sdf.format(sdf2.parse(dateStr1));
		   dateStr2 = sdf.format(sdf2.parse(dateStr2));
		  } catch (ParseException e) {
		   e.printStackTrace();
		  }
		  int year1 = Integer.parseInt(dateStr1.substring(0, 4));
		  int month1 = Integer.parseInt(dateStr1.substring(4, 6));
		  int day1 = Integer.parseInt(dateStr1.substring(6, 8));
		  int year2 = Integer.parseInt(dateStr2.substring(0, 4));
		  int month2 = Integer.parseInt(dateStr2.substring(4, 6));
		  int day2 = Integer.parseInt(dateStr2.substring(6, 8));
		  Calendar c1 = Calendar.getInstance();
		  c1.set(Calendar.YEAR, year1);
		  c1.set(Calendar.MONTH, month1 - 1);
		  c1.set(Calendar.DAY_OF_MONTH, day1);
		  Calendar c2 = Calendar.getInstance();
		  c2.set(Calendar.YEAR, year2);
		  c2.set(Calendar.MONTH, month2 - 1);
		  c2.set(Calendar.DAY_OF_MONTH, day2);
		  long mills =
		   c1.getTimeInMillis() > c2.getTimeInMillis()
		    ? c1.getTimeInMillis() - c2.getTimeInMillis()
		    : c2.getTimeInMillis() - c1.getTimeInMillis();
		  return (int) (mills / 1000 / 3600 / 24);
		
	  }
    public static void main(String[] args)
    {
        DateConvertor dateConvertor1 = new DateConvertor();
        
        String theLastDayByPreviousMonthString = DateConvertor.getLastMonthLastDate("20150504", "yyyy-MM-dd");
        System.out.println(theLastDayByPreviousMonthString);
        
        /*
        System.out.println("  args[0]="+args[0]);
        String strYYYY = "";
        strYYYY = dateConvertor1.getStrDateYear(args[0]);

        String strMonth = "";
        strMonth = dateConvertor1.getStrDateMonth(args[0]);

        String strDay = "";
        strDay = dateConvertor1.getStrDateDay(args[0]);

        System.out.println(" strYYYY="+strYYYY+"; strMonth="+strMonth+"; strDay="+strDay);
        */

        //测试将日期改为指定格式
        //System.out.println(" today format(yyyy-mm-dd) = " + DateConvertor.dateToString(Calendar.getInstance().getTime(),"yyyy-MM-dd"));
        //测试偏移日期函数
        //System.out.println(" 偏移日期"+DateConvertor.offsetDate("2004-7-30",1,"year"));
        //测试偏移日期函数对润年的支持，润年2004-02-29 非润年2004-02-28, test ok!!!
        //System.out.println(" 偏移日期 2004-02-29 前推一年:"+DateConvertor.offsetDate("2004-02-29",1,"year"));
        //测试取得指定日期对应周第一天--周一日期
        //System.out.println(" 2004-8-1 Monday  日期对应周第一天 "+DateConvertor.getThisWeekMonday("2004-8-1"));
        //System.out.println(" 2004-8-2 Tuesday 日期对应周第一天 "+DateConvertor.getThisWeekMonday("2004-8-2"));
        //System.out.println(" 2004-8-8 Sunday  日期对应周第一天 "+DateConvertor.getThisWeekMonday("2004-8-8"));
        //System.out.println(" 2004-8-9 Sunday  日期对应周第一天 "+DateConvertor.getThisWeekMonday("2004-8-9"));
        //测试取得指定日期对应周最后一天--周日日期
        //System.out.println(" 2004-7-12 Monday  日期对应周最后一天 "+DateConvertor.getThisWeekSunday("2004-7-12"));
        //System.out.println(" 2004-7-13 Tuesday 日期对应周最后一天 "+DateConvertor.getThisWeekSunday("2004-7-13"));
        //System.out.println("2004-8-1   日期对应周最后一天"+DateConvertor.getThisWeekSunday("2004-8-1"));
        //System.out.println("2004-8-2   日期对应周最后一天"+DateConvertor.getThisWeekSunday("2004-8-2"));
        //测试指定日期属于该月的第几周
        //System.out.println(" 2004-8-1 该月的第几周 "+DateConvertor.getNumWeekOfMonth("2004-8-1"));
        //System.out.println(" 2004-8-2 该月的第几周 "+DateConvertor.getNumWeekOfMonth("2004-8-2"));
        //System.out.println(" 2004-8-15 该月的第几周 "+DateConvertor.getNumWeekOfMonth("2004-8-15"));
        //System.out.println(" 2004-8-16 该月的第几周 "+DateConvertor.getNumWeekOfMonth("2004-8-16"));
        //System.out.println(" 2004-7-3 该月的第几周 "+DateConvertor.getNumWeekOfMonth("2004-7-3"));
        //System.out.println(" 2004-7-4 该月的第几周 "+DateConvertor.getNumWeekOfMonth("2004-7-4"));
        //System.out.println(" 2004-7-5 该月的第几周 "+DateConvertor.getNumWeekOfMonth("2004-7-6"));
        //System.out.println(" 2004-7-11 该月的第几周 "+DateConvertor.getNumWeekOfMonth("2004-7-11"));
        //System.out.println(" 2004-3-3 该月的第几周 "+DateConvertor.getNumWeekOfMonth("2004-3-3"));
        //System.out.println(" 2004-3-7 该月的第几周 "+DateConvertor.getNumWeekOfMonth("2004-3-7"));
        //System.out.println(" 2004-3-8 该月的第几周 "+DateConvertor.getNumWeekOfMonth("2004-3-8"));
        //测试取得指定日期对应月的第一天日期
        //System.out.println(" 取得指定日期对应月的第一天日期"+DateConvertor.getThisMonthFirstDate("2004-7-12"));
        //测试取得指定日期对应月的最后一天日期
        //System.out.println(" 取得指定日期对应月的最后一天日期"+DateConvertor.getThisMonthLastDate("2004-7-12"));
        //System.out.println(" 取得指定日期对应月的最后一天日期"+DateConvertor.getThisMonthLastDate("2004-2-12"));
        //System.out.println(" 取得指定日期对应月的最后一天日期"+DateConvertor.getThisMonthLastDate("2003-2-12"));


        //System.out.println(" 2004-8-2 该周的第几天 "+DateConvertor.getDayOfWeek("2004-08-02"));
        //System.out.println(" 2004-8-2 所在月份的第2周的星期一日期是：" + DateConvertor.getMondayOfWeek("2004-8-2",2));
        //DateConvertor.getYearList("2003-08-02","2004-09-02");
        //System.out.println("" + DateConvertor.getNumWeekOfMonth("2004-08-08"));

        //System.out.println(" 2004-8-2 该周的第几天 "+DateConvertor.getDayOfWeek("2004-8-2"));
        //System.out.println(" 2004-8-3 该周的第几天 "+DateConvertor.getDayOfWeek("2004-8-3"));
        //System.out.println(" 2004-8-1 该周的第几天 "+DateConvertor.getDayOfWeek("2004-8-1"));
        //System.out.println(" 2004-8-15 该周的第几天 "+DateConvertor.getDayOfWeek("2004-8-15"));
        //System.out.println(" 2004-8-15 该周的第几天 "+DateConvertor.getDayOfWeek("2004-8-15"));
        //System.out.println(DateConvertor.offsetDate("2004-7-30",-1,"year"));
        //System.out.println(DateConvertor.getLastMonthCurrDate("2011-03-30"));
        // System.out.println(DateConvertor.converStringToYYYYMMDDEE("20110518"));
        
//        System.out.println("  获取某一时间段的天数:"+DateConvertor.getDaysBySub("2011-05-20", "2011-05-30"));
//        System.out.println("  偏移begin："+DateConvertor.offsetDate("2011-05-20",-10,"day"));
//        System.out.println("  偏移end："+DateConvertor.offsetDate("2011-05-30",-10,"day"));
        
       

       
    }

}
