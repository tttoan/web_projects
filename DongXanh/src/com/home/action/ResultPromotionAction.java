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
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;

import com.home.conts.Params;
import com.home.dao.GroupCustomerHome;
import com.home.dao.PromotionHome;
import com.home.dao.PromotionRegistHome;
import com.home.entities.UserAware;
import com.home.model.GroupCustomer;
import com.home.model.Product;
import com.home.model.Promotion;
import com.home.model.PromotionCus;
import com.home.model.PromotionGift;
import com.home.model.PromotionProduct;
import com.home.model.PromotionRegister;
import com.home.model.RegisterGift;
import com.home.model.RegisterProduct;
import com.home.model.User;
import com.home.util.ExcelUtil;
import com.home.util.HibernateUtil;
import com.home.util.StringUtil;
import com.home.util.SystemUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ResultPromotionAction extends ActionSupport implements Action, ServletContextAware, ServletRequestAware, UserAware {
	private User userSes;
	private ServletContext ctx;
	private HttpServletRequest request;
	private List<Promotion> promotions = new ArrayList<Promotion>();
	private List<PromotionCus> promotionCuss = new ArrayList<PromotionCus>();
	private LinkedHashMap<Integer, String> groupCustomers ;
	private Integer promotion_id;
	private HashMap<Integer, Integer> mapProductPoint = new HashMap<Integer, Integer>();
	private InputStream inputStream;
	private String filenameDownload = "Kết quả khuyến mãi.xls";
	private int type;
	private String filterValue;
	private int resultType ;
	private Promotion promotion;
	private List<String> listFilterValues = new ArrayList<String>();

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
			new ResultPromotionAction().listPromotionResult();
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
			type = 0;
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
	
	public String listPromotionResult() throws Exception {
		try {
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get( ServletActionContext.HTTP_REQUEST);
			type = Integer.parseInt(request.getParameter("type"));
			
			PromotionHome promotionHome = new PromotionHome(HibernateUtil.getSessionFactory());
			List<Promotion> list = promotionHome.getListPromotionActive(type);

			GroupCustomerHome groupCustomerHome = new GroupCustomerHome(HibernateUtil.getSessionFactory());
			groupCustomers = groupCustomerHome.getListGroupCustomer();
			promotions = new ArrayList<Promotion>();
			int idx = 1;
			for (Promotion p : list) {
				//System.out.println(p.getGroupCustomer().getId());
				GroupCustomer group = new GroupCustomer();
				group.setId(p.getGroupCustomer().getId());
				group.setGroupName(groupCustomers.get(group.getId()));
				//p.setGroupCustomer(group);
				//p.setRow_index(idx++);
				
				Promotion promotion = new Promotion();
				promotion.setId(p.getId());
				promotion.setPromotionName(p.getPromotionName());
				promotion.setStartDate(p.getStartDate());
				promotion.setEndDate(p.getEndDate());
				promotion.setRemarks(p.getRemarks());
				promotion.setGroupCustomer(group);
				promotion.setRow_index(idx++);
				promotions.add(promotion);
			}
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
	
	public String getFilterValues() {
		try {
			listFilterValues = new ArrayList<String>();
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get( ServletActionContext.HTTP_REQUEST);
			type = Integer.parseInt(request.getParameter("type"));
			switch (type) {
			case 1:
				listFilterValues = (List<String>) ActionContext.getContext().getSession().get("listFilterValues");
				break;
			case 2:
				GroupCustomerHome groupCustomerHome = new GroupCustomerHome(HibernateUtil.getSessionFactory());
				groupCustomers = groupCustomerHome.getListGroupCustomer();
				Set<Integer> set = groupCustomers.keySet();
				for (Integer id : set) {
					if(!listFilterValues.contains(groupCustomers.get(id))){
						listFilterValues.add(groupCustomers.get(id));
					}
				}
				break;
			default:
				listFilterValues.add("");
				break;
			}

			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
	

	public String listPromotionCusFilter() throws Exception {
		try {
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get( ServletActionContext.HTTP_REQUEST);
			type = Integer.parseInt(request.getParameter("type"));
			filterValue = request.getParameter("filterValue");
			resultType = Integer.parseInt(request.getParameter("resultType"));
			
			promotion = (Promotion) ActionContext.getContext().getSession().get("promotion");
			setFilenameDownload(ExcelUtil.removeInvalidChar(promotion.getPromotionName()) + ".xls");
			mapProductPoint = (HashMap) ActionContext.getContext().getSession().get("mapProductPoint");
			List<PromotionCus> list = (List<PromotionCus>) ActionContext.getContext().getSession().get("promotionCuss");
			promotionCuss = new ArrayList<PromotionCus>();
			for (PromotionCus pCus : list) {
				switch (type) {
				case 1://Filter by NVTT
					if(pCus.getSellMan().equals(filterValue)){
						if(isCustomerResult(pCus, resultType)){
							promotionCuss.add(pCus);
						}
					}
					break;
				case 2://Filter by Customer group
					if(isCustomerResult(pCus, resultType)){
						promotionCuss.add(pCus);
					}
					break;
				default:
					if(isCustomerResult(pCus, resultType)){
						promotionCuss.add(pCus);
					}
					break;
				}
			}
			
			getFilterValues();
			
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
	
	private boolean isCustomerResult(PromotionCus pCus, int result){
		switch (result) {
		case 3://Đạt
			if(pCus.isResult()){
				return true;
			}
			break;
		case 2://Ko Đạt
			if(!pCus.isResult()){
				return true;
			}
			break;
		case 1:
		default:
			return true;
		}
		return false;
	}
	
	public String listPromotionCusResult() throws Exception {
		try {
			PromotionHome promotionHome = new PromotionHome(HibernateUtil.getSessionFactory());

			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get( ServletActionContext.HTTP_REQUEST);
			promotion_id = Integer.parseInt(request.getParameter("id"));

			//Get promotion setting
			promotion = promotionHome.findById(promotion_id);

			Date start = new Date(promotion.getStartDate().getTime());
			Date end = new Date(promotion.getEndDate().getTime());
			LinkedHashMap<String, PromotionCus> mapResult = promotionHome.listPromotionCusResult(start, end);
			//			for (PromotionCus promotionCus : promotionCuss) {
			//				System.out.println("--------->" + promotionCus.getCustomerCode());
			//				System.out.println("--------->" + promotionCus.getCustomerName());
			//			}

			listFilterValues = new ArrayList<String>();
			promotionCuss = accessPromotionResult(mapResult, promotion);
			ActionContext.getContext().getSession().put("promotionCuss", promotionCuss);
			ActionContext.getContext().getSession().put("promotion", promotion);
			setFilenameDownload(ExcelUtil.removeInvalidChar(promotion.getPromotionName()) + ".xls");
			ActionContext.getContext().getSession().put("filenameDownload", getFilenameDownload());
			ActionContext.getContext().getSession().put("listFilterValues", listFilterValues);
			type = 0;
			resultType = 1;
			filterValue = "";
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
				ActionContext.getContext().getSession().put("mapProductPoint", mapProductPoint);

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
				if(promotion.getCustomerRegist() != null && promotion.getCustomerRegist() == 1){

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

							if(!listRegisterGifts.isEmpty()){
								pCus.setResult(true);
							}
							//Duyet trong danh sach qua tang dang ky
							//StringBuilder resultString = new StringBuilder();
//							for (RegisterGift registerGift : listRegisterGifts) {
//								String str = getPromotionResult(
//										registerGift.getPromotionGift().getFormula(), 
//										pCus, 
//										promotionRegister.getTotalBox(), 
//										promotionRegister.getTotalPoint(), 
//										paramRegisterGifts(listRegisterGifts), 
//										paramRegisterProducts(listRegisterProducts));
//								resultString.append(str).append("; ");
//							}
							
							String str = getPromotionResult(
									promotion.getRule(), 
									pCus, 
									promotionRegister.getTotalBox(), 
									promotionRegister.getTotalPoint(), 
									paramRegisterGifts(listRegisterGifts), 
									paramRegisterProducts(listRegisterProducts),
									getAveragePoint2Value(listRegisterGifts)
									);
							
							pCus.setResultPromotion(str);
							if(pCus.isResult()){
								pCus.setResultString("Đạt");
							}else{
								pCus.setResultString("Không đạt");
							}
							pCus.setListRegisterGifts(listRegisterGifts);
							pCus.setListRegisterProducts(listRegisterProducts);
							pCus.setPromotionRegister(promotionRegister);
							pCus.setPromotion(promotion);
							if(!listFilterValues.contains(pCus.getSellMan())){
								listFilterValues.add(new String(pCus.getSellMan()));
							}
							result.add(pCus);
						}
					}
				}else{
					//Get promotion result
					for (String customerCode : customerCodes) {
						PromotionCus pCus = mapResult.get(customerCode);
						if(!promotionGifts.isEmpty()){
							pCus.setResult(true);
						}
						//Duyet trong danh sach qua tang dang ky
//						StringBuilder resultString = new StringBuilder();
//						for (PromotionGift promotionGift : promotionGifts) {
//							String str = getPromotionResult(
//									promotionGift.getFormula(), 
//									pCus, 
//									0, 
//									0, 
//									null, 
//									promotionGift.getGift().getGiftName(),
//									null);
//							resultString.append(str).append("; ");
//						}
//						pCus.setResultPromotion(resultString.toString().trim().replaceAll(";$", ""));
						
						int arr_target[] = getGiftTarget(promotionGifts);
						int total_box_regist = arr_target[0];
						int total_point_regist = arr_target[1];
						String str = getPromotionResult(
								promotion.getRule(), 
								pCus, 
								total_box_regist, 
								total_point_regist, 
								null, 
								null,
								getAveragePoint2Value(promotionGifts));
						pCus.setResultPromotion(str);
						
						if(pCus.isResult()){
							pCus.setResultString("Đạt");
						}else{
							pCus.setResultString("Không đạt");
						}

						pCus.setPromotion(promotion);
						if(!listFilterValues.contains(pCus.getSellMan())){
							listFilterValues.add(new String(pCus.getSellMan()));
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
	
	public String getMyGift(int total_box_regist, int total_point_regist, int maxQuantity, int maxPoint, boolean isResult){
		if(isResult){
			if(maxPoint >= total_point_regist && total_point_regist > 0){
				return "X";
			}
			else if(maxQuantity >= total_box_regist && total_box_regist > 0){
				return "X";
			}
		}
		
		return "";
	}

	private Object[][] paramRegisterGifts(List<RegisterGift> listRegisterGifts){
		Object[][] arrGifts = new Object[listRegisterGifts.size()][2];
		for (int i = 0; i < listRegisterGifts.size(); i++) {
			arrGifts[i] = new Object[]{
					listRegisterGifts.get(i).getPromotionGift().getGift().getGiftName(),
					listRegisterGifts.get(i).getPromotionGift().getMaxQuantity(),
					listRegisterGifts.get(i).getPromotionGift().getMaxPoint(),
					listRegisterGifts.get(i).getPromotionGift().getPrice(),
					};
		}
		return arrGifts;
	}
	
	private int getAveragePoint2Value(List<RegisterGift> listRegisterGifts){
		double avg = 0;
		int size = listRegisterGifts.size();
		if(size > 0){
			int total = 0;
			for (int i = 0; i < size; i++) {
				Double price =  listRegisterGifts.get(i).getPromotionGift().getPrice();
				int point = listRegisterGifts.get(i).getPromotionGift().getMaxPoint();
				int quantity = listRegisterGifts.get(i).getPromotionGift().getMaxQuantity();
				if(price != null && price > 0){
					if(point > 0){
						avg += price/point;
						total++;
					}
					else if(quantity > 0){
						avg += price/quantity;
						total++;
					}
				}
			}
			avg = avg/total;
		}
		return (int)avg;
	}
	
	private int getAveragePoint2Value(Set<PromotionGift> promotionGifts){
		double avg = 0;
		int size = promotionGifts.size();
		if(size > 0){
			int total = 0;
			for (PromotionGift promotionGift : promotionGifts) {
				Double price =  promotionGift.getPrice();
				int point = promotionGift.getMaxPoint();
				int quantity = promotionGift.getMaxQuantity();
				if(price != null && price > 0){
					if(point > 0){
						avg += price/point;
						total++;
					}
					else if(quantity > 0){
						avg += price/quantity;
						total++;
					}
				}
			}
			avg = avg/total;
		}
		return (int)avg;
	}
	
	private int[] getGiftTarget(Set<PromotionGift> promotionGifts){
		int arr[] = new int[]{0, 0};
		int min_Point = 0;
		int min_Quantity = 0;
		for (PromotionGift promotionGift : promotionGifts) {
			int point = promotionGift.getMaxPoint();
			int quantity = promotionGift.getMaxQuantity();
			if(min_Point == 0){
				min_Point = point;
			}
			else{
				if(min_Point > point){
					min_Point = point;
				}
			}
			if(min_Quantity == 0){
				min_Quantity = quantity;
			}
			else{
				if(min_Quantity > quantity){
					min_Quantity = quantity;
				}
			}
		}
		arr[0] = min_Quantity;
		arr[1] = min_Point;
		return arr;
	}

	private Object[][] paramRegisterProducts(List<RegisterProduct> listRegisterProducts){
		Object[][] arrProducts = new Object[listRegisterProducts.size()][3];
		for (int i = 0; i < listRegisterProducts.size(); i++) {
			arrProducts[i] = new Object[]{
					listRegisterProducts.get(i).getPromotionProduct().getProduct().getProductCode(), 
					listRegisterProducts.get(i).getBox(), 
					listRegisterProducts.get(i).getPoint()};
		}
		return arrProducts;
	}

	private PromotionRegister isRegister(PromotionCus pCus, Set<PromotionRegister>  promotionRegisters){
		for (PromotionRegister promotionRegister : promotionRegisters) {
			//System.out.println(promotionRegister.getCustomer().getCustomerCode());
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
			Object[][] gift_regist,
			Object[][] product_regist,
			int avg_price) throws Exception{
		try {
			ScriptEngine engine  = SystemUtil.compileScript(generateScriptFunction(script));
			engine.put(Params.BOX_DONE, pCus.getTotalBox());
			engine.put(Params.BOX_REGIST, total_box_regist);
			engine.put(Params.CUSTOMER, pCus.getCustomerCode());
			engine.put(Params.GIFT_REGIST, gift_regist);
//			engine.put(Params.GIFT, gift);
			engine.put(Params.POINT_DONE, pCus.getTotaPoint(mapProductPoint));// Sum (sam pham * diem)
			engine.put(Params.POINT_REGIST, total_point_regist);
			engine.put(Params.PRODUCT_DONE, pCus.paramProducts(mapProductPoint));
			engine.put(Params.PRODUCT_REGIST, product_regist);
			engine.put(Params.PRICE, avg_price);

			Object objDescription = engine.eval(Params.FUNCION1);
			boolean objResult = (boolean)engine.eval(Params.FUNCION2);
			pCus.setResult(objResult);
			pCus.setTotalBoxRegist(total_box_regist);
			pCus.setTotalPointRegist(total_point_regist);
//			if(Params.GIFT_DEBT.equalsIgnoreCase(gift)){
//				if(objResult){
//					pCus.setResult(false);
//				}
//			}else{
//				if(pCus.isResult()){
//					
//				}
//			}
//			pCus.getMapGifts().put(gift, objResult);

			return StringUtil.notNull(objDescription);
		} catch (Exception e) {
			e.printStackTrace();
			//throw e;
			return (e.getMessage() + "<br>" + e.toString());
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
//		str.append("var ").append(Params.GIFT).append(";\n");
		str.append("var ").append(Params.POINT_DONE).append(";\n");
		str.append("var ").append(Params.POINT_REGIST).append(";\n");
		str.append("var ").append(Params.PRODUCT_DONE).append(";\n");
		str.append("var ").append(Params.PRODUCT_REGIST).append(";\n");
		str.append("var ").append(Params.PRICE).append(";\n");

		str.append("var ").append(Params.RESULT).append("=false;\n");
		str.append("function ").append(Params.FUNCION2).append("{return ").append(Params.RESULT).append(";}\n");
		str.append("function format(num){var n = num.toString(), p = n.indexOf('.');return n.replace(/\\d(?=(?:\\d{3})+(?:\\.|$))/g, function($0, i){return p<0 || i<p ? ($0+',') : $0; });}");

		return str.toString();
	}
	
	public String exportPromotionReport() throws Exception {
		ExcelUtil excelUtil = new ExcelUtil();
		try {
			Promotion promotion = (Promotion) ActionContext.getContext().getSession().get("promotion");
			mapProductPoint = (HashMap) ActionContext.getContext().getSession().get("mapProductPoint");
			promotionCuss = (List<PromotionCus>) ActionContext.getContext().getSession().get("promotionCuss");
			filenameDownload = "Result_"+SystemUtil.getCurrentDateYYYY_MM_DD()+".xls";//(String) ActionContext.getContext().getSession().get("filenameDownload");
			try {
				//Init data
				String[] sheetNames = new String[]{(String) ActionContext.getContext().getSession().get("filenameDownload")};
	            List<String[]> headerColumns = new ArrayList<>();
	            headerColumns.add(createHeaderReport(promotion, excelUtil));
	            List<List<String[]>> listAllData = createExportContent(headerColumns.get(0), promotion);
	            
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
	
	private String[] createHeaderReport(Promotion promotion, ExcelUtil excelUtil){
		List<String> list = new ArrayList<>();
		list.add("No");
		list.add("Mã khách hàng");
		list.add("Tên khách hàng");
		list.add("NVTT");
		list.add("Số mặt hàng");
		list.add("Số thùng");
		list.add("Số lượng");
		list.add("Tổng tiền");
		if(promotion.getCustomerRegist() != null && promotion.getCustomerRegist() == 1){
			list.add("Số điểm");
		}
		list.add("Kết quả");
		list.add("Báo cáo");
		Set<PromotionGift> promotionGifts = promotion.getPromotionGifts();
		for (PromotionGift promotionGift : promotionGifts) {
			list.add(promotionGift.getGift().getGiftName());
			excelUtil.getMapColor().put(promotionGift.getGift().getGiftName(), HSSFColor.YELLOW.index);
		}
		Set<PromotionProduct> promotionProducts = promotion.getPromotionProducts();
		for (PromotionProduct promotionProduct : promotionProducts) {
			list.add(promotionProduct.getProduct().getProductName());
			excelUtil.getMapColor().put(promotionProduct.getProduct().getProductName(), HSSFColor.ORANGE.index);
		}
		return (String[])list.toArray(new String[0]);
	}
	
	private List<List<String[]>> createExportContent(String[] header, Promotion promotion) throws Exception{
		 List<List<String[]>> listAllData = new ArrayList<>();
		try {
			List<String[]> data = new ArrayList<>();
			int no = 1;
			if(promotion.getCustomerRegist() != null && promotion.getCustomerRegist() == 1){
				for (PromotionCus pc : promotionCuss) {
					//Get list gifts register
					List<RegisterGift> listRegisterGifts = pc.getListRegisterGifts();
					//Get list product register
					List<RegisterProduct> listRegisterProducts = pc.getListRegisterProducts();
					
					List<String> list = new ArrayList<>();
					list.add("" + (no++));
					list.add(pc.getCustomerCode());
					list.add(pc.getCustomerName());
					list.add(pc.getSellMan());
					list.add("" + pc.getTotalProduct() + "/" + pc.getPromotionRegister().getRegisterProducts().size());
					list.add("" + pc.getTotalBox() + "/" + pc.getPromotionRegister().getTotalBox());
					list.add("" + pc.getQuality());
					list.add("" + pc.getTotaPrice());
					list.add("" + pc.getTotaPoint(mapProductPoint) + "/" + pc.getPromotionRegister().getTotalPoint());
					list.add(pc.getResultString());
					list.add(pc.getResultPromotion());
					//Set gift
					int g_len = 11+promotion.getPromotionGifts().size();
					for (int i = 11; i < g_len; i++) {
						RegisterGift registerGift = getRegisterGift(listRegisterGifts, header[i]);
						if(registerGift != null){
							list.add("" + registerGift.getTotal());
						}else{
							list.add("");
						}
					}
					//Set product
					int p_len = g_len+promotion.getPromotionProducts().size();
					for (int i = g_len; i < p_len; i++) {
						RegisterProduct registerProduct = getRegisterProduct(listRegisterProducts, header[i]);
						if(registerProduct != null){
							list.add(getProductBoxDone(pc.getProducts(), header[i]) + "/" +registerProduct.getBox());
						}else{
							list.add("");
						}
					}
					data.add((String[])list.toArray(new String[0]));
				}
			}else{
				for (PromotionCus pc : promotionCuss) {
					List<String> list = new ArrayList<>();
					list.add("" + (no++));
					list.add(pc.getCustomerCode());
					list.add(pc.getCustomerName());
					list.add(pc.getSellMan());
					list.add("" + pc.getTotalProduct());
					list.add("" + pc.getTotalBox());
					list.add("" + pc.getQuality());
					list.add("" + pc.getTotaPrice());
					list.add(pc.getResultString());
					list.add(pc.getResultPromotion());
					//Set gift
					int g_len = 10+promotion.getPromotionGifts().size();
					for (int i = 10; i < g_len; i++) {
						//if(pc.getMapGifts().containsKey(header[i])){
							list.add("x");
						//}else{
						//	list.add("");
						//}
					}
					//Set product
					int p_len = g_len+promotion.getPromotionProducts().size();
					for (int i = g_len; i < p_len; i++) {
						list.add(""+getProductBoxDone(pc.getProducts(), header[i]));
					}
					data.add((String[])list.toArray(new String[0]));
				}
			}
			listAllData.add(data);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return listAllData;
	}
	
	private int getProductBoxDone(Set<Product> products, String productName){
		for (Product product : products) {
			if(productName.equalsIgnoreCase(product.getProductName())){
				return product.getTotalBox();
			}
		}
		return 0;
	}
	
	public String getProductBoxDoneReport(Set<Product> products, String productName){
		for (Product product : products) {
			if(productName.equalsIgnoreCase(product.getProductName())){
				return "" + product.getTotalBox();
			}
		}
		return "";
	}
	
	private RegisterGift getRegisterGift(List<RegisterGift> listRegisterGifts, String giftName){
		for (RegisterGift registerGift : listRegisterGifts) {
			if(giftName.equalsIgnoreCase(registerGift.getPromotionGift().getGift().getGiftName())){
				return registerGift;
			}
		}
		return null;
	}

	public String getRegiterGiftTotal(List<RegisterGift> listRegisterGifts, String giftName){
		if(listRegisterGifts != null){
			RegisterGift registerGift = getRegisterGift(listRegisterGifts, giftName);
			if(registerGift != null){
				return "" + registerGift.getTotal();
			}
		}
		return "";
	}
	
	private RegisterProduct getRegisterProduct(List<RegisterProduct> listRegisterProducts, String productName){
		for (RegisterProduct registerProduct : listRegisterProducts) {
			if(productName.equalsIgnoreCase(registerProduct.getPromotionProduct().getProduct().getProductName())){
				return registerProduct;
			}
		}
		return null;
	}
	
	public String getRegiterProductTotal(List<RegisterProduct> listRegisterProducts, String productName){
		if(listRegisterProducts != null){
			RegisterProduct registerProduct = getRegisterProduct(listRegisterProducts, productName);
			if(registerProduct != null){
				return "" + registerProduct.getBox();
			}
		}
		return "";
	}
	
//	public HttpServletRequest getRequest() {
//		return request;
//	}
//
//	public void setRequest(HttpServletRequest request) {
//		this.request = request;
//	}

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

	public void setGroupCustomers(LinkedHashMap<Integer, String> groupCustomers) {
		this.groupCustomers = groupCustomers;
	}


	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	
	public String getFilenameDownload() {
		return filenameDownload;
	}

	public void setFilenameDownload(String filenameDownload) {
		this.filenameDownload = filenameDownload;
	}
	
	public List<String> getListFilterValues() {
		return listFilterValues;
	}

	public void setListFilterValues(List<String> listFilterValues) {
		this.listFilterValues = listFilterValues;
	}

	public Promotion getPromotion() {
		return promotion;
	}

	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	public String getFilterValue() {
		return filterValue;
	}

	public void setFilterValue(String filterValue) {
		this.filterValue = filterValue;
	}

	public int getResultType() {
		return resultType;
	}

	public void setResultType(int resultType) {
		this.resultType = resultType;
	}
	public User getUserSes() {
		return userSes;
	}

	public void setUserSes(User userSes) {
		this.userSes = userSes;
	}
}
