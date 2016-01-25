<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- Using Struts2 Tags in JSP --%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/struts-dojo-tags" prefix="sd" %>
<%@ include file="header.jsp"%>
<%@ include file="user_profile.jsp"%>
<%@ include file="menu.jsp"%>

<!-- page content -->
<div class="right_col" role="main">
	<div class="">
		<div class="clearfix"></div>
		<div class="row">
			<div class="col-md-12 col-sm-12 col-xs-12">
				<div class="x_panel">
					<div class="x_content">
						<s:form action="add_invoice" theme="bootstrap"
							class="form-horizontal form-label-left">
							<span class="section"></span>
							<%
								Calendar d = Calendar.getInstance();
								int yearNow = d.get(Calendar.YEAR);
							%>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="customerCode">Mã KH <%=yearNow %>: <span class="required">*</span>
								</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<s:textfield id="customerCode" name="customerCode" cssClass="form-control col-md-7 col-xs-12"/>
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="userFullName">NVTT: <span class="required">*</span>
								</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<s:hidden id="userId" name="userId" />
									<sd:autocompleter id="userFullName" name="userFullName" cssClass="form-control col-md-7 col-xs-12" showDownArrow="false" autoComplete="true"
										list="{'Arsenal','Leicester City','Man City','Tottenham','Man United','West Ham',
										'Stoke City','Crystal Palace','Liverpool','Southampton','Everton','Watford','West Bromwich','Chelsea','Bournemouth'}" />
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="createTime">Ngày lập: <span class="required">*</span>
								</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<sd:datetimepicker id="createTime"  name="createTime" cssClass="form-control col-md-7 col-xs-12" 
										displayFormat="dd-MM-yyyy" />
								</div>
							</div>
							<span class="section">Thông Tin Khách Hàng</span>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="customerName">Tên doanh nghiệp (cửa hàng): <span class="required">*</span>
								</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<sd:autocompleter id="customerName" name="customerName" cssClass="form-control col-md-7 col-xs-12" showDownArrow="false" autoComplete="true"
										list="{'Arsenal','Leicester City','Man City','Tottenham','Man United','West Ham',
										'Stoke City','Crystal Palace','Liverpool','Southampton','Everton','Watford','West Bromwich','Chelsea','Bournemouth'}" />
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="certificateNumber">Giấy phép ĐKKD số: <span class="required">*</span>
								</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<s:textfield id="certificateNumber" name="certificateNumber" cssClass="form-control col-md-7 col-xs-12" type="number" />
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="certificateDate">Ngày cấp: <span class="required">*</span>
								</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<sd:datetimepicker id="certificateDate" cssClass="form-control col-md-7 col-xs-12" 
										name="certificateDate" displayFormat="dd-MM-yyyy" />
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="certificateAddress">Địa chỉ đăng kí KD: <span class="required">*</span>
								</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<s:textfield id="certificateAddress" name="certificateAddress" cssClass="form-control col-md-7 col-xs-12"/>
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="taxNumber">Mã số thuế: <span class="required">*</span>
								</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<s:textfield id="taxNumber" name="taxNumber" cssClass="form-control col-md-7 col-xs-12"/>
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="budgetRegister">Vốn đăng kí: <span class="required">*</span>
								</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<s:textfield id="budgetRegister" name="budgetRegister" cssClass="form-control col-md-7 col-xs-12"/>
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="telefone">Điện thoại bàn: <span class="required">*</span>
								</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<s:textfield id="telefone" name="telefone" cssClass="form-control col-md-7 col-xs-12"/>
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="fax">Fax: <span class="required">*</span>
								</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<s:textfield id="fax" name="fax" cssClass="form-control col-md-7 col-xs-12"/>
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="email">Email: <span class="required">*</span>
								</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<s:textfield id="email" name="email" type="email" cssClass="form-control col-md-7 col-xs-12"/>
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="socialAddress">Địa chỉ mạng xã hội (Facebook, Twitter, Zalo,…): <span class="required">*</span>
								</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<s:textfield id="socialAddress" name="socialAddress" cssClass="form-control col-md-7 col-xs-12"/>
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="businessAddress">Địa điểm kinh doanh: <span class="required">*</span>
								</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<s:textfield id="businessAddress" name="businessAddress" cssClass="form-control col-md-7 col-xs-12"/>
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="adviser">Người đại diện pháp luật: <span class="required">*</span>
								</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<s:textfield id="adviser" name="adviser" cssClass="form-control col-md-7 col-xs-12"/>
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="director">Người quyết định chính công việc: <span class="required">*</span>
								</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<s:textfield id="director" name="director" cssClass="form-control col-md-7 col-xs-12"/>
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="directorMobile">ĐT di động: <span class="required">*</span>
								</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<s:textfield id="directorMobile" name="directorMobile"  type="number" cssClass="form-control col-md-7 col-xs-12"/>
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="directorBirthday">Ngày sinh: <span class="required">*</span>
								</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<sd:datetimepicker id="directorBirthday" name="directorBirthday" displayFormat="dd-MM-yyyy" />
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="directorDomicile">Nguyên quán: <span class="required">*</span>
								</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<s:textfield id="directorDomicile" name="directorDomicile" cssClass="form-control col-md-7 col-xs-12"/>
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="sellMan">Người bán hàng trực tiếp: <span class="required">*</span>
								</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<s:textfield id="sellMan" name="sellMan" cssClass="form-control col-md-7 col-xs-12"/>
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="sellManMobile">ĐT di động: <span class="required">*</span>
								</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<s:textfield id="sellManMobile" name="sellManMobile" type="number" cssClass="form-control col-md-7 col-xs-12"/>
								</div>
							</div>
							
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="budgetOriginal">Ước vốn tự có để kinh doanh (triệu): <span class="required">*</span>
								</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<s:textfield id="budgetOriginal" name="budgetOriginal" type="number" cssClass="form-control col-md-7 col-xs-12"/>
								</div>
							</div>
							<span class="section">Hiện trạng kinh doanh thuốc BVTV</span>
							
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="otherBusiness">Ngành nghề kinh doanh khác: <span class="required">*</span>
								</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<s:textfield id="otherBusiness" name="otherBusiness" cssClass="form-control col-md-7 col-xs-12" />
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12">Các cấp I đang nhận hàng chính:
								</label>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="customer1Level1">Tên: <span class="required">*</span>
								</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<s:textfield id="customer1Level1" name="customer1Level1" cssClass="form-control col-md-7 col-xs-12" />
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="customer1Phone">Điện thoại: <span class="required">*</span>
								</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<s:textfield id="customer1Phone" name="customer1Phone" cssClass="form-control col-md-7 col-xs-12" />
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="customer1Percent">Tỉ lệ nhận (%): <span class="required">*</span>
								</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<s:textfield id="customer1Percent" name="customer1Percent" cssClass="form-control col-md-7 col-xs-12" />
								</div>
							</div>
							
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="customer2Level1">Tên: <span class="required">*</span>
								</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<s:textfield id="customer2Level1" name="customer2Level1" cssClass="form-control col-md-7 col-xs-12" />
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="customer2Phone">Điện thoại: <span class="required">*</span>
								</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<s:textfield id="customer2Phone" name="customer2Phone" cssClass="form-control col-md-7 col-xs-12" />
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="customer2Percent">Tỉ lệ nhận (%): <span class="required">*</span>
								</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<s:textfield id="customer2Percent" name="customer2Percent" cssClass="form-control col-md-7 col-xs-12" />
								</div>
							</div>
							
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="customer3Level1">Tên: <span class="required">*</span>
								</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<s:textfield id="customer3Level1" name="customer3Level1" cssClass="form-control col-md-7 col-xs-12" />
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="customer3Phone">Điện thoại: <span class="required">*</span>
								</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<s:textfield id="customer3Phone" name="customer3Phone" cssClass="form-control col-md-7 col-xs-12" />
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="customer3Percent">Tỉ lệ nhận (%): <span class="required">*</span>
								</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<s:textfield id="customer3Percent" name="customer3Percent" cssClass="form-control col-md-7 col-xs-12" />
								</div>
							</div>
							
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="customer4Level1">Tên: <span class="required">*</span>
								</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<s:textfield id="customer4Level1" name="customer4Level1" cssClass="form-control col-md-7 col-xs-12" />
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="customer4Phone">Điện thoại: <span class="required">*</span>
								</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<s:textfield id="customer4Phone" name="customer4Phone" cssClass="form-control col-md-7 col-xs-12" />
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="customer4Percent">Tỉ lệ nhận (%): <span class="required">*</span>
								</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<s:textfield id="customer4Percent" name="customer4Percent" cssClass="form-control col-md-7 col-xs-12" />
								</div>
							</div>
							
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="customer5Level1">Tên: <span class="required">*</span>
								</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<s:textfield id="customer5Level1" name="customer5Level1" cssClass="form-control col-md-7 col-xs-12" />
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="customer5Phone">Điện thoại: <span class="required">*</span>
								</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<s:textfield id="customer5Phone" name="customer5Phone" cssClass="form-control col-md-7 col-xs-12" />
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="customer5Percent">Tỉ lệ nhận (%): <span class="required">*</span>
								</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<s:textfield id="customer5Percent" name="customer5Percent" cssClass="form-control col-md-7 col-xs-12" />
								</div>
							</div>
							
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12">Doanh số bán thuốc BVTV 2 niên vụ vừa qua:
								</label>
							</div>
							
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12" for="revenue1">
								 <%=(yearNow - 2) %> - <%=(yearNow-1) %> (triệu đồng): <span class="required">*</span>
								</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<s:textfield id="revenue1" name="revenue1" cssClass="form-control col-md-7 col-xs-12" />
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12" for="revenue2">
								 <%=(yearNow -1) %> - <%=(yearNow) %> (triệu đồng): <span class="required">*</span>
								</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<s:textfield id="revenue2" name="revenue2" cssClass="form-control col-md-7 col-xs-12" />
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12">Tỉ lệ doanh số phân phối của các Cty kinh doanh thuốc BVTV cung ứng:
								</label>
							</div>
							
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="percentProvide1">Trên 30% <span class="required">*</span>
								</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<s:textfield id="percentProvide1" name="percentProvide1" cssClass="form-control col-md-7 col-xs-12" />
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="percentProvide2">20 - 30% <span class="required">*</span>
								</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<s:textfield id="percentProvide2" name="percentProvide2" cssClass="form-control col-md-7 col-xs-12" />
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="percentProvide3">10 - 20% <span class="required">*</span>
								</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<s:textfield id="percentProvide3" name="percentProvide3" cssClass="form-control col-md-7 col-xs-12" />
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="percentProvide4">Dưới 10% <span class="required">*</span>
								</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<s:textfield id="percentProvide4" name="percentProvide4" cssClass="form-control col-md-7 col-xs-12" />
								</div>
							</div>
							
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="productSell">Tên ít nhất 7 sản phẩm Đồng Xanh đang bán theo số lượng thấp dần:
								</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<s:textfield id="productSell" name="productSell" cssClass="form-control col-md-7 col-xs-12" />
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12" >Danh mục các mặt hàng (tên thương mại) đang tiêu thụ mạnh:
								</label>
							</div>
							
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="product1Hot">3 Sản phẩm thuốc trừ cỏ:
								</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<s:textfield id="product1Hot" name="product1Hot" cssClass="form-control col-md-7 col-xs-12" />
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="product2Hot">5 Sản phẩm thuốc trừ sâu:
								</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<s:textfield id="product2Hot" name="product2Hot" cssClass="form-control col-md-7 col-xs-12" />
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="product3Hot">3 Sản phẩm thuốc trừ rầy:
								</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<s:textfield id="product3Hot" name="product3Hot" cssClass="form-control col-md-7 col-xs-12" />
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="product4Hot">5 Sản phẩm thuốc trừ bệnh:
								</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<s:textfield id="product4Hot" name="product4Hot" cssClass="form-control col-md-7 col-xs-12" />
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="product5Hot">3 Sản phẩm kích thích sinh trưởng:
								</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<s:textfield id="product5Hot" name="product5Hot" cssClass="form-control col-md-7 col-xs-12" />
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="product6Hot">3 Sản phẩm thuốc trừ ốc:
								</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<s:textfield id="product6Hot" name="product6Hot" cssClass="form-control col-md-7 col-xs-12" />
								</div>
							</div>
							<div class="ln_solid"></div>
							<div class="form-group">
								<div class="col-md-6 col-md-offset-3">
									<s:reset class="btn btn-primary" value="Reset" />
									<s:submit id="send" value="Submit" class="btn btn-success" />
								</div>
							</div>
						</s:form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- footer content -->
	<footer>
		<div class="">
			<p class="pull-right">
				@Copyright by <a>ThienTran</a>. | <span class="lead"> <i
					class="fa fa-paw"></i> VNFamily Tech!
				</span>
			</p>
		</div>
		<div class="clearfix"></div>
	</footer>
	<!-- /footer content -->

