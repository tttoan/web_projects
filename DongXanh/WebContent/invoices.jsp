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
		<div class="row" >
			<div class="col-md-12 col-sm-12 col-xs-12">
				<div class="x_panel">
					<div class="x_content">
						<table  id="example" class="table table-striped responsive-utilities jambo_table">
							<thead>
								<tr class="headings">
									<th>Tháng</th>
									<th>Ngày nhận</th>
									<th>Mã Cấp 2</th>
									<th>Tên cấp 2</th>
									<th>Mã Cấp 1</th>
									<th>Tên Cấp 1</th>
									<th>Mã Hàng</th>
									<th>Mặt Hàng</th>
									<th>Tên Hàng</th>
									<th>Số Thùng</th>
									<th>Số Lượng</th>
									<th>Giá có điểm+Ko điểm</th>
									<th>Thành Tiền</th>
									<th>NVTT</th>
									<th class=" no-link last"><span class="nobr"></span></th>
								</tr>
							</thead>

							<tbody>

								<s:iterator value="statistics">
									<tr class="even pointer">
										<td class=" "><s:property value="" /></td>
										<td class=" "><s:property value="%{getText('format.date',{dateReceived})}" /></td>
										<td class=" "><s:property value="customerCodeLevel2" /></td>
										<td class=" "><s:property value="customerNameLevel2" /></td>
										<td class=" "><s:property value="customerCodeLevel1" /></td>
										<td class=" "><s:property value="customerNameLevel1" /></td>
										<td class=" "><s:property value="productCode" /></td>
										<td class=" "><s:property value="categoryName" /></td>
										<td class=" "><s:property value="productName" /></td>
										<td class=" "><s:property value="totalBox" /></td>
										<td class=" "><s:property value="quantiy" /></td>
										<td class=" "><s:property value="unitPrice" /></td>
										<td class=" "><s:property value="total" /></td>
										<td class=" "><s:property value="" /></td>
										<td class=" last"> 
											<a href="#">View</a> 
											<a href="#" class="btn btn-info btn-xs"><i class="fa fa-pencil"></i>Edit </a> 
											<s:url action="delete_invoice" var="deleteURL">
												<s:param name="id" value="%{id}" ></s:param>
											</s:url>
											<s:a href="%{deleteURL}" class="btn btn-danger btn-xs"><i class="fa fa-trash-o"></i>Delete </s:a>
										</td>
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


<!-- Datatables -->
<script src="js/datatables/js/jquery.dataTables.js"></script>
<script src="js/datatables/tools/js/dataTables.tableTools.js"></script>
<script>
	$(document).ready(function() {
		$('input.tableflat').iCheck({
			checkboxClass : 'icheckbox_flat-green',
			radioClass : 'iradio_flat-green'
		});
	});

	var asInitVals = new Array();
	$(document)
			.ready(
					function() {
						var oTable = $('#example')
								.dataTable(
										{
											"oLanguage" : {
												"sSearch" : "Search all columns:"
											},
											"aoColumnDefs" : [ {
												'bSortable' : false,
												'aTargets' : [ 0 ]
											} //disables sorting for column one
											],
											'iDisplayLength' : 12,
											"sPaginationType" : "full_numbers",
											"dom" : 'T<"clear">lfrtip',
											"tableTools" : {
												"sSwfPath" : "<?php echo base_url('assets2/js/Datatables/tools/swf/copy_csv_xls_pdf.swf'); ?>"
											}
										});
						$("tfoot input").keyup(
								function() {
									/* Filter on the column based on the index of this element's parent <th> */
									oTable.fnFilter(this.value, $("tfoot th")
											.index($(this).parent()));
								});
						$("tfoot input").each(function(i) {
							asInitVals[i] = this.value;
						});
						$("tfoot input").focus(function() {
							if (this.className == "search_init") {
								this.className = "";
								this.value = "";
							}
						});
						$("tfoot input")
								.blur(
										function(i) {
											if (this.value == "") {
												this.className = "search_init";
												this.value = asInitVals[$(
														"tfoot input").index(
														this)];
											}
										});
					});
</script>
</body>

</html>