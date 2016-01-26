package com.home.util;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {

	public Workbook getWorkbook(FileInputStream fis, String type) throws IOException {
		Workbook wb = null;
		if (type.equalsIgnoreCase("xls")) {
			wb = new HSSFWorkbook(fis);
		} else if (type.equalsIgnoreCase("xlsx")) {
			wb = new XSSFWorkbook(fis);
		}
		return wb;
	}

	public Object getValue(Cell cell) throws Exception {
		Object value = "";
		switch (cell.getCellType()) {
			case Cell.CELL_TYPE_BOOLEAN:
				value = cell.getBooleanCellValue();
				System.out.print(cell.getBooleanCellValue() + "\t\t");
				break;
			case Cell.CELL_TYPE_NUMERIC:
				if (DateUtil.isCellDateFormatted(cell)) {
					value = cell.getDateCellValue();
					System.out.print(cell.getDateCellValue() + "\t\t");
				} else {
					value = cell.getNumericCellValue();
					System.out.print(cell.getNumericCellValue() + "\t\t");
				}
				break;
			case Cell.CELL_TYPE_STRING:
				value = cell.getStringCellValue();
				System.out.print(cell.getStringCellValue() + "\t\t");
				break;
			case Cell.CELL_TYPE_FORMULA:
				switch (cell.getCachedFormulaResultType()) {
					case Cell.CELL_TYPE_NUMERIC:
						value = cell.getNumericCellValue();
						System.out.println("Last evaluated as: " + cell.getNumericCellValue());
						break;
					case Cell.CELL_TYPE_STRING:
						value = cell.getStringCellValue();
						System.out.println("Last evaluated as \"" + cell.getStringCellValue() + "\"");
						break;
				}
				break;
		}
		return value;
	}

}
