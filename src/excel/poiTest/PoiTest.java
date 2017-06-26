package excel.poiTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import jxl.write.Label;
import jxl.write.WritableSheet;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFHyperlink;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.ss.util.CellRangeAddress;
import org.junit.Test;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

import db2Test.util.MyProperties;


public class PoiTest {
	@Test
	public void test() throws Exception {
		// 创建Excel的工作书册 Workbook,对应到一个excel文档
		HSSFWorkbook wb = new HSSFWorkbook();

		// 创建Excel的工作sheet,对应到一个excel文档的tab
		HSSFSheet sheet = wb.createSheet("sheet1");

		// 设置excel每列宽度
		sheet.setColumnWidth(0, 4000);
		sheet.setColumnWidth(1, 3500);

		// 创建字体样式
		HSSFFont font = wb.createFont();
		font.setFontName("Verdana");
		font.setBoldweight((short) 100);
		font.setFontHeight((short) 300);
		font.setColor(HSSFColor.BLUE.index);

		// 创建单元格样式
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		// 设置边框
		style.setBottomBorderColor(HSSFColor.RED.index);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);

		style.setFont(font);// 设置字体

		// 创建Excel的sheet的一行
		HSSFRow row = sheet.createRow(0);
		row.setHeight((short) 500);// 设定行的高度
		// 创建一个Excel的单元格
		HSSFCell cell = row.createCell(0);

