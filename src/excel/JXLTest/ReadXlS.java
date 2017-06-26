package excel.JXLTest;

import java.io.File;
import java.util.Date;

import jxl.Cell;
import jxl.CellType;
import jxl.DateCell;
import jxl.LabelCell;
import jxl.NumberCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class ReadXlS {
	public static void main(String[] args) {
		try {
			//打开文件
			Workbook book = Workbook.getWorkbook(new File("测试.xls"));
			//获得第一个工作表对象
			Sheet sheet = book.getSheet(0);
			//1
			//得到第一列第一行的单元格
//			Cell cell1 = sheet.getCell(0,0);
//			String result = cell1.getContents();
//			System.out.println(result);
			
			//2
			foo(sheet);
			
			//获得表格的行数和列数
			sheet.getRows();
			sheet.getColumns();
			//获得工作簿中工作表的个数
			book.getNumberOfSheets();
			//返回工作簿中工作表对象组
			book.getSheets();
			//获得工作表的名字
			sheet.getName();
			//获得某一列所有单元格
			sheet.getColumn(0);
			//获得某一行所有单元格
			sheet.getRow(0);
			
			book.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public static void foo(Sheet rs){
		String strc00 = null;
		double strc10 = 0.00;
		Date strc11 = null;

		Cell c00 = rs.getCell(0,0);
		Cell c10 = rs.getCell(1,0);
		Cell c11 = rs.getCell(1,1);

		if(c00.getType()==CellType.LABEL){
			LabelCell labelc00 = (LabelCell)c00;
			strc00 = labelc00.getString();
		}
		if(c10.getType()==CellType.NUMBER){
			NumberCell numc10 = (NumberCell)c10;
			strc10 = numc10.getValue();
		}
		if(c11.getType()==CellType.DATE){
			DateCell datec11 = (DateCell)c11;
			strc11 = datec11.getDate();
		}
		System.out.println("Cell(0,0) value:"+strc00+";type:"+c00.getType());
		System.out.println("Cell(1,0) value:"+strc10+";type:"+c10.getType());
		System.out.println("Cell(1,1) value:"+strc11+";type:"+c11.getType());
	}
}
