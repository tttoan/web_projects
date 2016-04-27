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
							<td width="120px" valign="middle"><label>Thời gian từ</label></td>
							<td width="500px valign="bottom">
							<fieldset>
									<div class="control-group">
										<div class="controls">
											<div
												class="col-md-6 xdisplay_inputx form-group has-feedback">
												<input type="text" class="form-control has-feedback-left"
													id="single_cal1" name="single_cal1" 
													aria-describedby="inputSuccess2Status"> <span
													class="fa fa-calendar-o form-control-feedback left"
													aria-hidden="true"></span> <span id="inputSuccess2Status"
													class="sr-only">(success)</span>
											</div>
											<div
												class="col-md-6 xdisplay_inputx form-group has-feedback">
												<input type="text" class="form-control has-feedback-left"
													id="single_cal2" name="single_cal2" 
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
								<button type="button" class="btn btn-primary" id="btnFilter" onclick="btnFilterValues()">Xem kết quả</button>
							</td>
						</tr>
						<tr>
							<td width="120px" valign="bottom"></td>
							<td valign="middle">
							</td>
							<td></td>
						</tr>
					</table>
				</div>

				<div class="x_panel">
					<div id="x_content" class="x_content">
						<table id="example"
							class="table table-striped responsive-utilities jambo_table display nowrap cell-border" style="width: 100%">
							<thead>
								<tr class="headings">
									<th colspan="7"></th>
									<th colspan="3">Chi tiết mặt hàng</th>
								</tr>
								<tr class="headings">
									<th>No</th>
									<th>Mã cấp 2</th>
									<th>Tên cấp 2</th>
									<th>Tên Cấp 1</th>
									<th>NVTT</th>
									<th>Tổng doanh số<br>(triệu)</th>
									<th>Tổng mặt hàng<br>(thùng)</th>
									
									<th>SP 1</th>
									<th>SP 2</th>
									<th>SP 3</th>
								</tr>
							</thead>

							<tbody>
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
    $('#example').DataTable( {
        "scrollX": true
    } );
} );
</script>

 <!-- daterangepicker -->
    <script type="text/javascript" src="js/moment.min2.js"></script>
    <script type="text/javascript" src="js/datepicker/daterangepicker.js"></script>
    
<script type="text/javascript">
        $(document).ready(function () {
            $('#single_cal1').daterangepicker({
                singleDatePicker: true,
                calender_style: "picker_2",
                format: 'DD/MM/YYYY',
                showDropdowns: true
            }, function (start, end, label) {
                console.log(start.toISOString(), end.toISOString(), label);
            });
            $('#single_cal2').daterangepicker({
            	 singleDatePicker: true,
                 calender_style: "picker_2",
                 format: 'DD/MM/YYYY',
                 showDropdowns: true
            }, function (start, end, label) {
                console.log(start.toISOString(), end.toISOString(), label);
            });
            
            var d = new Date();
            var currDate = d.getDate();
            if(currDate < 10)currDate = '0'+currDate;
            var currMonth = d.getMonth()+1;
            if(currMonth < 10) currMonth = '0'+currMonth;
            var currYear = d.getFullYear();
            var startDate = currDate + "/" + currMonth + "/" + currYear;
            $("#single_cal1").attr("value", startDate);
            $("#single_cal2").attr("value", startDate);

        });
</script>
    
<script type="text/javascript">

	function btnFilterValues() {
		var vStart = $('[name="single_cal1"]').val();
		var vEnd = $('[name="single_cal2"]').val();
		$(document).ready(
				function() {
					$.ajax({
						type : "POST",
						url : 'reportRevenuesCustomerL2Action?start=' + vStart + '&end=' + vEnd,
						success : function(responseText) {
							//alert(responseText);
							$('#x_content').html(responseText);
							$('#example').DataTable({
								scrollX : true,
							});
						}
					});
				});
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