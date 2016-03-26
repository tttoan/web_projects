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
						<s:set var="rId">
							<s:property value="%{userSes.role.roleId}" />
						</s:set>
						<table id="example" class="jambo_table display nowrap"
							style="width: 100%">
							<thead>
								<tr class="headings">
									<th>STT</th>
									<th>Ngày nhận</th>
									<th>Tên cấp 2</th>
									<th>Tên cấp 1</th>
									<th>Sản phẩm</th>
									<th>Đơn giá</th>
									<th>Số thùng</th>
									<th>Số lượng</th>
									<th>Thành tiền</th>
									<th>NVTT</th>
									<s:if test="%{#rId == 1}">
										<th class="no-link last"><span class="nobr"></span></th>
									</s:if>
								</tr>
							</thead>

							<tbody>

								<s:iterator value="statistics" status="rowStatus">
									<tr class="even pointer">
										<td class=""><s:property value="#rowStatus.count" /></td>
										<td class=""><s:property
												value="%{getText('format.date',{dateReceived})}" /></td>
										<td class=""><s:property
												value="customerByCustomerCodeLevel1.director" /></td>
										<td class=""><s:property
												value="customerByCustomerCodeLevel2.director" /></td>
										<td class=""><s:property value="product.productName" /></td>
										<td class=""><s:property
												value="%{getText('format.money',{product.unitPrice})}" /></td>
										<td class=""><s:property
												value="%{getText('format.number',{totalBox})}" /></td>
										<td class=""><s:property
												value="%{getText('format.number',{quantity})}" /></td>
										<td class=""><s:property
												value="%{getText('format.money',{total})}" /></td>
										<td class=""><s:property value="user.fullName" /></td>
										<s:if test="%{#rId == 1}">
											<td class="last"><s:url action="move_to_add_statistic"
													var="editURL">
													<s:param name="statId" value="%{id}"></s:param>
												</s:url> <s:a href="%{editURL}" class="btn btn-info btn-xs">
													<i class="fa fa-pencil"></i> Sửa </s:a> <s:url
													action="delete_statistic" var="deleteURL">
													<s:param name="statId" value="%{id}"></s:param>
												</s:url> <s:a href="%{deleteURL}" class="btn btn-danger btn-xs">
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