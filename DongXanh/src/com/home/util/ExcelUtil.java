package com.home.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.format.CellTextFormatter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {

	private HashMap<String, Short> mapColor = new HashMap<String, Short>();
	
	public HashMap<String, Short> getMapColor() {
		return mapColor;
	}

	public void setMapColor(HashMap<String, Short> mapColor) {
		this.mapColor = mapColor;
	}

	public Workbook getWorkbook(FileInputStream fis, String type) throws IOException {
		Workbook wb = null;
		if (type.equalsIgnoreCase("xls")) {
			wb = new HSSFWorkbook(fis);
		} else if (type.equalsIgnoreCase("xlsx")) {
			wb = new XSSFWorkbook(fis);
		}
		return wb;
	}
	public boolean isDateReceived(String value, SimpleDateFormat dateFormat) {
		dateFormat.setLenient(false);
		try {
			dateFormat.parse(value.trim());
		} catch (ParseException pe) {
			return false;
		}
		return true;
	}
	public Object getValue(Cell cell) throws Exception {
		Object value = "";
		switch (cell.getCellType()) {
			case Cell.CELL_TYPE_BOOLEAN:
				value = cell.getBooleanCellValue();
//				System.out.print(cell.getBooleanCellValue() + "\t\t");
				break;
			case Cell.CELL_TYPE_NUMERIC:
				if (DateUtil.isCellDateFormatted(cell)) {
					value = cell.getDateCellValue();
//					System.out.print(cell.getDateCellValue() + "\t\t");
				} else {
					value = cell.getNumericCellValue();
//					System.out.print(cell.getNumericCellValue() + "\t\t");
				}
				break;
			case Cell.CELL_TYPE_STRING:
				value = cell.getStringCellValue();
//				System.out.print(cell.getStringCellValue() + "\t\t");
				break;
			case Cell.CELL_TYPE_FORMULA:
				switch (cell.getCachedFormulaResultType()) {
					case Cell.CELL_TYPE_NUMERIC:
						value = cell.getNumericCellValue();
//						System.out.println("Last evaluated as: " + cell.getNumericCellValue());
						break;
					case Cell.CELL_TYPE_STRING:
						value = cell.getStringCellValue();
//						System.out.println("Last evaluated as \"" + cell.getStringCellValue() + "\"");
						break;
				}
				break;
		}
		return value;
	}
	
	public void addRowData(Sheet sheet, int startIndexRow, int startIndexCell, String... valPerCells) throws Exception{
		Row row = sheet.createRow(startIndexRow);
		int markIndex = 0;
		for (int i = 0; i<valPerCells.length;i++) {
			//If value is null. That mean is merge cell
			if(valPerCells[i] == null){
				sheet.addMergedRegion(new CellRangeAddress(startIndexRow, startIndexRow, markIndex, i));
				continue;
			}
			markIndex = i;
			Cell cell = row.createCell(startIndexCell + i);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(valPerCells[i]);
			sheet.autoSizeColumn(startIndexCell + i);
		}
	}
	
	public void addRowData(Sheet sheet, int startIndexRow, int startIndexCell, Object... valPerCells) throws Exception{
		Row row = sheet.createRow(startIndexRow);
		int markIndex = 0;
		for (int i = 0; i<valPerCells.length;i++) {
			//If value is null. That mean is merge cell
			if(valPerCells[i] == null){
				sheet.addMergedRegion(new CellRangeAddress(startIndexRow, startIndexRow, markIndex, i));
				continue;
			}
			markIndex = i;
			Cell cell = row.createCell(startIndexCell + i);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			if(valPerCells[i] instanceof Integer )
				cell.setCellValue((int)valPerCells[i]);
			else
				cell.setCellValue(valPerCells[i]+"");
			//sheet.autoSizeColumn(startIndexCell + i);
		}
	}
	
	public void updateRowData(Sheet sheet, int rowIdex, int columnIndex, Object value) throws Exception{
		Row row = sheet.getRow(rowIdex);
		Cell cell = row.getCell(columnIndex);
		cell.setCellType(Cell.CELL_TYPE_STRING);
		cell.setCellValue(value + "");
	}
	
	/**
	 * @param sheetNames
	 * @param headerColumns
	 * @param listAllData
	 * @param isHeader
	 * @return
	 * @throws Exception
	 */
    public HSSFWorkbook createWorkbook(
    		String[] sheetNames,
            List<String[]> headerColumns,
            List<List<String[]>> listAllData,
            boolean isHeader
            ) throws Exception{
        
    	HSSFWorkbook wb = new HSSFWorkbook();
        try {
        	//Create font style
            creatFontStyle(wb);
            //Create sheet
            HSSFSheet sheets[] = new HSSFSheet[sheetNames.length];
            for (int i = 0; i < sheetNames.length; i++) {
                if("".equals(StringUtil.notNull(sheetNames[i]))){
                    sheetNames[i] = "Sheet" + (i+1);
                }
                sheets[i] = wb.createSheet(removeInvalidChar(sheetNames[i]));
            }
            //Create content
            creatWbBody(wb, sheets, headerColumns, listAllData, isHeader);
        } catch (Exception e) {
            e.printStackTrace();
            throw  e;
        }
        return wb;
    }
    
    /**
     * @author tttoan
     * @param wb
     */
    private void creatFontStyle(HSSFWorkbook wb) throws Exception{
        // Create font
        HSSFFont font01Bold = wb.createFont();
        font01Bold.setFontHeightInPoints((short) 12);
        font01Bold.setFontName("Calibri");
        font01Bold.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        
        HSSFFont font01Normal = wb.createFont();
        font01Normal.setFontHeightInPoints((short) 12);
        font01Normal.setFontName("Calibri");
        font01Normal.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        
        HSSFCellStyle cellstyleTblLeft = wb.createCellStyle();
        cellstyleTblLeft.setFont(font01Normal);
        cellstyleTblLeft.setAlignment(HSSFCellStyle.ALIGN_LEFT);
    }
    
    /**
     * @author tttoan
     *
     * @param HSSFSheet[] styleSheetNames
     * @param List<String[]> HeaderColumns
     * @param List<List<String[]>> allData
     */
    private void creatWbBody(HSSFWorkbook wb, HSSFSheet[] styleSheetNames, List<String[]> HeaderColumns, List<List<String[]>> allData, boolean isHeader) throws Exception {
        
        for (int i = 0; i < styleSheetNames.length; i++) {
            if(isHeader){
                creatHeader(wb, styleSheetNames[i], HeaderColumns.get(i));
                creatSheetBody(wb, styleSheetNames[i], allData.get(i), 1);
            }else{
                creatSheetBody(wb, styleSheetNames[i], allData.get(i), 0);
            }
            resizeColumn(styleSheetNames[i], HeaderColumns.get(i));
        }
    }
    
    private void resizeColumn(HSSFSheet styleSheetName, String[] columnNames){
    	 for (int i = 0; i < columnNames.length; i++) {
    		 styleSheetName.autoSizeColumn(i);
    	 }
    }
    
    
    /**
     * @author tttoan
     * @param wb
     * @param styleSheetName
     * @param columnNames
     */
    private void creatHeader(HSSFWorkbook wb, HSSFSheet styleSheetName, String[] columnNames) throws Exception{
        
        HSSFRow rowHeader;
        HSSFCell cellHeader;
        
        // menge cell
        //styleSheetName.addMergedRegion(new Region((short)0,(short)0,(short)0,(short)7));
        
        // Create font
        HSSFFont font01Bold = wb.createFont();
        font01Bold.setFontHeightInPoints((short) 12);
        font01Bold.setFontName("Calibri");
        font01Bold.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font01Bold.setColor(HSSFColor.BLACK.index);
        
        //Create type
//        HSSFCellStyle cell_style = wb.createCellStyle();
//        cell_style.setFont(font01Bold);
//        
//        cell_style.setBorderTop(HSSFCellStyle.BORDER_THIN);
//        cell_style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//        cell_style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//        cell_style.setBorderRight(HSSFCellStyle.BORDER_THIN);
//        cell_style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        
//        cell_style.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
//        cell_style.setFillBackgroundColor(HSSFColor.PALE_BLUE.index);
//        cell_style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        
//        HSSFCellStyle font_cellHeader_day = wb.createCellStyle();
//        font_cellHeader_day.setFont(font01Bold);
//        font_cellHeader_day.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        
        rowHeader = styleSheetName.createRow((int) 0);
        for (int i = 0; i < columnNames.length; i++) {
        	
        	 HSSFCellStyle cell_style = wb.createCellStyle();
             cell_style.setFont(font01Bold);
             
             cell_style.setBorderTop(HSSFCellStyle.BORDER_THIN);
             cell_style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
             cell_style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
             cell_style.setBorderRight(HSSFCellStyle.BORDER_THIN);
             cell_style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        	if(mapColor.containsKey(columnNames[i])){
        		cell_style.setFillForegroundColor(mapColor.get(columnNames[i]));
        		cell_style.setFillBackgroundColor(mapColor.get(columnNames[i]));
        		cell_style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        	}else{
        		cell_style.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
        		cell_style.setFillBackgroundColor(HSSFColor.PALE_BLUE.index);
        		cell_style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        	}

            cellHeader = rowHeader.createCell(i);
            //cellHeader.setEncoding(cellHeader.ENCODING_UTF_16);
            cellHeader.setCellValue(new HSSFRichTextString(columnNames[i]));
            cellHeader.setCellStyle(cell_style);
            
//            int colNum = cellHeader.getColumnIndex();
//            styleSheetName.setColumnWidth(colNum, (styleSheetName.getColumnWidth(colNum) + 3000));
//            styleSheetName.autoSizeColumn(i);
        }
        
    }
    //
    
    /**
     * @author tttoan
     *
     * Tao body cho 1 stylesheet trong workbook
     * @param styleSheetName
     * @param oneTable
     */
    private void creatSheetBody(HSSFWorkbook wb, HSSFSheet styleSheetName, List<String[]> oneTable, int startRow) throws Exception{
        
        org.apache.poi.hssf.usermodel.HSSFCell cellContent;
        org.apache.poi.hssf.usermodel.HSSFRow rowContent;
        
        int len = oneTable.size();
        int lenOfData;
        
        // Create font
//        HSSFFont font01Bold = wb.createFont();
//        font01Bold.setFontHeightInPoints((short) 14);
//        font01Bold.setFontName("Calibri");
//        font01Bold.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
//        font01Bold.setColor(HSSFColor.BLACK.index);
        
        HSSFFont cell_font = wb.createFont();
        cell_font.setFontHeightInPoints((short) 11);
        cell_font.setFontName("Calibri");
        cell_font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        
        //
        HSSFCellStyle cell_styleL = wb.createCellStyle();
        cell_styleL.setFont(cell_font);
        cell_styleL.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        cell_styleL.setBorderTop(HSSFCellStyle.BORDER_THIN);
        cell_styleL.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        cell_styleL.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        cell_styleL.setBorderRight(HSSFCellStyle.BORDER_THIN);
        cell_styleL.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        
//        HSSFCellStyle cell_styleR = wb.createCellStyle();
//        cell_styleR.setFont(cell_font);
//        cell_styleR.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
//        
//        
//        //
//        HSSFFont cell_fontL = wb.createFont();
//        cell_fontL.setFontHeightInPoints((short) 11);
//        cell_fontL.setFontName("Calibri");
//        cell_fontL.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
//        cell_fontL.setColor(HSSFColor.BLUE.index);
//        
//        
//        //
//        HSSFCellStyle cell_styleLink = wb.createCellStyle();
//        cell_styleLink.setFont(cell_fontL);
//        cell_styleLink.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        
        String value;
        for (int i = 0; i < len; i++) {
            
            lenOfData = oneTable.get(i).length;
            
            rowContent = styleSheetName.createRow((int) i + startRow);
            
            for (int k = 0; k < lenOfData; k++) {
                cellContent = rowContent.createCell(k);
                value = oneTable.get(i)[k];
                
                //if (isNum(value)) {
                //   cellContent.setCellStyle(cell_styleR);
                //    cellContent.setCellValue(Double.parseDouble(value));
                //} else {
                cellContent.setCellStyle(cell_styleL);
                cellContent.setCellValue(new HSSFRichTextString(value));
                // }
                
            }
            
        }
        
    }
    
    /**
     * @To do: remove any invalid char for make file name
     * @param strC
     * @return
     * @throws Exception
     */
    public static String removeInvalidChar(String strC) throws Exception{
        try {
            strC = StringUtil.notNull(strC);
            if (strC.length() > 0) {
                
                strC = strC.replace("\n", " ").replace("\r", " ").replace("\t", " ");
                while (strC.contains("  ")) {
                    strC = strC.replace("  ", " ");
                }
                
                strC = strC.replace("<", "");
                strC = strC.replace(">", "");
                strC = strC.replace(":", "");
                strC = strC.replace("\"", "");
                strC = strC.replace("\\", "");
                strC = strC.replace("/", "");
                strC = strC.replace("|", "");
                strC = strC.replace("?", "");
                strC = strC.replace("*", "");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        
        return strC;
    }

}