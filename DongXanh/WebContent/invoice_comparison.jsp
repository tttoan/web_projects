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
					<s:form action="compare_statistic" method="post" theme="bootstrap"
						namespace="/" cssClass="form-horizontal form-label-left">
						<table style="width: 100%">
							<tr>
								<td width="150px" valign="middle"><label>Thời gian
										từ</label></td>
								<td width="500px valign="bottom">
									<fieldset>
										<div class="control-group">
											<div class="controls">
												<div
													class="col-md-6 xdisplay_inputx form-group has-feedback">
													<input type="text" class="form-control has-feedback-left"
														id="single_cal1" name="fromDate"
														aria-describedby="inputSuccess2Status"> <span
														class="fa fa-calendar-o form-control-feedback left"
														aria-hidden="true"></span> <span id="inputSuccess2Status"
														class="sr-only">(success)</span>
												</div>
												<div
													class="col-md-6 xdisplay_inputx form-group has-feedback">
													<input type="text" class="form-control has-feedback-left"
														id="single_cal2" name="toDate"
														aria-describedby="inputSuccess2Status2"> <span
														class="fa fa-calendar-o form-control-feedback left"
														aria-hidden="true"></span> <span id="inputSuccess2Status2"
														class="sr-only">(success)</span>
												</div>
											</div>
										</div>
									</fieldset>
								</td>
								<td align="center" valign="middle">
									<button id="send" type="submit" class="btn btn-primary">Xem
										kết quả</button>
								</td>
							</tr>
							<tr>

								<td width="150px" valign="bottom">
									<label>Khách
										hàng Cấp 1 <span class="required">*</span>
									</label>
								</td>
								<td valign="middle">
									<div >
										<s:select id="cusLevel1Id" name="cusLevel1.id" headerKey="-1"
											headerValue="---" cssClass="form-control col-md-7 col-xs-12"
											list="listCustomerLevel1" listKey="id"
											listValue="statisticName +' - '+ customerCode"
											value="%{stat.customerByCustomerCodeLevel1.id}" />
									</div></td>
								<td></td>
							</tr>
						</table>
					</s:form>
				</div>

				<div class="x_panel">
					<div class="x_content">
						<table id="example"
							class="table table-striped responsive-utilities jambo_table display nowrap cell-border"
							style="width: 100%">
							<thead>
<!-- 								<tr class="headings"> -->
<!-- 									<th colspan="2"></th> -->
<!-- 									<th colspan="7">Tên khách hàng cấp 1</th> -->
<!-- 								</tr> -->
								<tr class="headings">
									<th>No</th>
									<th>Tên thuốc và quy cách sản phẩm</th>
									<th>Tồn đầu kỳ<br>(thùng)</th>
									<th>SL cấp 1<br>(thùng)
									</th>
									<th>DS cấp 1<br>(triệu)
									</th>
									<th>SL cấp 2<br>(thùng)
									</th>
									<th>DS cấp 2<br>(triệu)
									</th>
									<th>Chênh lệch<br>(thùng)
									</th>
									<th>Ghi chú</th>
								</tr>
							</thead>

							<tbody>
								<s:iterator value="listStatComp" status="rowStatus">
									<tr class="even pointer">
										<td class=""><s:property value="#rowStatus.count" /></td>
										<td class=""><s:property value="product.productName" /></td>
										<td class=""><s:property value="%{getText('format.number',{balance})}" /></td>
										<td class=""><s:property value="%{getText('format.number',{totalBox})}" /></td>
										<td class=""><s:property value="%{getText('format.money',{total})}" /></td>
										<td class=""><s:property value="%{getText('format.number',{totalBoxLevel2})}" /></td>
										<td class=""><s:property value="%{getText('format.money',{totalLevel2})}" /></td>
										<td class=""><s:property value="%{getText('format.number',{different})}" /></td>
										<td class=""><s:property value="" /></td>
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
	padding: 10px 10px 10px 10px;
	border-style: outset;
}

th {
	text-align: center;
	vertical-align: middle;
}
</style>

<!-- Datatables -->
<%-- <script src="js/datatables/js/jquery.dataTables.js"></script> --%>
<!--<script src="js/datatables/tools/js/dataTables.tableTools.js"></script>-->

<!-- Datatables -->
<script src="js/jquery.dataTables.min.js"></script>
<script>
	$(document).ready(function() {
		$('#example').DataTable({
			"scrollX" : true
		});
	});
</script>

<!-- daterangepicker -->
<script type="text/javascript" src="js/moment.min2.js"></script>
<script type="text/javascript" src="js/datepicker/daterangepicker.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$('#single_cal1').daterangepicker({
			singleDatePicker : true,
			calender_style : "picker_2",
			format : 'DD/MM/YYYY',
			showDropdowns : true
		}, function(start, end, label) {
			console.log(start.toISOString(), end.toISOString(), label);
		});
		$('#single_cal2').daterangepicker({
			singleDatePicker : true,
			calender_style : "picker_2",
			format : 'DD/MM/YYYY',
			showDropdowns : true
		}, function(start, end, label) {
			console.log(start.toISOString(), end.toISOString(), label);
		});

		var d = new Date();
		var currDate = d.getDate();
		if (currDate < 10)
			currDate = '0' + currDate;
		var currMonth = d.getMonth() + 1;
		if (currMonth < 10)
			currMonth = '0' + currMonth;
		var currYear = d.getFullYear();
		var startDate = currDate + "/" + currMonth + "/" + currYear;
		$("#single_cal1").attr("value", startDate);
		$("#single_cal2").attr("value", startDate);

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

<script>
	$(document).ready(function() {
		$('input.tableflat').iCheck({
			checkboxClass : 'icheckbox_flat-green',
			radioClass : 'iradio_flat-green'
		});
	});

	var asInitVals = new Array();
	$(document).ready(function() {
		var oTable = $('#example').dataTable({
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
		$("tfoot input").keyup(function() {
			/* Filter on the column based on the index of this element's parent <th> */
			oTable.fnFilter(this.value, $("tfoot th").index($(this).parent()));
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
		$("tfoot input").blur(function(i) {
			if (this.value == "") {
				this.className = "search_init";
				this.value = asInitVals[$("tfoot input").index(this)];
			}
		});
	});
</script>
</body>

</html>