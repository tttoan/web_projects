package com.home.model;

// Generated Mar 28, 2016 10:31:23 PM by Hibernate Tools 4.3.1

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Customer generated by hbm2java
 */
public class Customer implements java.io.Serializable {

	private Integer id;
	private Customer customerByCustomer5Level1Id;
	private Customer customerByCustomer4Level1Id;
	private Customer customerByCustomer2Level1Id;
	private Customer customerByCustomer3Level1Id;
	private Customer customerByCustomer1Level1Id;
	private GroupCustomer groupCustomer;
	private User user;
	private Date createTime;
	private String certificateNumber;
	private Date certificateDate;
	private String certificateAddress;
	private String taxNumber;
	private String businessName;
	private String statisticName;
	private BigDecimal budgetRegister;
	private String telefone;
	private String fax;
	private String email;
	private String socialAddress;
	private String businessAddress;
	private String lawyer;
	private String adviser;
	private String director;
	private String directorMobile;
	private Date directorBirthday;
	private Boolean directorBirthdayNotify;
	private String customerCode;
	private String directorDomicile;
	private String sellMan;
	private String sellManMobile;
	private Integer budgetOriginal;
	private String otherBusiness;
	private Float customer1Percent;
	private Float customer2Percent;
	private Float customer3Percent;
	private Float customer4Percent;
	private Float customer5Percent;
	private BigDecimal revenue1;
	private BigDecimal revenue2;
	private BigDecimal revenueExpect1;
	private BigDecimal revenueExpect2;
	private BigDecimal revenueExpect3;
	private String percentProvide1;
	private String percentProvide2;
	private String percentProvide3;
	private String percentProvide4;
	private String productSell;
	private String product1Hot;
	private String product2Hot;
	private String product3Hot;
	private String product4Hot;
	private String product5Hot;
	private String product6Hot;
	private Float farmProduct1;
	private String farmProduct1Session;
	private Float farmProduct2;
	private String farmProduct2Session;
	private Float farmProduct3;
	private String farmProduct3Session;
	private Float farmProduct4;
	private String farmProduct4Session;
	private Integer totalVipCustomer;
	private Set<PromotionRegister> promotionRegisters = new HashSet<PromotionRegister>(0);
	private Set<Customer> customersForCustomer5Level1Id = new HashSet<Customer>(0);
	private Set<Statistic> statisticsForCustomerCodeLevel1 = new HashSet<Statistic>(0);
	private Set<Statistic> statisticsForCustomerCodeLevel2 = new HashSet<Statistic>(0);
	private Set<Customer> customersForCustomer4Level1Id = new HashSet<Customer>(0);
	private Set<Customer> customersForCustomer2Level1Id = new HashSet<Customer>(0);
	private Set<Customer> customersForCustomer3Level1Id = new HashSet<Customer>(0);
	private Set<Customer> customersForCustomer1Level1Id = new HashSet<Customer>(0);

	public Customer() {
	}

