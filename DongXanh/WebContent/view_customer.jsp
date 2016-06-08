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
				<h3>Thông tin khách hàng</h3>
			</div>
		</div>
		<div class="clearfix"></div>
		<div class="row">
			<div class="col-md-12 col-sm-12 col-xs-12">
				<s:set var="product1Session" value="%{cust.farmProduct1Session}" />
				<jsp:useBean id="product1Session" type="java.lang.String" />
				<s:set var="farmProduct2Session" value="%{cust.farmProduct2Session}" />
				<jsp:useBean id="farmProduct2Session" type="java.lang.String" />
				<s:set var="farmProduct3Session" value="%{cust.farmProduct3Session}" />
				<jsp:useBean id="farmProduct3Session" type="java.lang.String" />
				<s:set var="farmProduct4Session" value="%{cust.farmProduct4Session}" />
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
					if (farmProduct2Session == null)
						farmProduct2Session = "";
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

				<sx:tabbedpanel id="tabContainer">
					<s:form action="add_customer" method="post"
						enctype="multipart/form-data"
						cssClass="form-horizontal form-label-left" theme="bootstrap">
						<sx:div label="Thông Tin Khách Hàng" id="createCustomerInfo1">
							<div class="x_panel">
								<div class="x_content">

									<div class="item form-group">
										<label class="control-label col-md-3 col-sm-3 col-xs-12"
											for="createTime">Ngày lập: </label>
										<div class="col-md-3 xdisplay_inputx has-feedback">
											<label class="control-label"> <s:property value="cust.createTime"/></label>
										</div>
									</div>

									<div class="item form-group">
										<label class="control-label col-md-3 col-sm-3 col-xs-12"
											for="customerCode">Mã khách hàng:
										</label>
										<div class="col-md-2 col-sm-6 col-xs-12">
											<label class="control-label"> <s:property value="cust.customerCode"/></label>
										</div>
									</div>
									<div class="item form-group">
										<label class="control-label col-md-3 col-sm-3 col-xs-12"
											for="cityName">Khu vực: 
										</label>
										<div class="col-md-5 col-sm-6 col-xs-12">
											<label class="control-label"> <s:property value="cust.certificateAddress"/></label>
										</div>
									</div>
									<div class="item form-group">
										<label class="control-label col-md-3 col-sm-3 col-xs-12"
											for="grpCustomer_id">Nhóm: 
										</label>
										<div class="col-md-5 col-sm-6 col-xs-12">
											<label class="control-label"> <s:property value="cust.groupCustomer.groupName"/></label>
										</div>
									</div>
									<div class="item form-group">
										<label class="control-label col-md-3 col-sm-3 col-xs-12"
											for="emp_id">Nhân viên TT:
										</label>
										<div class="col-md-5 col-sm-6 col-xs-12">
											<label class="control-label"> <s:property value="cust.user.userName"/></label>
										</div>
									</div>
									<div class="item form-group">
										<label class="control-label col-md-3 col-sm-3 col-xs-12"
											for="statisticName">Tên bảng kê: <span
											class="required">*</span>
										</label>
										<div class="col-md-5 col-sm-6 col-xs-12">
											<label class="control-label"> <s:property value="cust.statisticName"/></label>
										</div>
									</div>
									<div class="item form-group">
										<label class="control-label col-md-3 col-sm-3 col-xs-12"
											for="cusImageScan">Ảnh scan (*.jpg, *.png, *.gif) </label>
											<div id="dvPreview" class="col-md-5 col-sm-6 col-xs-12 divborder">
												<img src="<s:property value="cust.pathDocScan"/>"  width="300" height="250"  />
											</div>
									</div>
									<br>

									<div class="item form-group">
										<label class="control-label col-md-3 col-sm-3 col-xs-12"
											for="businessName">Tên doanh nghiệp (cửa hàng): <span
											class="required">*</span>
										</label>
										<div class="col-md-5 col-sm-6 col-xs-12">
											<label class="control-label"> <s:property value="cust.businessName"/></label>
										</div>
									</div>
									<div class="item form-group">
										<label class="control-label col-md-3 col-sm-3 col-xs-12"
											for="certificateNumber">Giấy phép ĐKKD số: 
										</label>
										<div class="col-md-5 col-sm-6 col-xs-12">
											<label class="control-label"> <s:property value="cust.certificateNumber"/></label>
										</div>
									</div>

									<div class="item form-group">
										<label class="control-label col-md-3 col-sm-3 col-xs-12"
											for="certificateDate">Ngày cấp: 
										</label>
										<div class="col-md-3 xdisplay_inputx has-feedback">
											<label class="control-label"> <s:property value="cust.certificateDate"/></label>
										</div>
									</div>

									<div class="item form-group">
										<label class="control-label col-md-3 col-sm-3 col-xs-12"
											for="certificateAddress">Địa chỉ đăng kí KD: 
										</label>
										<div class="col-md-5 col-sm-6 col-xs-12">
											<label class="control-label"> <s:property value="cust.certificateAddress"/></label>
										</div>
									</div>
									<div class="item form-group">
										<label class="control-label col-md-3 col-sm-3 col-xs-12"
											for="taxNumber">Mã số thuế: 
										</label>
										<div class="col-md-5 col-sm-6 col-xs-12">
											<label class="control-label"> <s:property value="cust.taxNumber"/></label>
										</div>
									</div>

									<div class="item form-group">
										<label class="control-label col-md-3 col-sm-3 col-xs-12"
											for="budgetRegister">Vốn đăng kí: </label>
										<div class="col-md-5 col-sm-6 col-xs-12">
											<label class="control-label"> <s:property value="cust.budgetRegister"/></label>
										</div>
									</div>
									<div class="item form-group">
										<label class="control-label col-md-3 col-sm-3 col-xs-12"
											for="telefone">Điện thoại bàn: </label>
										<div class="col-md-5 col-sm-6 col-xs-12">
											<label class="control-label"> <s:property value="cust.telefone"/></label>
										</div>
									</div>
									<div class="item form-group">
										<label class="control-label col-md-3 col-sm-3 col-xs-12"
											for="fax">Fax: </label>
										<div class="col-md-5 col-sm-6 col-xs-12">
											<label class="control-label"> <s:property value="cust.fax"/></label>
										</div>
									</div>
									<div class="item form-group">
										<label class="control-label col-md-3 col-sm-3 col-xs-12"
											for="email">Email: </label>
										<div class="col-md-5 col-sm-6 col-xs-12">
											<label class="control-label"> <s:property value="cust.email"/></label>
										</div>
									</div>
									<div class="item form-group">
										<label class="control-label col-md-3 col-sm-3 col-xs-12"
											for="socialAddress">Địa chỉ mạng xã hội (Facebook,
											Twitter, Zalo,…): </label>
										<div class="col-md-5 col-sm-6 col-xs-12">
											<label class="control-label"> <s:property value="cust.socialAddress"/></label>
										</div>
									</div>
									<div class="item form-group">
										<label class="control-label col-md-3 col-sm-3 col-xs-12"
											for="businessAddress">Địa điểm kinh doanh: </label>
										<div class="col-md-5 col-sm-6 col-xs-12">
											<label class="control-label"> <s:property value="cust.businessAddress"/></label>
										</div>
									</div>
									<div class="item form-group">
										<label class="control-label col-md-3 col-sm-3 col-xs-12"
											for="adviser">Người đại diện pháp luật: </label>
										<div class="col-md-5 col-sm-6 col-xs-12">
											<label class="control-label"> <s:property value="cust.lawyer"/></label>
										</div>
									</div>
									<div class="item form-group">
										<label class="control-label col-md-3 col-sm-3 col-xs-12"
											for="director">Người quyết định chính công việc: </label>
										<div class="col-md-5 col-sm-6 col-xs-12">
											<label class="control-label"> <s:property value="cust.director"/></label>
										</div>
									</div>
									<div class="item form-group">
										<label class="control-label col-md-3 col-sm-3 col-xs-12"
											for="directorMobile">ĐTDĐ Người quyết định: </label>
										<div class="col-md-5 col-sm-6 col-xs-12">
											<label class="control-label"> <s:property value="cust.directorMobile"/></label>
										</div>
									</div>
									<div class="item form-group">
										<label class="control-label col-md-3 col-sm-3 col-xs-12"
											for="directorBirthday">Ngày sinh: </label>
										<div class="col-md-3 xdisplay_inputx has-feedback">
											<label class="control-label"> <s:property value="cust.directorBirthday"/></label>
										</div>
									</div>
									<div class="item form-group">
										<label class="control-label col-md-3 col-sm-3 col-xs-12"
											for="directorDomicile">Nguyên quán: </label>
										<div class="col-md-5 col-sm-6 col-xs-12">
											<label class="control-label"> <s:property value="cust.directorDomicile"/></label>
										</div>
									</div>
									<div class="item form-group">
										<label class="control-label col-md-3 col-sm-3 col-xs-12"
											for="sellMan">Người bán hàng trực tiếp: </label>
										<div class="col-md-5 col-sm-6 col-xs-12">
											<label class="control-label"> <s:property value="cust.sellMan"/></label>
										</div>
									</div>
									<div class="item form-group">
										<label class="control-label col-md-3 col-sm-3 col-xs-12"
											for="sellManMobile">ĐTDĐ Người bán hàng: </label>
										<div class="col-md-5 col-sm-6 col-xs-12">
											<label class="control-label"> <s:property value="cust.sellManMobile"/></label>
										</div>
									</div>

									<div class="item form-group">
										<label class="control-label col-md-3 col-sm-3 col-xs-12"
											for="budgetOriginal">Ước vốn tự có để kinh doanh
											(Triệu): </label>
										<div class="col-md-5 col-sm-6 col-xs-12">
											<label class="control-label"> <s:property value="cust.budgetRegister"/></label>
										</div>
									</div>
								</div>
							</div>
						</sx:div>
						<sx:div label="Hiện Trạng Kinh Doanh Thuốc"
							id="createCustomerInfo2">
							<div class="x_panel">
								<div class="x_content">
									<span class="section"></span>
									<div class="item form-group">
										<label class="control-label col-md-3 col-sm-3 col-xs-12"
											for="otherBusiness">Ngành nghề kinh doanh khác: </label>
										<div class="col-md-4 col-sm-6 col-xs-12">
											<label class="control-label"> <s:property value="cust.otherBusiness"/></label>
										</div>
									</div>
									<div class="item form-group">
										<label class="col-md-5 col-sm-3 col-xs-12"><u><i>CÁC CẤP 1
											ĐANG NHẬN HÀNG CHÍNH</i></u> </label>
									</div>
									<div class="item form-group">
										<label class="control-label col-md-3 col-sm-3 col-xs-12"
											for="cus1Level1_id">Tên: <span class="required">(1)</span>
										</label>
										<div class="col-md-5 col-sm-6 col-xs-12">
											<label class="control-label"> <s:property value="cust.customerByCustomer1Level1Id.businessName"/></label>
										</div>
									</div>
									<div class="item form-group">
										<label class="control-label col-md-3 col-sm-3 col-xs-12"
											for="cus1Phone">Ðiện thoại: <span class="required">(1)</span>
										</label>
										<div class="col-md-3 col-sm-6 col-xs-12">
											<label class="control-label"> <s:property value="cust.customerByCustomer1Level1Id.telefone"/></label>
										</div>
									</div>
									<div class="item form-group">
										<label class="control-label col-md-3 col-sm-3 col-xs-12"
											for="customer1Percent">Tỉ lệ nhận (%): <span
											class="required">(1)</span>
										</label>
										<div class="col-md-3 col-sm-6 col-xs-12">
											<label class="control-label"> <s:property value="cust.customer1Percent"/></label>
										</div>
									</div>

									<div class="item form-group">
										<label class="control-label col-md-3 col-sm-3 col-xs-12"
											for="cus2Level1_id">Tên: <span class="required">(2)</span>
										</label>
										<div class="col-md-5 col-sm-6 col-xs-12">
											<label class="control-label"> <s:property value="cust.customerByCustomer2Level1Id.businessName"/></label>
										</div>
									</div>
									<div class="item form-group">
										<label class="control-label col-md-3 col-sm-3 col-xs-12"
											for="cus2Phone">Ðiện thoại: <span class="required">(2)</span>
										</label>
										<div class="col-md-3 col-sm-6 col-xs-12">
											<label class="control-label"> <s:property value="cust.customerByCustomer2Level1Id.telefone"/></label>
										</div>
									</div>
									<div class="item form-group">
										<label class="control-label col-md-3 col-sm-3 col-xs-12"
											for="customer2Percent">Tỉ lệ nhận (%): <span
											class="required">(2)</span>
										</label>
										<div class="col-md-3 col-sm-6 col-xs-12">
											<label class="control-label"> <s:property value="cust.customer2Percent"/></label>
										</div>
									</div>

									<div class="item form-group">
										<label class="control-label col-md-3 col-sm-3 col-xs-12"
											for="cus3Level1_id">Tên: <span class="required">(3)</span>
										</label>
										<div class="col-md-5 col-sm-6 col-xs-12">
											<label class="control-label"> <s:property value="cust.customerByCustomer3Level1Id.businessName"/></label>
										</div>
									</div>
									<div class="item form-group">
										<label class="control-label col-md-3 col-sm-3 col-xs-12"
											for="cus3Phone">Ðiện thoại: <span class="required">(3)</span>
										</label>
										<div class="col-md-3 col-sm-6 col-xs-12">
											<label class="control-label"> <s:property value="cust.customerByCustomer3Level1Id.telefone"/></label>
										</div>
									</div>
									<div class="item form-group">
										<label class="control-label col-md-3 col-sm-3 col-xs-12"
											for="customer3Percent">Tỉ lệ nhận (%): <span
											class="required">(3)</span>
										</label>
										<div class="col-md-3 col-sm-6 col-xs-12">
											<label class="control-label"> <s:property value="cust.customer3Percent"/></label>
										</div>
									</div>

									<div class="item form-group">
										<label class="control-label col-md-3 col-sm-3 col-xs-12"
											for="cus4Level1_id">Tên: <span class="required">(4)</span>
										</label>
										<div class="col-md-5 col-sm-6 col-xs-12">
											<label class="control-label"> <s:property value="cust.customerByCustomer4Level1Id.businessName"/></label>
										</div>
									</div>
									<div class="item form-group">
										<label class="control-label col-md-3 col-sm-3 col-xs-12"
											for="cus4Phone">Ðiện thoại: <span class="required">(4)</span>
										</label>
										<div class="col-md-3 col-sm-6 col-xs-12">
											<label class="control-label"> <s:property value="cust.customerByCustomer4Level1Id.telefone"/></label>
										</div>
									</div>
									<div class="item form-group">
										<label class="control-label col-md-3 col-sm-3 col-xs-12"
											for="customer4Percent">Tỉ lệ nhận (%): <span
											class="required">(4)</span>
										</label>
										<div class="col-md-3 col-sm-6 col-xs-12">
											<label class="control-label"> <s:property value="cust.customer4Percent"/></label>
										</div>
									</div>

									<div class="item form-group">
										<label class="control-label col-md-3 col-sm-3 col-xs-12"
											for="cus5Level1_id">Tên: <span class="required">(5)</span>
										</label>
										<div class="col-md-5 col-sm-6 col-xs-12">
											<label class="control-label"> <s:property value="cust.customerByCustomer5Level1Id.businessName"/></label>
										</div>
									</div>
									<div class="item form-group">
										<label class="control-label col-md-3 col-sm-3 col-xs-12"
											for="cus5Phone">Ðiện thoại: <span class="required">(5)</span>
										</label>
										<div class="col-md-3 col-sm-6 col-xs-12">
											<label class="control-label"> <s:property value="cust.customerByCustomer5Level1Id.telefone"/></label>
										</div>
									</div>
									<div class="item form-group">
										<label class="control-label col-md-3 col-sm-3 col-xs-12"
											for="customer5Percent">Tỉ lệ nhận (%): <span
											class="required">(5)</span>
										</label>
										<div class="col-md-3 col-sm-6 col-xs-12">
											<label class="control-label"> <s:property value="cust.customer5Percent"/></label>
										</div>
									</div>

									<div class="item form-group">
										<label class="col-md-8 col-sm-3 col-xs-12"><u><i>DOANH SỐ
											BÁN THUỐC BVTV 2 NIÊN VỤ VỪA QUA </i></u></label>
									</div>

									<div class="item form-group">
										<label class="control-label col-md-3 col-sm-3 col-xs-12"
											for="revenue1"> <%=(yearNow - 2)%> - <%=(yearNow - 1)%>
											(Triệu đồng):
										</label>
										<div class="col-md-5 col-sm-6 col-xs-12">
											<label class="control-label"> <s:property value="cust.revenue1"/></label>
										</div>
									</div>
									<div class="item form-group">
										<label class="control-label col-md-3 col-sm-3 col-xs-12"
											for="revenue2"> <%=(yearNow - 1)%> - <%=(yearNow)%>
											(Triệu đồng):
										</label>
										<div class="col-md-5 col-sm-6 col-xs-12">
											<label class="control-label"> <s:property value="cust.revenue2"/></label>
										</div>
									</div>


									<div class="item form-group">
										<label class="col-md-8 col-sm-3 col-xs-12"><u><i>TỈ LỆ DOANH
											SỐ PHÂN PHỐI CỦA CÁC CTY KINH DOANH THUỐC BVTV CUNG ỨNG </i></u></label>
									</div>

									<div class="item form-group">
										<label class="control-label col-md-3 col-sm-3 col-xs-12"
											for="percentProvide1">Trên 30% </label>
										<div class="col-md-5 col-sm-6 col-xs-12">
											<label class="control-label"> <s:property value="cust.percentProvide1"/></label>
										</div>
									</div>
									<div class="item form-group">
										<label class="control-label col-md-3 col-sm-3 col-xs-12"
											for="percentProvide2">20 - 30% </label>
										<div class="col-md-5 col-sm-6 col-xs-12">
											<label class="control-label"> <s:property value="cust.percentProvide2"/></label>
										</div>
									</div>
									<div class="item form-group">
										<label class="control-label col-md-3 col-sm-3 col-xs-12"
											for="percentProvide3">10 - 20% </label>
										<div class="col-md-5 col-sm-6 col-xs-12">
											<label class="control-label"> <s:property value="cust.percentProvide3"/></label>
										</div>
									</div>
									<div class="item form-group">
										<label class="control-label col-md-3 col-sm-3 col-xs-12"
											for="percentProvide4">Dưới 10% </label>
										<div class="col-md-5 col-sm-6 col-xs-12">
											<label class="control-label"> <s:property value="cust.percentProvide4"/></label>
										</div>
									</div>

									<div class="item form-group">
										<label class="control-label col-md-3 col-sm-3 col-xs-12"
											for="productSell">Tên ít nhất 7 sản phẩm Đồng Xanh
											đang bán theo số lượng thấp dần: </label>
										<div class="col-md-5 col-sm-6 col-xs-12">
											<label class="control-label"> <s:property value="cust.productSell"/></label>
										</div>
									</div>

									<br>
									<div class="item form-group">
										<label class="col-md-8 col-sm-3 col-xs-12"><u><i>DANH MỤC
											CÁC MẶT HÀNG (TÊN THƯƠNG MẠI) ĐANG TIÊU THỤ MẠNH </i></u></label>
									</div>

									<div class="item form-group">
										<label class="control-label col-md-3 col-sm-3 col-xs-12"
											for="product1Hot">3 Sản phẩm thuốc trừ cỏ: </label>
										<div class="col-md-6 col-sm-6 col-xs-12">
											<label class="control-label"> <s:property value="cust.product1Hot"/></label>
										</div>
									</div>
									<div class="item form-group">
										<label class="control-label col-md-3 col-sm-3 col-xs-12"
											for="product2Hot">5 Sản phẩm thuốc trừ sâu: </label>
										<div class="col-md-6 col-sm-6 col-xs-12">
											<label class="control-label"> <s:property value="cust.product2Hot"/></label>
										</div>
									</div>
									<div class="item form-group">
										<label class="control-label col-md-3 col-sm-3 col-xs-12"
											for="product3Hot">3 Sản phẩm thuốc trừ rầy: </label>
										<div class="col-md-6 col-sm-6 col-xs-12">
											<label class="control-label"> <s:property value="cust.product3Hot"/></label>
										</div>
									</div>
									<div class="item form-group">
										<label class="control-label col-md-3 col-sm-3 col-xs-12"
											for="product4Hot">5 Sản phẩm thuốc trừ bệnh: </label>
										<div class="col-md-6 col-sm-6 col-xs-12">
											<label class="control-label"> <s:property value="cust.product4Hot"/></label>
										</div>
									</div>
									<div class="item form-group">
										<label class="control-label col-md-3 col-sm-3 col-xs-12"
											for="product5Hot">3 Sản phẩm kích thích sinh trưởng: </label>
										<div class="col-md-6 col-sm-6 col-xs-12">
											<label class="control-label"> <s:property value="cust.product5Hot"/></label>
										</div>
									</div>
									<div class="item form-group">
										<label class="control-label col-md-3 col-sm-3 col-xs-12"
											for="product6Hot">3 Sản phẩm thuốc trừ ốc: </label>
										<div class="col-md-6 col-sm-6 col-xs-12">
											<label class="control-label"> <s:property value="cust.product6Hot"/></label>
										</div>
									</div>

								</div>
							</div>
						</sx:div>
						<sx:div label="Kế Hoạch Hoạt Động Của NVTT"
							id="createCustomerInfo3">
							<div class="x_panel">
								<div class="x_content">
									<span class="section"></span>
									<div class="item form-group">
										<label class="col-md-5 col-sm-3 col-xs-12"><u><i>DOANH SỐ DỰ
											KIẾN TRONG 3 NĂM TỚI </i></u></label>
									</div>
									<div class="item form-group">
										<label class="control-label col-md-3 col-sm-3 col-xs-12"
											for="revenueExpect1"> <%=(yearNow)%> - <%=(yearNow + 1)%>
											(Triệu đồng):
										</label>
										<div class="col-md-5 col-sm-6 col-xs-12">
											<label class="control-label"> <s:property value="cust.revenueExpect1"/></label>
										</div>
									</div>
									<div class="item form-group">
										<label class="control-label col-md-3 col-sm-3 col-xs-12"
											for="revenueExpect2"> <%=(yearNow + 1)%> - <%=(yearNow + 2)%>
											(Triệu đồng):
										</label>
										<div class="col-md-5 col-sm-6 col-xs-12">
											<label class="control-label"> <s:property value="cust.revenueExpect2"/></label>
										</div>
									</div>
									<div class="item form-group">
										<label class="control-label col-md-3 col-sm-3 col-xs-12"
											for="revenueExpect3"> <%=(yearNow + 2)%> - <%=(yearNow + 3)%>
											(Triệu đồng):
										</label>
										<div class="col-md-5 col-sm-6 col-xs-12">
											<label class="control-label"> <s:property value="cust.revenueExpect3"/></label>
										</div>
									</div>
								</div>
							</div>
						</sx:div>
						<sx:div label="Số liệu bán hàng 3 năm qua"
							id="createCustomerInfo4">
							<div class="x_panel">
								<div class="x_content">
								
								</div>
							</div>
						</sx:div>
					</s:form>
				</sx:tabbedpanel>
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
		$('#cityName').change(function() {
			var custCode = $("#customerCode").val();
			if (custCode.length > 3)
				custCode = custCode.substr(custCode.length - 3);
			var cityCode = $("#cityName").val();
			$("#customerCode").val(cityCode + "" + custCode);
		});
	});

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
<!-- daterangepicker -->
<script type="text/javascript" src="js/moment.min2.js"></script>
<script type="text/javascript" src="js/datepicker/daterangepicker.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$('#createTime,#certificateDate,#directorBirthday').daterangepicker({
			singleDatePicker : true,
			calender_style : "picker_2",
			format : 'DD/MM/YYYY',
			showDropdowns : true
		}, function(start, end, label) {
			console.log(start.toISOString(), end.toISOString(), label);
		});
	});
