package test.model;

import java.util.ArrayList;

public class UploadExcelBean {
		private ArrayList<ExcelColumnBean> columns;
		private String fileName;
		private String fileSize;
		
		
		public UploadExcelBean() {
			super();
		}
		
		
		public UploadExcelBean(String fileName, String fileSize,ArrayList<ExcelColumnBean> columns) {
			super();
			this.columns = columns;
			this.fileName = fileName;
			this.fileSize = fileSize;
		}


		public ArrayList<ExcelColumnBean> getColumns() {
			return columns;
		}
		public void setColumns(ArrayList<ExcelColumnBean> columns) {
			this.columns = columns;
		}
		public String getFileName() {
			return fileName;
		}
		public void setFileName(String fileName) {
			this.fileName = fileName;
		}
		public String getFileSize() {
			return fileSize;
		}
		public void setFileSize(String fileSize) {
			this.fileSize = fileSize;
		}
		
}
