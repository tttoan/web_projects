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
					<table width="100%">
						<tr>
							<td width="120px" valign="bottom"><label>Hiển thị
									kết quả</label></td>
							<td><s:form name="promotionTypeForm"
									class="form-horizontal form-label-left">
									<div class="form-group">

										<s:select id="cboPromotionStatus"
											class="select2_group form-control" onchange="onTypeChange()"
											list="#{'0':'+ Tất cả', '1':'+ Theo NVTT', '2':'+ Theo cấp 1', '3':'+ Theo cấp 2'}"
											value='%{type}' required="true" />
									</div>
								</s:form></td>
							<td valign="bottom">
								<input type="radio" name="p_result_status" value="3"> <label>Đạt | </label>
  								<input type="radio" name="p_result_status" value="2"> <label>Không đạt | </label>
  								<input type="radio" name="p_result_status" value="1" checked> <label>Tất cả</label>
							</td>
							<td>
							<td align="right" valign="bottom">
								<p class="url">
									<span class="fs1 text-info" aria-hidden="true" data-icon=""></span>
									<s:url id="fileDownload" namespace="/"
										action="promotionResultDownload"></s:url>
									Download file - <b> <s:a href="%{fileDownload}">
											<i class="fa fa-paperclip"></i>
											<s:property value="filenameDownload" />
										</s:a>
									</b>
								</p>
							</td>
						</tr>
					</table>
				</div>

				<div class="x_panel">
					<div class="x_content">
						<table id="example"
							class="table table-striped responsive-utilities jambo_table">
							<thead>
								<!-- <tr>
									<th rowspan="2">Name</th>
									<th colspan="2">HR Information</th>
									<th colspan="7">Contact</th>
								</tr> -->
								<tr class="headings">
									<th>No</th>
									<th>Mã khách hàng</th>
									<th>Tên khách hàng</th>
									<th>NVTT</th>
									<th>Số mặt hàng</th>
									<th>Số thùng</th>
									<th>Số lượng</th>
									<th>Kết quả</th>
									<th>Báo cáo</th>
								</tr>
							</thead>

							<tbody>
								<s:iterator value="PromotionCuss">
									<tr class="even pointer">
										<td class=""><s:property value="row_index" /></td>
										<td class=""><s:property value="customerCode" /></td>
										<td class=""><s:property value="customerName" /></td>
										<td class=""><s:property value="sellMan" /></td>
										<td class=""><s:property value="totalProduct" /></td>
										<td class=""><s:property value="totalBox" /></td>
										<td class=""><s:property value="quality" /></td>
										<td class=""><s:property value="resultString" /></td>
										<td class=""><s:property value="resultPromotion" /></td>
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

<style>
.view_pro {
	margin: 0px;
	text-align: left;
	padding: 0px 10px 10px 10px;
	border-style: outset;
}
</style>

<!-- Datatables -->
<script src="js/datatables/js/jquery.dataTables.js"></script>
<!--<script src="js/datatables/tools/js/dataTables.tableTools.js"></script>-->
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