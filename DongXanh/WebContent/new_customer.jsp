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
				<s:if test="%{edit}">
					<h3>Sửa thông tin khách hàng</h3>
				</s:if>
				<s:else>
					<h3>Thêm khách hàng</h3>
				</s:else>
			</div>
		</div>
		<div class="clearfix"></div>
		<div class="row">
			<div class="col-md-12 col-sm-12 col-xs-12">
				<div class="x_panel">
					<s:set var="product1Session"
						value="%{cust.farmProduct1Session}" />
					<jsp:useBean id="product1Session" type="java.lang.String"/>
					<s:set var="farmProduct2Session"
						value="%{cust.farmProduct2Session}" />
					<jsp:useBean id="farmProduct2Session" type="java.lang.String" />
					<s:set var="farmProduct3Session"
						value="%{cust.farmProduct3Session}" />
					<jsp:useBean id="farmProduct3Session" type="java.lang.String" />
					<s:set var="farmProduct4Session"
						value="%{cust.farmProduct4Session}" />
					<jsp:useBean id="farmProduct4Session" type="java.lang.String" />
					<%
						String session1From1 = "0";
						String session1To1 = "0";
						String session1From2 = "0";
						String session1To2 = "0";
						String session1From3 = "0";
						String session1To3 = "0";
						if (product1Session.split(",").length == 6) {
							session1From1 = product1Session.split(",")[0].trim();
							session1To1 = product1Session.split(",")[1].trim();
							session1From2 = product1Session.split(",")[2].trim();
							session1To2 = product1Session.split(",")[3].trim();
							session1From3 = product1Session.split(",")[4].trim();
							session1To3 = product1Session.split(",")[5].trim();
						}
						//-----------------
						String session2From1 = "0";
						String session2To1 = "0";
						String session2From2 = "0";
						String session2To2 = "0";
						String session2From3 = "0";
						String session2To3 = "0";
						if (farmProduct2Session.split(",").length == 6) {
							session2From1 = farmProduct2Session.split(",")[0].trim();
							session2To1 = farmProduct2Session.split(",")[1].trim();
							session2From2 = farmProduct2Session.split(",")[2].trim();
							session2To2 = farmProduct2Session.split(",")[3].trim();
							session2From3 = farmProduct2Session.split(",")[4].trim();
							session2To3 = farmProduct2Session.split(",")[5].trim();
						}
						//-----------------
						String session3From1 = "0";
						String session3To1 = "0";
						String session3From2 = "0";
						String session3To2 = "0";
						String session3From3 = "0";
						String session3To3 = "0";
						if (farmProduct3Session.split(",").length == 6) {
							session3From1 = farmProduct3Session.split(",")[0].trim();
							session3To1 = farmProduct3Session.split(",")[1].trim();
							session3From2 = farmProduct3Session.split(",")[2].trim();
							session3To2 = farmProduct3Session.split(",")[3].trim();
							session3From3 = farmProduct3Session.split(",")[4].trim();
							session3To3 = farmProduct3Session.split(",")[5].trim();
						}
						//-----------------
						String session4From1 = "0";
						String session4To1 = "0";
						String session4From2 = "0";
						String session4To2 = "0";
						String session4From3 = "0";
						String session4To3 = "0";
						if (farmProduct4Session.split(",").length == 6) {
							session4From1 = farmProduct4Session.split(",")[0].trim();
							session4To1 = farmProduct4Session.split(",")[1].trim();
							session4From2 = farmProduct4Session.split(",")[2].trim();
							session4To2 = farmProduct4Session.split(",")[3].trim();
							session4From3 = farmProduct4Session.split(",")[4].trim();
							session4To3 = farmProduct4Session.split(",")[5].trim();
						}
						Calendar d = Calendar.getInstance();
						int yearNow = d.get(Calendar.YEAR);
					%>
					<div class="x_content">

						<s:form action="add_customer" method="post"
							cssClass="form-horizontal form-label-left" theme="bootstrap">
							<s:hidden name="id" value="%{custId}"></s:hidden>
							<s:hidden name="edit" value="%{edit}"></s:hidden>
							<s:if test="hasActionErrors()">
								<div class="errors">
									<s:actionerror />
								</div>
							</s:if>
							<span class="section"></span>

							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="createTime">Ngày lập </label>
								<div class="col-md-5 col-sm-6 col-xs-12">
									<sd:datetimepicker id="createTime" name="createTime"
										displayFormat="dd-MM-yyyy" value="%{'today'}"
										cssClass="form-control col-md-7 col-xs-12" />
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="customerCode">Mã khách hàng (<%=yearNow%>) <span
									class="required">*</span>
								</label>
								<div class="col-md-2 col-sm-6 col-xs-12">
									<input id="customerCode" type="text" name="customerCode"
										readonly data-validate-length-range="0,20"
										value="${cust.customerCode}"
										class="form-control col-md-7 col-xs-12">
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="grpCustomer_id">Nhóm <span class="required">*</span>
								</label>
								<div class="col-md-5 col-sm-6 col-xs-12">
									<s:select id="grpCustomer_id" name="grpCustomer.id"
										cssClass="form-control col-md-7 col-xs-12" list="listGrpCus"
										listKey="id" listValue="groupName"
										value="%{cust.groupCustomer.id}" />
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="emp_id">Nhân viên TT <span class="required">*</span>
								</label>

								<div class="col-md-5 col-sm-6 col-xs-12">
									<s:select id="emp_id" name="emp.id"
										cssClass="form-control col-md-7 col-xs-12" headerKey="-1"
										headerValue="---" showDownArrow="false" autoComplete="true"
										list="listEmployee" listKey="id"
										listValue="fullName +' - '+ userName" value="%{cust.user.id}" />
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="statisticName">Tên bảng kê <span class="required">*</span>
								</label>
								<div class="col-md-5 col-sm-6 col-xs-12">
									<input id="statisticName" type="text" name="cust.statisticName"
										data-validate-length-range="1,500"
										value="${cust.statisticName}"
										class="form-control col-md-7 col-xs-12">
								</div>
							</div>
							<br>
							<div class="page-title">
								<div class="title_left">
									<h3>Phần I: Thông tin khách hàng</h3>
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="businessName">Tên doanh nghiệp (cửa hàng) <span
									class="required">*</span>
								</label>
								<div class="col-md-5 col-sm-6 col-xs-12">
									<input id="businessName" type="text" name="cust.businessName"
										data-validate-length-range="1,100"
										value="${cust.businessName}"
										class="form-control col-md-7 col-xs-12">
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="certificateNumber">Giấy phép ĐKKD số <span
									class="required">*</span>
								</label>
								<div class="col-md-5 col-sm-6 col-xs-12">
									<input id="certificateNumber" type="text"
										name="cust.certificateNumber"
										data-validate-length-range="1,100"
										value="${cust.certificateNumber}"
										class="form-control col-md-7 col-xs-12">
								</div>
							</div>

							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="certificateDate">Ngày cấp <span class="required">*</span>
								</label>
								<div class="col-md-5 col-sm-6 col-xs-12">
									<sd:datetimepicker id="certificateDate" name="certificateDate"
										displayFormat="dd-MM-yyyy" value="%{'today'}"
										cssClass="form-control col-md-7 col-xs-12" />
								</div>
							</div>

							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="certificateAddress">Địa chỉ đăng kí KD <span
									class="required">*</span>
								</label>
								<div class="col-md-5 col-sm-6 col-xs-12">
									<textarea id="certificateAddress"
										name="cust.certificateAddress"
										class="form-control col-md-7 col-xs-12">${cust.certificateAddress}</textarea>
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="taxNumber">Mã số thuế <span class="required">*</span>
								</label>
								<div class="col-md-5 col-sm-6 col-xs-12">
									<input id="taxNumber" type="text" name="cust.taxNumber"
										data-validate-length-range="1,100" value="${cust.taxNumber}"
										class="form-control col-md-7 col-xs-12">
								</div>
							</div>

							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="budgetRegister">Vốn đăng kí <span class="required">*</span>
								</label>
								<div class="col-md-5 col-sm-6 col-xs-12">
									<input id="budgetRegister" type="text"
										name="cust.budgetRegister" data-validate-length-range="1,100"
										value="${cust.budgetRegister}"
										class="form-control col-md-7 col-xs-12">
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="telefone">Điện thoại bàn <span class="required">*</span>
								</label>
								<div class="col-md-5 col-sm-6 col-xs-12">
									<input id="telefone" type="text" name="cust.telefone"
										data-validate-length-range="1,100" value="${cust.telefone}"
										class="form-control col-md-7 col-xs-12">
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="fax">Fax <span class="required">*</span>
								</label>
								<div class="col-md-5 col-sm-6 col-xs-12">
									<input id="fax" type="text" name="cust.fax"
										data-validate-length-range="1,100" value="${cust.fax}"
										class="form-control col-md-7 col-xs-12">
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="email">Email <span class="required">*</span>
								</label>
								<div class="col-md-5 col-sm-6 col-xs-12">
									<input type="email" id="email" name="cust.email"
										value="${cust.email}" class="form-control col-md-7 col-xs-12">
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="socialAddress">Địa chỉ mạng xã hội (Facebook,
									Twitter, Zalo,…) <span class="required">*</span>
								</label>
								<div class="col-md-5 col-sm-6 col-xs-12">
									<input id="socialAddress" type="text" name="cust.socialAddress"
										data-validate-length-range="1,1000"
										value="${cust.socialAddress}"
										class="form-control col-md-7 col-xs-12">
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="businessAddress">Địa điểm kinh doanh <span
									class="required">*</span>
								</label>
								<div class="col-md-5 col-sm-6 col-xs-12">
									<input id="businessAddress" type="text"
										name="cust.businessAddress"
										data-validate-length-range="1,1000"
										value="${cust.socialAddress}"
										class="form-control col-md-7 col-xs-12">
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="adviser">Người đại diện pháp luật <span
									class="required">*</span>
								</label>
								<div class="col-md-5 col-sm-6 col-xs-12">
									<input id="adviser" type="text" name="cust.adviser"
										data-validate-length-range="1,50"
										value="${cust.socialAddress}"
										class="form-control col-md-7 col-xs-12">
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="director">Người quyết định chính công việc <span
									class="required">*</span>
								</label>
								<div class="col-md-5 col-sm-6 col-xs-12">
									<input id="director" type="text" name="cust.director"
										data-validate-length-range="1,50" value="${cust.director}"
										class="form-control col-md-7 col-xs-12">
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="directorMobile">ĐTDĐ Người quyết định <span
									class="required">*</span>
								</label>
								<div class="col-md-5 col-sm-6 col-xs-12">
									<input id="directorMobile" name="cust.directorMobile"
										type="text" data-validate-length-range="0,11"
										value="${cust.directorMobile}"
										class="form-control col-md-7 col-xs-12">
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="directorBirthday">Ngày sinh: <span
									class="required">*</span>
								</label>
								<div class="col-md-3 col-sm-6 col-xs-12">
									<sd:datetimepicker id="directorBirthday" value="%{'today'}"
										name="cust.directorBirthday" displayFormat="dd-MM-yyyy"
										cssClass="form-control col-md-7 col-xs-12" />
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="directorDomicile">Nguyên quán: <span
									class="required">*</span>
								</label>
								<div class="col-md-5 col-sm-6 col-xs-12">
									<input id="directorDomicile" type="text"
										name="cust.directorDomicile"
										data-validate-length-range="0,100"
										value="${cust.directorDomicile}"
										class="form-control col-md-7 col-xs-12">
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="sellMan">Người bán hàng trực tiếp <span
									class="required">*</span>
								</label>
								<div class="col-md-5 col-sm-6 col-xs-12">
									<input id="sellMan" type="text" name="cust.sellMan"
										data-validate-length-range="1,100" value="${cust.sellMan}"
										class="form-control col-md-7 col-xs-12">
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="sellManMobile">ĐTDĐ Người bán hàng <span
									class="required">*</span>
								</label>
								<div class="col-md-5 col-sm-6 col-xs-12">
									<input id="sellManMobile" type="text" name="cust.sellManMobile"
										data-validate-length-range="1,100"
										value="${cust.sellManMobile}"
										class="form-control col-md-7 col-xs-12">
								</div>
							</div>

							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="budgetOriginal">Ước vốn tự có để kinh doanh
									(Triệu) <span class="required">*</span>
								</label>
								<div class="col-md-5 col-sm-6 col-xs-12">
									<input id="budgetOriginal" type="text"
										name="cust.budgetOriginal" data-validate-length-range="1,100"
										value="${cust.sellManMobile}"
										class="form-control col-md-7 col-xs-12">
								</div>
							</div>
							<br>
							<div class="page-title">
								<div class="title_left">
									<h3>Phần II: Hiện trạng kinh doanh thuốc BVTV</h3>
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="otherBusiness">Ngành nghề kinh doanh khác <span
									class="required">*</span>
								</label>
								<div class="col-md-5 col-sm-6 col-xs-12">
									<input id="otherBusiness" type="text" name="cust.otherBusiness"
										data-validate-length-range="0,100"
										value="${cust.otherBusiness}"
										class="form-control col-md-7 col-xs-12">
								</div>
							</div>
							<div class="item form-group">
								<label class="col-md-3 col-sm-3 col-xs-12">Các cấp I
									đang nhận hàng chính </label>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="cus1Level1_id">Tên <span class="required">(1)*</span>
								</label>
								<div class="col-md-5 col-sm-6 col-xs-12">
									<s:select id="cus1Level1_id" name="cus1Level1.id"
										showDownArrow="false" autoComplete="true"
										value="%{cust.customerByCustomer1Level1Id.id}"
										cssClass="form-control col-md-7 col-xs-12" list="listCustomer"
										listKey="id" headerKey="-1" headerValue="---"
										listValue="director +' - '+ customerCode" />
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="cus1Phone">Ðiện thoại <span class="required">(1)*</span>
								</label>
								<div class="col-md-3 col-sm-6 col-xs-12">
									<input id="cus1Phone" type="text" readonly name="cus1Phone"
										data-validate-length-range="1,100"
										value="${cust.customerByCustomer1Level1Id.telefone}"
										class="form-control col-md-7 col-xs-12">
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="customer1Percent">Tỉ lệ nhận (%) <span
									class="required">(1)*</span>
								</label>
								<div class="col-md-3 col-sm-6 col-xs-12">
									<input type="number" id="customer1Percent"
										name="cust.customer1Percent" value="${cust.customer1Percent}"
										data-validate-minmax="0,100"
										class="form-control col-md-7 col-xs-12">
								</div>
							</div>

							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="cus2Level1_id">Tên <span class="required">(2)*</span>
								</label>
								<div class="col-md-5 col-sm-6 col-xs-12">
									<s:select id="cus2Level1_id" name="cus2Level1.id"
										showDownArrow="false" autoComplete="true"
										value="%{cust.customerByCustomer2Level1Id.id}"
										cssClass="form-control col-md-7 col-xs-12" list="listCustomer"
										listKey="id" headerKey="-1" headerValue="---"
										listValue="director +' - '+ customerCode" />
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="cus2Phone">Ðiện thoại <span class="required">(2)*</span>
								</label>
								<div class="col-md-3 col-sm-6 col-xs-12">
									<input id="cus2Phone" name="cus2Phone" type="text" readonly
										data-validate-length-range="1,100"
										value="${cust.customerByCustomer2Level1Id.telefone}"
										class="form-control col-md-7 col-xs-12">
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="customer2Percent">Tỉ lệ nhận (%) <span
									class="required">(2)*</span>
								</label>
								<div class="col-md-3 col-sm-6 col-xs-12">
									<input type="number" id="customer2Percent"
										name="cust.customer2Percent" value="${cust.customer2Percent}"
										data-validate-minmax="0,100"
										class="form-control col-md-7 col-xs-12">
								</div>
							</div>

							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="cus3Level1_id">Tên <span class="required">(3)*</span>
								</label>
								<div class="col-md-5 col-sm-6 col-xs-12">
									<s:select id="cus3Level1_id" name="cus3Level1.id"
										showDownArrow="false" autoComplete="true"
										value="%{cust.customerByCustomer3Level1Id.id}"
										cssClass="form-control col-md-7 col-xs-12" list="listCustomer"
										listKey="id" headerKey="-1" headerValue="---"
										listValue="director +' - '+ customerCode" />
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="cus3Phone">Ðiện thoại <span class="required">(3)*</span>
								</label>
								<div class="col-md-3 col-sm-6 col-xs-12">
									<input id="cus3Phone" name="cus3Phone" type="text" readonly
										data-validate-length-range="1,100"
										value="${cust.customerByCustomer3Level1Id.telefone}"
										class="form-control col-md-7 col-xs-12">
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="customer3Percent">Tỉ lệ nhận (%) <span
									class="required">(3)*</span>
								</label>
								<div class="col-md-3 col-sm-6 col-xs-12">
									<input id="customer3Percent" name="cust.customer3Percent"
										type="number" value="${cust.customer3Percent}"
										data-validate-minmax="0,100"
										class="form-control col-md-7 col-xs-12">
								</div>
							</div>

							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="cus4Level1_id">Tên <span class="required">(4)*</span>
								</label>
								<div class="col-md-5 col-sm-6 col-xs-12">
									<s:select id="cus4Level1_id" name="cus4Level1.id"
										showDownArrow="false" autoComplete="true"
										value="%{cust.customerByCustomer4Level1Id.id}"
										cssClass="form-control col-md-7 col-xs-12" list="listCustomer"
										listKey="id" headerKey="-1" headerValue="---"
										listValue="director +' - '+ customerCode" />
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="cus4Phone">Ðiện thoại <span class="required">(4)*</span>
								</label>
								<div class="col-md-3 col-sm-6 col-xs-12">
									<input id="cus4Phone" name="cus4Phone" type="text" readonly
										data-validate-length-range="1,100"
										value="${cust.customerByCustomer4Level1Id.telefone}"
										class="form-control col-md-7 col-xs-12">
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="customer4Percent">Tỉ lệ nhận (%) <span
									class="required">(4)*</span>
								</label>
								<div class="col-md-3 col-sm-6 col-xs-12">
									<input id="customer4Percent" name="cust.customer4Percent"
										type="number" value="${cust.customer4Percent}"
										data-validate-minmax="0,100"
										class="form-control col-md-7 col-xs-12">
								</div>
							</div>

							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="cus5Level1_id">Tên <span class="required">(5)*</span>
								</label>
								<div class="col-md-5 col-sm-6 col-xs-12">
									<s:select id="cus5Level1_id" name="cus5Level1.id"
										showDownArrow="false" autoComplete="true"
										value="%{cust.customerByCustomer5Level1Id.id}"
										cssClass="form-control col-md-7 col-xs-12" list="listCustomer"
										listKey="id" headerKey="-1" headerValue="---"
										listValue="director +' - '+ customerCode" />
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="cus5Phone">Ðiện thoại <span class="required">(5)*</span>
								</label>
								<div class="col-md-3 col-sm-6 col-xs-12">
									<input id="cus5Phone" name="cus5Phone" type="text" readonly
										data-validate-length-range="0,100"
										value="${cust.customerByCustomer5Level1Id.telefone}"
										class="form-control col-md-7 col-xs-12">
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="customer5Percent">Tỉ lệ nhận (%) <span
									class="required">(5)*</span>
								</label>
								<div class="col-md-3 col-sm-6 col-xs-12">
									<input id="customer5Percent" name="cust.customer5Percent"
										type="number" value="${cust.customer5Percent}"
										data-validate-minmax="0,100"
										class="form-control col-md-7 col-xs-12">
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
								<div class="col-md-5 col-sm-6 col-xs-12">
									<input id="revenue1" name="revenue1" type=text
										data-validate-minmax="1,100000000" value="${cust.revenue1}"
										class="form-control col-md-7 col-xs-12">
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="revenue2"> <%=(yearNow - 1)%> - <%=(yearNow)%>
									(Triệu đồng): <span class="required">*</span>
								</label>
								<div class="col-md-5 col-sm-6 col-xs-12">
									<input id="revenue2" name="revenue2" type=text
										data-validate-minmax="1,100000000" value="${cust.revenue1}"
										class="form-control col-md-7 col-xs-12">
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
								<div class="col-md-5 col-sm-6 col-xs-12">
									<input id="revenueExpect1" name="revenueExpect1" type=text
										data-validate-minmax="1,100000000"
										value="${cust.revenueExpect1}"
										class="form-control col-md-7 col-xs-12">
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="revenueExpect2"> <%=(yearNow + 1)%> - <%=(yearNow + 2)%>
									(Triệu đồng): <span class="required">*</span>
								</label>
								<div class="col-md-5 col-sm-6 col-xs-12">
									<input id="revenueExpect2" name="revenueExpect2" type=text
										data-validate-minmax="1,100000000"
										value="${cust.revenueExpect2}"
										class="form-control col-md-7 col-xs-12">
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="revenueExpect3"> <%=(yearNow + 2)%> - <%=(yearNow + 3)%>
									(Triệu đồng): <span class="required">*</span>
								</label>
								<div class="col-md-5 col-sm-6 col-xs-12">
									<input id="revenueExpect3" name="revenueExpect3" type=text
										data-validate-minmax="1,100000000"
										value="${cust.revenueExpect3}"
										class="form-control col-md-7 col-xs-12">
								</div>
							</div>
							<div class="item form-group">
								<label class="col-md-8 col-sm-3 col-xs-12">Tỉ lệ doanh
									số phân phối của các Cty kinh doanh thuốc BVTV cung ứng </label>
							</div>

							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="percentProvide1">Trên 30% <span class="required">*</span>
								</label>
								<div class="col-md-5 col-sm-6 col-xs-12">
									<input id="percentProvide1" name="percentProvide1" type=text
										data-validate-minmax="1,100000000"
										value="${cust.percentProvide1}"
										class="form-control col-md-7 col-xs-12">
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="percentProvide2">20 - 30% <span class="required">*</span>
								</label>
								<div class="col-md-5 col-sm-6 col-xs-12">
									<input id="percentProvide2" name="percentProvide2" type=text
										data-validate-minmax="1,100000000"
										value="${cust.percentProvide2}"
										class="form-control col-md-7 col-xs-12">
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="percentProvide3">10 - 20% <span class="required">*</span>
								</label>
								<div class="col-md-5 col-sm-6 col-xs-12">
									<input id="percentProvide3" name="percentProvide3" type=text
										data-validate-minmax="1,100000000"
										value="${cust.percentProvide3}"
										class="form-control col-md-7 col-xs-12">
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="percentProvide4">Dưới 10% <span class="required">*</span>
								</label>
								<div class="col-md-5 col-sm-6 col-xs-12">
									<input id="percentProvide4" name="percentProvide4" type=text
										data-validate-minmax="1,100000000"
										value="${cust.percentProvide4}"
										class="form-control col-md-7 col-xs-12">
								</div>
							</div>

							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="productSell">Tên ít nhất 7 sản phẩm Đồng Xanh đang
									bán theo số lượng thấp dần </label>
								<div class="col-md-5 col-sm-6 col-xs-12">
									<textarea id="productSell" name="productSell"
										class="form-control col-md-7 col-xs-12">${cust.productSell}</textarea>
								</div>
							</div>

							<br>
							<div class="item form-group">
								<label class="col-md-8 col-sm-3 col-xs-12">Danh mục các
									mặt hàng (tên thương mại) đang tiêu thụ mạnh </label>
							</div>

							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="product1Hot">3 Sản phẩm thuốc trừ cỏ </label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<input id="product1Hot" name="product1Hot" type="text"
										data-validate-length-range="0,1000"
										value="${cust.product1Hot}"
										class="form-control col-md-7 col-xs-12">
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="product2Hot">5 Sản phẩm thuốc trừ sâu </label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<input id="product2Hot" name="product2Hot" type="text"
										data-validate-length-range="0,1000"
										value="${cust.product2Hot}"
										class="form-control col-md-7 col-xs-12">
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="product3Hot">3 Sản phẩm thuốc trừ rầy </label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<input id="product3Hot" name="product3Hot" type="text"
										data-validate-length-range="0,1000"
										value="${cust.product3Hot}"
										class="form-control col-md-7 col-xs-12">
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="product4Hot">5 Sản phẩm thuốc trừ bệnh </label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<input id="product4Hot" name="product4Hot" type="text"
										data-validate-length-range="0,1000"
										value="${cust.product4Hot}"
										class="form-control col-md-7 col-xs-12">
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="product5Hot">3 Sản phẩm kích thích sinh trưởng </label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<input id="product5Hot" name="product5Hot" type="text"
										data-validate-length-range="0,1000"
										value="${cust.product5Hot}"
										class="form-control col-md-7 col-xs-12">
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="product6Hot">3 Sản phẩm thuốc trừ ốc </label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<input id="product6Hot" name="product6Hot" type="text"
										data-validate-length-range="0,1000"
										value="${cust.product6Hot}"
										class="form-control col-md-7 col-xs-12">
								</div>
							</div>
							<br>
							<div class="item form-group">
								<label class="col-md-8 col-sm-3 col-xs-12">Cây trồng
									trong khu vực (chọn cây trồng chính) </label>
							</div>

							<div class="item form-group">
								<label class="control-label col-md-2 col-sm-3 col-xs-12"
									for="farmProduct1">+ Lúa (%) </label>
								<div class="col-md-2 col-sm-6 col-xs-12">
									<input id="farmProduct1" name="cust.farmProduct1" type="number"
										value="${cust.farmProduct1}" data-validate-minmax="0,100"
										class="form-control col-md-7 col-xs-12">
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12">Mùa
									vụ 1: Từ tháng </label>
								<div class="col-md-2 col-sm-6 col-xs-12">
									<input id="farmProduct1Session" name="farmProduct1Session"
										type="number" value="<%=session1From1 %>" data-validate-minmax="0,100"
										class="form-control col-md-7 col-xs-12">
								</div>
								<label class="col-md-1 col-sm-3 col-xs-12">đến </label>
								<div class="col-md-2 col-sm-6 col-xs-12">
									<input id="farmProduct1Session" name="farmProduct1Session"
										type="number" value="<%=session1To1 %>" data-validate-minmax="0,100"
										class="form-control col-md-7 col-xs-12">
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12">Mùa
									vụ 2 : Từ tháng </label>
								<div class="col-md-2 col-sm-6 col-xs-12">
									<input id="farmProduct1Session" name="farmProduct1Session"
										type="number" value="<%=session1From2 %>" data-validate-minmax="0,100"
										class="form-control col-md-7 col-xs-12">
								</div>
								<label class="col-md-1 col-sm-3 col-xs-12">đến </label>
								<div class="col-md-2 col-sm-6 col-xs-12">
									<input id="farmProduct1Session" name="farmProduct1Session"
										type="number" value="<%=session1To2 %>" data-validate-minmax="0,100"
										class="form-control col-md-7 col-xs-12">
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12">Mùa
									vụ 3: Từ tháng </label>
								<div class="col-md-2 col-sm-6 col-xs-12">
									<input id="farmProduct1Session" name="farmProduct1Session"
										type="number" value="<%=session1From3 %>" data-validate-minmax="0,100"
										class="form-control col-md-7 col-xs-12">
								</div>
								<label class="col-md-1 col-sm-3 col-xs-12">đến </label>
								<div class="col-md-2 col-sm-6 col-xs-12">
									<input id="farmProduct1Session" name="farmProduct1Session"
										type="number" value="<%=session1To3 %>" data-validate-minmax="0,100"
										class="form-control col-md-7 col-xs-12">
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-2 col-sm-3 col-xs-12"
									for="farmProduct2">+ Rau màu (%) </label>
								<div class="col-md-2 col-sm-6 col-xs-12">
									<input id="farmProduct2" name="farmProduct2" type="number"
										value="${cust.farmProduct2}" data-validate-minmax="0,100"
										class="form-control col-md-7 col-xs-12">
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12">Mùa
									vụ 1: Từ tháng </label>
								<div class="col-md-2 col-sm-6 col-xs-12">
									<input id="farmProduct2Session" name="farmProduct2Session"
										type="number" value="<%=session2From1 %>" data-validate-minmax="0,100"
										class="form-control col-md-7 col-xs-12">
								</div>
								<label class="col-md-1 col-sm-3 col-xs-12">đến </label>
								<div class="col-md-2 col-sm-6 col-xs-12">
									<input id="farmProduct2Session" name="farmProduct2Session"
										type="number" value="<%=session2To1 %>" data-validate-minmax="0,100"
										class="form-control col-md-7 col-xs-12">
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12">Mùa
									vụ 2 : Từ tháng </label>
								<div class="col-md-2 col-sm-6 col-xs-12">
									<input id="farmProduct2Session" name="farmProduct2Session"
										type="number" value="<%=session2From2 %>" data-validate-minmax="0,100"
										class="form-control col-md-7 col-xs-12">
								</div>
								<label class="col-md-1 col-sm-3 col-xs-12">đến </label>
								<div class="col-md-2 col-sm-6 col-xs-12">
									<input id="farmProduct2Session" name="farmProduct2Session"
										type="number" value="<%=session2To2 %>" data-validate-minmax="0,100"
										class="form-control col-md-7 col-xs-12">
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12">Mùa
									vụ 3: Từ tháng </label>
								<div class="col-md-2 col-sm-6 col-xs-12">
									<input id="farmProduct2Session" name="farmProduct2Session"
										type="number" value="<%=session2From3 %>" data-validate-minmax="0,100"
										class="form-control col-md-7 col-xs-12">
								</div>
								<label class="col-md-1 col-sm-3 col-xs-12">đến </label>
								<div class="col-md-2 col-sm-6 col-xs-12">
									<input id="farmProduct2Session" name="farmProduct2Session"
										type="number" value="<%=session2To3 %>" data-validate-minmax="0,100"
										class="form-control col-md-7 col-xs-12">
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-2 col-sm-3 col-xs-12"
									for="farmProduct1">+ Cây ăn trái (%) </label>
								<div class="col-md-2 col-sm-6 col-xs-12">
									<input id="farmProduct1" name="cust.farmProduct3" type="number"
										value="${cust.farmProduct3}" data-validate-minmax="0,100"
										class="form-control col-md-7 col-xs-12">
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12">Mùa
									vụ 1: Từ tháng </label>
								<div class="col-md-2 col-sm-6 col-xs-12">
									<input id="farmProduct3Session" name="farmProduct3Session"
										type="number" value="<%=session3From1 %>" data-validate-minmax="0,100"
										class="form-control col-md-7 col-xs-12">
								</div>
								<label class="col-md-1 col-sm-3 col-xs-12">đến </label>
								<div class="col-md-2 col-sm-6 col-xs-12">
									<input id="farmProduct3Session" name="farmProduct3Session"
										type="number" value="<%=session3To1 %>" data-validate-minmax="0,100"
										class="form-control col-md-7 col-xs-12">
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12">Mùa
									vụ 2 : Từ tháng </label>
								<div class="col-md-2 col-sm-6 col-xs-12">
									<input id="farmProduct3Session" name="farmProduct3Session"
										type="number" value="<%=session3From2 %>" data-validate-minmax="0,100"
										class="form-control col-md-7 col-xs-12">
								</div>
								<label class="col-md-1 col-sm-3 col-xs-12">đến </label>
								<div class="col-md-2 col-sm-6 col-xs-12">
									<input id="farmProduct3Session" name="farmProduct3Session"
										type="number" value="<%=session3To2 %>" data-validate-minmax="0,100"
										class="form-control col-md-7 col-xs-12">
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12">Mùa
									vụ 3: Từ tháng </label>
								<div class="col-md-2 col-sm-6 col-xs-12">
									<input id="farmProduct3Session" name="farmProduct3Session"
										type="number" value="<%=session3From3 %>" data-validate-minmax="0,100"
										class="form-control col-md-7 col-xs-12">
								</div>
								<label class="col-md-1 col-sm-3 col-xs-12">đến </label>
								<div class="col-md-2 col-sm-6 col-xs-12">
									<input id="farmProduct3Session" name="farmProduct3Session"
										type="number" value="<%=session3To3 %>" data-validate-minmax="0,100"
										class="form-control col-md-7 col-xs-12">
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-2 col-sm-3 col-xs-12"
									for="farmProduct4">+ Khác (%) </label>
								<div class="col-md-2 col-sm-6 col-xs-12">
									<input id="farmProduct4" name="cust.farmProduct4" type="number"
										value="${cust.farmProduct4}" data-validate-minmax="0,100"
										class="form-control col-md-7 col-xs-12">
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12">Mùa
									vụ 1: Từ tháng </label>
								<div class="col-md-2 col-sm-6 col-xs-12">
									<input id="farmProduct4Session" name="farmProduct4Session"
										type="number" value="<%=session4From1 %>" data-validate-minmax="0,100"
										class="form-control col-md-7 col-xs-12">
								</div>
								<label class="col-md-1 col-sm-3 col-xs-12">đến </label>
								<div class="col-md-2 col-sm-6 col-xs-12">
									<input id="farmProduct4Session" name="farmProduct4Session"
										type="number" value="<%=session4To1 %>" data-validate-minmax="0,100"
										class="form-control col-md-7 col-xs-12">
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12">Mùa
									vụ 2 : Từ tháng </label>
								<div class="col-md-2 col-sm-6 col-xs-12">
									<input id="farmProduct4Session" name="farmProduct4Session"
										type="number" value="<%=session4From2 %>" data-validate-minmax="0,100"
										class="form-control col-md-7 col-xs-12">
								</div>
								<label class="col-md-1 col-sm-3 col-xs-12">đến </label>
								<div class="col-md-2 col-sm-6 col-xs-12">
									<input id="farmProduct4Session" name="farmProduct4Session"
										type="number" value="<%=session4To2 %>" data-validate-minmax="0,100"
										class="form-control col-md-7 col-xs-12">
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12">Mùa
									vụ 3: Từ tháng </label>
								<div class="col-md-2 col-sm-6 col-xs-12">
									<input id="farmProduct4Session" name="farmProduct4Session"
										type="number" value="<%=session4From3 %>" data-validate-minmax="0,100"
										class="form-control col-md-7 col-xs-12">
								</div>
								<label class="col-md-1 col-sm-3 col-xs-12">đến </label>
								<div class="col-md-2 col-sm-6 col-xs-12">
									<input id="farmProduct4Session" name="farmProduct4Session"
										type="number" value="<%=session4To3 %>" data-validate-minmax="0,100"
										class="form-control col-md-7 col-xs-12">
								</div>
							</div>
							<div class="ln_solid"></div>
							<div class="form-group">
								<div class="col-md-6 col-md-offset-3">
									<button type="reset" class="btn btn-primary">Reset</button>
									<button id="send" type="submit" class="btn btn-success">Save</button>
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
<script>
	$(document).ready(function() {
		$('#cus1Level1_id').change(function() {
			var commonCusId = {
				"cus1Level1Id" : $("#cus1Level1_id").val()
			};
			$.ajax({
				url : "readInfoCustomer",
				data : JSON.stringify(commonCusId),
				dataType : 'json',
				contentType : 'application/json',
				type : 'POST',
				async : true,
				success : function(res) {
					$('#cus1Phone').val(res);
				}
			});
		});
	});

	$(document).ready(function() {
		$('#cus2Level1_id').change(function() {
			var commonCusId = {
				"cus2Level1Id" : $("#cus2Level1_id").val()
			};
			$.ajax({
				url : "readInfoCustomer",
				data : JSON.stringify(commonCusId),
				dataType : 'json',
				contentType : 'application/json',
				type : 'POST',
				async : true,
				success : function(res) {
					$('#cus2Phone').val(res);
				}
			});
		});
	});

	$(document).ready(function() {
		$('#cus3Level1_id').change(function() {
			var commonCusId = {
				"cus3Level1Id" : $("#cus3Level1_id").val()
			};
			$.ajax({
				url : "readInfoCustomer",
				data : JSON.stringify(commonCusId),
				dataType : 'json',
				contentType : 'application/json',
				type : 'POST',
				async : true,
				success : function(res) {
					$('#cus3Phone').val(res);
				}
			});
		});
	});

	$(document).ready(function() {
		$('#cus4Level1_id').change(function() {
			var commonCusId = {
				"cus4Level1Id" : $("#cus4Level1_id").val()
			};
			$.ajax({
				url : "readInfoCustomer",
				data : JSON.stringify(commonCusId),
				dataType : 'json',
				contentType : 'application/json',
				type : 'POST',
				async : true,
				success : function(res) {
					$('#cus4Phone').val(res);
				}
			});
		});
	});

	$(document).ready(function() {
		$('#cus5Level1_id').change(function() {
			var commonCusId = {
				"cus5Level1Id" : $("#cus5Level1_id").val()
			};
			$.ajax({
				url : "readInfoCustomer",
				data : JSON.stringify(commonCusId),
				dataType : 'json',
				contentType : 'application/json',
				type : 'POST',
				async : true,
				success : function(res) {
					$('#cus5Phone').val(res);
				}
			});
		});
	});

	
</script>
</body>

</html>