	public Customer(Customer customerByCustomer5Level1Id, Customer customerByCustomer4Level1Id, Customer customerByCustomer2Level1Id, Customer customerByCustomer3Level1Id,
			Customer customerByCustomer1Level1Id, GroupCustomer groupCustomer, User user, Date createTime, String certificateNumber, Date certificateDate, String certificateAddress, String taxNumber,
			String businessName, String statisticName, BigDecimal budgetRegister, String telefone, String fax, String email, String socialAddress, String businessAddress, String lawyer,
			String adviser, String director, String directorMobile, Date directorBirthday, Boolean directorBirthdayNotify, String customerCode, String directorDomicile, String sellMan,
			String sellManMobile, Integer budgetOriginal, String otherBusiness, Float customer1Percent, Float customer2Percent, Float customer3Percent, Float customer4Percent, Float customer5Percent,
			BigDecimal revenue1, BigDecimal revenue2, BigDecimal revenueExpect1, BigDecimal revenueExpect2, BigDecimal revenueExpect3, String percentProvide1, String percentProvide2,
			String percentProvide3, String percentProvide4, String productSell, String product1Hot, String product2Hot, String product3Hot, String product4Hot, String product5Hot, String product6Hot,
			Float farmProduct1, String farmProduct1Session, Float farmProduct2, String farmProduct2Session, Float farmProduct3, String farmProduct3Session, Float farmProduct4,
			String farmProduct4Session, Integer totalVipCustomer, Set<PromotionRegister> promotionRegisters, Set<Customer> customersForCustomer5Level1Id,
			Set<Statistic> statisticsForCustomerCodeLevel1, Set<Statistic> statisticsForCustomerCodeLevel2, Set<Customer> customersForCustomer4Level1Id, Set<Customer> customersForCustomer2Level1Id,
			Set<Customer> customersForCustomer3Level1Id, Set<Customer> customersForCustomer1Level1Id) {
		this.customerByCustomer5Level1Id = customerByCustomer5Level1Id;
		this.customerByCustomer4Level1Id = customerByCustomer4Level1Id;
		this.customerByCustomer2Level1Id = customerByCustomer2Level1Id;
		this.customerByCustomer3Level1Id = customerByCustomer3Level1Id;
		this.customerByCustomer1Level1Id = customerByCustomer1Level1Id;
		this.groupCustomer = groupCustomer;
		this.user = user;
		this.createTime = createTime;
		this.certificateNumber = certificateNumber;
		this.certificateDate = certificateDate;
		this.certificateAddress = certificateAddress;
		this.taxNumber = taxNumber;
		this.businessName = businessName;
		this.statisticName = statisticName;
		this.budgetRegister = budgetRegister;
		this.telefone = telefone;
		this.fax = fax;
		this.email = email;
		this.socialAddress = socialAddress;
		this.businessAddress = businessAddress;
		this.lawyer = lawyer;
		this.adviser = adviser;
		this.director = director;
		this.directorMobile = directorMobile;
		this.directorBirthday = directorBirthday;
		this.directorBirthdayNotify = directorBirthdayNotify;
		this.customerCode = customerCode;
		this.directorDomicile = directorDomicile;
		this.sellMan = sellMan;
		this.sellManMobile = sellManMobile;
		this.budgetOriginal = budgetOriginal;
		this.otherBusiness = otherBusiness;
		this.customer1Percent = customer1Percent;
		this.customer2Percent = customer2Percent;
		this.customer3Percent = customer3Percent;
		this.customer4Percent = customer4Percent;
		this.customer5Percent = customer5Percent;
		this.revenue1 = revenue1;
		this.revenue2 = revenue2;
		this.revenueExpect1 = revenueExpect1;
		this.revenueExpect2 = revenueExpect2;
		this.revenueExpect3 = revenueExpect3;
		this.percentProvide1 = percentProvide1;
		this.percentProvide2 = percentProvide2;
		this.percentProvide3 = percentProvide3;
		this.percentProvide4 = percentProvide4;
		this.productSell = productSell;
		this.product1Hot = product1Hot;
		this.product2Hot = product2Hot;
		this.product3Hot = product3Hot;
		this.product4Hot = product4Hot;
		this.product5Hot = product5Hot;
		this.product6Hot = product6Hot;
		this.farmProduct1 = farmProduct1;
		this.farmProduct1Session = farmProduct1Session;
		this.farmProduct2 = farmProduct2;
		this.farmProduct2Session = farmProduct2Session;
		this.farmProduct3 = farmProduct3;
		this.farmProduct3Session = farmProduct3Session;
		this.farmProduct4 = farmProduct4;
		this.farmProduct4Session = farmProduct4Session;
		this.totalVipCustomer = totalVipCustomer;
		this.promotionRegisters = promotionRegisters;
		this.customersForCustomer5Level1Id = customersForCustomer5Level1Id;
		this.statisticsForCustomerCodeLevel1 = statisticsForCustomerCodeLevel1;
		this.statisticsForCustomerCodeLevel2 = statisticsForCustomerCodeLevel2;
		this.customersForCustomer4Level1Id = customersForCustomer4Level1Id;
		this.customersForCustomer2Level1Id = customersForCustomer2Level1Id;
		this.customersForCustomer3Level1Id = customersForCustomer3Level1Id;
		this.customersForCustomer1Level1Id = customersForCustomer1Level1Id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Customer getCustomerByCustomer5Level1Id() {
		return this.customerByCustomer5Level1Id;
	}

	public void setCustomerByCustomer5Level1Id(Customer customerByCustomer5Level1Id) {
		this.customerByCustomer5Level1Id = customerByCustomer5Level1Id;
	}

	public Customer getCustomerByCustomer4Level1Id() {
		return this.customerByCustomer4Level1Id;
	}

	public void setCustomerByCustomer4Level1Id(Customer customerByCustomer4Level1Id) {
		this.customerByCustomer4Level1Id = customerByCustomer4Level1Id;
	}

	public Customer getCustomerByCustomer2Level1Id() {
		return this.customerByCustomer2Level1Id;
	}

	public void setCustomerByCustomer2Level1Id(Customer customerByCustomer2Level1Id) {
		this.customerByCustomer2Level1Id = customerByCustomer2Level1Id;
	}

	public Customer getCustomerByCustomer3Level1Id() {
		return this.customerByCustomer3Level1Id;
	}

	public void setCustomerByCustomer3Level1Id(Customer customerByCustomer3Level1Id) {
		this.customerByCustomer3Level1Id = customerByCustomer3Level1Id;
	}

	public Customer getCustomerByCustomer1Level1Id() {
		return this.customerByCustomer1Level1Id;
	}

	public void setCustomerByCustomer1Level1Id(Customer customerByCustomer1Level1Id) {
		this.customerByCustomer1Level1Id = customerByCustomer1Level1Id;
	}

	public GroupCustomer getGroupCustomer() {
		return this.groupCustomer;
	}

	public void setGroupCustomer(GroupCustomer groupCustomer) {
		this.groupCustomer = groupCustomer;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCertificateNumber() {
		return this.certificateNumber;
	}

	public void setCertificateNumber(String certificateNumber) {
		this.certificateNumber = certificateNumber;
	}

	public Date getCertificateDate() {
		return this.certificateDate;
	}

	public void setCertificateDate(Date certificateDate) {
		this.certificateDate = certificateDate;
	}

	public String getCertificateAddress() {
		return this.certificateAddress;
	}

	public void setCertificateAddress(String certificateAddress) {
		this.certificateAddress = certificateAddress;
	}

	public String getTaxNumber() {
		return this.taxNumber;
	}

	public void setTaxNumber(String taxNumber) {
		this.taxNumber = taxNumber;
	}

	public String getBusinessName() {
		return this.businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getStatisticName() {
		return this.statisticName;
	}

	public void setStatisticName(String statisticName) {
		this.statisticName = statisticName;
	}

	public BigDecimal getBudgetRegister() {
		return this.budgetRegister;
	}

	public void setBudgetRegister(BigDecimal budgetRegister) {
		this.budgetRegister = budgetRegister;
	}

	public String getTelefone() {
		return this.telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSocialAddress() {
		return this.socialAddress;
	}

	public void setSocialAddress(String socialAddress) {
		this.socialAddress = socialAddress;
	}

	public String getBusinessAddress() {
		return this.businessAddress;
	}

	public void setBusinessAddress(String businessAddress) {
		this.businessAddress = businessAddress;
	}

	public String getLawyer() {
		return this.lawyer;
	}

	public void setLawyer(String lawyer) {
		this.lawyer = lawyer;
	}

	public String getAdviser() {
		return this.adviser;
	}

	public void setAdviser(String adviser) {
		this.adviser = adviser;
	}

	public String getDirector() {
		return this.director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getDirectorMobile() {
		return this.directorMobile;
	}

	public void setDirectorMobile(String directorMobile) {
		this.directorMobile = directorMobile;
	}

	public Date getDirectorBirthday() {
		return this.directorBirthday;
	}

	public void setDirectorBirthday(Date directorBirthday) {
		this.directorBirthday = directorBirthday;
	}

	public Boolean getDirectorBirthdayNotify() {
		return this.directorBirthdayNotify;
	}

	public void setDirectorBirthdayNotify(Boolean directorBirthdayNotify) {
		this.directorBirthdayNotify = directorBirthdayNotify;
	}

	public String getCustomerCode() {
		return this.customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getDirectorDomicile() {
		return this.directorDomicile;
	}

	public void setDirectorDomicile(String directorDomicile) {
		this.directorDomicile = directorDomicile;
	}

	public String getSellMan() {
		return this.sellMan;
	}

	public void setSellMan(String sellMan) {
		this.sellMan = sellMan;
	}

	public String getSellManMobile() {
		return this.sellManMobile;
	}

	public void setSellManMobile(String sellManMobile) {
		this.sellManMobile = sellManMobile;
	}

	public Integer getBudgetOriginal() {
		return this.budgetOriginal;
	}

	public void setBudgetOriginal(Integer budgetOriginal) {
		this.budgetOriginal = budgetOriginal;
	}

	public String getOtherBusiness() {
		return this.otherBusiness;
	}

	public void setOtherBusiness(String otherBusiness) {
		this.otherBusiness = otherBusiness;
	}

	public Float getCustomer1Percent() {
		return this.customer1Percent;
	}

	public void setCustomer1Percent(Float customer1Percent) {
		this.customer1Percent = customer1Percent;
	}

	public Float getCustomer2Percent() {
		return this.customer2Percent;
	}

	public void setCustomer2Percent(Float customer2Percent) {
		this.customer2Percent = customer2Percent;
	}

	public Float getCustomer3Percent() {
		return this.customer3Percent;
	}

	public void setCustomer3Percent(Float customer3Percent) {
		this.customer3Percent = customer3Percent;
	}

	public Float getCustomer4Percent() {
		return this.customer4Percent;
	}

	public void setCustomer4Percent(Float customer4Percent) {
		this.customer4Percent = customer4Percent;
	}

	public Float getCustomer5Percent() {
		return this.customer5Percent;
	}

	public void setCustomer5Percent(Float customer5Percent) {
		this.customer5Percent = customer5Percent;
	}

	public BigDecimal getRevenue1() {
		return this.revenue1;
	}

	public void setRevenue1(BigDecimal revenue1) {
		this.revenue1 = revenue1;
	}

	public BigDecimal getRevenue2() {
		return this.revenue2;
	}

	public void setRevenue2(BigDecimal revenue2) {
		this.revenue2 = revenue2;
	}

	public BigDecimal getRevenueExpect1() {
		return this.revenueExpect1;
	}

	public void setRevenueExpect1(BigDecimal revenueExpect1) {
		this.revenueExpect1 = revenueExpect1;
	}

	public BigDecimal getRevenueExpect2() {
		return this.revenueExpect2;
	}

	public void setRevenueExpect2(BigDecimal revenueExpect2) {
		this.revenueExpect2 = revenueExpect2;
	}

	public BigDecimal getRevenueExpect3() {
		return this.revenueExpect3;
	}

	public void setRevenueExpect3(BigDecimal revenueExpect3) {
		this.revenueExpect3 = revenueExpect3;
	}

	public String getPercentProvide1() {
		return this.percentProvide1;
	}

	public void setPercentProvide1(String percentProvide1) {
		this.percentProvide1 = percentProvide1;
	}

	public String getPercentProvide2() {
		return this.percentProvide2;
	}

	public void setPercentProvide2(String percentProvide2) {
		this.percentProvide2 = percentProvide2;
	}

	public String getPercentProvide3() {
		return this.percentProvide3;
	}

	public void setPercentProvide3(String percentProvide3) {
		this.percentProvide3 = percentProvide3;
	}

	public String getPercentProvide4() {
		return this.percentProvide4;
	}

	public void setPercentProvide4(String percentProvide4) {
		this.percentProvide4 = percentProvide4;
	}

	public String getProductSell() {
		return this.productSell;
	}

	public void setProductSell(String productSell) {
		this.productSell = productSell;
	}

	public String getProduct1Hot() {
		return this.product1Hot;
	}

	public void setProduct1Hot(String product1Hot) {
		this.product1Hot = product1Hot;
	}

	public String getProduct2Hot() {
		return this.product2Hot;
	}

	public void setProduct2Hot(String product2Hot) {
		this.product2Hot = product2Hot;
	}

	public String getProduct3Hot() {
		return this.product3Hot;
	}

	public void setProduct3Hot(String product3Hot) {
		this.product3Hot = product3Hot;
	}

	public String getProduct4Hot() {
		return this.product4Hot;
	}

	public void setProduct4Hot(String product4Hot) {
		this.product4Hot = product4Hot;
	}

	public String getProduct5Hot() {
		return this.product5Hot;
	}

	public void setProduct5Hot(String product5Hot) {
		this.product5Hot = product5Hot;
	}

	public String getProduct6Hot() {
		return this.product6Hot;
	}

	public void setProduct6Hot(String product6Hot) {
		this.product6Hot = product6Hot;
	}

	public Float getFarmProduct1() {
		return this.farmProduct1;
	}

	public void setFarmProduct1(Float farmProduct1) {
		this.farmProduct1 = farmProduct1;
	}

	public String getFarmProduct1Session() {
		return this.farmProduct1Session;
	}

	public void setFarmProduct1Session(String farmProduct1Session) {
		this.farmProduct1Session = farmProduct1Session;
	}

	public Float getFarmProduct2() {
		return this.farmProduct2;
	}

	public void setFarmProduct2(Float farmProduct2) {
		this.farmProduct2 = farmProduct2;
	}

	public String getFarmProduct2Session() {
		return this.farmProduct2Session;
	}

	public void setFarmProduct2Session(String farmProduct2Session) {
		this.farmProduct2Session = farmProduct2Session;
	}

	public Float getFarmProduct3() {
		return this.farmProduct3;
	}

	public void setFarmProduct3(Float farmProduct3) {
		this.farmProduct3 = farmProduct3;
	}

	public String getFarmProduct3Session() {
		return this.farmProduct3Session;
	}

	public void setFarmProduct3Session(String farmProduct3Session) {
		this.farmProduct3Session = farmProduct3Session;
	}

	public Float getFarmProduct4() {
		return this.farmProduct4;
	}

	public void setFarmProduct4(Float farmProduct4) {
		this.farmProduct4 = farmProduct4;
	}

	public String getFarmProduct4Session() {
		return this.farmProduct4Session;
	}

	public void setFarmProduct4Session(String farmProduct4Session) {
		this.farmProduct4Session = farmProduct4Session;
	}

	public Integer getTotalVipCustomer() {
		return this.totalVipCustomer;
	}

	public void setTotalVipCustomer(Integer totalVipCustomer) {
		this.totalVipCustomer = totalVipCustomer;
	}

	public Set<PromotionRegister> getPromotionRegisters() {
		return this.promotionRegisters;
	}

	public void setPromotionRegisters(Set<PromotionRegister> promotionRegisters) {
		this.promotionRegisters = promotionRegisters;
	}

	public Set<Customer> getCustomersForCustomer5Level1Id() {
		return this.customersForCustomer5Level1Id;
	}

	public void setCustomersForCustomer5Level1Id(Set<Customer> customersForCustomer5Level1Id) {
		this.customersForCustomer5Level1Id = customersForCustomer5Level1Id;
	}

	public Set<Statistic> getStatisticsForCustomerCodeLevel1() {
		return this.statisticsForCustomerCodeLevel1;
	}

	public void setStatisticsForCustomerCodeLevel1(Set<Statistic> statisticsForCustomerCodeLevel1) {
		this.statisticsForCustomerCodeLevel1 = statisticsForCustomerCodeLevel1;
	}

	public Set<Statistic> getStatisticsForCustomerCodeLevel2() {
		return this.statisticsForCustomerCodeLevel2;
	}

	public void setStatisticsForCustomerCodeLevel2(Set<Statistic> statisticsForCustomerCodeLevel2) {
		this.statisticsForCustomerCodeLevel2 = statisticsForCustomerCodeLevel2;
	}

	public Set<Customer> getCustomersForCustomer4Level1Id() {
		return this.customersForCustomer4Level1Id;
	}

	public void setCustomersForCustomer4Level1Id(Set<Customer> customersForCustomer4Level1Id) {
		this.customersForCustomer4Level1Id = customersForCustomer4Level1Id;
	}

	public Set<Customer> getCustomersForCustomer2Level1Id() {
		return this.customersForCustomer2Level1Id;
	}

	public void setCustomersForCustomer2Level1Id(Set<Customer> customersForCustomer2Level1Id) {
		this.customersForCustomer2Level1Id = customersForCustomer2Level1Id;
	}

	public Set<Customer> getCustomersForCustomer3Level1Id() {
		return this.customersForCustomer3Level1Id;
	}

	public void setCustomersForCustomer3Level1Id(Set<Customer> customersForCustomer3Level1Id) {
		this.customersForCustomer3Level1Id = customersForCustomer3Level1Id;
	}

	public Set<Customer> getCustomersForCustomer1Level1Id() {
		return this.customersForCustomer1Level1Id;
	}

	public void setCustomersForCustomer1Level1Id(Set<Customer> customersForCustomer1Level1Id) {
		this.customersForCustomer1Level1Id = customersForCustomer1Level1Id;
	}

}