		// 合并单元格(startRow，endRow，startColumn，endColumn)
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 2));

		// 给Excel的单元格设置样式和赋值
		cell.setCellStyle(style);
		cell.setCellValue("hello world");

		// 设置单元格内容格式
		HSSFCellStyle style1 = wb.createCellStyle();
		style1.setDataFormat(HSSFDataFormat.getBuiltinFormat("h:mm:ss"));

		style1.setWrapText(true);// 自动换行

		row = sheet.createRow(1);

		// 设置单元格的样式格式

		cell = row.createCell(0);
		cell.setCellStyle(style1);
		cell.setCellValue(new Date());

		// 创建超链接
		HSSFHyperlink link = new HSSFHyperlink(HSSFHyperlink.LINK_URL);
		link.setAddress("http://www.baidu.com");
		cell = row.createCell(1);
		cell.setCellValue("百度");
		cell.setHyperlink(link);// 设定单元格的链接

		FileOutputStream os = new FileOutputStream(
				"C:\\Users\\lsg\\Desktop\\workbook.xls");
		wb.write(os);
		os.close();
	}

	@Test
	public void test2() {
		// TODO Auto-generated method stub
		try {
			InputStream in = new FileInputStream("C:\\Users\\lsg\\Desktop\\workbook.xls");
			Workbook work = new HSSFWorkbook(in);
			// 得到excel的第0张表
			Sheet sheet = work.getSheetAt(0);
			// 得到第1行的第一个单元格的样式
			Row rowCellStyle = sheet.getRow(1);
			CellStyle columnOne = rowCellStyle.getCell(0).getCellStyle();
			// 这里面的行和列的数法与计算机里的一样，从0开始是第一
			// 填充title数据
			Row row = sheet.getRow(0);
			Cell cell = row.getCell(0);
			cell.setCellValue("2010年花名测");
			int i = 2;// 计数器
			int number = 0;
			// 得到行，并填充数据和表格样式
			for (; i < 10; i++) {
				row = sheet.createRow(i);// 得到行
				cell = row.createCell(0);// 得到第0个单元格
				cell.setCellValue("琳" + i);// 填充值
				cell.setCellStyle(columnOne);// 填充样式
				cell = row.createCell(1);
				cell.setCellValue("女");
				cell.setCellStyle(columnOne);// 填充样式
				cell = row.createCell(2);
				cell.setCellValue(i + 20);
				cell.setCellStyle(columnOne);// 填充样式
				// .....给每个单元格填充数据和样式
				number++;
			}
			// 创建每个单元格，添加样式，最后合并
			row = sheet.createRow(i);
			cell = row.createCell(0);
			cell.setCellValue("总计：" + number + "个学生");// 填充值
			cell.setCellStyle(columnOne);
			cell = row.createCell(1);
			cell.setCellStyle(columnOne);
			cell = row.createCell(2);
			cell.setCellStyle(columnOne);
			// 合并单元格
			sheet.addMergedRegion(new CellRangeAddress(i, i, 0, 2));
			FileOutputStream os = new FileOutputStream(
					"D:\\report\\workbook.xls");
			work.write(os);
			os.close();
		} catch (FileNotFoundException e) {
			System.out.println("文件路径错误");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("文件输入流错误");
			e.printStackTrace();
		}

	}

	@Test
	public void test3() throws Exception {
		// 创建Excel的工作书册 Workbook,对应到一个excel文档
		HSSFWorkbook wb = new HSSFWorkbook();

		// 创建Excel的工作sheet,对应到一个excel文档的tab
		HSSFSheet sheet = wb.createSheet("sheet1");

		// 创建Excel的sheet的一行
		HSSFRow row = sheet.createRow(0);
		// 创建一个Excel的单元格
		HSSFCell cell = row.createCell(0);
		// 给Excel的单元格设置样式和赋值
		cell.setCellValue("hello world");

		row = sheet.createRow(1);
		cell = row.createCell(3);
		// 给Excel的单元格设置样式和赋值
		cell.setCellValue("hello world");
		sheet = wb.createSheet("sheet2");

		// 创建Excel的sheet的一行
		row = sheet.createRow(0);
		// 创建一个Excel的单元格
		cell = row.createCell(0);
		// 给Excel的单元格设置样式和赋值
		cell.setCellValue("hello world");

		row = sheet.createRow(1);
		cell = row.createCell(3);
		// 给Excel的单元格设置样式和赋值
		cell.setCellValue("hello world");

		FileOutputStream os = new FileOutputStream(
				"C:\\Users\\lsg\\Desktop\\workbook.xls");
		wb.write(os);
		os.close();
	}

	@Test
	public void test4() throws Exception {
		// 创建Excel的工作书册 Workbook,对应到一个excel文档
		HSSFWorkbook wb = new HSSFWorkbook();

		// 准备数据
		List<List<String>> list = new ArrayList<List<String>>();
		List<String> subList = null;
		for (int i = 0; i < 70000; i++) {
			subList = new ArrayList<String>();
			for (int j = 0; j < 2; j++) {
				subList.add("holyant:" + i);
			}
			list.add(subList);
		}


		int rownum = 0;
		int sheetnum = 0;
		//生成名为"第一页"的工作表，参数0表示第一页
		HSSFSheet sheet = wb.createSheet("sheet1");
		for(int i=0;i<list.size();i++){
			if(rownum>=65536){
				rownum = 0;
				sheetnum++;
				sheet = wb.createSheet("第"+sheetnum+"页");
			}
			HSSFRow row = sheet.createRow(rownum);
			for(int j=0;j<list.get(i).size();j++){
				HSSFCell cell = row.createCell(j);
				cell.setCellValue(list.get(i).get(j));
			}
			
			rownum++;
			
		}
//		// 创建Excel的sheet的一行
//		HSSFRow row = sheet.createRow(0);
//		// 创建一个Excel的单元格
//		HSSFCell cell = row.createCell(0);
//		// 给Excel的单元格设置样式和赋值
//		cell.setCellValue("hello world");
//
//		row = sheet.createRow(1);
//		cell = row.createCell(3);
//		// 给Excel的单元格设置样式和赋值
//		cell.setCellValue("hello world");

		FileOutputStream os = new FileOutputStream(
				"C:\\Users\\lsg\\Desktop\\workbook.xls");
		wb.write(os);
		os.close();

	}
	@Test
	public void test5() throws IOException{
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFRow row = null;
		HSSFCell cell = null;
		HSSFSheet sheet = wb.createSheet("sheet1");
		//台头
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 10));
		
		row =sheet.createRow(0);
		cell = row.createCell(0);
		this.setTitle(wb,cell,"浙江钧嘉生物科技有限公司   【销售单】");
		row.setHeightInPoints((short)70);
		
		//客户\联系人\录单日期
		row =sheet.createRow(1);
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 1));
		this.setContent(wb, row,0, "客户名称：");
		
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 2, 3));
		this.setContent(wb, row,2, "客户名称");
		
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 4, 5));
		this.setContent(wb, row,4, "联系人：");
		
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 6, 7));
		this.setContent(wb, row,6, "联系人");
		
		this.setContent(wb, row,8, "录单日期:");
		
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 9, 10));
		this.setContent(wb, row,10, "录单日期");
		
		//客户地址,联系电话,单据编号
		row =sheet.createRow(2);
		sheet.addMergedRegion(new CellRangeAddress(2, 2, 0, 1));
		cell = row.createCell(0);
		this.setContent(wb, row,0, "客户地址:");
		
		sheet.addMergedRegion(new CellRangeAddress(2, 2, 2, 3));
		this.setContent(wb, row,2, "杭州市西湖区西溪河下路37号");
		
		sheet.addMergedRegion(new CellRangeAddress(2, 2, 4, 5));
		this.setContent(wb, row,4, "联系电话");
		
		sheet.addMergedRegion(new CellRangeAddress(2, 2, 6, 7));
		this.setContent(wb, row,6, "18268846268");
		
		this.setContent(wb, row,8, "单据编号");
		
		sheet.addMergedRegion(new CellRangeAddress(2, 2, 9, 10));
		this.setContent(wb, row,10, "20140500039");
		
		//订货品牌
		row =sheet.createRow(3);
		
		sheet.addMergedRegion(new CellRangeAddress(3, 3, 0, 1));
		this.setContent(wb, row,0, "订货品牌");
		
		sheet.addMergedRegion(new CellRangeAddress(3, 3, 2, 10));
		this.setContent(wb, row,2, "曲之恋品牌");
		
		//附加说明
		row =sheet.createRow(4);
		
		sheet.addMergedRegion(new CellRangeAddress(4, 4, 0, 1));
		this.setContent(wb, row,0, "附加说明：");
		
		sheet.addMergedRegion(new CellRangeAddress(4, 4, 2, 10));
		this.setContent(wb, row,2, "如有差异，请在收到货后三日之内电话告之仓库，过期作废不予处理，多谢合作。");
		
		//序号,商品编号,商品名称,,规格,单位,数量,单价,金额,备注,
		row =sheet.createRow(5);
		this.setContent(wb, row,0, "序号");
		
		this.setContent(wb, row,1, "商品编号");
		
		sheet.addMergedRegion(new CellRangeAddress(5, 5, 2, 3));
		this.setContent(wb, row,2, "商品名称");
		
		this.setContent(wb, row,4, "规格");
		
		this.setContent(wb, row,5, "单位");
		
		this.setContent(wb, row,6, "数量");
		
		this.setContent(wb, row,7, "单价");
		
		this.setContent(wb, row,8, "金额");
		
		sheet.addMergedRegion(new CellRangeAddress(5, 5, 9, 10));
		this.setContent(wb, row,9, "备注");
		
		//合计金额：		大写：	贰佰元整					小写	¥200	元
		row =sheet.createRow(9);
		
		sheet.addMergedRegion(new CellRangeAddress(9, 9, 0, 1));
		this.setContent(wb, row,0, "合计金额：");
		this.setContent(wb, row,2, "大写：");
		this.setContent(wb, row,8, "小写：");
		this.setContent(wb, row,10, "元");
		
		//货品件数合计:
		row =sheet.createRow(10);
		
		sheet.addMergedRegion(new CellRangeAddress(10, 10, 0, 6));
		this.setContent(wb, row,0, "此销售单为客户收货和查帐重要凭据，请妥善保管。");
		sheet.addMergedRegion(new CellRangeAddress(10, 10, 7, 8));
		this.setContent(wb, row,7, "货品件数合计:");
		this.setContent(wb, row,10, "件");
		
		//服务热线		0571-87717555、87717558			制单人：	女王大人		发货人：	孟妍妍	
		row =sheet.createRow(11);
		sheet.addMergedRegion(new CellRangeAddress(11, 11, 0, 1));
		this.setContent(wb, row,0, "服务热线	");
		sheet.addMergedRegion(new CellRangeAddress(11, 11, 2, 4));
		this.setContent(wb, row,2, "0571-87717555、87717558	");
		this.setContent(wb, row,5, "制单人：	");
		sheet.addMergedRegion(new CellRangeAddress(11, 11, 6, 7));
		this.setContent(wb, row,6, "女王大人");
		this.setContent(wb, row,8, "发货人：");
		sheet.addMergedRegion(new CellRangeAddress(11, 11, 9, 10));
		this.setContent(wb, row,9, "孟妍妍");
		
		Properties properties = MyProperties.getProperties("/sys.properties");
		
		FileOutputStream os = new FileOutputStream(
				properties.getProperty("orderFilePath")+ "workbook.xls");
		wb.write(os);
		os.close();
	}
	public HSSFCell setTitle(HSSFWorkbook wb,HSSFCell cell,String value){
		HSSFFont font = wb.createFont();
		HSSFCellStyle style = wb.createCellStyle();
		font.setFontHeightInPoints((short)22);
		
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		style.setFont(font);
		cell.setCellStyle(style);
		cell.setCellValue(value);
		return cell;
	}
	public HSSFCell setContent(HSSFWorkbook wb,HSSFRow row,int column,String value){
		row.setHeightInPoints((short)23);
		HSSFFont font = wb.createFont();
		HSSFCellStyle style = wb.createCellStyle();
		HSSFCell cell = row.createCell(column);
		font.setFontHeightInPoints((short)13);
		style.setAlignment(CellStyle.ALIGN_LEFT);
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		style.setFont(font);
		cell.setCellStyle(style);
		cell.setCellValue(value);
		return cell;
	}
}
