package com.home.action;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.script.ScriptEngine;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;

import com.home.conts.Params;
import com.home.dao.GroupCustomerHome;
import com.home.dao.PromotionHome;
import com.home.model.GroupCustomer;
import com.home.model.Promotion;
import com.home.model.PromotionCus;
import com.home.model.PromotionGift;
import com.home.model.PromotionProduct;
import com.home.model.PromotionRegister;
import com.home.util.HibernateUtil;
import com.home.util.StringUtil;
import com.home.util.SystemUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ResultPromotionAction extends ActionSupport implements Action, ServletContextAware, ServletRequestAware {

	private ServletContext ctx;
	private HttpServletRequest request;
	private List<Promotion> promotions = new ArrayList<Promotion>();
	private List<PromotionCus> promotionCuss = new ArrayList<PromotionCus>();
	private HashMap<Integer, String> groupCustomers ;
	private Integer promotion_id;

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public void setServletContext(ServletContext sc) {
		this.ctx = sc;
	}

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	
	public static void main(String[] args) {
		try {
			new ResultPromotionAction().listPromotionActive();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String listPromotionActive() throws Exception {
		try {
			PromotionHome promotionHome = new PromotionHome(HibernateUtil.getSessionFactory());
			promotions = promotionHome.getListPromotionActive();
			
			GroupCustomerHome groupCustomerHome = new GroupCustomerHome(HibernateUtil.getSessionFactory());
			groupCustomers = groupCustomerHome.getListGroupCustomer();
			int idx = 1;
			for (Promotion p : promotions) {
				//System.out.println(p.getGroupCustomer().getId());
				GroupCustomer group = new GroupCustomer();
				group.setId(p.getGroupCustomer().getId());
				group.setGroupName(groupCustomers.get(group.getId()));
				p.setGroupCustomer(group);
				p.setRow_index(idx++);
			}
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
	
	public String listPromotionCusResult() throws Exception {
		try {
			PromotionHome promotionHome = new PromotionHome(HibernateUtil.getSessionFactory());
			
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get( ServletActionContext.HTTP_REQUEST);
			promotion_id = Integer.parseInt(request.getParameter("promotion_id"));
			
			//Get promotion setting
			Promotion promotion = promotionHome.findById(promotion_id);

			Date start = promotion.getStartDate();
			Date end = promotion.getEndDate();
			promotionCuss = promotionHome.listPromotionCusResult(start, end);
//			for (PromotionCus promotionCus : promotionCuss) {
//				System.out.println("--------->" + promotionCus.getCustomerCode());
//				System.out.println("--------->" + promotionCus.getCustomerName());
//			}
			
			promotionCuss = accessPromotionResult(promotion);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
	
	private List<PromotionCus> accessPromotionResult(Promotion promotion) throws Exception{
		List<PromotionCus> result = new ArrayList<>();
		try {
			if(promotionCuss != null && !promotionCuss.isEmpty()){
				Set<PromotionRegister>  promotionRegisters = promotion.getPromotionRegisters();
				Set<PromotionGift> registerGifts = promotion.getPromotionGifts();
				Set<PromotionProduct> promotionProducts = promotion.getPromotionProducts();
				if(promotionRegisters == null){
					//Get from DB again nha ku
				}
				if(registerGifts == null){
					//Get from DB again nha ku
				}
				if(promotionProducts == null){
					//Get from DB again nha ku
				}
				
				//Process customer registered yes or no
				if(promotion.getCustomerRegist() == 1){
					//Get promotion result
					for (PromotionCus pCus : promotionCuss) {
						if(isRegister(pCus, promotionRegisters)){
							result.add(pCus);
						}
					}
				}else{
					//Get promotion result
					for (PromotionCus pCus : promotionCuss) {
						result.add(pCus);
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	private boolean isRegister(PromotionCus pCus, Set<PromotionRegister>  promotionRegisters){
		for (PromotionRegister promotionRegister : promotionRegisters) {
			if(pCus.getCustomerId() == promotionRegister.getCustomer_id() ||
					pCus.getCustomerId() == promotionRegister.getCustomer().getId() ||
					pCus.getCustomerCode().equalsIgnoreCase(promotionRegister.getCustomer().getCustomerCode())){
				return true;
			}
		}
		return false;
	}
	
	private String getPromotionResult(PromotionCus pCus, String script) throws Exception{
		try {
			ScriptEngine engine  = SystemUtil.compileScript(generateScriptFunction(script));
			engine.put(Params.BOX_DONE, 41);
			engine.put(Params.BOX_REGIST, 41);
			engine.put(Params.CUSTOMER, 41);
			engine.put(Params.GIFT_REGIST, 41);
			engine.put(Params.GIFT_REGIST, 41);
			engine.put(Params.POINT_DONE, 41);
			engine.put(Params.POINT_REGIST, 41);
			engine.put(Params.PRODUCT_DONE, 41);
			engine.put(Params.PRODUCT_REGIST, 41);
			
			Object objDescription = engine.eval(Params.FUNCION1);
			Object objResult = engine.eval(Params.FUNCION2);
			
			if(objResult == null){
				pCus.setResult(false);
			}else{
				if(objResult instanceof String){
					try {
						pCus.setResult(Boolean.getBoolean(StringUtil.notNull(objResult)));
					} catch (Exception e) {
						e.printStackTrace();
						if("đạt".equalsIgnoreCase(StringUtil.notNull(objResult)) ||
								"dat".equalsIgnoreCase(StringUtil.notNull(objResult))){
							pCus.setResult(true);
						}
					}	
				}
				else if(objResult instanceof Boolean){
					pCus.setResult((boolean)objResult);
				}
				else if(objResult instanceof Integer){
					int intRs = (Integer)objResult;
					if(intRs > 0){
						pCus.setResult(true);
					}else{
						pCus.setResult(false);
					}
				}
				
			}
			return StringUtil.notNull(objDescription);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	private String generateScriptFunction(String script) throws Exception{
		StringBuilder str = new StringBuilder("function ");
		str.append(Params.FUNCION1).append("{\n");
		//Init script
		str.append(script);
		str.append("}\n");
		//Init params
		str.append("var ").append(Params.BOX_DONE).append(";\n");
		str.append("var ").append(Params.BOX_REGIST).append(";\n");
		str.append("var ").append(Params.CUSTOMER).append(";\n");
		str.append("var ").append(Params.GIFT_REGIST).append(";\n");
		str.append("var ").append(Params.POINT_DONE).append(";\n");
		str.append("var ").append(Params.POINT_REGIST).append(";\n");
		str.append("var ").append(Params.PRODUCT_DONE).append(";\n");
		str.append("var ").append(Params.PRODUCT_REGIST).append(";\n");
		
		str.append("var ").append(Params.RESULT).append("=false;\n");
		str.append("function ").append(Params.FUNCION2).append("{return ").append(Params.RESULT).append(";}\n");
		
		return str.toString();
	}
	
	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public List<Promotion> getPromotions() {
		return promotions;
	}

	public void setPromotions(List<Promotion> promotions) {
		this.promotions = promotions;
	}

	public List<PromotionCus> getPromotionCuss() {
		return promotionCuss;
	}

	public void setPromotionCuss(List<PromotionCus> promotionCuss) {
		this.promotionCuss = promotionCuss;
	}

	public HashMap<Integer, String> getGroupCustomers() {
		return groupCustomers;
	}

	public void setGroupCustomers(HashMap<Integer, String> groupCustomers) {
		this.groupCustomers = groupCustomers;
	}
}
