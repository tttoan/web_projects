
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- Using Struts2 Tags in JSP --%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/struts-dojo-tags" prefix="sx"%>
<%@ taglib uri="/struts-jquery-tags" prefix="sj"%>
<%@ include file="header.jsp"%>
<%@ include file="user_profile.jsp"%>
<%@ include file="menu.jsp"%>
<!-- page content -->
<div class="right_col" role="main">

	<div class="">
		<div class="page-title">
			<div class="title_left">
				<s:if test="%{edit}">
					<h3>Cập nhật thông tin bảng kê</h3>
				</s:if>
				<s:else>
					<h3>Thêm bảng kê</h3>
				</s:else>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12 col-sm-12 col-xs-12">
				<div class="x_panel">
					<div class="x_content">
						<s:form action="add_statistic" theme="bootstrap" method="post"
							cssClass="form-horizontal form-label-left">
							<s:hidden name="stat.id" value="%{statId}"></s:hidden>
							<s:hidden name="edit" value="%{edit}"></s:hidden>
							<s:if test="hasActionErrors()">
								<div class="errors">
									<s:actionerror escape="false" />
								</div>
							</s:if>
							<s:elseif test="hasActionMessages()">
								<div class="message">
									<s:actionmessage escape="false" />
								</div>
							</s:elseif>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="dateReceived">Ngày nhận <span class="required">*</span>
								</label>
								<div class="col-md-3 xdisplay_inputx has-feedback">
									<input type="text" class="form-control has-feedback-left"
										id="dateReceived" name="varDateReceived" value="${varDateReceived}"
										aria-describedby="inputSuccess2Status"> <span
										class="fa fa-calendar-o form-control-feedback left"
										aria-hidden="true"></span> <span id="inputSuccess2Status"
										class="sr-only">(success)</span>
								</div>
							</div>
							
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="cusLevel1.id">Tên cấp 1 <span class="required">*</span>
								</label>
								<div class="col-md-5 col-sm-6 col-xs-12">
									<s:hidden id="cusLevel1_id" name="cusLevel1.id" value="%{cusLevel1.id}"></s:hidden>
									<input id="cusLevel1" type="text" name="cusLevel1.statisticName" tabindex="1"
										value="${cusLevel1.statisticName}" required="required"
										class="form-control col-md-7 col-xs-12">
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for=cusLevel2.id>Tên cấp 2 <span class="required">*</span>
								</label>
								<div class="col-md-5 col-sm-6 col-xs-12">
									<s:hidden id="cusLevel2_id" name="cusLevel2.id" value="%{cusLevel2.id}"></s:hidden>
									<input id="cusLevel2" type="text" name="cusLevel2.statisticName" tabindex="2"
										value="${cusLevel2.statisticName}" required="required"
										class="form-control col-md-7 col-xs-12">
								</div>
							</div>

							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="pro_id">Sản phẩm <span class="required">*</span>
								</label>
								<div class="col-md-5 col-sm-6 col-xs-12">
									<s:hidden id="pro_id" name="pro.id" value="%{pro.id}"></s:hidden>
									<input id="product" type="text" name="pro.productName" tabindex="3"
										 value="${stat.product.productName}" required="required"
										class="form-control col-md-7 col-xs-12">
								</div>
								
								<label class="control-label col-md-1 col-sm-3 col-xs-12"
									for="unitPriceFm">Đơn giá
								</label>
								<div class="col-md-2 col-sm-6 col-xs-12">
									<s:hidden id="pro_unitPrice" name="pro_unitPrice" value="%{stat.product.unitPrice}"></s:hidden>
									<s:hidden id="pro_quantityPerBox" name="pro_quantityPerBox" value="%{stat.product.quantity}"></s:hidden>
									<input type="text" id="pro_unitPriceFm" name="pro_unitPriceFm" readonly
										data-validate-minmax="1,1000000000"
										value="${varUnitPrice}"
										class="form-control col-md-7 col-xs-12">
								</div>
							</div>
							
							
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="totalBox">Số thùng <span class="required">*</span>
								</label>
								<div class="col-md-2 col-sm-6 col-xs-12">
									<input type="number" id="pro_totalBoxFm" name="pro_totalBoxFm"
										value="${stat.totalBox}" required="required"
										data-validate-minmax="0,100000"
										class="form-control col-md-7 col-xs-12"
										onkeypress="onlySpecialChar(event, '[0-9\\.]+')">
								</div>
								
								<label class="control-label col-md-1 col-sm-3 col-xs-12"
									for="quantity">Số lượng
								</label>
								<div class="col-md-2 col-sm-6 col-xs-12">
									<input type="number" id="pro_quantityFm" name="pro_quantityFm"
										value="${stat.quantity}" required="required"
										data-validate-minmax="0,100000"
										class="form-control col-md-7 col-xs-12"
										onkeypress="onlySpecialChar(event, '[0-9\\.]+')">
								</div>
							</div>
							
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="total">Thành tiền <span class="required">*</span>
								</label>
								<div class="col-md-5 col-sm-6 col-xs-12">
									<s:hidden id="total_price" name="total" value="%{stat.total}"></s:hidden>
									<input type=text id="total_priceFm" name="totalFm"
										required="required" value="${varTotal}" readonly
										data-validate-minmax="0,1000000000"
										class="form-control col-md-7 col-xs-12">

								</div>
							</div>
							
							<div class="form-group">
								<div class="col-md-6 col-md-offset-3">
									<button id="resetStatistic" type="reset" class="btn btn-primary">Làm mới</button>
									<button id="addStatistic" type="button" class="btn btn-success"><s:if test="%{edit}">
											Cập nhật
										</s:if>
										<s:else>
											Thêm mới
										</s:else></button>
								</div>
							</div>
						</s:form>
						
						<div class="x_panel">
							<div id="x_content" class="x_content">
								<table id="example"
									class="table table-striped responsive-utilities jambo_table display nowrap cell-border" style="width: 100%">
									<thead>
										<tr class="headings">
											<th>No</th>
											<th>Ngày Nhận</th>
											<th>Khách Hàng Cấp 1</th>
											<th>Khách Hàng Cấp 2</th>
											<th>Sản Phẩm</th>
											<th>Số Thùng</th>
											<th>Số Lượng</th>
											<th>Thành Tiền</th>
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
		</div>
	</div>

	<!-- footer content -->
	<jsp:include page="footer.jsp"></jsp:include>
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

