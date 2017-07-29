package com.org.util.framework;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelHelper {

	public String dataFilePath = "/home/lsimon/output/agusta/resources/datatables/datas.xls";
	
	FileInputStream file;
	HSSFWorkbook workbook;
	HSSFSheet worksheet;
	public String test;

	public void setTestCaseId(String tcId) {
		this.test = tcId;
	}

	public String getTestCaseId() {
		return test;
	}

	public void loadExcelsheet(String sheetName) {
		try {
			file = new FileInputStream(dataFilePath);
			workbook = new HSSFWorkbook(file);
			worksheet = workbook.getSheet(sheetName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public String getData(String TestID, String Sheet, String field) {
		String value = "";
		HSSFRow row;
		int rowNo = 0;
		int colNo = 0;

		int totalRows = worksheet.getPhysicalNumberOfRows();

		for (int i = 1; i < totalRows; i++) {
			row = worksheet.getRow(i);
			String actTestID = row.getCell(0).getStringCellValue();
			if (actTestID.equalsIgnoreCase(TestID)) {
				rowNo = i;
				break;
			}
		}

		int totalCols = worksheet.getRow(rowNo).getPhysicalNumberOfCells();
		row = worksheet.getRow(0);

		for (int j = 0; j < totalCols; j++) {

			String actField = row.getCell(j).getStringCellValue();
			if (actField.equalsIgnoreCase(field)) {
				colNo = j;
				break;
			}
		}

		value = worksheet.getRow(rowNo).getCell(colNo).getStringCellValue();
		return value;

	}

}
