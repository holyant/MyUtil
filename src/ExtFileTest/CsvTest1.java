package ExtFileTest;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class CsvTest1 {

    public static File createCSVFile(List<String[]> exportData,String outPutPath, String filename) {

        File csvFile = null;
        BufferedWriter csvFileOutputStream = null;
        try {
            csvFile = new File(outPutPath + filename + ".csv");
            
            File parent = csvFile.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }
            csvFile.createNewFile();

            // GB2312使正确读取分隔符","
            csvFileOutputStream = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(csvFile), "GB2312"), 1024);
            // 写入文件内容
            for(String[] list : exportData){
//            	for(int i=0;i<list.length;i++){
//            		csvFileOutputStream.write(list[i]);  
//            		if(list.length-i>1){
//            			csvFileOutputStream.write(",");  
//            		}
//            	}
            	csvFileOutputStream.write("啊,2,3,4,5,6");  
            	csvFileOutputStream.newLine();  
            }
            csvFileOutputStream.flush();  
        } catch (Exception e) {  
           e.printStackTrace();  
        } finally {  
           try {  
                csvFileOutputStream.close();  
            } catch (IOException e) {  
               e.printStackTrace();
           }  
       }  
        return csvFile;
    }

    public static void main(String[] args) {
        List exportData = new ArrayList<Map>();
        String[] str1 = {"啊","2","3","4","5","6"};
        exportData.add(str1);
        String[] str2= {"2","方","4","5","6","1"};
        exportData.add(str2);
        CsvTest1.createCSVFile(exportData,"d:/", "活动目录");
    }
}