</div>
<!-- /page content -->
</div>

</div>

<div id="custom_notifications" class="custom-notifications dsp_none">
	<ul class="list-unstyled notifications clearfix"
		data-tabbed_notifications="notif-group">
	</ul>
	<div class="clearfix"></div>
	<div id="notif-group" class="tabbed_notifications"></div>
</div>

<script src="js/bootstrap.min.js"></script>

<!-- chart js -->
<script src="js/chartjs/chart.min.js"></script>
<!-- bootstrap progress js -->
<script src="js/progressbar/bootstrap-progressbar.min.js"></script>
<script src="js/nicescroll/jquery.nicescroll.min.js"></script>
<!-- icheck -->
<script src="js/icheck/icheck.min.js"></script>

<script src="js/custom.js"></script>
<!-- form validation -->
<script src="js/validator/validator.js"></script>
<script>
	// initialize the validator function
	validator.message['date'] = 'not a real date';

	// validate a field on "blur" event, a 'select' on 'change' event & a '.reuired' classed multifield on 'keyup':
	$('form').on('blur', 'input[required], input.optional, select.required',
			validator.checkField).on('change', 'select.required',
			validator.checkField).on('keypress', 'input[required][pattern]',
			validator.keypress);

	$('.multi.required').on('keyup blur', 'input', function() {
		validator.checkField.apply($(this).siblings().last()[0]);
	});

	// bind the validation to the form submit event
	//$('#send').click('submit');//.prop('disabled', true);

	$('form').submit(function(e) {
		e.preventDefault();
		var submit = true;
		// evaluate the form using generic validaing
		if (!validator.checkAll($(this))) {
			submit = false;
		}

		if (submit)
			this.submit();
		return false;
	});

	/* FOR DEMO ONLY */
	$('#vfields').change(function() {
		$('form').toggleClass('mode2');
	}).prop('checked', false);

	$('#alerts').change(function() {
		validator.defaults.alerts = (this.checked) ? false : true;
		if (this.checked)
			$('form .alert').remove();
	}).prop('checked', false);
</script>

</body>

</html>