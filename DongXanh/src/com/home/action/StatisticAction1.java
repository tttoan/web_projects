package com.home.action;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.home.conts.MyConts;
import com.home.dao.CustomerHome;
import com.home.dao.ProductHome;
import com.home.dao.StatisticHome;
import com.home.entities.UserAware;
import com.home.model.Customer;
import com.home.model.InvoiceType;
import com.home.model.Product;
import com.home.model.Statistic;
import com.home.model.User;
import com.home.util.HibernateUtil;
import com.home.util.StringUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class StatisticAction1 extends ActionSupport implements Action, UserAware{
	private User userSes;
	private List<Object[]> listCustomerL1 = new ArrayList<>();
	private List<Object[]> listCustomerL2 = new ArrayList<>();
	private List<Object[]> listProduct = new ArrayList<>();
	private String searchCusName;
	private String searchCusId;
	private String searchProductName;
	private Statistic statistic = new Statistic();
	private static final SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy");
	private Customer cusLevel1 = new Customer();
	private Customer cusLevel2 = new Customer();
	private Product product = new Product();
	private InvoiceType invoiceType = new InvoiceType();

	public static void main(String[] args) {
		try {
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
	
	public String addStatistic() throws Exception {
		try {
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get( ServletActionContext.HTTP_REQUEST);
			
			String id =  StringUtil.notNull(request.getParameter("id"));
			String date_received =  StringUtil.notNull(request.getParameter("date_received"));
			String customer_code_level1 =  StringUtil.notNull(request.getParameter("customer_code_level1"));
			String customer_code_level2 =  StringUtil.notNull(request.getParameter("customer_code_level2"));
			String product_id =  StringUtil.notNull(request.getParameter("product_id"));
			String total_box =  StringUtil.notNull(request.getParameter("total_box"));
			String quantity =  StringUtil.notNull(request.getParameter("quantity"));
			String invoice_type_id =  StringUtil.notNull(request.getParameter("invoice_type_id"));
			
			statistic.setDateReceived(SDF.parse(date_received));
			cusLevel1.setId(Integer.parseInt(customer_code_level1));
			statistic.setCustomerByCustomerCodeLevel1(cusLevel1);
			cusLevel2.setId(Integer.parseInt(customer_code_level2));
			statistic.setCustomerByCustomerCodeLevel2(cusLevel2);
			product.setId(Integer.parseInt(product_id));
			statistic.setProduct(product);
			//statistic.setTotalBox(Float.parseFloat(total_box));///cho nay update lai float
			statistic.setQuantity(Integer.parseInt(quantity));
			statistic.setTotal(new BigDecimal(Float.parseFloat(total_box)*Integer.parseInt(quantity)));
			if(userSes != null){
				statistic.setUser(userSes);
			}
			invoiceType.setId(Integer.parseInt(invoice_type_id));
			statistic.setInvoiceType(invoiceType);
			
			StatisticHome sttHome = new StatisticHome(HibernateUtil.getSessionFactory());
			if (id.isEmpty()) {
				boolean isDuplicated = sttHome.isStatictisDuplicateLevel2(
						getStatistic().getDateReceived(), 
						getStatistic().getCustomerByCustomerCodeLevel1() == null ? null : getStatistic().getCustomerByCustomerCodeLevel1().getId(),
						getStatistic().getCustomerByCustomerCodeLevel2() == null ? null : getStatistic().getCustomerByCustomerCodeLevel2().getId(), 
						getStatistic().getProduct() == null ? null : getStatistic().getProduct().getId(), 
						getStatistic().getUser() == null ? null : getStatistic().getUser().getId(), 
						getStatistic().getInvoiceType().getId());
				if (!isDuplicated)
					sttHome.attachDirty(statistic);
				else {
					addActionMessage("Dữ liệu đã được tồn tại!");
					return INPUT;
				}
			} else {
				statistic.setId(Integer.parseInt(id));
				sttHome.updateDirty(statistic);
			}
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
			return INPUT;
		}
		return SUCCESS;
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
	
	public Statistic getStatistic() {
		return statistic;
	}

	public void setStatistic(Statistic statistic) {
		this.statistic = statistic;
	}

	public Customer getCusLevel1() {
		return cusLevel1;
	}

	public void setCusLevel1(Customer cusLevel1) {
		this.cusLevel1 = cusLevel1;
	}

	public Customer getCusLevel2() {
		return cusLevel2;
	}

	public void setCusLevel2(Customer cusLevel2) {
		this.cusLevel2 = cusLevel2;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public User getUserSes() {
		return userSes;
	}

	@Override
	public void setUserSes(User user) {
		this.userSes = user;
	}
	
	public InvoiceType getInvoiceType() {
		return invoiceType;
	}

	public void setInvoiceType(InvoiceType invoiceType) {
		this.invoiceType = invoiceType;
	}

}
