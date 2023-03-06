package youtube.datadriven.testing;

import java.io.File;
import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelData {
	
	public static File excelFile;
	public static FileInputStream ip;
	
	
	public static Object[][] getTestDataFromExcel(String sheetName) {
		excelFile = new File(System.getProperty("user.dir")+ "\\src\\test\\java\\datadriven\\testing\\TestData.xlsx");
		XSSFWorkbook workbook = null;
		try {
		ip = new FileInputStream(excelFile);
		workbook = new XSSFWorkbook(ip);
		} catch(Throwable e) {
			e.printStackTrace();
		}
		XSSFSheet sheet =  workbook.getSheet(sheetName);
		int rows = sheet.getLastRowNum();
		int cols = sheet.getRow(0).getLastCellNum();
		
		Object[][] data = new Object[rows][cols];
		
		for(int i=0 ; i<rows ; i++) {
			XSSFRow row = sheet.getRow(i+1);
			
			for(int j=0 ; j<cols ; j++) {
				XSSFCell cell = row.getCell(j);
				CellType cellType = cell.getCellType();
				
				switch(cellType) {
				case STRING: 
					data[i][j] = cell.getStringCellValue();
					break;
				case NUMERIC:
					data[i][j] = Integer.toString((int)cell.getNumericCellValue());
					break;
				case BOOLEAN:
				    data[i][j] = cell.getBooleanCellValue();
				}
				
			}
		}
		
		return data;
	}

}