</script>

<script type="text/javascript">
	function onTypeChange() {
		var type = document.getElementById('cboPromotionStatus').value;
		//alert(type);
		if (type == '0') {
			document.getElementById("cboFilterValue").disabled = true;
		} else {
			document.getElementById("cboFilterValue").disabled = false;
		}

	}

	function btnFilterValues() {
		var type = document.getElementById('cboPromotionStatus').value;
		var filterValue = document.getElementById('cboFilterValue').value;
		//alert(type + "/" + filterValue); 
		//var resultType = $('radio[name="p_result_status"]:checked').val();
		//var resultType = document.getElementsByName('p_result_status').value;
		//alert(type + "/" + filterValue + "/" + resultType); 

		var resultType = 1;
		var moduleNameRadio = document.getElementsByName("p_result_status");
		for (var i = 0; i < moduleNameRadio.length; i++) {
			if (moduleNameRadio[i].checked) {
				//alert('Radio button selected' + moduleNameRadio[i].value);
				resultType = moduleNameRadio[i].value;
			}
		}

		var actionUrl = "promotionCusFilterAction?type=" + type
				+ "&filterValue=" + filterValue + "&resultType=" + resultType;
	}
</script>

<style>
	.divleft {
	    float: left;
	    width: 300;
	    height: 150;
	    border: 3px solid #73AD21;
	    padding: 5px;
	}
	.divright {
	    float: right;
	    width: 300;
	    height: 150;
	    border: 3px solid #73AD21;
	    padding: 5px;
	}
	.divborder {
	    border: 3px solid #73AD21;
	    padding: 5px;
	}
</style>
</body>
</html>