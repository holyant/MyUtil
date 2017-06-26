package excel.JXLTest;

import java.io.File;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class StreamControl {
	public static void main(String[] args) {
		try {
			//构建Workbook对象，只读Workbook对象
			//Method1:创建可写入的Excel工作簿
			WritableWorkbook book = Workbook.createWorkbook(new File("测试.xls"));
			//Method2:将WritableWorkbook直接写入到输出流
			//OutputStream os = new FileOutputStream(new File("测试.xls"));
			//WritableWorkbook wwb = Work.createWorkbook(os);

			//添加一个工作表
			WritableSheet sheet = book.createSheet("第一页",2);
			sheet.addCell(new Label(0,0,"第一页的测试数据1"));
			book.write();
			book.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
