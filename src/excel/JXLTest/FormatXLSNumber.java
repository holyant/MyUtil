package excel.JXLTest;

import java.io.File;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.NumberFormat;
import jxl.write.NumberFormats;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.Number;

public class FormatXLSNumber {
	public static void main(String[] args) {
		try {
			//Excel获得文件
			Workbook wb = Workbook.getWorkbook(new File("测试.xls"));
			//打开一个文件的副本，并且制定数据写回到原文件
			WritableWorkbook book = Workbook.createWorkbook(new File("测试.xls"),wb);
			//添加一个工作表
			WritableSheet sheet = book.getSheet(0);
			//设置为整数
			WritableCellFormat integerFormat = new WritableCellFormat(NumberFormats.INTEGER);
			Number number2 = new Number(0,4,3.141519,integerFormat);
			sheet.addCell(number2);
			//设置为浮点小数
			WritableCellFormat floatFormat = new WritableCellFormat(NumberFormats.FLOAT);
			Number number3 = new Number(1,4,3.141519,floatFormat);
			sheet.addCell(number3);
			//设置展示格式
			NumberFormat fivedps = new NumberFormat("#.#####");
			WritableCellFormat fivedpsFormat = new WritableCellFormat(fivedps);
			Number number4 = new Number(2,4,3.141519,fivedpsFormat);
			sheet.addCell(number4);
			//设置字体大小等
			WritableFont times16font = new WritableFont(WritableFont.TIMES,16);
			WritableCellFormat fivedpsFontFormat = new WritableCellFormat(times16font,fivedps);
			Number number5 = new Number(3,4,3.141519,fivedpsFontFormat);
			sheet.addCell(number5);
			book.write();
			book.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
