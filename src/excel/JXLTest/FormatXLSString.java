package excel.JXLTest;

import java.io.File;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class FormatXLSString {
	public static void main(String[] args) {
		try {
			//Excel获得文件
			Workbook wb = Workbook.getWorkbook(new File("测试.xls"));
			//打开一个文件的副本，并且制定数据写回到原文件
			WritableWorkbook book = Workbook.createWorkbook(new File("测试.xls"),wb);
			//添加一个工作表
			WritableSheet sheet = book.getSheet(0);
			//指定了字串格式：字体为TIMES，字号16，加粗显示。
			WritableFont font = new WritableFont(WritableFont.TIMES,16,WritableFont.BOLD);
			//WritableCellFormat类，通过它可以指定单元格的各种属性
			WritableCellFormat format = new WritableCellFormat(font);
			//指定数据的对齐方式
			format.setAlignment(jxl.format.Alignment.CENTRE);
			//设置自动换行
			format.setWrap(true);
			//使用Label的构造子，指定了字串被赋予哪种格式
			Label label = new Label(0,0,"fontTest",format);
			sheet.addCell(label);
			book.write();
			book.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
