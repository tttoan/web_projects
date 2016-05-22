<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
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
<%-- 						<s:form action="add_customer" method="post" --%>
<%-- 							enctype="multipart/form-data" --%>
<%-- 							cssClass="form-horizontal form-label-left" theme="bootstrap"> --%>
<%-- 							<s:hidden name="custId" value="%{custId}"></s:hidden> --%>
<%-- 							<s:hidden name="edit" value="%{edit}"></s:hidden> --%>
<%-- 							<s:if test="hasActionErrors()"> --%>
<!-- 								<div class="errors"> -->
<%-- 									<s:actionerror escape="false" /> --%>
<!-- 								</div> -->
<%-- 							</s:if> --%>
<%-- 							<s:elseif test="hasActionMessages()"> --%>
<!-- 								<div class="message"> -->
<%-- 									<s:actionmessage escape="false" /> --%>
<!-- 								</div> -->
<%-- 							</s:elseif> --%>
<%-- 							<span class="section"></span> --%>
<!-- 							<div class="form-group"> -->
<!-- 								<div class="col-md-6 col-md-offset-4"> -->
<!-- 									<button id="rs" type="submit" class="btn btn-primary">Import -->
<!-- 										Cấp I</button> -->
<!-- 									<button id="send" type="submit" class="btn btn-success">Import -->
<!-- 										Cấp II</button> -->
<!-- 								</div> -->
<!-- 							</div> -->
<%-- 						</s:form> --%>
						<s:set var="rId">
							<s:property value="%{userSes.role.roleId}" />
						</s:set>
						<table id="example" class="jambo_table display nowrap cell-border"
							style="width: 100%">
							<thead>
								<tr class="headings">
									<th>STT</th>
									<th>Ngày lập</th>
									<th>Mã khách hàng</th>
									<th>Nhóm</th>
									<th>Nhân viên TT</th>
									<th>Tên bảng kê</th>
									<th>Tên doanh nghiệp</th>
									<th>Giấy phép ĐKKD số</th>
									<th>Ngày cấp</th>
									<th>Địa chỉ đăng kí KD</th>
									<th>Mã số thuế</th>
									<th>Vốn đăng kí</th>
									<th>Điện thoại bàn</th>
									<th>Fax</th>
									<th>Email</th>
									<th>Địa chỉ mạng xã hội</th>
									<th>Địa điểm kinh doanh</th>
									<th>Người đại diện pháp luật</th>
									<th>Người quyết định chính công việc</th>
									<th>ĐTDĐ Người quyết định</th>
									<th>Ngày sinh</th>
									<th>Nguyên quán</th>
									<th>Người bán hàng trực tiếp</th>
									<th>ĐTDĐ Người bán hàng</th>
									<th>Ước vốn tự có để kinh doanh</th>
									<th>Ngành nghề kinh doanh khác</th>
									<th>Cấp 1 (5)</th>
									<th>Tỉ lệ nhận (5)</th>
									<th>Cấp 1 (4)</th>
									<th>Tỉ lệ nhận (4)</th>
									<th>Cấp 1 (3)</th>
									<th>Tỉ lệ nhận (3)</th>
									<th>Cấp 1 (2)</th>
									<th>Tỉ lệ nhận (2)</th>
									<th>Cấp 1 (1)</th>
									<th>Tỉ lệ nhận (1)</th>
									<th>3 Sản phẩm thuốc trừ cỏ</th>
									<th>5 Sản phẩm thuốc trừ sâu</th>
									<th>3 Sản phẩm thuốc trừ rầy</th>
									<th>5 Sản phẩm thuốc trừ bệnh</th>
									<th>3 Sản phẩm kích thích sinh trưởng</th>
									<th>3 Sản phẩm thuốc trừ ốc</th>
									<th>Lúa (%)</th>
									<th>3 Mùa vụ Lúa</th>
									<th>Rau màu (%)</th>
									<th>3 Mùa vụ Rau màu</th>
									<th>Cây ăn trái (%)</th>
									<th>3 Mùa vụ Cây ăn trái</th>
									<th>Khác (%)</th>
									<th>3 Mùa vụ Khác</th>

									<s:if test="%{#rId == 1}">
										<th class=" no-link last"><span class="nobr"></span></th>
									</s:if>

								</tr>
							</thead>
							<tbody>
								<s:iterator value="customers" status="rowStatus">
									<tr class="even pointer">
										<td class=""><s:property value="#rowStatus.count" /></td>

										<td class=""><s:property
												value="%{getText('format.date',{createTime})}" /></td>
										<td class=""><s:property value="customerCode" /></td>
										<td class=""><a class="btn btn-success btn-xs"><i
												class="fa"></i> <s:property value="groupCustomer.groupName" />
										</a></td>
										<td class=""><s:property value="user.fullName" /></td>
										<td class=""><s:property value="statisticName" /></td>
										<td class=""><s:property value="businessName" /></td>
										<td class=""><s:property value="certificateNumber" /></td>
										<td class=""><s:property
												value="%{getText('format.date',{certificateDate})}" /></td>
										<td class=""><s:property value="certificateAddress" /></td>
										<td class=""><s:property value="taxNumber" /></td>
										<td class=""><s:property value="budgetRegister" /></td>
										<td class=""><s:property value="telefone" /></td>
										<td class=""><s:property value="fax" /></td>
										<td class=""><s:property value="email" /></td>
										<td class=""><s:property value="socialAddress" /></td>
										<td class=""><s:property value="businessAddress" /></td>
										<td class=""><s:property value="adviser" /></td>
										<td class=""><s:property value="director" /></td>
										<td class=""><s:property value="directorMobile" /></td>
										<td class=""><s:property
												value="%{getText('format.date',{directorBirthday})}" /></td>
										<td class=""><s:property value="directorDomicile" /></td>
										<td class=""><s:property value="sellMan" /></td>
										<td class=""><s:property value="sellManMobile" /></td>
										<td class=""><s:property value="budgetOriginal" /></td>
										<td class=""><s:property value="otherBusiness" /></td>
										<td class=""><s:property
												value="customerByCustomer5Level1Id.businessName" /></td>
										<td class=""><s:property
												value="%{getText('format.percent',{customer5Percent})}" /></td>
										<td class=""><s:property
												value="customerByCustomer4Level1Id.businessName" /></td>
										<td class=""><s:property
												value="%{getText('format.percent',{customer4Percent})}" /></td>
										<td class=""><s:property
												value="customerByCustomer3Level1Id.businessName" /></td>
										<td class=""><s:property
												value="%{getText('format.percent',{customer3Percent})}" /></td>
										<td class=""><s:property
												value="customerByCustomer2Level1Id.businessName" /></td>
										<td class=""><s:property
												value="%{getText('format.percent',{customer2Percent})}" /></td>
										<td class=""><s:property
												value="customerByCustomer1Level1Id.businessName" /></td>
										<td class=""><s:property
												value="%{getText('format.percent',{customer1Percent})}" /></td>
										<td class=""><s:property value="product1Hot" /></td>
										<td class=""><s:property value="product2Hot" /></td>
										<td class=""><s:property value="product3Hot" /></td>
										<td class=""><s:property value="product4Hot" /></td>
										<td class=""><s:property value="product5Hot" /></td>
										<td class=""><s:property value="product6Hot" /></td>
										<td class=""><s:property
												value="%{getText('format.percent',{farmProduct1})}" /></td>
										<td class=""><s:property value="farmProduct1Session" /></td>
										<td class=""><s:property
												value="%{getText('format.percent',{farmProduct2})}" /></td>
										<td class=""><s:property value="farmProduct2Session" /></td>
										<td class=""><s:property
												value="%{getText('format.percent',{farmProduct3})}" /></td>
										<td class=""><s:property value="farmProduct3Session" /></td>
										<td class=""><s:property
												value="%{getText('format.percent',{farmProduct4})}" /></td>
										<td class=""><s:property value="farmProduct4Session" /></td>

										<s:if test="%{#rId == 1}">
											<td class="last"><s:url action="move_to_add_customer"
													var="editURL">
													<s:param name="custId" value="%{id}"></s:param>
												</s:url> <s:a href="%{editURL}" cssClass="btn btn-info btn-xs">
													<i class="fa fa-pencil"></i> Sửa </s:a> <s:url
													action="delete_customer" var="deleteURL">
													<s:param name="custId" value="%{id}"></s:param>
												</s:url> <s:a href="%{deleteURL}" cssClass="btn btn-danger btn-xs">
													<i class="fa fa-trash-o"></i> Xóa </s:a></td>
										</s:if>

									</tr>
								</s:iterator>
							</tbody>

						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="import_customer_level1.jsp"%>
	<!-- footer content -->
	<s:include value="footer.jsp" />
	<!-- /footer content -->

</div>
<!-- /page content -->
</div>

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

<!-- Datatables -->
<script src="js/jquery.dataTables.min.js"></script>
<script>
	$(document).ready(function() {
		$('#example').DataTable({
			"scrollX" : true
		});
	});
</script>
</body>

</html>