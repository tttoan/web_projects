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
				<s:set var="rId">
					<s:property value="%{userSes.role.roleId}" />
				</s:set>
				<table id="example" class="jambo_table display nowrap cell-border"
					style="width: 100%">
					<thead>
						<tr class="headings">
							<th>STT</th>
							<th>Họ và tên</th>
							<th>Tên tài khoản</th>
							<th>Email</th>
							<th>Ngày sinh</th>
							<th>Điện thoại</th>
							<th>Nhóm quyền</th>
							<s:if test="%{#rId == 1}">
								<th class=" no-link last"><span class="nobr"></span></th>
							</s:if>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="listEmployee" status="rowStatus">
							<tr class="even pointer">
								<td class=""><s:property value="#rowStatus.count" /></td>

								<td class=""><s:property value="fullName" /></td>
								<td class=""><s:property value="userName" /></td>
								<td class=""><s:property value="email" /></td>
								<td class=""><s:property
										value="%{getText('format.date',{birthDate})}" /></td>
								<td class=""><s:property value="mobilePhone" /></td>

								<td class=""><a class="btn btn-success btn-xs" style="width: 80px"><i
										class="fa"></i> <s:property value="%{role.roleName}" /> </a></td>
								<s:if test="%{#rId == 1}">
									<td class="last">
									<s:url action="move_to_add_employee"
											var="editURL">
											<s:param name="userId" value="%{id}"></s:param>
										</s:url> <s:a href="%{editURL}" class="btn btn-info btn-xs"
											disabled="false">
											<i class="fa fa-pencil"></i> Sửa </s:a> <s:url
											action="delete_employee" var="deleteURL">
											<s:param name="userId" value="%{id}"></s:param>
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
	<!-- footer content -->
	<s:include value="footer.jsp"></s:include>
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
<script src="js/jquery-1.12.3.min.js"></script>
<script src="js/jquery.dataTables.min.js"></script>
<script src="js/dataTables.fixedHeader.min.js"></script>


<script>
	$(document).ready(function() {
		$('#example').DataTable({
			 scrollX: true,
		        fixedHeader: true,
		        "lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
		});
		var table = $('#example').DataTable();

		$('#example tbody').on('click', 'tr', function() {
			if ($(this).hasClass('selected')) {
				$(this).removeClass('selected');
			} else {
				table.$('tr.selected').removeClass('selected');
				$(this).addClass('selected');
			}
		});

		$('#button').click(function() {
			table.row('.selected').remove().draw(false);
		});
	});
</script>

</body>

</html>