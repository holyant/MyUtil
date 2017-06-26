package test;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import org.junit.Test;

public class MathTest {
	@Test
	/**
	 * 保留2位小数
	 */
	public void test1(){
		double d = 12345.67890d;

		//方法一
		BigDecimal bd = new BigDecimal(d);
		double f1 = bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        System.out.println(f1);
        //方法二
        DecimalFormat df = new DecimalFormat("#.00");
        System.out.println(df.format(d));
        //方法三
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(2);
        System.out.println(nf.format(d));
        //方法四
        System.out.println(String.format("%.2f", d));
	}
}
