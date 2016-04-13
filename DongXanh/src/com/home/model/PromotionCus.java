package com.home.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.home.util.StringUtil;


public class PromotionCus implements java.io.Serializable {
	private String promotionName;
	private int customerId;
	private String customerCode;
	private String customerName;
	private String sellMan;
	private long totalProduct;
	private  long totalPass;
	private  long totalBox;
	private  long totalBoxRegist;
	private  long quality;
	private  long totalGift;
	private float percentPass;
	private boolean result;
	private String resultString;
	private String resultPromotion;
	private int row_index;
	private String productCode;
	private String categoryName;
	private String productName;
	private  long totalPoint;
	private  long totalPointRegist;
	private BigDecimal totaPrice;
	private Set<Product> products = new HashSet<Product>(0);
	//Get customer register
	private PromotionRegister promotionRegister;
	//Get list gifts register
	private List<RegisterGift> listRegisterGifts = new ArrayList<>();
	//Get list product register
	private List<RegisterProduct> listRegisterProducts = new ArrayList<>();
	
	private Promotion promotion;
	
	public long getTotalBoxRegist() {
		return totalBoxRegist;
	}
	public void setTotalBoxRegist(long totalBoxRegist) {
		this.totalBoxRegist = totalBoxRegist;
	}
	public long getTotalPointRegist() {
		return totalPointRegist;
	}
	public void setTotalPointRegist(long totalPointRegist) {
		this.totalPointRegist = totalPointRegist;
	}
	
	public Promotion getPromotion() {
		return promotion;
	}
	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	}
	//Gift
//	private HashMap<String, Boolean> mapGifts = new HashMap<>();
//	
//	public HashMap<String, Boolean> getMapGifts() {
//		return mapGifts;
//	}
//	public void setMapGifts(HashMap<String, Boolean> mapGifts) {
//		this.mapGifts = mapGifts;
//	}
	public String getResultPromotion() {
		return resultPromotion;
	}
	public void setResultPromotion(String resultPromotion) {
		this.resultPromotion = resultPromotion;
	}
	
	public String getResultString() {
		if(StringUtil.notNull(resultString).isEmpty()){
			if(result){
				return "Đạt";
			}else{
				return "Không đạt";
			}
		}
		return resultString;
	}
	public void setResultString(String resultString) {
		this.resultString = resultString;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public Set<Product> getProducts() {
		return products;
	}
	public void setProducts(Set<Product> products) {
		this.products = products;
	}
	public String getPromotionName() {
		return promotionName;
	}
	public void setPromotionName(String promotionName) {
		this.promotionName = promotionName;
	}
	public String getCustomerCode() {
		return customerCode;
	}
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getSellMan() {
		return sellMan;
	}
	public void setSellMan(String sellMan) {
		this.sellMan = sellMan;
	}
	public long getTotalProduct() {
		return totalProduct;
	}
	public void setTotalProduct(long totalProduct) {
		this.totalProduct = totalProduct;
	}
	public long getTotalPass() {
		return totalPass;
	}
	public void setTotalPass(long totalPass) {
		this.totalPass = totalPass;
	}
	public long getTotalBox() {
		return totalBox;
	}
	public void setTotalBox(long totalBox) {
		this.totalBox = totalBox;
	}
	public long getQuality() {
		return quality;
	}
	public void setQuality(long quality) {
		this.quality = quality;
	}
	public long getTotalGift() {
		return totalGift;
	}
	public void setTotalGift(long totalGift) {
		this.totalGift = totalGift;
	}
	public float getPercentPass() {
		return percentPass;
	}
	public void setPercentPass(float percentPass) {
		this.percentPass = percentPass;
	}
	public boolean isResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	public int getRow_index() {
		return row_index;
	}
	public void setRow_index(int row_index) {
		this.row_index = row_index;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public long getTotaPoint(HashMap<Integer, Integer> mapProductPoint) {
		int total_p = 0;
		for (Product product : products) {
			if(mapProductPoint.containsKey(product.getId())){
				total_p += (product.getTotalBox()/*so thung*/ * mapProductPoint.get(product.getId()));
			}
		}
		totalPoint = total_p;
		return totalPoint;
	}
	public long getTotalPoint() {
		return totalPoint;
	}
	public void setTotalPoint(long totaPoint) {
		this.totalPoint = totaPoint;
	}
	public BigDecimal getTotaPrice() {
		return totaPrice;
	}
	public void setTotaPrice(BigDecimal totaPrice) {
		this.totaPrice = totaPrice;
	}
	
	public Object[][] paramProducts(HashMap<Integer, Integer> mapProductPoint){
		Object[][]values = new Object[products.size()][3];
		int i = 0;
		for (Product product : products) {
			if(mapProductPoint.containsKey(product.getId())){
				values[i++] = new Object[]{product.getProductCode(), product.getTotalBox(), mapProductPoint.get(product.getId())};
			}else{
				values[i++] = new Object[]{product.getProductCode(), product.getTotalBox(), 0};
			}
		}
		return values;
	}
	
	public List<RegisterGift> getListRegisterGifts() {
		return listRegisterGifts;
	}
	public void setListRegisterGifts(List<RegisterGift> listRegisterGifts) {
		this.listRegisterGifts = listRegisterGifts;
	}
	public List<RegisterProduct> getListRegisterProducts() {
		return listRegisterProducts;
	}
	public void setListRegisterProducts(List<RegisterProduct> listRegisterProducts) {
		this.listRegisterProducts = listRegisterProducts;
	}
	public PromotionRegister getPromotionRegister() {
		return promotionRegister;
	}
	public void setPromotionRegister(PromotionRegister promotionRegister) {
		this.promotionRegister = promotionRegister;
	}
}
