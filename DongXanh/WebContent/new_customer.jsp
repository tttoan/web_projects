<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/struts-dojo-tags" prefix="sd"%>

<%@ include file="header.jsp"%>
<%@ include file="user_profile.jsp"%>
<%@ include file="menu.jsp"%>

<!-- page content -->
<div class="right_col" role="main">
	<div class="">
		<div class="page-title">
			<div class="title_left">
				<h3>Tạo Hồ Sơ Khách Hàng</h3>
			</div>
		</div>
		<div class="clearfix"></div>
		<div class="row">
			<div class="col-md-12 col-sm-12 col-xs-12">
				<div class="x_panel">
					<div class="x_content">
						<%
							Calendar d = Calendar.getInstance();
							int yearNow = d.get(Calendar.YEAR);
						%>
						<s:form action="add_customer" method="post"
							cssClass="form-horizontal form-label-left" theme="bootstrap">
							<div class="item">
								<label class="control-label col-md-2 col-sm-3 col-xs-12"
									for="customerCode">Mã KH <%=yearNow%> <span
									class="required">*</span>
								</label>
								<div class="col-md-3 col-sm-6 col-xs-12">
									<s:textfield id="customerCode" name="customerCode"
										cssClass="form-control col-md-7 col-xs-12" value="%{customer.customerCode}" />
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-2 col-sm-1 col-xs-12"
									for="email">NVTT <span class="required">+</span>
								</label>
								<div class="col-md-3 col-sm-6 col-xs-12">
									<sd:autocompleter id="employeeName" name="employeeName"
										list="{1}" showDownArrow="false" emptyOption="true"
										autoComplete="false" cssClass="form-control col-md-7 col-xs-12" />
								</div>
							</div>
							<div class="item">
								<s:hidden name="groupCustomerId" value="" />
								<label class="control-label col-md-2 col-sm-3 col-xs-12"
									for="groupCustomerName">Nhóm <span
									class="required">*</span>
								</label>	
								<div class="col-md-3 col-sm-6 col-xs-12">
									<s:select id="groupCustomerName" name="groupCustomerName"
										cssClass="form-control col-md-7 col-xs-12" list="listGroupCustomer" listKey="id" listValue="groupName" />
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-2 col-sm-3 col-xs-12"
									for="createTime">Ngày Lập <span class="required">*</span>
								</label>
								<div class="col-md-2 col-sm-6 col-xs-12">
									<sd:datetimepicker id="createTime" name="createTime"
										displayFormat="dd-MM-yyyy" value="%{'today'}"
										cssClass="form-control col-md-7 col-xs-12" />
								</div>
							</div>
							<span class="section">Phần I: Thông tin khách hàng</span>
							<div class="item">
								<label class="control-label col-md-2 col-sm-3 col-xs-12"
									for="businessName">Tên doanh nghiệp (cửa hàng) <span
									class="required">+</span>
								</label>
								<div class="col-md-3 col-sm-6 col-xs-12">
									<s:textfield id="businessName" name="businessName"
										cssClass="form-control col-md-7 col-xs-12" />
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-2 col-sm-3 col-xs-12"
									for="certificateNumber">Giấy phép ĐKKD số <span
									class="required">*</span>
								</label>
								<div class="col-md-2 col-sm-6 col-xs-12">
									<s:textfield id="certificateNumber" name="certificateNumber"
										class="form-control col-md-7 col-xs-12" />
								</div>
							</div>

							<div class="item">
								<label class="control-label col-md-2 col-sm-3 col-xs-12"
									for="certificateDate">Ngày cấp <span class="required">*</span>
								</label>
								<div class="col-md-3 col-sm-6 col-xs-12">
									<sd:datetimepicker id="certificateDate" name="certificateDate"
										displayFormat="dd-MM-yyyy" value="%{'today'}"
										cssClass="form-control col-md-7 col-xs-12" />
								</div>
							</div>

							<div class="item form-group">
								<label class="control-label col-md-2 col-sm-3 col-xs-12"
									for="certificateAddress">Địa chỉ đăng kí KD <span
									class="required">*</span>
								</label>
								<div class="col-md-3 col-sm-6 col-xs-12">
									<s:textfield id="certificateAddress" name="certificateAddress"
										cssClass="form-control col-md-7 col-xs-12" />
								</div>
							</div>
							<div class="item">
								<label class="control-label col-md-2 col-sm-3 col-xs-12"
									for="taxNumber">Mã số thuế <span class="required">*</span>
								</label>
								<div class="col-md-3 col-sm-6 col-xs-12">
									<s:textfield id="taxNumber" name="taxNumber"
										cssClass="form-control col-md-7 col-xs-12" />
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-2 col-sm-3 col-xs-12"
									for="budgetRegister">Vốn đăng kí <span class="required">*</span>
								</label>
								<div class="col-md-3 col-sm-6 col-xs-12">
									<s:textfield id="budgetRegister" name="budgetRegister"
										cssClass="form-control col-md-7 col-xs-12" />
								</div>
							</div>
							<div class="item">
								<label class="control-label col-md-2 col-sm-3 col-xs-12"
									for="telefone">Điện thoại bàn <span class="required">*</span>
								</label>
								<div class="col-md-3 col-sm-6 col-xs-12">
									<s:textfield id="telefone" name="telefone"
										cssClass="form-control col-md-7 col-xs-12" />
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-2 col-sm-3 col-xs-12"
									for="fax">Fax <span class="required">*</span>
								</label>
								<div class="col-md-3 col-sm-6 col-xs-12">
									<s:textfield id="fax" name="fax"
										cssClass="form-control col-md-2 col-xs-12" />
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-2 col-sm-3 col-xs-12"
									for="email">Email <span class="required">*</span>
								</label>
								<div class="col-md-4 col-sm-6 col-xs-12">
									<s:textfield id="email" name="email" type="email"
										value="%{'thanhhqq@gmail.com'}"
										cssClass="form-control col-md-7 col-xs-12" />
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="socialAddress">Địa chỉ mạng xã hội (Facebook,
									Twitter, Zalo,…) <span class="required">*</span>
								</label>
								<div class="col-md-8 col-sm-6 col-xs-12">
									<s:textfield id="socialAddress" name="socialAddress"
										cssClass="form-control col-md-7 col-xs-12" />
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="businessAddress">Địa điểm kinh doanh <span
									class="required">*</span>
								</label>
								<div class="col-md-5 col-sm-6 col-xs-12">
									<s:textfield id="businessAddress" name="businessAddress"
										cssClass="form-control col-md-7 col-xs-12" />
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="adviser">Người đại diện pháp luật <span
									class="required">*</span>
								</label>
								<div class="col-md-5 col-sm-6 col-xs-12">
									<s:textfield id="adviser" name="adviser"
										cssClass="form-control col-md-7 col-xs-12" />
								</div>
							</div>
							<div class="item">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="director">Người quyết định chính công việc <span
									class="required">*</span>
								</label>
								<div class="col-md-3 col-sm-6 col-xs-12">
									<s:textfield id="director" name="director"
										cssClass="form-control col-md-7 col-xs-12" />
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-2 col-sm-3 col-xs-12"
									for="directorMobile">ĐT di động <span class="required">*</span>
								</label>
								<div class="col-md-3 col-sm-6 col-xs-12">
									<s:textfield id="directorMobile" name="directorMobile"
										value="%{'0935441344'}"
										cssClass="form-control col-md-7 col-xs-12" />
								</div>
							</div>
							<div class="item">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="directorBirthday">Ngày sinh: <span
									class="required">*</span>
								</label>
								<div class="col-md-3 col-sm-6 col-xs-12">
									<sd:datetimepicker id="directorBirthday" value="%{'today'}"
										name="directorBirthday" displayFormat="dd-MM-yyyy"
										cssClass="form-control col-md-7 col-xs-12" />
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-2 col-sm-3 col-xs-12"
									for="directorDomicile">Nguyên quán: <span
									class="required">*</span>
								</label>
								<div class="col-md-3 col-sm-6 col-xs-12">
									<s:textfield id="directorDomicile" name="directorDomicile"
										cssClass="form-control col-md-7 col-xs-12" />
								</div>
							</div>
							<div class="item">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="sellMan">Người bán hàng trực tiếp <span
									class="required">*</span>
								</label>
								<div class="col-md-3 col-sm-6 col-xs-12">
									<s:textfield id="sellMan" name="sellMan"
										cssClass="form-control col-md-7 col-xs-12" />
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-2 col-sm-3 col-xs-12"
									for="sellManMobile">ĐT di động <span class="required">*</span>
								</label>
								<div class="col-md-3 col-sm-6 col-xs-12">
									<s:textfield id="sellManMobile" name="sellManMobile"
										value="%{'0935441344'}"
										cssClass="form-control col-md-7 col-xs-12" />
								</div>
							</div>

							<div class="item form-group">
								<label class="control-label col-md-4 col-sm-3 col-xs-12"
									for="budgetOriginal">Ước vốn tự có để kinh doanh
									(Triệu) <span class="required">*</span>
								</label>
								<div class="col-md-4 col-sm-6 col-xs-12">
									<s:textfield id="budgetOriginal" name="budgetOriginal"
										cssClass="form-control col-md-7 col-xs-12" />
								</div>
							</div>

							<span class="section">Phần II: Hiện trạng kinh doanh thuốc
								BVTV</span>

							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="otherBusiness">Ngành nghề kinh doanh khác <span
									class="required">*</span>
								</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<s:textfield id="otherBusiness" name="otherBusiness"
										cssClass="form-control col-md-7 col-xs-12" />
								</div>
							</div>
							<div class="item form-group">
								<label class="col-md-3 col-sm-3 col-xs-12">Các cấp I
									đang nhận hàng chính </label>
							</div>
							<div class="item">
								<label class="control-label col-md-1 col-sm-3 col-xs-12"
									for="customer1Level1">Tên: <span class="required">+</span>
								</label>
								<div class="col-md-3 col-sm-6 col-xs-12">
									<sd:autocompleter id="customer1Level1" name="customer1Level1"
										cssClass="form-control col-md-7 col-xs-12"
										showDownArrow="false" autoComplete="true" list="{1}" />
								</div>
							</div>
							<div class="item">
								<label class="control-label col-md-1 col-sm-3 col-xs-12"
									for="customer1Phone">ÐT <span class="required">*</span>
								</label>
								<div class="col-md-3 col-sm-6 col-xs-12">
									<s:textfield id="customer1Phone" name="customer1Phone"
										value="%{'0935441344'}"
										cssClass="form-control col-md-7 col-xs-12" />
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-2 col-sm-3 col-xs-12"
									for="customer1Percent">Tỉ lệ nhận (%) <span
									class="required">*</span>
								</label>
								<div class="col-md-2 col-sm-6 col-xs-12">
									<s:textfield id="customer1Percent" name="customer1Percent"
										type="number" cssClass="form-control col-md-7 col-xs-12"
										value="%{0}" />
								</div>
							</div>

							<div class="item">
								<label class="control-label col-md-1 col-sm-3 col-xs-12"
									for="customer2Level1">Tên: <span class="required">+</span>
								</label>
								<div class="col-md-3 col-sm-6 col-xs-12">
									<sd:autocompleter id="customer2Level1" name="customer2Level1"
										cssClass="form-control col-md-7 col-xs-12"
										showDownArrow="false" autoComplete="true" list="{1}" />
								</div>
							</div>
							<div class="item">
								<label class="control-label col-md-1 col-sm-3 col-xs-12"
									for="customer2Phone">ÐT: <span class="required">*</span>
								</label>
								<div class="col-md-3 col-sm-6 col-xs-12">
									<s:textfield id="customer2Phone" name="customer2Phone"
										value="%{'0935441344'}"
										cssClass="form-control col-md-7 col-xs-12" />
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-2 col-sm-3 col-xs-12"
									for="customer2Percent">Tỉ lệ nhận (%) <span
									class="required">*</span>
								</label>
								<div class="col-md-2 col-sm-6 col-xs-12">
									<s:textfield id="customer2Percent" name="customer2Percent"
										type="number" cssClass="form-control col-md-7 col-xs-12"
										value="%{0}" />
								</div>
							</div>

							<div class="item">
								<label class="control-label col-md-1 col-sm-3 col-xs-12"
									for="customer3Level1">Tên: <span class="required">+</span>
								</label>
								<div class="col-md-3 col-sm-6 col-xs-12">
									<sd:autocompleter id="customer3Level1" name="customer3Level1"
										cssClass="form-control col-md-7 col-xs-12"
										showDownArrow="false" autoComplete="true" list="{1}" />
								</div>
							</div>
							<div class="item">
								<label class="control-label col-md-1 col-sm-3 col-xs-12"
									for="customer3Phone">ÐT <span class="required">*</span>
								</label>
								<div class="col-md-3 col-sm-6 col-xs-12">
									<s:textfield id="customer3Phone" name="customer3Phone"
										value="%{'0935441344'}"
										cssClass="form-control col-md-7 col-xs-12" />
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-2 col-sm-3 col-xs-12"
									for="customer3Percent">Tỉ lệ nhận (%) <span
									class="required">*</span>
								</label>
								<div class="col-md-2 col-sm-6 col-xs-12">
									<s:textfield id="customer3Percent" name="customer3Percent"
										type="number" cssClass="form-control col-md-7 col-xs-12"
										value="%{0}" />
								</div>
							</div>

							<div class="item">
								<label class="control-label col-md-1 col-sm-3 col-xs-12"
									for="customer4Level1">Tên: <span class="required">*</span>
								</label>
								<div class="col-md-3 col-sm-6 col-xs-12">
									<sd:autocompleter id="customer4Level1" name="customer4Level1"
										cssClass="form-control col-md-7 col-xs-12"
										showDownArrow="false" autoComplete="true" list="{1}" />
								</div>
							</div>
							<div class="item">
								<label class="control-label col-md-1 col-sm-3 col-xs-12"
									for="customer4Phone">ĐT <span class="required">*</span>
								</label>
								<div class="col-md-3 col-sm-6 col-xs-12">
									<s:textfield id="customer4Phone" name="customer4Phone"
										value="%{'0935441344'}"
										cssClass="form-control col-md-7 col-xs-12" />
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-2 col-sm-3 col-xs-12"
									for="customer4Percent">Tỉ lệ nhận (%) <span
									class="required">*</span>
								</label>
								<div class="col-md-2 col-sm-6 col-xs-12">
									<s:textfield id="customer4Percent" name="customer4Percent"
										type="number" cssClass="form-control col-md-7 col-xs-12"
										value="%{0}" />
								</div>
							</div>

							<div class="item">
								<label class="control-label col-md-1 col-sm-3 col-xs-12"
									for="customer5Level1">Tên: <span class="required">+</span>
								</label>
								<div class="col-md-3 col-sm-6 col-xs-12">
									<sd:autocompleter id="customer5Level1" name="customer5Level1"
										cssClass="form-control col-md-7 col-xs-12"
										showDownArrow="false" autoComplete="true" list="{1}" />
								</div>
							</div>
							<div class="item">
								<label class="control-label col-md-1 col-sm-3 col-xs-12"
									for="customer5Phone">ĐT <span class="required">*</span>
								</label>
								<div class="col-md-3 col-sm-6 col-xs-12">
									<s:textfield id="customer5Phone" name="customer5Phone"
										value="%{'0935441344'}"
										cssClass="form-control col-md-7 col-xs-12" />
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-2 col-sm-3 col-xs-12"
									for="customer5Percent">Tỉ lệ nhận (%) <span
									class="required">*</span>
								</label>
								<div class="col-md-2 col-sm-6 col-xs-12">
									<s:textfield id="customer5Percent" name="customer5Percent"
										type="number" cssClass="form-control col-md-7 col-xs-12"
										value="%{0}" />
								</div>
							</div>

							<div class="item form-group">
								<label class="col-md-5 col-sm-3 col-xs-12">Doanh số bán
									thuốc BVTV 2 niên vụ vừa qua </label>
							</div>

							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="revenue1"> <%=(yearNow - 2)%> - <%=(yearNow - 1)%>
									(Triệu đồng): <span class="required">*</span>
								</label>
								<div class="col-md-3 col-sm-6 col-xs-12">
									<s:textfield id="revenue1" name="revenue1" value="%{0}"
										cssClass="form-control col-md-7 col-xs-12" />
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="revenue2"> <%=(yearNow - 1)%> - <%=(yearNow)%>
									(Triệu đồng): <span class="required">*</span>
								</label>
								<div class="col-md-3 col-sm-6 col-xs-12">
									<s:textfield id="revenue2" name="revenue2" value="%{0}"
										cssClass="form-control col-md-7 col-xs-12" />
								</div>
							</div>

							<div class="item form-group">
								<label class="col-md-5 col-sm-3 col-xs-12">Doanh số dự
									kiến trong 3 năm tới </label>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="revenueExpect1"> <%=(yearNow)%> - <%=(yearNow + 1)%>
									(Triệu đồng): <span class="required">*</span>
								</label>
								<div class="col-md-3 col-sm-6 col-xs-12">
									<s:textfield id="revenueExpect1" name="revenueExpect1" value="%{0}"
										cssClass="form-control col-md-7 col-xs-12" />
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="revenueExpect2"> <%=(yearNow + 1)%> - <%=(yearNow + 2)%>
									(Triệu đồng): <span class="required">*</span>
								</label>
								<div class="col-md-3 col-sm-6 col-xs-12">
									<s:textfield id="revenueExpect2" name="revenueExpect2"  value="%{0}"
										cssClass="form-control col-md-7 col-xs-12" />
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="revenueExpect3"> <%=(yearNow + 2)%> - <%=(yearNow + 3)%>
									(Triệu đồng): <span class="required">*</span>
								</label>
								<div class="col-md-3 col-sm-6 col-xs-12">
									<s:textfield id="revenueExpect3" name="revenueExpect3" value="%{0}"
										cssClass="form-control col-md-7 col-xs-12" />
								</div>
							</div>
							<div class="item form-group">
								<label class="col-md-8 col-sm-3 col-xs-12">Tỉ lệ doanh
									số phân phối của các Cty kinh doanh thuốc BVTV cung ứng </label>
							</div>

							<div class="item">
								<label class="control-label col-md-2 col-sm-3 col-xs-12"
									for="percentProvide1">Trên 30% <span class="required">*</span>
								</label>
								<div class="col-md-3 col-sm-6 col-xs-12">
									<s:textfield id="percentProvide1" name="percentProvide1"
										cssClass="form-control col-md-7 col-xs-12" />
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-2 col-sm-3 col-xs-12"
									for="percentProvide2">20 - 30% <span class="required">*</span>
								</label>
								<div class="col-md-3 col-sm-6 col-xs-12">
									<s:textfield id="percentProvide2" name="percentProvide2"
										cssClass="form-control col-md-7 col-xs-12" />
								</div>
							</div>
							<div class="item">
								<label class="control-label col-md-2 col-sm-3 col-xs-12"
									for="percentProvide3">10 - 20% <span class="required">*</span>
								</label>
								<div class="col-md-3 col-sm-6 col-xs-12">
									<s:textfield id="percentProvide3" name="percentProvide3"
										cssClass="form-control col-md-7 col-xs-12" />
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-2 col-sm-3 col-xs-12"
									for="percentProvide4">Dưới 10% <span class="required">*</span>
								</label>
								<div class="col-md-3 col-sm-6 col-xs-12">
									<s:textfield id="percentProvide4" name="percentProvide4"
										cssClass="form-control col-md-7 col-xs-12" />
								</div>
							</div>

							<div class="item form-group">
								<label class="control-label col-md-4 col-sm-3 col-xs-12"
									for="productSell">Tên ít nhất 7 sản phẩm Đồng Xanh đang
									bán theo số lượng thấp dần </label>
								<div class="col-md-8 col-sm-6 col-xs-12">
									<s:textarea id="productSell" name="productSell"
										cssClass="form-control col-md-7 col-xs-12" />
								</div>
							</div>


							<div class="item form-group">
								<label class="col-md-8 col-sm-3 col-xs-12">Danh mục các
									mặt hàng (tên thương mại) đang tiêu thụ mạnh </label>
							</div>

							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="product1Hot">3 Sản phẩm thuốc trừ cỏ </label>
								<div class="col-md-9 col-sm-6 col-xs-12">
									<s:textfield id="product1Hot" name="product1Hot"
										cssClass="form-control col-md-7 col-xs-12" />
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="product2Hot">5 Sản phẩm thuốc trừ sâu </label>
								<div class="col-md-9 col-sm-6 col-xs-12">
									<s:textfield id="product2Hot" name="product2Hot"
										cssClass="form-control col-md-7 col-xs-12" />
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="product3Hot">3 Sản phẩm thuốc trừ rầy </label>
								<div class="col-md-9 col-sm-6 col-xs-12">
									<s:textfield id="product3Hot" name="product3Hot"
										cssClass="form-control col-md-7 col-xs-12" />
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="product4Hot">5 Sản phẩm thuốc trừ bệnh </label>
								<div class="col-md-9 col-sm-6 col-xs-12">
									<s:textfield id="product4Hot" name="product4Hot"
										cssClass="form-control col-md-7 col-xs-12" />
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="product5Hot">3 Sản phẩm kích thích sinh trưởng </label>
								<div class="col-md-9 col-sm-6 col-xs-12">
									<s:textfield id="product5Hot" name="product5Hot"
										cssClass="form-control col-md-7 col-xs-12" />
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="product6Hot">3 Sản phẩm thuốc trừ ốc </label>
								<div class="col-md-9 col-sm-6 col-xs-12">
									<s:textfield id="product6Hot" name="product6Hot"
										cssClass="form-control col-md-7 col-xs-12" />
								</div>
							</div>
							<div class="item form-group">
								<label class="col-md-8 col-sm-3 col-xs-12">Cây trồng
									trong khu vực (chọn cây trồng chính) </label>
							</div>

							<div class="item form-group">
								<label class="control-label col-md-2 col-sm-3 col-xs-12"
									for="farmProduct1">+ Lúa (%) </label>
								<div class="col-md-2 col-sm-6 col-xs-12">
									<s:textfield id="farmProduct1" name="farmProduct1"
										type="number" cssClass="form-control col-md-7 col-xs-12"
										value="%{0}" />

								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12">Mùa
									vụ 1: Từ tháng </label>
								<div class="col-md-2 col-sm-6 col-xs-12">
									<s:textfield id="farmProduct1Session"
										name="farmProduct1Session" type="number"
										cssClass="form-control col-md-7 col-xs-12" value="%{0}" />
								</div>
								<label class="col-md-1 col-sm-3 col-xs-12">đến </label>
								<div class="col-md-2 col-sm-6 col-xs-12">
									<s:textfield id="farmProduct1Session"
										name="farmProduct1Session" type="number"
										cssClass="form-control col-md-7 col-xs-12" value="%{0}" />
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12">Mùa
									vụ 2 : Từ tháng </label>
								<div class="col-md-2 col-sm-6 col-xs-12">
									<s:textfield id="farmProduct1Session"
										name="farmProduct1Session" type="number"
										cssClass="form-control col-md-7 col-xs-12" value="%{0}" />
								</div>
								<label class="col-md-1 col-sm-3 col-xs-12">đến </label>
								<div class="col-md-2 col-sm-6 col-xs-12">
									<s:textfield id="farmProduct1Session"
										name="farmProduct1Session" type="number"
										cssClass="form-control col-md-7 col-xs-12" value="%{0}" />
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12">Mùa
									vụ 3: Từ tháng </label>
								<div class="col-md-2 col-sm-6 col-xs-12">
									<s:textfield id="farmProduct1Session"
										name="farmProduct1Session" type="number"
										cssClass="form-control col-md-7 col-xs-12" value="%{0}" />
								</div>
								<label class="col-md-1 col-sm-3 col-xs-12">đến </label>
								<div class="col-md-2 col-sm-6 col-xs-12">
									<s:textfield id="farmProduct1Session"
										name="farmProduct1Session" type="number"
										cssClass="form-control col-md-7 col-xs-12" value="%{0}" />
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-2 col-sm-3 col-xs-12"
									for="product2Hot">+ Rau màu (%) </label>
								<div class="col-md-2 col-sm-6 col-xs-12">
									<s:textfield id="farmProduct2" name="farmProduct2"
										type="number" cssClass="form-control col-md-7 col-xs-12"
										value="%{0}" />
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12">Mùa
									vụ 1: Từ tháng </label>
								<div class="col-md-2 col-sm-6 col-xs-12">
									<s:textfield id="farmProduct2Session"
										name="farmProduct2Session" type="number"
										cssClass="form-control col-md-7 col-xs-12" value="%{0}" />
								</div>
								<label class="col-md-1 col-sm-3 col-xs-12">đến </label>
								<div class="col-md-2 col-sm-6 col-xs-12">
									<s:textfield id="farmProduct2Session"
										name="farmProduct2Session" type="number"
										cssClass="form-control col-md-7 col-xs-12" value="%{0}" />
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12">Mùa
									vụ 2 : Từ tháng </label>
								<div class="col-md-2 col-sm-6 col-xs-12">
									<s:textfield id="farmProduct2Session"
										name="farmProduct2Session" type="number"
										cssClass="form-control col-md-7 col-xs-12" value="%{0}" />
								</div>
								<label class="col-md-1 col-sm-3 col-xs-12">đến </label>
								<div class="col-md-2 col-sm-6 col-xs-12">
									<s:textfield id="farmProduct2Session"
										name="farmProduct2Session" type="number"
										cssClass="form-control col-md-7 col-xs-12" value="%{0}" />
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12">Mùa
									vụ 3: Từ tháng </label>
								<div class="col-md-2 col-sm-6 col-xs-12">
									<s:textfield id="farmProduct2Session"
										name="farmProduct2Session" type="number"
										cssClass="form-control col-md-7 col-xs-12" value="%{0}" />
								</div>
								<label class="col-md-1 col-sm-3 col-xs-12">đến </label>
								<div class="col-md-2 col-sm-6 col-xs-12">
									<s:textfield id="farmProduct2Session"
										name="farmProduct2Session" type="number"
										cssClass="form-control col-md-7 col-xs-12" value="%{0}" />
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-2 col-sm-3 col-xs-12"
									for="product3Hot">+ Cây ăn trái (%) </label>
								<div class="col-md-2 col-sm-6 col-xs-12">
									<s:textfield id="farmProduct3" name="farmProduct3"
										type="number" cssClass="form-control col-md-7 col-xs-12"
										value="%{0}" />
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12">Mùa
									vụ 1: Từ tháng </label>
								<div class="col-md-2 col-sm-6 col-xs-12">
									<s:textfield id="farmProduct3Session"
										name="farmProduct3Session" type="number"
										cssClass="form-control col-md-7 col-xs-12" value="%{0}" />
								</div>
								<label class="col-md-1 col-sm-3 col-xs-12">đến </label>
								<div class="col-md-2 col-sm-6 col-xs-12">
									<s:textfield id="farmProduct3Session"
										name="farmProduct3Session" type="number"
										cssClass="form-control col-md-7 col-xs-12" value="%{0}" />
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12">Mùa
									vụ 2 : Từ tháng </label>
								<div class="col-md-2 col-sm-6 col-xs-12">
									<s:textfield id="farmProduct3Session"
										name="farmProduct3Session" type="number"
										cssClass="form-control col-md-7 col-xs-12" value="%{0}" />
								</div>
								<label class="col-md-1 col-sm-3 col-xs-12">đến </label>
								<div class="col-md-2 col-sm-6 col-xs-12">
									<s:textfield id="farmProduct3Session"
										name="farmProduct3Session" type="number"
										cssClass="form-control col-md-7 col-xs-12" value="%{0}" />
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12">Mùa
									vụ 3: Từ tháng </label>
								<div class="col-md-2 col-sm-6 col-xs-12">
									<s:textfield id="farmProduct3Session"
										name="farmProduct3Session" type="number"
										cssClass="form-control col-md-7 col-xs-12" value="%{0}" />
								</div>
								<label class="col-md-1 col-sm-3 col-xs-12">đến </label>
								<div class="col-md-2 col-sm-6 col-xs-12">
									<s:textfield id="farmProduct3Session"
										name="farmProduct3Session" type="number"
										cssClass="form-control col-md-7 col-xs-12" value="%{0}" />
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-2 col-sm-3 col-xs-12"
									for="product4Hot">+ Khác (%) </label>
								<div class="col-md-2 col-sm-6 col-xs-12">
									<s:textfield id="farmProduct4" name="farmProduct4"
										type="number" cssClass="form-control col-md-7 col-xs-12"
										value="%{0}" />
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12">Mùa
									vụ 1: Từ tháng </label>
								<div class="col-md-2 col-sm-6 col-xs-12">
									<s:textfield id="farmProduct4Session"
										name="farmProduct4Session" type="number"
										cssClass="form-control col-md-7 col-xs-12" value="%{0}" />
								</div>
								<label class="col-md-1 col-sm-3 col-xs-12">đến </label>
								<div class="col-md-2 col-sm-6 col-xs-12">
									<s:textfield id="farmProduct4Session"
										name="farmProduct4Session" type="number"
										cssClass="form-control col-md-7 col-xs-12" value="%{0}" />
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12">Mùa
									vụ 2 : Từ tháng </label>
								<div class="col-md-2 col-sm-6 col-xs-12">
									<s:textfield id="farmProduct4Session"
										name="farmProduct4Session" type="number"
										cssClass="form-control col-md-7 col-xs-12" value="%{0}" />
								</div>
								<label class="col-md-1 col-sm-3 col-xs-12">đến </label>
								<div class="col-md-2 col-sm-6 col-xs-12">
									<s:textfield id="farmProduct4Session"
										name="farmProduct4Session" type="number"
										cssClass="form-control col-md-7 col-xs-12" value="%{0}" />
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12">Mùa
									vụ 3: Từ tháng </label>
								<div class="col-md-2 col-sm-6 col-xs-12">
									<s:textfield id="farmProduct4Session"
										name="farmProduct4Session" type="number"
										cssClass="form-control col-md-7 col-xs-12" value="%{0}" />
								</div>
								<label class="col-md-1 col-sm-3 col-xs-12">đến </label>
								<div class="col-md-2 col-sm-6 col-xs-12">
									<s:textfield id="farmProduct4Session"
										name="farmProduct4Session" type="number"
										cssClass="form-control col-md-7 col-xs-12" value="%{0}" />
								</div>
							</div>
							<div class="ln_solid"></div>
							<div class="form-group">
								<div class="col-md-6 col-md-offset-3">
									<s:reset cssClass="btn btn-primary" value="Reset" />
									<s:submit id="send" cssClass="btn btn-success" value="Save" />
								</div>
							</div>
						</s:form>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- footer content -->
	<s:include value="footer.jsp" />
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