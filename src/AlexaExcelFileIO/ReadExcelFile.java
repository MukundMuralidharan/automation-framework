/*
 * This class is created to read the xlsx file that has the test cases.
 */

package AlexaExcelFileIO;

import java.io.*;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelFile {

	
	
	@SuppressWarnings("resource")
	public Sheet readExcel(String filePath,String fileName,String sheetName) throws IOException{
		File file =	new File(filePath+"//"+fileName);
		
		
		FileInputStream inputStream = new FileInputStream(file);
		Workbook alexaWorkbook = null;
		String fileExtensionName = fileName.substring(fileName.indexOf("."));
		
		if(fileExtensionName.equals(".xlsx")){
			alexaWorkbook= new XSSFWorkbook(inputStream);
		}else if(fileExtensionName.equals("xls")){
			alexaWorkbook= new HSSFWorkbook(inputStream);
		}
		
		
		
		Sheet alexaSheet= alexaWorkbook.getSheet(sheetName);
		
		
		
		
		return alexaSheet;
		
	}
	
	
	
}
