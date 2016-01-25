package com.home.action;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.hibernate.SessionFactory;

import com.home.dao.StatisticHome;
import com.home.model.Statistic;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;

public class StatisticAction implements Action, ModelDriven<Statistic>, ServletContextAware, ServletRequestAware {
	private File upload;
	private String uploadContentType;
	private String uploadFileName;
	private HttpServletRequest request;

	public File getUpload() {
		return upload;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	private Statistic statistic = new Statistic();
	public List<Statistic> statistics = new ArrayList<Statistic>();
	private ServletContext ctx;
	private HSSFWorkbook workbook;

	@Override
	public Statistic getModel() {
		return statistic;
	}

	@Override
	public void setServletContext(ServletContext sc) {
		this.ctx = sc;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

	public String listInvoice() throws Exception {
		try {
			SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
			StatisticHome sttHome = new StatisticHome(sf);
			statistics = sttHome.getListInvoice();
			return SUCCESS;
		} catch (Exception e) {
			return ERROR;
		}
	}

	public String addInvoice() throws Exception {
		try {
			SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
			StatisticHome sttHome = new StatisticHome(sf);
			sttHome.attachDirty(statistic);
			return SUCCESS;
		} catch (Exception e) {
			return ERROR;
		}
	}

	public String importInvoice() throws Exception {
		System.out.println("File name:" + upload);
		File theFile = new File(this.getUploadFileName());
		FileUtils.copyFile(upload, theFile);
		try (FileInputStream fis = new FileInputStream(theFile)) {
			workbook = new HSSFWorkbook(fis);
			HSSFSheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_BOOLEAN:
						System.out.print(cell.getBooleanCellValue() + "\t\t");
						break;
					case Cell.CELL_TYPE_NUMERIC:
						System.out.print(cell.getNumericCellValue() + "\t\t");
						break;
					case Cell.CELL_TYPE_STRING:
						System.out.print(cell.getStringCellValue() + "\t\t");
						break;
					case Cell.CELL_TYPE_FORMULA:
						switch (cell.getCachedFormulaResultType()) {
						case Cell.CELL_TYPE_NUMERIC:
							System.out.println("Last evaluated as: " + cell.getNumericCellValue());
							break;
						case Cell.CELL_TYPE_STRING:
							System.out.println("Last evaluated as \"" + cell.getStringCellValue() + "\"");
							break;
						}
						break;
					}
				}
			}
		} catch (Exception e) {
			throw e;
		}

		System.out.println(theFile.getAbsolutePath());
		return SUCCESS;
	}
}
