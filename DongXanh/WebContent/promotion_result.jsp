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

				<div class="view_pro">
					<table style="width: 100%">
						<tr>
							<td width="220px" valign="bottom"><label>Hiển thị danh sách khuyến mãi</label></td>
							<td><s:form name="promotionTypeForm"
									class="form-horizontal form-label-left">
									<div class="form-group">
										<s:select id="cboPromotionStatus"
											class="select2_group form-control" onchange="onTypeChange()"
											list="#{'0':'+ Tất cả', '1':'+ Đang diễn ra', '2':'+ Sắp diễn ra', '3':'+ Đã kết thúc trong vòng 7 ngày', '4':'+ Đã kết thúc quá 7 ngày'}"
											value='%{type}' required="true" />
									</div>
								</s:form></td>
						</tr>
					</table>
				</div>

				<div class="x_panel">
					<div class="x_content">
						<table id="example"
							class="table table-striped responsive-utilities jambo_table">
							<thead>
								<tr class="headings">
									<th>No</th>
									<th>Nhóm khách hàng</th>
									<th>Tên khuyến mãi</th>
									<th>Ngày bắt đầu</th>
									<th>Ngày kết thúc</th>
									<th>Đặc tả</th>
									<th>Kết quả</th>
								</tr>
							</thead>

							<tbody>
								<s:iterator value="promotions">
									<tr class="even pointer">
										<td class=""><s:property value="row_index" /></td>
										<td class=""><s:property value="groupCustomer.groupName" /></td>
										<td class=""><s:property value="promotionName" /></td>
										<td class=""><s:date name="startDate" format="dd-MM-yyyy" /></td>
										<td class=""><s:date name="endDate" format="dd-MM-yyyy" /></td>
										<td class=""><s:property value="remarks" /></td>
										<td class="">
											<s:url id="resultURL" action="promotionCusResultAction">
												<s:param name="id" value="%{id}"></s:param>
											</s:url>
											<s:a href="%{resultURL}" class="btn btn-info btn-xs"> Xem... </s:a>
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
<script src="js/datatables/js/jquery.dataTables.js"></script>
<!--<script src="js/datatables/tools/js/dataTables.tableTools.js"></script>-->

<style>
.view_pro {
	margin: 0px;
	text-align: left;
	padding: 0px 0px 10px 10px;
	border-style: outset;
}
</style>

<script type="text/javascript">
	function onTypeChange() {
		var type = "listPromotionResultAction?type="+document.getElementById('cboPromotionStatus').value;
		//alert(type);
		document.promotionTypeForm.action = type;
		document.promotionTypeForm.submit();
	}
	
</script>

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
												//"sSwfPath" : "<?php echo base_url('assets2/js/Datatables/tools/swf/copy_csv_xls_pdf.swf'); ?>"
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