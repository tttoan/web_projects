package com.home.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.util.ServletContextAware;

import com.home.conts.MyConts;
import com.home.dao.CustomerHome;
import com.home.dao.ProductHome;
import com.home.dao.StatisticHome;
import com.home.entities.StatisticHistory;
import com.home.model.Statistic;
import com.home.util.DateUtils;
import com.home.util.HibernateUtil;
import com.home.util.StringUtil;
import com.home.util.SystemUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class StatisticAction2 extends ActionSupport implements Action, ServletContextAware{
	private ServletContext ctx;
	private List<Statistic> data;
	private int recordsFiltered ;
	private int recordsTotal  ;
	private int draw;
	private String order;
	private String search;
	private InputStream inputStream;
	private List<Object[]> listCustomerL1 = new ArrayList<>();
	private List<Object[]> listCustomerL2 = new ArrayList<>();
	private List<Object[]> listProduct = new ArrayList<>();
	private String searchCusName;
	private String searchCusId;
	private String searchProductName;

	public InputStream getInputStream() {
		return inputStream;
	}

	public static void main(String[] args) {
		try {
			StatisticHome sttHome = new StatisticHome(HibernateUtil.getSessionFactory());
			System.out.println(new StatisticAction2().getCustomerStatisticHistory());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String lookupCustomerL1Statistic(){
		try {
			String cusName = searchCusName;
			if(cusName == null){
				HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get( ServletActionContext.HTTP_REQUEST);
				cusName = StringUtil.notNull(request.getParameter("searchCusName"));
			}

			//System.out.println("lookupCustomerL1Statistic = " + cusName);
			CustomerHome cusHome = new CustomerHome(HibernateUtil.getSessionFactory());
			listCustomerL1 = cusHome.lookupCustomer(cusName, ""+MyConts.CUS_L1);
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}
	
	public String lookupCustomerL2Statistic(){
		try {
			String cusName2 = searchCusName;
			String cusId1 = searchCusId;
			if(cusName2 == null){
				HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get( ServletActionContext.HTTP_REQUEST);
				cusName2 = StringUtil.notNull(request.getParameter("searchCusName"));
				cusId1 = StringUtil.notNull(request.getParameter("searchCusId"));
			}

			//System.out.println("lookupCustomerL1Statistic = " + cusName);
			CustomerHome cusHome = new CustomerHome(HibernateUtil.getSessionFactory());
			if(StringUtil.notNull(cusId1).isEmpty()){
				listCustomerL2 = cusHome.lookupCustomer(cusName2, ""+MyConts.CUS_L2);	
			}else{
				listCustomerL2 = cusHome.lookupCustomerL2WithL1(cusName2, cusId1);	
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}
	
	public String lookupProductStatistic(){
		try {
			String productName = searchProductName;
			if(productName == null){
				HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get( ServletActionContext.HTTP_REQUEST);
				productName = StringUtil.notNull(request.getParameter("searchProductName"));
			}

			//System.out.println("productName = " + productName);
			ProductHome proHome = new ProductHome(HibernateUtil.getSessionFactory());
			listProduct = proHome.lookupProduct(productName);
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}
	
	public String listStatisticJson() throws Exception {
		try {
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get( ServletActionContext.HTTP_REQUEST);
			// Fetch Data from User Table
			int statistic_type = 0;
			Date startDate1 = null;
			Date endDate1 = null;
			try {
				String startday = StringUtil.notNull(request.getParameter("startday"));
				String endday = StringUtil.notNull(request.getParameter("endday"));
				statistic_type = Integer.parseInt(StringUtil.notNull(request.getParameter("statistic_type")));
				if(startday.length() == 10 && endday.length() == 10){
					 startDate1 = new Date(DateUtils.getDateFromString(startday, "dd/MM/yyyy").getTime());
					 endDate1 = new Date(DateUtils.getDateFromString(endday, "dd/MM/yyyy").getTime());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			String start  = (request.getParameter("start"));
			String length  = (request.getParameter("length"));
			String strDraw  = (request.getParameter("draw"));
			draw = StringUtil.notNull(strDraw).isEmpty()?0:Integer.parseInt(strDraw);
			order = StringUtil.notNull((request.getParameter("order[i][dir]")));
			System.out.println("order = " + order);
			search = StringUtil.replaceInvalidChar(StringUtil.notNull(request.getParameter("search[value]")));
			System.out.println("search = " + search);

			int pageSize = length != null? Integer.parseInt(length) : 0;
			int skip = start != null ? Integer.parseInt(start) : 0;

			StatisticHome sttHome = new StatisticHome(HibernateUtil.getSessionFactory());
			data = sttHome.getListStatistic(skip, pageSize, search, startDate1, endDate1, statistic_type);
			// Get Total Record Count for Pagination
			recordsTotal = sttHome.getTotalRecords(search, startDate1, endDate1, statistic_type);
			recordsFiltered = recordsTotal;

			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	
	public String getCustomerStatisticHistory() {
		try {
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
			String custId = StringUtil.notNull(request.getParameter("cusId"));
			int cus_id = Integer.parseInt(custId.isEmpty()?"0":custId);

			LinkedHashMap<String, HashMap<Integer, StatisticHistory>> hmStatisticHistory;
			if(cus_id > 0){
				StatisticHome statisticHome = new StatisticHome(HibernateUtil.getSessionFactory());
				hmStatisticHistory = statisticHome.getStatisticHistory(cus_id);		
			}else{
				hmStatisticHistory = new LinkedHashMap<>();
			}
			

			int yearNow = Calendar.getInstance().get(Calendar.YEAR);
			StringBuilder htmlTable = new StringBuilder("<table id=\"example\" class=\"table table-striped responsive-utilities jambo_table display nowrap cell-border\" style=\"width: 100%\">");
			StringBuilder tblHeader = new StringBuilder();
			tblHeader.append("<tr class=\"headings\">");
			tblHeader.append("<th rowspan=\"3\">STT</th>");
			tblHeader.append("<th>NIÊN VỤ</th>");
			tblHeader.append("<th style=\"text-align:center\">"+(yearNow-2)+"</th>");
			tblHeader.append("<th style=\"text-align:center\">"+(yearNow-1)+"</th>");
			tblHeader.append("<th style=\"text-align:center\">"+(yearNow-0)+"</th>");
			tblHeader.append("<th rowspan=\"3\">Ghi Chú</th>");
			tblHeader.append("</tr>");

			StringBuilder tblContent = new StringBuilder();
			Set<String> set = hmStatisticHistory.keySet();
			int no = 1;
			int q1 = 0,q2 = 0,q3 = 0;
			BigDecimal total1 = new BigDecimal(0);
			BigDecimal total2 = new BigDecimal(0);
			BigDecimal total3 = new BigDecimal(0);
			for (String produc_name : set) {
				HashMap<Integer, StatisticHistory> hmHistory = hmStatisticHistory.get(produc_name);
				
				tblContent.append("<tr class=\"even pointer\">");
				tblContent.append("<td>" + (no++) + "</td>");
				tblContent.append("<td style=\"text-align:left\">" + produc_name + "</td>");
				tblContent.append("<td style=\"text-align:center\">" + (hmHistory.containsKey(yearNow-2)?hmHistory.get(yearNow-2).getQuantity():"") + " </td>");
				tblContent.append("<td style=\"text-align:center\">" + (hmHistory.containsKey(yearNow-1)?hmHistory.get(yearNow-1).getQuantity():"") + " </td>");
				tblContent.append("<td style=\"text-align:center\">" + (hmHistory.containsKey(yearNow-0)?hmHistory.get(yearNow-0).getQuantity():"") + " </td>");
				tblContent.append("<td></td>");
				tblContent.append("</tr>");
				
				if(hmHistory.containsKey(yearNow-2)){
					q1 += hmHistory.get(yearNow-2).getQuantity();
					total1 = total1.add(hmHistory.get(yearNow-2).getTotal());
				}
				if(hmHistory.containsKey(yearNow-1)){
					q2 += hmHistory.get(yearNow-1).getQuantity();
					total2 = total2.add(hmHistory.get(yearNow-1).getTotal());
				}
				if(hmHistory.containsKey(yearNow-0)){
					q3 += hmHistory.get(yearNow-0).getQuantity();
					total3 = total3.add(hmHistory.get(yearNow-0).getTotal());
				}
			}

			tblHeader.append("<tr class=\"headings\">");
			tblHeader.append("<th>DOANH SỐ(triệu)</th>");
			tblHeader.append("<th style=\"text-align:center\">"+ SystemUtil.format2Money(total1)+"</th>");
			tblHeader.append("<th style=\"text-align:center\">"+ SystemUtil.format2Money(total2)+"</th>");
			tblHeader.append("<th style=\"text-align:center\">"+ SystemUtil.format2Money(total3)+"</th>");
			tblHeader.append("</tr>");
			tblHeader.append("<tr class=\"headings\">");
			tblHeader.append("<th>MẶT HÀNG(thùng)</th>");
			tblHeader.append("<th style=\"text-align:center\">"+q1+"</th>");
			tblHeader.append("<th style=\"text-align:center\">"+q2+"</th>");
			tblHeader.append("<th style=\"text-align:center\">"+q3+"</th>");
			tblHeader.append("</tr>");

			htmlTable.append("<thead>");
			htmlTable.append(tblHeader);
			htmlTable.append("</thead>");
			htmlTable.append("<tbody>");
			htmlTable.append(tblContent);
			htmlTable.append("</tbody>");
			htmlTable.append("</table>");
			inputStream = new ByteArrayInputStream(htmlTable.toString().getBytes("UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
			return Action.ERROR;
		}
		return Action.SUCCESS;
	}
	
	@Override
	public void setServletContext(ServletContext context) {
		this.ctx = context;
	}

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

	public List<Statistic> getData() {
		return data;
	}

	public void setData(List<Statistic> data) {
		this.data = data;
	}

	public int getRecordsFiltered() {
		return recordsFiltered;
	}

	public void setRecordsFiltered(int recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

	public int getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(int recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	public int getDraw() {
		return draw;
	}

	public void setDraw(int draw) {
		this.draw = draw;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}
	
	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public List<Object[]> getListCustomerL1() {
		return listCustomerL1;
	}

	public void setListCustomerL1(List<Object[]> listCustomerL1) {
		this.listCustomerL1 = listCustomerL1;
	}

	public List<Object[]> getListCustomerL2() {
		return listCustomerL2;
	}

	public void setListCustomerL2(List<Object[]> listCustomerL2) {
		this.listCustomerL2 = listCustomerL2;
	}
	
	public String getSearchCusName() {
		return searchCusName;
	}

	public void setSearchCusName(String searchCusName) {
		this.searchCusName = searchCusName;
	}
	
	public String getSearchCusId() {
		return searchCusId;
	}

	public void setSearchCusId(String searchCusId) {
		this.searchCusId = searchCusId;
	}
	public String getSearchProductName() {
		return searchProductName;
	}

	public void setSearchProductName(String searchProductName) {
		this.searchProductName = searchProductName;
	}
	
	public List<Object[]> getListProduct() {
		return listProduct;
	}

	public void setListProduct(List<Object[]> listProduct) {
		this.listProduct = listProduct;
	}

}
