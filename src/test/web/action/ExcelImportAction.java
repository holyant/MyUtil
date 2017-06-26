package test.web.action;

import java.io.File;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.apache.commons.validator.GenericValidator;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;
import org.codehaus.jackson.map.ObjectMapper;

import test.model.DwWoPlusKey4bSaleDtlBean;
import test.model.ExcelColumnBean;

import com.opensymphony.xwork2.ActionSupport;
import common.utils.FileUtil;
import common.utils.PropertiesUtil;

public class ExcelImportAction extends ActionSupport implements
		ServletResponseAware, ServletRequestAware, ServletContextAware {
	Logger logger = Logger.getLogger(this.getClass());
	private HttpServletResponse response;
	private HttpServletRequest request;
	private ServletContext servletContext;
	private PropertiesUtil fileUploadPro = null;
	private ArrayList<DwWoPlusKey4bSaleDtlBean> datas = new ArrayList<DwWoPlusKey4bSaleDtlBean>();

	public String init() {
//		this.request.setAttribute("errMsg", "holyant");
//		logger.debug(this.request);
//		logger.debug(ServletActionContext.getRequest());
//		return this.ERROR;
		return this.SUCCESS;
	}

	public String upload() {
		fileUploadPro = new PropertiesUtil(this.servletContext
				.getRealPath("/conf/fileProperties.properties"));

		try {
			String filePath = fileUploadPro.getProperty("filePath");

			FileUtil.createFolder(filePath);

			// 获取所有文件列表
			MultiPartRequestWrapper wrapper = (MultiPartRequestWrapper) request;
			File uploadFile = wrapper.getFiles("file")[0];
			String columnString = this.request.getParameter("columnString");
			// 获取需要校验的字段
			ExcelColumnBean[] columns = new ObjectMapper().readValue(
					columnString, ExcelColumnBean[].class);
			String re = validateMakeData(uploadFile,columns);
			
			if(re!=null){
				this.request.setAttribute("errMsg", re);
				return ERROR;
			}
			logger.debug("holyant:re = "+re);

		} catch (Exception e) {
			logger.error("上传文件异常!", e);
			return INPUT;
		}

		return SUCCESS;
	}


	public String validateMakeData(File excelFile,ExcelColumnBean[] columnMetas){
		//打开文件
		Workbook book = null;
		StringBuilder re = new StringBuilder();
		try {
			book = Workbook.getWorkbook(excelFile);
			//获得第一个工作表对象
			Sheet sheet = book.getSheet(0);
			
			//获得表格的行数和列数
			int rows = sheet.getRows();
			int startRow = 1;
			int startCol = 0;
			for(int i=startRow;i<sheet.getRows();i++){
				Cell[] cells = sheet.getRow(i);
				for(int j=startCol;j<columnMetas.length;j++){
					ExcelColumnBean columnMeta = columnMetas[j];
					Cell cell = cells[j];
					String type = columnMeta.getType();
					String value = cell.getContents();
					int size = columnMeta.getSize();
					re = new StringBuilder(i+"行"+j+"列:"+value+" ");
					if("".equals(value)&&columnMeta.isNullable()!=true){
						re.append("不可为空");
						return re.toString();
					}else if("DECIMAL".equals(type)&&!GenericValidator.isDouble(value)&&value.length()>size){
						re.append("DECIMAL类型无效");
						return re.toString();
					}else if("TIMESTAMP".equals(type)&&!GenericValidator.isDate(cell.getContents(), columnMeta.getPattern(), false)){
						re.append("TIMESTAMP类型无效");
						return re.toString();
					}else if("VARCHAR".equals(type)&&value.length()>(size/3)){
						re.append("VARCHAR类型无效");
						return re.toString();
					}
				}
				DwWoPlusKey4bSaleDtlBean bean = new DwWoPlusKey4bSaleDtlBean(cells[0].getContents(),
						cells[1].getContents(),
						cells[2].getContents(),
						cells[3].getContents(),
						cells[4].getContents(),
						cells[5].getContents(),
						cells[6].getContents());
				
				datas.add(bean);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			book.close();
		}
		
		
		return null;
	}
	
	
	public void setServletResponse(HttpServletResponse arg0) {
		this.response = arg0;
	}
	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
	}
	public void setServletContext(ServletContext context) {
		this.servletContext = context;
	}
}
