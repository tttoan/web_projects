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
							<td width="120px" valign="bottom"><label>Hiển thị
									kết quả</label></td>
							<td><s:form name="promotionTypeForm"
									class="form-horizontal form-label-left">
									<div class="form-group">

										<s:select id="cboPromotionStatus"  style="width: 250px"
											class="select2_group form-control" onchange="onTypeChange()"
											list="#{'0':'Tất cả', '1':'Theo NVTT', '2':'Theo nhóm khách hàng'}"
											value='%{type}' required="true" />
									</div>
								</s:form></td>
							<td valign="bottom">
								<!-- <input type="radio" name="p_result_status" value="3"> <label>Đạt | </label>
  								<input type="radio" name="p_result_status" value="2"> <label>Không đạt | </label>
  								<input type="radio" name="p_result_status" value="1" checked> <label>Tất cả</label> -->
  								<s:form
									class="form-horizontal form-label-left">
									<div class="form-group">
  								<s:radio id="p_result_status_id" name="p_result_status" 
  									list="#{'3':' Đạt | ', '2':' Không đạt | ', '1':' Tất cả '}" 
  									value='%{resultType}' />
								</div>
								</s:form>
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
						<tr>
							<td></td>
							<td><s:form
									class="form-horizontal form-label-left">
									<div class="form-group">

										<s:select id="cboFilterValue"  style="width: 250px"
											class="select2_group form-control"
											list="listFilterValues"
											value='%{filterValue}'
											required="true" />
									</div>
								</s:form></td>
							<td valign="bottom">
								<button type="button" class="btn btn-primary" id="btnFilter" onclick="btnFilterValues()">Xem kết quả</button></td>
						</tr>
					</table>
				</div>

				<div class="x_panel">
					<div class="x_content">
						<table id="example"
							class="table table-striped responsive-utilities jambo_table display nowrap cell-border" style="width: 100%">
							<thead>
								<!-- <tr>
									<th rowspan="2">Name</th>
									<th colspan="2">HR Information</th>
									<th colspan="7">Contact</th>
								</tr> -->
								<tr class="headings">
									<th colspan="4"></th>
									
									<s:if test="%{promotion.customerRegist==1}">
										<th colspan="8"><s:property value="promotion.promotionName" /></th>
									</s:if>
									<s:else>
										<th colspan="6"><s:property value="promotion.promotionName" /></th>
									</s:else>
									
									<th colspan=<s:property value="promotion.promotionGifts.size"/> align="center">Quà tặng</th>
									<s:if test="%{promotion.customerRegist==1}">
										<s:iterator value="promotion.promotionProducts">
											<th colspan="2"><s:property value="product.productName" /></th>
										</s:iterator>
									</s:if>
									<s:else>
										<th colspan=<s:property value="promotion.promotionProducts.size"/> align="center">Chi tiết mặt hàng</th>
									</s:else>
									
								</tr>
								<tr class="headings">
									
									<th>No</th>
									<th>Mã khách hàng</th>
									<th>Tên khách hàng</th>
									<th>NVTT</th>
									
									<th>Tổng số <br>mặt hàng</th>
									<th>Tổng số <br>thùng TH</th>
									<s:if test="%{promotion.customerRegist==1}">
										<th>Tổng số <br>thùng ĐK</th>
									</s:if>
									<th>Tổng số <br>lượng TH</th>
									<th>Tổng số <br>điểm TH</th>
									<s:if test="%{promotion.customerRegist==1}">
										<th>Tổng số <br>điểm ĐK</th>
									</s:if>
									<th>Kết quả</th>
									<th>Báo cáo</th>
									
									<s:iterator value="promotion.promotionGifts">
										<th><s:property value="gift.giftName" /></th>
									</s:iterator>
									<s:iterator value="promotion.promotionProducts">
										<s:if test="%{promotion.customerRegist==1}">
											<th >TH</th>
											<th >ĐK</th>
										</s:if>
										<s:else>
											<th><s:property value="product.productName" /></th>
										</s:else>
									</s:iterator>
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
										<s:if test="%{promotion.customerRegist==1}">
											<td class=""><s:property value="totalBoxRegist" /></td>
										</s:if>
										<td class=""><s:property value="quality" /></td>
										<td class=""><s:property value="totalPoint" /></td>
										<s:if test="%{promotion.customerRegist==1}">
											<td class=""><s:property value="totalPointRegist" /></td>
										</s:if>
										<td class=""><s:property value="resultString" /></td>
										<td class=""><s:property value="resultPromotion" /></td>
										<s:iterator value="promotion.promotionGifts">
											<s:if test="%{promotion.customerRegist==1}">
												<td><s:property value="getRegiterGiftTotal(listRegisterGifts, gift.giftName)" /></td>
											</s:if>
											<s:else>
												<td><s:property value="getMyGift(totalBoxRegist, totalPointRegist, maxQuantity, maxPoint, result)" /></td>
											</s:else>
											
										</s:iterator>
										<s:iterator value="promotion.promotionProducts">
											<s:if test="%{promotion.customerRegist==1}">
												<td><s:property value="getProductBoxDoneReport(products, product.productName)" /></td>
												<td><s:property value="getRegiterProductTotal(listRegisterProducts, product.productName)" /></td>
											</s:if>
											<s:else>
												<td><s:property value="getProductBoxDoneReport(products, product.productName)" /></td>
											</s:else>
											
										</s:iterator>
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

<script type="text/javascript">
	function onTypeChange() {
		var type = document.getElementById('cboPromotionStatus').value;
		//alert(type);
		if(type == '0'){
			 document.getElementById("cboFilterValue").disabled = true;
		}else{
			 document.getElementById("cboFilterValue").disabled = false;
		}
		
		 $.ajax({ //Not found in cache, get from server
             url: 'promotionFilterValues?type='+type,
             type: 'POST',
             dataType: 'json',
             async: false,
             success: function (data) {
            	 //alert("hiiiii  " + JSON.stringify(data)); 
            	 var select = $('#cboFilterValue');
                 select.find('option').remove();
                 for (var i = 0; i < data.listFilterValues.length; i++) {
                	 $('<option>').val(data.listFilterValues[i]).text(data.listFilterValues[i]).appendTo(select);
                 }
             }
         });
	}
	
	function btnFilterValues(){
		var type = document.getElementById('cboPromotionStatus').value; 
		var filterValue = document.getElementById('cboFilterValue').value;
		//alert(type + "/" + filterValue); 
		//var resultType = $('radio[name="p_result_status"]:checked').val();
		//var resultType = document.getElementsByName('p_result_status').value;
		//alert(type + "/" + filterValue + "/" + resultType); 
		
		var resultType = 1;
		var moduleNameRadio=document.getElementsByName("p_result_status");
		for(var i=0;i<moduleNameRadio.length;i++){
		       if(moduleNameRadio[i].checked){
		          //alert('Radio button selected' + moduleNameRadio[i].value);
		    	   resultType = moduleNameRadio[i].value;
		      }
		}
		
		var actionUrl = "promotionCusFilterAction?type="+type+"&filterValue="+filterValue+"&resultType="+resultType;
		//alert(type);
		document.promotionTypeForm.action = actionUrl;
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