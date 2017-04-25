package com.home.action;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

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
import com.home.util.DateUtils;
import com.home.util.HibernateUtil;
import com.home.util.StringUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class StatisticAction1 extends ActionSupport implements Action, UserAware, SessionAware{
	private User userSes;
	private Map<String, Object> session;
	private List<Object[]> listCustomerL1 = new ArrayList<>();
	private List<Object[]> listCustomerL2 = new ArrayList<>();
	private List<Object[]> listProduct = new ArrayList<>();
	private String searchCusName;
	private String searchCusId;
	private String searchProductName;
	private Statistic statistic = new Statistic();
	private List<Statistic> statisticsHistory = new ArrayList<Statistic>(); 
	private static final SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy");
	private Customer cusLevel1 = new Customer();
	private Customer cusLevel2 = new Customer();
	private Product product = new Product();
	private InvoiceType invoiceType = new InvoiceType();
	private List<InvoiceType> listInvoiceType = new ArrayList<InvoiceType>();
	private String result;
	//params send from client
	private String id ;
	private String date_received ;
	private String customer_code_level1 ;
	private String customer_code_level2 ;
	private String product_id ;
	private String total_box;
	private String quantity;
	private String invoice_type_id ;
	private String unit_price;
	private boolean edit;
	

	public static void main(String[] args) {
		try {
			System.out.println("customer#$#code_level1".replaceAll(".+#", ""));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String execute() throws Exception {
		listInvoiceType.add(new InvoiceType(MyConts.INVOICE_STATISTIC_CUS_L2, "Hóa đơn bán hàng cho cấp 2"));
		listInvoiceType.add(new InvoiceType(MyConts.INVOICE_STATISTIC_CUS_L1, "Hóa đơn bán hàng cho cấp 1"));
		
		if(StringUtil.notNull(id).isEmpty()){
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get( ServletActionContext.HTTP_REQUEST);
			id =  StringUtil.notNull(request.getParameter("id"));
		}
		if(!StringUtil.notNull(id).isEmpty()){
			setEdit(true);
			try {
				statistic = new StatisticHome(HibernateUtil.getSessionFactory()).findById(Integer.parseInt(id));
				date_received = SDF.format(statistic.getDateReceived());
//				customer_code_level1 = statistic.getCustomerByCustomerCodeLevel1().getId() + "";
//				customer_code_level2 = statistic.getCustomerByCustomerCodeLevel2().getId() + "";
//				product_id = statistic.getProduct().getId() + "";
//				total_box = statistic.getTotalBox() + "";
//				quantity = statistic.getQuantity() + "";
				invoice_type_id = statistic.getInvoiceType().getId() + "";
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			restoreStatisticsHitory();
		}
		return SUCCESS;
	}
	
	private void restoreStatisticsHitory(){
		try {
			if(session != null && session.containsKey(MyConts.SESSION_NEW_STATISTICS_HISTORY)){
				statisticsHistory = (List<Statistic>) session.get(MyConts.SESSION_NEW_STATISTICS_HISTORY);
			}
			else{
				HttpSession httpSession = ServletActionContext.getRequest().getSession();
				if(httpSession.getAttribute(MyConts.SESSION_NEW_STATISTICS_HISTORY) != null){
					statisticsHistory = (List<Statistic>) httpSession.getAttribute(MyConts.SESSION_NEW_STATISTICS_HISTORY);
				}
			}
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
			
			if(StringUtil.notNull(invoice_type_id).isEmpty()){
				HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get( ServletActionContext.HTTP_REQUEST);
				id =  StringUtil.notNull(request.getParameter("id"));
				date_received =  StringUtil.notNull(request.getParameter("date_received"));
				customer_code_level1 =  StringUtil.notNull(request.getParameter("customer_code_level1"));
				customer_code_level2 =  StringUtil.notNull(request.getParameter("customer_code_level2"));
				product_id =  StringUtil.notNull(request.getParameter("product_id"));
				total_box =  StringUtil.notNull(request.getParameter("total_box"));
				quantity =  StringUtil.notNull(request.getParameter("quantity"));
				invoice_type_id =  StringUtil.notNull(request.getParameter("invoice_type_id"));
				unit_price =  StringUtil.notNull(request.getParameter("unit_price"));
			}
			
			statistic.setDateReceived(SDF.parse(date_received));
			cusLevel1.setId(Integer.parseInt(customer_code_level1.replaceAll("#.+", "")));
			cusLevel1.setStatisticName(customer_code_level1.replaceAll(".+#", ""));
			statistic.setCustomerByCustomerCodeLevel1(cusLevel1);
			cusLevel2.setId(Integer.parseInt(customer_code_level2.replaceAll("#.+", "")));
			cusLevel2.setStatisticName(customer_code_level2.replaceAll(".+#", ""));
			statistic.setCustomerByCustomerCodeLevel2(cusLevel2);
			product.setId(Integer.parseInt(product_id.replaceAll("#.+", "")));
			product.setProductName(product_id.replaceAll(".+#", ""));
			statistic.setProduct(product);
			statistic.setTotalBox(Float.parseFloat(total_box));///cho nay update lai float
			statistic.setQuantity(Integer.parseInt(quantity));
			statistic.setTotal(new BigDecimal(Float.parseFloat(unit_price)*Integer.parseInt(quantity)));
			if(userSes != null){
				statistic.setUser(userSes);
			}
			else{
				HttpSession httpSession = ServletActionContext.getRequest().getSession();
				if(httpSession.getAttribute(MyConts.LOGIN_SESSION) != null){
					statistic.setUser((User)httpSession.getAttribute(MyConts.LOGIN_SESSION));
				}
			}
			invoiceType.setId(Integer.parseInt(invoice_type_id));
			invoiceType.setInvoiceType(Integer.parseInt(invoice_type_id)==MyConts.INVOICE_STATISTIC_CUS_L1?"Cấp 1":"Cấp 2");
			statistic.setInvoiceType(invoiceType);
			
			StatisticHome sttHome = new StatisticHome(HibernateUtil.getSessionFactory());
			if (id.isEmpty()) {
				boolean isDuplicated = sttHome.isStatictisDuplicateLevel2(
						getStatistic().getDateReceived(), 
						getStatistic().getCustomerByCustomerCodeLevel1() == null ? null : getStatistic().getCustomerByCustomerCodeLevel1().getId(),
						getStatistic().getCustomerByCustomerCodeLevel2() == null ? null : getStatistic().getCustomerByCustomerCodeLevel2().getId(), 
						getStatistic().getProduct() == null ? null : getStatistic().getProduct().getId(), 
						getStatistic().getUser() == null ? null : getStatistic().getUser().getId(), 
						getStatistic().getInvoiceType().getId(),
						getStatistic().getTotalBox(),
						getStatistic().getQuantity());
				
				int statistic_id = sttHome.attachDirty(statistic);
				statistic.setId(statistic_id);
				/**
				 * Store in session
				 */
				storeStatistisHistory();
				
				if (isDuplicated){
					result = "duplicate;" + statistic.getId();
				}else{
					result = SUCCESS + ";" + statistic.getId();
				}
			} else {
				statistic.setId(Integer.parseInt(id));
				sttHome.updateDirty(statistic);
				result = SUCCESS + ";" + statistic.getId();
			}
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
			result = e.getMessage();
		}
		return SUCCESS;
	}
	
	
	public String deleteStatistic() throws Exception {
		try {
			if(StringUtil.notNull(id).isEmpty()){
				HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get( ServletActionContext.HTTP_REQUEST);
				id =  StringUtil.notNull(request.getParameter("id"));
			}
			
			StatisticHome sttHome = new StatisticHome(HibernateUtil.getSessionFactory());
			Statistic stt = sttHome.findById(Integer.parseInt(id));
			sttHome.delete(stt);
			/**
			 * remove from session
			 */
			removeStatistisHistory(Integer.parseInt(id));
			result = SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			result = e.getMessage();
		}
		return SUCCESS;
	}
	
	
	private void storeStatistisHistory(){
		try {
			if(session == null){
				HttpSession httpSession = ServletActionContext.getRequest().getSession();
				if(httpSession.getAttribute(MyConts.SESSION_NEW_STATISTICS_HISTORY) == null){
					httpSession.setAttribute(MyConts.SESSION_NEW_STATISTICS_HISTORY, new ArrayList<Statistic>());
				}
				((ArrayList)httpSession.getAttribute(MyConts.SESSION_NEW_STATISTICS_HISTORY)).add(statistic);
			}else{
				if(!session.containsKey(MyConts.SESSION_NEW_STATISTICS_HISTORY)){
					session.put(MyConts.SESSION_NEW_STATISTICS_HISTORY, new ArrayList<Statistic>());
				}
				((ArrayList)session.get(MyConts.SESSION_NEW_STATISTICS_HISTORY)).add(statistic);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void removeStatistisHistory(int id){
		try {
			restoreStatisticsHitory();
			for (Statistic start : statisticsHistory) {
				if(id == start.getId()){
					statisticsHistory.remove(start);
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
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

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Map<String, Object> getSession() {
		return session;
	}
	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	
	public List<InvoiceType> getListInvoiceType() {
		return listInvoiceType;
	}

	public void setListInvoiceType(List<InvoiceType> listInvoiceType) {
		this.listInvoiceType = listInvoiceType;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDate_received() {
		return date_received;
	}

	public void setDate_received(String date_received) {
		this.date_received = date_received;
	}

	public String getCustomer_code_level1() {
		return customer_code_level1;
	}

	public void setCustomer_code_level1(String customer_code_level1) {
		this.customer_code_level1 = customer_code_level1;
	}

	public String getCustomer_code_level2() {
		return customer_code_level2;
	}

	public void setCustomer_code_level2(String customer_code_level2) {
		this.customer_code_level2 = customer_code_level2;
	}

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public String getTotal_box() {
		return total_box;
	}

	public void setTotal_box(String total_box) {
		this.total_box = total_box;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getInvoice_type_id() {
		return invoice_type_id;
	}

	public void setInvoice_type_id(String invoice_type_id) {
		this.invoice_type_id = invoice_type_id;
	}
	
	public List<Statistic> getStatisticsHistory() {
		return statisticsHistory;
	}

	public void setStatisticsHistory(List<Statistic> statisticsHistory) {
		this.statisticsHistory = statisticsHistory;
	}
	
	public boolean isEdit() {
		return edit;
	}

	public void setEdit(boolean edit) {
		this.edit = edit;
	}
	
	public String getUnit_price() {
		return unit_price;
	}

	public void setUnit_price(String unit_price) {
		this.unit_price = unit_price;
	}
}
