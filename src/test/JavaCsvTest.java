package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import com.csvreader.CsvWriter;

public class JavaCsvTest {
	public static void main(String[] args) {
		try {

			String csvFilePath = "d:/test.csv";
			CsvWriter wr = new CsvWriter(csvFilePath, ',', Charset
					.forName("GB2312"));
			String[] contents = { "中午", "bbbbb", "cccccc", "ddddddddd" };
			String cotent = "aaa,bbb,ccc";
			wr.writeRecord(contents);
			wr.write(cotent);
			wr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void addCloumn(List<String> pList, String filePath)
			throws IOException {
		BufferedReader bufReader = new BufferedReader(new FileReader(filePath));
		String lineStr = "";
		int rowNumber = 0;
		StringBuffer nContent = new StringBuffer();
		while ((lineStr = bufReader.readLine()) != null) {
			String addValue = "";
			if (rowNumber < pList.size()) {
				addValue = pList.get(rowNumber);
			}
			if (lineStr.endsWith(",")) {
				nContent.append(lineStr).append("\"" + addValue + "\"");
			} else {
				nContent.append(lineStr).append(",\"" + addValue + "\"");
			}
			rowNumber++;
			nContent.append("\r\n");
		}
		bufReader.close();

		FileOutputStream fileOs = new FileOutputStream(new File(filePath),
				false);
		fileOs.write(nContent.toString().getBytes());
		fileOs.close();
	}
}