<!-- Auto lookup  -->
<link rel="stylesheet" type="text/css" href="css/autocomplete/tautocomplete.css" />
<script src="Scripts/autocomplete/tautocomplete.js" type="text/javascript"></script>
<!-- Datatables -->
<script src="js/jquery.dataTables.min.js"></script>

<script>
	
 	//Stop backspace
 	$(document).keydown(function(e){
	    var typeName = e.target.type;//typeName should end up being things like 'text', 'textarea', 'radio', 'undefined' etc.
	    // Prevent Backspace as navigation backbutton
	    if(e.keyCode == 8 
	    		&& typeName != "text" 
	    		&& typeName != "textarea"
	    		&& typeName != "number"
	    		&& typeName != "search"){
	        e.preventDefault();
	    }
	})
	
	//Press enter to next focus
	// register jQuery extension
	jQuery.extend(jQuery.expr[':'], {
	    focusable: function (el, index, selector) {
	        return $(el).is('a, button, :input, [tabindex]');
	    }
	});
	
	$(document).on('keydown', ':focusable', function (e) {
	    if (e.which == 13) {
	        e.preventDefault();
	        // Get all focusable elements on the page
	        var $canfocus = $(':focusable');
	        var index = $canfocus.index(this) + 1;
	        if (index >= $canfocus.length) index = 0;
	        $canfocus.eq(index).focus();
	    }
	});
	
	//Auto lookup
    $(document).ready(function () {
        var cus1 = $("#cusLevel1").tautocomplete({
            width: "500px",
            columns: ['Tên KH', 'Điện thoại', 'Địa chỉ'],
            ajax: {
                url: "lookupCustomerL1StatisticAction",
                data: function () {
                    return { searchCusName: cus1.searchdata() };
                },
                dataType: 'json',
                contentType : 'application/json',
				 type : 'POST',
				 async : false,
                success: function (data) {
                    return data.listCustomerL1;
                }
            },
            onchange: function () {
                $("#cusLevel1_id").val(cus1.id());
            }
        });
        
        var cus2 = $("#cusLevel2").tautocomplete({
            width: "500px",
            columns: ['Tên KH', 'Điện thoại', 'Địa chỉ'],
            ajax: {
                url: "lookupCustomerL2StatisticAction",
                data: function () {
                    return { searchCusName: cus2.searchdata() };
                },
                dataType: 'json',
                contentType : 'application/json',
				 type : 'POST',
				 async : false,
                success: function (data) {
                    return data.listCustomerL2;
                }
            },
            onchange: function () {
                $("#cusLevel2_id").val(cus2.id());
            }
        });
        
        var product = $("#product").tautocomplete({
            width: "500px",
            columns: ['Tên SP', 'Mã SP', 'Đơn giá', 'SL/Thùng'],
            ajax: {
                url: "lookupProductStatisticAction",
                data: function () {
                    return { searchProductName: product.searchdata() };
                },
                dataType: 'json',
                contentType : 'application/json',
				 type : 'POST',
				 async : false,
                success: function (data) {
                    return data.listProduct;
                }
            },
            onchange: function () {
                $("#pro_id").val(product.id());
                //alert(JSON.stringify(product.all().value2));
                $("#pro_unitPriceFm").val(formatCurrency(product.all().value2));
                $("#pro_unitPrice").val((product.all().value2));
                $("#pro_quantityPerBox").val((product.all().value3));
            }
        });
        
        
        //Auto get total price after change total box
        $('#pro_totalBoxFm').keyup(function(e) {
        	//var key = String.fromCharCode(!e.charCode ? e.which : e.charCode);
        	if(e.which != 13){//Enter
        		var quantityPerBox = $("#pro_quantityPerBox").val();
    			var unitPrice = $("#pro_unitPrice").val();
    			
    			var totalBoxFm =  $("#pro_totalBoxFm").val();
    			//var totalQuantityFm = $("#pro_quantityFm").val();
    			var totalQuantityFm = quantityPerBox * totalBoxFm;
    			
    			$("#pro_quantityFm").val(totalQuantityFm);
    			$('#total_price').val((unitPrice * totalQuantityFm));
    			$('#total_priceFm').val(formatCurrency(unitPrice * totalQuantityFm));
        	}
		});
        //Auto get total price after change total quantity
        $('#pro_quantityFm').keyup(function(e) {
        	//alert(e.charCode + "/" + e.which)
        	if(e.which != 13){//Enter
        		var quantityPerBox = $("#pro_quantityPerBox").val();
    			var unitPrice = $("#pro_unitPrice").val();
    			
    			var totalBoxFm =  $("#pro_totalBoxFm").val();
    			var totalQuantityFm = $("#pro_quantityFm").val();
    			
    			$("#pro_totalBoxFm").val(totalQuantityFm/quantityPerBox);
    			
    			$('#total_price').val((unitPrice * totalQuantityFm));
    			$('#total_priceFm').val(formatCurrency(unitPrice * totalQuantityFm));
        	}
		});
        
      //Save data
        $('#pro_quantityFm').keydown(function(e) {
        	//alert(e.charCode + "/" + e.which);
        	if(e.which == 13){//Enter
        		//Check & validate data
        		if(checkInvalidData()){
        			//Save
            		$('#addStatistic').click();
            		//Reset
            		$('#resetStatistic').click();
            		//Set focus
            		$('.lookupCls').eq(0).focus();
        		}
        	}
		});
      
        //Add new statistic or update
        var tbl = $('#example').DataTable( {
            "scrollX": true
        } );
        
        var counter = 1;
        $('#addStatistic').click(function () {
        	if(checkInvalidData()){
        		tbl.row.add( [
      		                counter,
      		                $("#dateReceived").val(),
      		                $("#cusLevel1").val(),
      		                $("#cusLevel2").val(),
      		                $("#product").val(),
      		                $("#pro_totalBoxFm").val(),
      		                $("#pro_quantityFm").val(),
      		                $("#total_priceFm").val()
      		            ] ).draw( false ); 
	        	tbl.order([0, 'desc']).draw();
	        	
	        	//Store in cookie
	        	//storeStatisticHistory(counter);
	     
	            counter++;
        	}
        } );
    });
	
	function storeStatisticHistory(counter){
		//alert('cusLevel1' + counter + '='+ $("#cusLevel1").val().replace(/.+#/i,""));
    	setCookie('total_statistic', counter, 1);
    	//alert('total_statistic='+ getCookie('total_statistic'));
    	setCookie('dateReceived' + counter, $("#dateReceived").val().replace(/.+#/i,""), 1);
    	setCookie('cusLevel1' + counter, $("#cusLevel1").val().replace(/.+#/i,""), 1);
    	setCookie('cusLevel2' + counter, $("#cusLevel2").val().replace(/.+#/i,""), 1);
    	setCookie('product' + counter, $("#product").val().replace(/.+#/i,""), 1);
    	setCookie('pro_totalBoxFm' + counter, $("#pro_totalBoxFm").val().replace(/.+#/i,""), 1);
    	setCookie('pro_quantityFm' + counter, $("#pro_quantityFm").val().replace(/.+#/i,""), 1);
    	setCookie('total_priceFm' + counter, $("#total_priceFm").val().replace(/.+#/i,""), 1);
    	
    	//alert('cusLevel1' + counter + '=' + getCookie('cusLevel1' + counter));
	}
	
	function showStatisticHistory(){
		var total = '';//getCookie('total_statistic');
		//alert('showStatisticHistory='+total);
		if(total !=  ''){
			var counter = parseInt(total);
			for(var i=1; i<= counter; i++){
				tbl.row.add( [
				                i,
				                getCookie('cusLevel1' + i),
				                getCookie('cusLevel2' + i),
				                getCookie('product' + i),
				                getCookie('pro_totalBoxFm' + i),
				                getCookie('pro_quantityFm' + i),
				                getCookie('total_priceFm' + i)
				            ] ).draw( false ); 
			}
		}
	}
	//Get statistic history
	window.onload = showStatisticHistory();
	
    function checkInvalidData(){
  	  	var cus1 = $("#cusLevel1").val();
        var cus2 = $("#cusLevel2").val();
        var prod = $("#product").val();
        var b = $("#pro_totalBoxFm").val();
        var q = $("#pro_quantityFm").val();
        if(cus1 == ''){
      	  alert('Vui lòng nhập thông tin khách hàng cấp 1');
      	  $('.lookupCls').eq(0).focus();
      	  return false;
        }
        if(cus2 == ''){
      	  alert('Vui lòng nhập thông tin khách hàng cấp 2');
      	  $('.lookupCls').eq(1).focus();
      	  return false;
        }
        if(prod == ''){
      	  alert('Vui lòng nhập thông tin sản phẩm');
      	  $('.lookupCls').eq(2).focus();
      	  return false;
        }
        if(b == '' || b == '0'){
      	  alert('Vui lòng nhập tổng số thùng sản phẩm');
      	  $('#pro_totalBoxFm').eq(0).focus();
      	  return false;
        }
        if(q == '' || q == '0'){
      	  alert('Vui lòng nhập số lượng sản phẩm');
      	  $('#pro_quantityFm').eq(0).focus();
      	  return false;
        }
        return true;
    }
    
</script>
<!-- Auto lookup  -->

<!-- daterangepicker -->
<script type="text/javascript" src="js/moment.min2.js"></script>
<script type="text/javascript" src="js/datepicker/daterangepicker.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#dateReceived').daterangepicker({
			singleDatePicker : true,
			calender_style : "picker_2",
			format : 'DD/MM/YYYY',
			showDropdowns : true
		}, function(start, end, label) {
			console.log(start.toISOString(), end.toISOString(), label);
		});
	});
</script>
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


</script>


<%-- <script>
	$(document).ready(function() {
		$('#pro_id').change(function() {
			var proId = {
				"proId" : $("#pro_id").val()
			};
			$.ajax({
				url : "readInfoStatistic",
				data : JSON.stringify(proId),
				dataType : 'json',
				contentType : 'application/json',
				type : 'POST',
				async : true,
				success : function(res) {
					$('#unitPrice').val(res);
					$('#unitPriceFm').val((res+"").replace(/(\d)(?=(\d{3})+(?!\d))/g, "$1,"));
					var quantity = $("#quantity").val();
					$('#total').val(res * quantity);
					$('#totalFm').val(((res * quantity)+"").replace(/(\d)(?=(\d{3})+(?!\d))/g, "$1,"));
				}
			});
		});
	});
</script> --%>

</body>

</html>