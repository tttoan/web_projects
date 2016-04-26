package com.home.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.util.ServletContextAware;

import com.home.dao.StatisticHome;
import com.home.entities.RevenuesComparison;
import com.home.entities.RevenuesCustomerDetail;
import com.home.entities.RevenuesCustomerL1;
import com.home.entities.RevenuesCustomerL2;
import com.home.entities.RevenuesSellman;
import com.home.model.Product;
import com.home.util.DateUtils;
import com.home.util.HibernateUtil;
import com.home.util.StringUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;

public class ReportRevenuesAction  implements Action, ServletContextAware{
	private ServletContext ctx;
	private List<RevenuesComparison> data;
	private int draw;
	private int recordsTotal;
	private int recordsFiltered;
	private InputStream inputStream;
	
	public InputStream getInputStream() {
		return inputStream;
	}

	public int getDraw() {
		return draw;
	}

	public void setDraw(int draw) {
		this.draw = draw;
	}

	public int getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(int recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	public int getRecordsFiltered() {
		return recordsFiltered;
	}

	public void setRecordsFiltered(int recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}


	public List<RevenuesComparison> getData() {
		return data;
	}

	public void setData(List<RevenuesComparison> data) {
		this.data = data;
	}

	@Override
	public void setServletContext(ServletContext context) {
		this.ctx = context;
	}

	@Override
	public String execute() throws Exception {
		return Action.SUCCESS;
	}
	
	public static void main(String[] args) {
		new ReportRevenuesAction().compareRevenues();
	}
	
	public String compareRevenues(){
		try {
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get( ServletActionContext.HTTP_REQUEST);
			String startDate = StringUtil.notNull(request.getParameter("start"));
			String endDate = StringUtil.notNull(request.getParameter("end"));
			int revanues = 0;
			try {
				revanues = Integer.parseInt(request.getParameter("revenues"));
			} catch (Exception e) {}
			
			StatisticHome statisticHome = new StatisticHome(HibernateUtil.getSessionFactory());
			Date startDate1 = new Date(DateUtils.getDateFromString(startDate, "dd/MM/yyyy").getTime());
			Date endDate1 = new Date(DateUtils.getDateFromString(endDate, "dd/MM/yyyy").getTime());
			
			Calendar cal = Calendar.getInstance();
		    cal.setTime(startDate1);
		    int year = cal.get(Calendar.YEAR)-1;
		    cal.set(Calendar.YEAR, year);
			Date startDate2 = new Date(cal.getTimeInMillis());
			
			cal.setTime(endDate1);
		    year = cal.get(Calendar.YEAR)-1;
		    cal.set(Calendar.YEAR, year);
			Date endDate2 = new Date(cal.getTimeInMillis());
			float minRevenues = revanues*1000000;
			data = new ArrayList<RevenuesComparison>();
			int no = 1;
			
			LinkedHashMap<String, RevenuesComparison> results1 = statisticHome.getRevenuesComparison(startDate1, endDate1, 0);
			LinkedHashMap<String, RevenuesComparison> results2 = statisticHome.getRevenuesComparison(startDate2, endDate2, 0);
			
			Set<String> set = results1.keySet();
			for (String customerCode : set) {
				RevenuesComparison revenues = results1.get(customerCode);
				if(results2.containsKey(customerCode)){
					RevenuesComparison revenues2 = results2.get(customerCode);
					
					if(revenues.getRevenues1().floatValue() >= minRevenues || 
							revenues2.getRevenues1().floatValue() >= minRevenues){
						
						revenues.setRevenues2(revenues2.getRevenues1());
						String arrProvider[] = revenues2.getProvider().split(";");
						for (String provider : arrProvider) {
							if(provider.length() > 0 && !revenues.getProvider().contains(provider)){
								revenues.setProvider(revenues.getProvider() + ";" + provider);
							}
						}
						String arrSellMan[] = revenues2.getSellMan().split(";");
						for (String sellMan : arrSellMan) {
							if(sellMan.length() > 0 && !revenues.getSellMan().contains(sellMan)){
								revenues.setSellMan(revenues.getSellMan() + ";" + sellMan);
							}
						}
						float r = revenues.getRevenues1().floatValue()-revenues.getRevenues2().floatValue();
						if(r > 0 && ((r*100)/revenues.getRevenues2().floatValue()) >= 30){
							revenues.setIncrease30("X");
						}
						else if(r < 0 && ((-r*100)/revenues.getRevenues2().floatValue()) >= 30){
							revenues.setDecrease30("X");
						}
						if(revenues.getProvider().split(";").length > 1){
							revenues.setMultiProvide("X");
						}
						revenues.setNo(no++);
						data.add(revenues);
					}
					
				}else{
					if(revenues.getRevenues1().floatValue() >= minRevenues){
						if(revenues.getProvider().split(";").length > 1){
							revenues.setMultiProvide("X");
						}
						revenues.setNotBuy("X");
						revenues.setNo(no++);
						data.add(revenues);
					}
				}
			}
			draw = 1;
			recordsTotal = data.size();
			recordsFiltered = data.size();
			
			System.out.println("revenuesComparisons = " + data.size());
		} catch (Exception e) {
			e.printStackTrace();
			return Action.ERROR;
		}
		return Action.SUCCESS;
	}

	
	public String customerRevenuesDetail(){
		try {
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get( ServletActionContext.HTTP_REQUEST);
			String startDate = StringUtil.notNull(request.getParameter("start"));
			String endDate = StringUtil.notNull(request.getParameter("end"));
			String customerCode = StringUtil.notNull(request.getParameter("cus_code"));
			
			Date startDate1 = new Date(DateUtils.getDateFromString(startDate, "dd/MM/yyyy").getTime());
			Date endDate1 = new Date(DateUtils.getDateFromString(endDate, "dd/MM/yyyy").getTime());
			
			Calendar cal = Calendar.getInstance();
		    cal.setTime(startDate1);
		    int year = cal.get(Calendar.YEAR)-1;
		    cal.set(Calendar.YEAR, year);
			Date startDate2 = new Date(cal.getTimeInMillis());
			
			cal.setTime(endDate1);
		    year = cal.get(Calendar.YEAR)-1;
		    cal.set(Calendar.YEAR, year);
			Date endDate2 = new Date(cal.getTimeInMillis());
			
			StatisticHome statisticHome = new StatisticHome(HibernateUtil.getSessionFactory());
			RevenuesCustomerDetail revenuesDetail1 = statisticHome.getRevenuesDetail(startDate1, endDate1, customerCode);
			RevenuesCustomerDetail revenuesDetail2 = statisticHome.getRevenuesDetail(startDate2, endDate2, customerCode);
			Vector<String> listP = new Vector<String>();
			
			StringBuilder html = new StringBuilder("<table id=\"example\" class=\"table table-striped responsive-utilities jambo_table display nowrap cell-border\" style=\"width: 100%\">");
			html.append("<thead>");
			html.append("<tr class=\"headings\">");
			html.append("<th>No</th><th>Mã khách hàng</th><th>Tên khách hàng</th>");
			html.append("<th>NVTT</th>");
			html.append("<th>Thời gian</th>");
			html.append("<th>Nơi mua hàng</th>");
			html.append("<th>Doanh số</th>");
			html.append("<th>Số mặt hàng</th>");
			if(revenuesDetail1 != null){
				for (Product p : revenuesDetail1.getListProduct()) {
					if(!listP.contains(p.getProductCode())){
						listP.add(p.getProductCode());
						html.append("<th>"+p.getProductCode()+"</th>");
					}
				}
			}
			if(revenuesDetail2 != null){
				for (Product p : revenuesDetail2.getListProduct()) {
					if(!listP.contains(p.getProductCode())){
						listP.add(p.getProductCode());
						html.append("<th>"+p.getProductCode()+"</th>");
					}
				}
			}
			html.append("</tr");
			html.append("</thead>");
			html.append("<tbody>");
			if(revenuesDetail1 != null){
				html.append("<tr class=\"even pointer\">");
				html.append("<th>1</th><th>"+revenuesDetail1.getCustomerCode()+"</th><th>"+revenuesDetail1.getCustomerName()+"</th>");
				html.append("<th>"+revenuesDetail1.getSellMan()+"</th>");
				html.append("<th>"+DateUtils.getStringFromDate(startDate1, "dd/MM") + "-" + DateUtils.getStringFromDate(endDate1, "dd/MM/yyyy") + "</th>");
				html.append("<th>"+revenuesDetail1.getProvider()+"</th>");
				html.append("<th>"+revenuesDetail1.getRevenues()+"</th>");
				html.append("<th>"+revenuesDetail1.getListProduct().size()+"</th>");

				for (String productCode : listP) {
					for (Product p : revenuesDetail1.getListProduct()) {
						if(p.getProductCode().equals(productCode)){
							html.append("<th>"+p.getTotalBox()+"</th>");
							break;
						}
					}
				}
				
				html.append("</tr");
			}
			if(revenuesDetail2 != null){
				html.append("<tr class=\"even pointer\">");
				html.append("<th>2</th><th></th><th></th>");
				html.append("<th>"+revenuesDetail2.getSellMan()+"</th>");
				html.append("<th>"+DateUtils.getStringFromDate(startDate2, "dd/MM") + "-" + DateUtils.getStringFromDate(endDate2, "dd/MM/yyyy") + "</th>");
				html.append("<th>"+revenuesDetail2.getProvider()+"</th>");
				html.append("<th>"+revenuesDetail2.getRevenues()+"</th>");
				html.append("<th>"+revenuesDetail2.getListProduct().size()+"</th>");

				for (String productCode : listP) {
					for (Product p : revenuesDetail2.getListProduct()) {
						if(p.getProductCode().equals(productCode)){
							html.append("<th>"+p.getTotalBox()+"</th>");
							break;
						}
					}
				}
				
				html.append("</tr");
			}
			html.append("</tbody>");
			html.append("</table>");
			inputStream = new ByteArrayInputStream(html.toString().getBytes("UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
			return Action.ERROR;
		}
		return Action.SUCCESS;
	}
	
	public String revenuesCustomerL1(){
		try {
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get( ServletActionContext.HTTP_REQUEST);
			String startDate = StringUtil.notNull(request.getParameter("start"));
			String endDate = StringUtil.notNull(request.getParameter("end"));
			
			Date startDate1 = new Date(DateUtils.getDateFromString(startDate, "dd/MM/yyyy").getTime());
			Date endDate1 = new Date(DateUtils.getDateFromString(endDate, "dd/MM/yyyy").getTime());
			
			StatisticHome statisticHome = new StatisticHome(HibernateUtil.getSessionFactory());
			LinkedHashMap<String, RevenuesCustomerL1> hmRevenuesL1 = statisticHome.getRevenuesCustomerL1(startDate1, endDate1);
			
			StringBuilder htmlTable = new StringBuilder("<table id=\"example\" class=\"table table-striped responsive-utilities jambo_table display nowrap cell-border\" style=\"width: 100%\">");
			StringBuilder tblHeader = new StringBuilder();
			tblHeader.append("<tr class=\"headings\">");
			tblHeader.append("<th>No</th><th>Mã cấp 1</th><th>Tên cấp 1</th>");
			tblHeader.append("<th>Số lượng cấp 2</th>");
			tblHeader.append("<th>Tổng doanh số</th>");
			tblHeader.append("<th>Tổng mặt hàng</th>");
			
			StringBuilder tblContent = new StringBuilder();
			Set<String> set = hmRevenuesL1.keySet();
			int no = 1;
			Vector<String> listP = new Vector<String>();
			for (String customerCode : set) {
				RevenuesCustomerL1 revenuesCustomerL1 = hmRevenuesL1.get(customerCode);
				tblContent.append("<tr class=\"even pointer\">");
				tblContent.append("<th>"+(no++)+"</th><th>"+customerCode+"</th><th>"+revenuesCustomerL1.getCustomerNameL1()+"</th>");
				tblContent.append("<th>"+revenuesCustomerL1.getTotalCustomerL2()+"</th>");
				tblContent.append("<th>"+revenuesCustomerL1.getTotalRevenues()+"</th>");
				tblContent.append("<th>"+revenuesCustomerL1.getTotalProduct()+"</th>");
				
				for (Product product : revenuesCustomerL1.getListProduct()) {
					if(!listP.contains(product.getProductCode())){
						tblHeader.append("<th>"+product.getProductCode()+"</th>");
						listP.add(product.getProductCode());
					}
				}
				for (int i = 0; i < listP.size(); i++) {
					boolean flag = true;
					for (Product product : revenuesCustomerL1.getListProduct()) {
						if(listP.get(i).equals(product.getProductCode())){
							tblContent.append("<th>"+product.getTotalBox()+"</th>");
							flag = false;
							break;
						}
					}
					if(flag){
						tblContent.append("<th></th>");
					}
				}
				
				tblContent.append("</tr");
			}
			
			tblHeader.append("</tr");
			StringBuilder tblHeader0 = new StringBuilder();
			tblHeader0.append("<tr class=\"headings\">");
			tblHeader0.append("<th colspan=\"6\"></th>");
			tblHeader0.append("<th colspan=\""+listP.size()+"\">Chi tiết mặt hàng</th>");
			tblHeader0.append("</tr");
			
			htmlTable.append("<thead>");
			htmlTable.append(tblHeader0);
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
	
	public String revenuesCustomerL2(){
		try {
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get( ServletActionContext.HTTP_REQUEST);
			String startDate = StringUtil.notNull(request.getParameter("start"));
			String endDate = StringUtil.notNull(request.getParameter("end"));
			
			Date startDate1 = new Date(DateUtils.getDateFromString(startDate, "dd/MM/yyyy").getTime());
			Date endDate1 = new Date(DateUtils.getDateFromString(endDate, "dd/MM/yyyy").getTime());
			
			StatisticHome statisticHome = new StatisticHome(HibernateUtil.getSessionFactory());
			Vector<RevenuesCustomerL2> listRevenuesL2 = new Vector<RevenuesCustomerL2>();
			
			StringBuilder htmlTable = new StringBuilder("<table id=\"example\" class=\"table table-striped responsive-utilities jambo_table display nowrap cell-border\" style=\"width: 100%\">");
			StringBuilder tblHeader = new StringBuilder();
			tblHeader.append("<tr class=\"headings\">");
			tblHeader.append("<th>No</th><th>Mã cấp 2</th><th>Tên cấp 2</th><th>Tên cấp 1</th>");
			tblHeader.append("<th>NVTT</th>");
			tblHeader.append("<th>Tổng doanh số</th>");
			tblHeader.append("<th>Tổng mặt hàng</th>");
			
			StringBuilder tblContent = new StringBuilder();
			int no = 1;
			Vector<String> listP = new Vector<String>();
			for (RevenuesCustomerL2 revenuesCustomerL2 : listRevenuesL2) {
				tblContent.append("<tr class=\"even pointer\">");
				tblContent.append("<th>"+(no++)+"</th><th>"+revenuesCustomerL2.getCustomerCodeL2()+"</th><th>"+revenuesCustomerL2.getCustomerNameL2()+"</th>");
				tblContent.append("<th>"+revenuesCustomerL2.getCustomerNameL1()+"</th>");
				tblContent.append("<th>"+revenuesCustomerL2.getSellman()+"</th>");
				tblContent.append("<th>"+revenuesCustomerL2.getTotalRevenues()+"</th>");
				tblContent.append("<th>"+revenuesCustomerL2.getTotalProduct()+"</th>");
				
				for (Product product : revenuesCustomerL2.getListProduct()) {
					if(!listP.contains(product.getProductCode())){
						tblHeader.append("<th>"+product.getProductCode()+"</th>");
						listP.add(product.getProductCode());
					}
				}
				for (int i = 0; i < listP.size(); i++) {
					boolean flag = true;
					for (Product product : revenuesCustomerL2.getListProduct()) {
						if(listP.get(i).equals(product.getProductCode())){
							tblContent.append("<th>"+product.getTotalBox()+"</th>");
							flag = false;
							break;
						}
					}
					if(flag){
						tblContent.append("<th></th>");
					}
				}
				
				tblContent.append("</tr");
			}
			
			tblHeader.append("</tr");
			StringBuilder tblHeader0 = new StringBuilder();
			tblHeader0.append("<tr class=\"headings\">");
			tblHeader0.append("<th colspan=\"7\"></th>");
			tblHeader0.append("<th colspan=\""+listP.size()+"\">Chi tiết mặt hàng</th>");
			tblHeader0.append("</tr");
			
			htmlTable.append("<thead>");
			htmlTable.append(tblHeader0);
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
	
	public String revenuesSellman(){
		try {
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get( ServletActionContext.HTTP_REQUEST);
			String startDate = StringUtil.notNull(request.getParameter("start"));
			String endDate = StringUtil.notNull(request.getParameter("end"));
			
			Date startDate1 = new Date(DateUtils.getDateFromString(startDate, "dd/MM/yyyy").getTime());
			Date endDate1 = new Date(DateUtils.getDateFromString(endDate, "dd/MM/yyyy").getTime());
			
			StatisticHome statisticHome = new StatisticHome(HibernateUtil.getSessionFactory());
			LinkedHashMap<String, RevenuesSellman> hmRevenuesSellman = new LinkedHashMap<String, RevenuesSellman>();
			
			StringBuilder htmlTable = new StringBuilder("<table id=\"example\" class=\"table table-striped responsive-utilities jambo_table display nowrap cell-border\" style=\"width: 100%\">");
			StringBuilder tblHeader = new StringBuilder();
			tblHeader.append("<tr class=\"headings\">");
			tblHeader.append("<th>No</th><th>NVTT</th>");
			tblHeader.append("<th>Số lượng khách hàng</th>");
			tblHeader.append("<th>Tổng doanh số</th>");
			tblHeader.append("<th>Tổng mặt hàng</th>");
			
			StringBuilder tblContent = new StringBuilder();
			Set<String> set = hmRevenuesSellman.keySet();
			int no = 1;
			Vector<String> listP = new Vector<String>();
			for (String sellMan : set) {
				RevenuesSellman revenuesSellman = hmRevenuesSellman.get(sellMan);
				tblContent.append("<tr class=\"even pointer\">");
				tblContent.append("<th>"+(no++)+"</th><th>"+sellMan+"</th><th>"+revenuesSellman.getTotalCustomer()+"</th>");
				tblContent.append("<th>"+revenuesSellman.getTotalRevenues()+"</th>");
				tblContent.append("<th>"+revenuesSellman.getTotalProduct()+"</th>");
				
				for (Product product : revenuesSellman.getListProduct()) {
					if(!listP.contains(product.getProductCode())){
						tblHeader.append("<th>"+product.getProductCode()+"</th>");
						listP.add(product.getProductCode());
					}
				}
				for (int i = 0; i < listP.size(); i++) {
					boolean flag = true;
					for (Product product : revenuesSellman.getListProduct()) {
						if(listP.get(i).equals(product.getProductCode())){
							tblContent.append("<th>"+product.getTotalBox()+"</th>");
							flag = false;
							break;
						}
					}
					if(flag){
						tblContent.append("<th></th>");
					}
				}
				
				tblContent.append("</tr");
			}
			
			tblHeader.append("</tr");
			StringBuilder tblHeader0 = new StringBuilder();
			tblHeader0.append("<tr class=\"headings\">");
			tblHeader0.append("<th colspan=\"5\"></th>");
			tblHeader0.append("<th colspan=\""+listP.size()+"\">Chi tiết mặt hàng</th>");
			tblHeader0.append("</tr");
			
			htmlTable.append("<thead>");
			htmlTable.append(tblHeader0);
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
	
}
