package excel.JXLTest;

import java.io.File;

import org.apache.commons.io.FileUtils;

import jxl.Workbook;
import jxl.write.Number;
import jxl.write.NumberFormat;
import jxl.write.NumberFormats;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableImage;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class CellControl {
	public static void main(String[] args) {
		try {
			//Excel获得文件
			Workbook wb = Workbook.getWorkbook(new File("测试.xls"));
			//打开一个文件的副本，并且制定数据写回到原文件
			WritableWorkbook book = Workbook.createWorkbook(new File("测试.xls"),wb);
			//添加一个工作表
			WritableSheet sheet = book.getSheet(0);
			//1合并单元格
			//WritableSheet.mergeCells(int m,int n,int p,int q);
			//作用是从(m,n)到(p,q)的单元格全部合并
			//合并既可以是横向的，也可以是纵向的。合并后的单元格不能再次进行合并，否则会出发异常
			//当试图合并一个已经合并的单元格的时候，如果数量少的话，只会在面板上打出几个warning,
			//数量大的话，整个文件将无法输出
//			sheet.mergeCells(2,0,3,1);

			//2行高和列宽
			//WritableSheet.setRowView(int i,int height);
			//指定第i+1行的高度
//			sheet.setRowView(1,200);
			//WritableSheet.setColumnView(int i,int width);
			//指定第i+1行的宽度
//			sheet.setColumnView(0,30);
			//操作图片
			File file = new File("a.png");
			WritableImage image = new WritableImage(1,4,2,2,file);
			sheet.addImage(image);
			book.write();
			book.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
