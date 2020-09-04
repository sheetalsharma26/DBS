package dbs.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataProvider {
	
		XSSFWorkbook currentWorkbook;

		// constructor to load excel file
		/**
		 * Constructor to load the workbook
		 */
		public ExcelDataProvider() {

			File src = new File("./TestData/TestData.xlsx");
			try {
				FileInputStream fis = new FileInputStream(src);
				currentWorkbook = new XSSFWorkbook(fis);
			} catch (Exception e) {
				System.out.println("Unable to read file" + e.getMessage());
			}

		}

		/**
		 * @param sheetIndex
		 * @return total no of rows present in the sheet
		 */
		public void createRow(int sheetIndex) throws IOException {
			XSSFSheet sheet = currentWorkbook.getSheetAt(sheetIndex);
			XSSFRow row= sheet.createRow(1);
			XSSFCell cell=row.createCell(1);
			
			 cell.setCellValue("SoftwareTestingMaterial.com");
			 FileOutputStream fos = new FileOutputStream("./TestData/TestData.xlsx");
			 currentWorkbook.write(fos);
			 fos.close();
			 System.out.println("END OF WRITING DATA IN EXCEL");
		}

}
