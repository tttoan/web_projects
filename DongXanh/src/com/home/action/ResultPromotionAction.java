package com.home.action;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import javax.script.ScriptEngine;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;

import com.home.conts.Params;
import com.home.dao.GroupCustomerHome;
import com.home.dao.PromotionHome;
import com.home.dao.PromotionRegistHome;
import com.home.model.GroupCustomer;
import com.home.model.Promotion;
import com.home.model.PromotionCus;
import com.home.model.PromotionGift;
import com.home.model.PromotionProduct;
import com.home.model.PromotionRegister;
import com.home.model.RegisterGift;
import com.home.model.RegisterProduct;
import com.home.util.ExcelUtil;
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
	private HashMap<Integer, Integer> mapProductPoint = new HashMap<Integer, Integer>();
	private InputStream inputStream;
	private String reportFile;

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
			promotion_id = Integer.parseInt(request.getParameter("id"));

			//Get promotion setting
			Promotion promotion = promotionHome.findById(promotion_id);

			Date start = new Date(promotion.getStartDate().getTime());
			Date end = new Date(promotion.getEndDate().getTime());
			LinkedHashMap<String, PromotionCus> mapResult = promotionHome.listPromotionCusResult(start, end);
			//			for (PromotionCus promotionCus : promotionCuss) {
			//				System.out.println("--------->" + promotionCus.getCustomerCode());
			//				System.out.println("--------->" + promotionCus.getCustomerName());
			//			}

			promotionCuss = accessPromotionResult(mapResult, promotion);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	private List<PromotionCus> accessPromotionResult(LinkedHashMap<String, PromotionCus> mapResult, Promotion promotion) throws Exception{
		List<PromotionCus> result = new ArrayList<>();
		PromotionRegistHome promotionRegistHome = new PromotionRegistHome(HibernateUtil.getSessionFactory());
		try {
			if(mapResult != null && !mapResult.isEmpty()){
				Set<PromotionRegister>  promotionRegisters = promotion.getPromotionRegisters();
				Set<PromotionGift> promotionGifts = promotion.getPromotionGifts();
				Set<PromotionProduct> promotionProducts = promotion.getPromotionProducts();
				mapProductPoint = promotionRegistHome.getMapProductsRegister(promotion_id);

				if(promotionRegisters == null){
					//Get from DB again nha ku
				}
				if(promotionGifts == null){
					//Get from DB again nha ku
				}
				if(promotionProducts == null){
					//Get from DB again nha ku
				}

				//Process customer registered yes or no
				Set<String> customerCodes = mapResult.keySet();
				if(promotion.getCustomerRegist() == 1){

					//Get promotion result
					for (String customerCode : customerCodes) {
						PromotionCus pCus = mapResult.get(customerCode);
						PromotionRegister promotionRegister = isRegister(pCus, promotionRegisters);
						if(promotionRegister != null){
							//Get customer register
							//PromotionRegister promotionRegister = promotionRegistHome.getPromotionRegister(promotion_id, pCus.getCustomerId());

							//Get list gifts register
							List<RegisterGift> listRegisterGifts = promotionRegistHome.getRegisterGifts(promotionRegister.getId(), promotion_id);
							//Get list product register
							List<RegisterProduct> listRegisterProducts = promotionRegistHome.getRegisterProducts(promotionRegister.getId(), promotion_id);

							//Duyet trong danh sach qua tang dang ky
							StringBuilder resultString = new StringBuilder();
							for (RegisterGift registerGift : listRegisterGifts) {
								String str = getPromotionResult(
										registerGift.getPromotionGift().getFormula(), 
										pCus, 
										promotionRegister.getTotalBox(), 
										promotionRegister.getTotalPoint(), 
										paramRegisterGifts(listRegisterGifts), 
										paramRegisterProducts(listRegisterProducts));
								resultString.append(str).append("; ");
							}
							pCus.setResultPromotion(resultString.toString().trim().replaceAll(";$", ""));
							if(pCus.isResult()){
								pCus.setResultString("Đạt");
							}else{
								pCus.setResultString("Không đạt");
							}

							result.add(pCus);
						}
					}
				}else{
					//Get promotion result
					for (String customerCode : customerCodes) {
						PromotionCus pCus = mapResult.get(customerCode);
						//Duyet trong danh sach qua tang dang ky
						StringBuilder resultString = new StringBuilder();
						for (PromotionGift promotionGift : promotionGifts) {
							String str = getPromotionResult(
									promotionGift.getFormula(), 
									pCus, 
									0, 
									0, 
									null, 
									null);
							resultString.append(str).append("; ");
						}
						pCus.setResultPromotion(resultString.toString().trim().replaceAll(";$", ""));
						if(pCus.isResult()){
							pCus.setResultString("Đạt");
						}else{
							pCus.setResultString("Không đạt");
						}

						result.add(pCus);
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private String[] paramRegisterGifts(List<RegisterGift> listRegisterGifts){
		String[] arrGifts = new String[listRegisterGifts.size()];
		for (int i = 0; i < listRegisterGifts.size(); i++) {
			arrGifts[i] = listRegisterGifts.get(i).getPromotionGift().getGift().getGiftName();
		}
		return arrGifts;
	}

	private Object[][] paramRegisterProducts(List<RegisterProduct> listRegisterProducts){
		Object[][] arrProducts = new Object[listRegisterProducts.size()][3];
		for (int i = 0; i < listRegisterProducts.size(); i++) {
			arrProducts[i] = new Object[]{listRegisterProducts.get(i).getPromotionProduct().getProduct().getProductCode(), listRegisterProducts.get(i).getBox(), listRegisterProducts.get(i).getPoint()};
		}
		return arrProducts;
	}

	private PromotionRegister isRegister(PromotionCus pCus, Set<PromotionRegister>  promotionRegisters){
		for (PromotionRegister promotionRegister : promotionRegisters) {
			System.out.println(promotionRegister.getCustomer().getCustomerCode());
			if(/*pCus.getCustomerId() == promotionRegister.getCustomer_id() ||
					pCus.getCustomerId() == promotionRegister.getCustomer().getId() ||*/
					pCus.getCustomerCode().equalsIgnoreCase(promotionRegister.getCustomer().getCustomerCode())){
				return promotionRegister;
			}
		}
		return null;
	}

	private String getPromotionResult(String script, 
			PromotionCus pCus, 
			int total_box_regist, 
			int total_point_regist, 
			Object[] gift_regist, 
			Object[][] product_regist) throws Exception{
		try {
			ScriptEngine engine  = SystemUtil.compileScript(generateScriptFunction(script));
			engine.put(Params.BOX_DONE, pCus.getTotalBox());
			engine.put(Params.BOX_REGIST, total_box_regist);
			engine.put(Params.CUSTOMER, pCus.getCustomerCode());
			engine.put(Params.GIFT_REGIST, gift_regist);
			engine.put(Params.POINT_DONE, pCus.getTotaPoint(mapProductPoint));// Sum (sam pham * diem)
			engine.put(Params.POINT_REGIST, total_point_regist);
			engine.put(Params.PRODUCT_DONE, pCus.paramProducts());
			engine.put(Params.PRODUCT_REGIST, product_regist);

			Object objDescription = engine.eval(Params.FUNCION1);
			Object objResult = engine.eval(Params.FUNCION2);

			if(!pCus.isResult()){
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
	
	public String exportPromotionReport() throws Exception {
		try {
			PromotionHome promotionHome = new PromotionHome(HibernateUtil.getSessionFactory());

			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get( ServletActionContext.HTTP_REQUEST);
			promotion_id = Integer.parseInt(request.getParameter("id"));

			//Get promotion setting
			Promotion promotion = promotionHome.findById(promotion_id);

			Date start = new Date(promotion.getStartDate().getTime());
			Date end = new Date(promotion.getEndDate().getTime());
			LinkedHashMap<String, PromotionCus> mapResult = promotionHome.listPromotionCusResult(start, end);
			//			for (PromotionCus promotionCus : promotionCuss) {
			//				System.out.println("--------->" + promotionCus.getCustomerCode());
			//				System.out.println("--------->" + promotionCus.getCustomerName());
			//			}

			promotionCuss = accessPromotionResult(mapResult, promotion);
			
			try {
				//Init data
				String[] sheetNames = new String[]{"Ket qua"};
	            List<String[]> headerColumns = new ArrayList<>();
	            headerColumns.add( new String[]{"No","Mã khách hàng","Tên khách hàng","NVTT","Số mặt hàng","Số thùng","Số lượng","Kết quả","Báo cáo"});
	            List<List<String[]>> listAllData = new ArrayList<>();
	            listAllData.add(headerColumns);
	            listAllData.add(headerColumns);
	            
				ExcelUtil excelUtil = new ExcelUtil();
				HSSFWorkbook myWorkBook = excelUtil.createWorkbook(sheetNames, headerColumns, listAllData, true);
				//Write file to download
				ByteArrayOutputStream boas = new ByteArrayOutputStream();
				myWorkBook.write(boas);
				setInputStream(new ByteArrayInputStream(boas.toByteArray()));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
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

	public String getReportFile() {
		return reportFile;
	}

	public void setReportFile(String reportFile) {
		this.reportFile = reportFile;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
}
