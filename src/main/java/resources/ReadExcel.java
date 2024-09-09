package resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.testng.annotations.Test;
//
//import lombok.Data;

public class ReadExcel {

	
	public ArrayList<ExcelData> getData() throws IOException{
		FileInputStream fis = new FileInputStream("C:\\Users\\Poovanna HG\\Downloads\\TestData2.xlsx");
		Workbook book = new XSSFWorkbook(fis);
		Sheet sheet = book.getSheetAt(0);
		Iterator<Row> rows = sheet.iterator();
		ArrayList<ExcelData> columnData = new ArrayList<>();
		
		while (rows.hasNext()) {
            Row row = rows.next();
            int columnIndex = 0;
			Cell cell = row.getCell(columnIndex);
            if (cell != null) {
            	ExcelData data = new ExcelData();
            	data.setSearchData(cell.toString());
                columnData.add(data);
            }
            
        
        }
		book.close();
        return columnData;
		
	}

			
	
}
