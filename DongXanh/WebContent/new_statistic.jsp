
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
						<s:form action="" theme="bootstrap" method="post"
							cssClass="form-horizontal form-label-left">
							<s:hidden id="id" name="id" value="%{id}"></s:hidden>
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
										id="dateReceived" name="date_received" value="${date_received}"
										aria-describedby="inputSuccess2Status"> <span
										class="fa fa-calendar-o form-control-feedback left"
										aria-hidden="true"></span> <span id="inputSuccess2Status"
										class="sr-only">(success)</span>
								</div>
								
								<label class="control-label col-md-2 col-sm-3 col-xs-12"
									for="dateReceived">Loại bảng kê
								</label>
								<div class="col-md-3 col-sm-3 col-xs-12">
									<s:select id="statisticType" name="invoice_type_id"
										cssClass="col-md-12 col-xs-12" list="listInvoiceType"
										showDownArrow="false" autoComplete="true" headerKey="-1" headerValue=""
										listKey="id" listValue="invoiceType"
										style="width:245px"
										value="%{invoice_type_id}" />
								</div>
							</div>
							
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="cusLevel1.id">Tên cấp 1 <span class="required">*</span>
								</label>
								<div class="col-md-5 col-sm-6 col-xs-12">
									<s:hidden id="cusLevel1_id" name="customer_code_level1" value="%{customer_code_level1}"></s:hidden>
									<input id="cusLevel1" type="text" name="cusLevel1" tabindex="1"
										value="${customer_code_level1}" required="required"
										class="form-control col-md-7 col-xs-12">
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for=cusLevel2.id>Tên cấp 2 <span class="required">*</span>
								</label>
								<div class="col-md-5 col-sm-6 col-xs-12">
									<s:hidden id="cusLevel2_id" name="customer_code_level2" value="%{customer_code_level2}"></s:hidden>
									<input id="cusLevel2" type="text" name="cusLevel2" tabindex="2"
										value="${customer_code_level2}" required="required"
										class="form-control col-md-7 col-xs-12">
								</div>
							</div>

							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="pro_id">Sản phẩm <span class="required">*</span>
								</label>
								<div class="col-md-5 col-sm-6 col-xs-12">
									<s:hidden id="pro_id" name="product_id" value="%{product_id}"></s:hidden>
									<input id="product" type="text" name="product" tabindex="3"
										 value="${product_id}" required="required"
										class="form-control col-md-7 col-xs-12">
								</div>
								
								<label class="control-label col-md-1 col-sm-3 col-xs-12"
									for="unitPriceFm">Đơn giá
								</label>
								<div class="col-md-2 col-sm-6 col-xs-12">
									<s:hidden id="pro_unitPrice" name="pro_unitPrice" value="%{statistic.product.unitPrice}"></s:hidden>
									<s:hidden id="pro_quantityPerBox" name="pro_quantityPerBox" value="%{statistic.product.quantity}"></s:hidden>
									<input type="text" id="pro_unitPriceFm" name="pro_unitPriceFm" readonly
										data-validate-minmax="1,1000000000"
										value="${statistic.product.unitPrice}"
										class="form-control col-md-7 col-xs-12">
								</div>
							</div>
							
							
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="totalBox">Số thùng <span class="required">*</span>
								</label>
								<div class="col-md-2 col-sm-6 col-xs-12">
									<input type="number" id="pro_totalBoxFm" name="total_box"
										value="${total_box}" required="required"
										data-validate-minmax="0,100000"
										class="form-control col-md-7 col-xs-12"
										onkeypress="onlySpecialChar(event, '[0-9\\.]+')">
								</div>
								
								<label class="control-label col-md-1 col-sm-3 col-xs-12"
									for="quantity">Số lượng
								</label>
								<div class="col-md-2 col-sm-6 col-xs-12">
									<input type="number" id="pro_quantityFm" name="quantity"
										value="${quantity}" required="required"
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
									<s:if test="%{edit}">
										<button id="editStatistic" type="button" class="btn btn-success">Cập nhật</button>
									</s:if>
									<s:else>
										<button id="addStatistic" type="button" class="btn btn-success">Thêm mới</button>
									</s:else>
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
											<th>Id</th>
											<th>Ngày Nhận</th>
											<th>Loại</th>
											<th>Khách Hàng Cấp 1</th>
											<th>Khách Hàng Cấp 2</th>
											<th>Sản Phẩm</th>
											<th>Số Thùng</th>
											<th>Số Lượng</th>
											<th>Thành Tiền</th>
											<th></th>
										</tr>
									</thead>
		
									<tbody>
										<s:iterator value="statisticsHistory" status="rowStatus">
										<tr class="even pointer">
											<td><s:property value="#rowStatus.count" /></td>
											<td><s:property	value="id" /></td>
											<td><s:property	value="%{getText('format.date',{dateReceived})}" /></td>
											<td><s:property	value="invoiceType.invoiceType" /></td>
											<td><s:property	value="customerByCustomerCodeLevel1.statisticName" /></td>
											<td><s:property	value="customerByCustomerCodeLevel2.statisticName" /></td>
											<td><s:property	value="product.productName" /></td>
											<td><s:property	value="totalBox" /></td>
											<td><s:property	value="quantity" /></td>
											<td><s:property	value="%{getText('format.money',{total})}" /></td>
											<td class="last">
												<s:url
													action="deleteStatisticAction" var="deleteURL">
													<s:param name="statId" value="%{id}"></s:param>
												</s:url> <s:a href="%{deleteURL}" class="btn btn-danger btn-xs">
												<i class="fa fa-trash-o"></i> Xóa </s:a></td>
										</tr>
										</s:iterator>
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
            		var type = $("#statisticType").val();
    				var dr = $("#dateReceived").val();
            		$('#resetStatistic').click();
            		$("#statisticType").val(type);
            		$("#dateReceived").val(dr);
            		//Set focus
            		$('.lookupCls').eq(0).focus();
        		}
        	}
		});
      
        //Add new statistic or update
        var tbl = $('#example').DataTable( {
            "scrollX": true,
            "columnDefs": [ 
                {
                    "targets": [ 1 ],
                    "visible": false
                },
                {
	                "targets": -1,
	                "data": null,
	                "defaultContent": "<button class='fa fa-trash-o btn btn-danger btn-xs'>Xóa</button>"
                } 
             ]
        } );
        $('#example tbody').on( 'click', 'button', function () {
            var data = tbl.row( $(this).parents('tr') ).data();
            //alert( "id = " + data[1] );
           // tbl.row( $(this).parents('tr') ).remove().draw( false );
            var selectedRow = ($(this).parents('tr'));
            var params = {
    				"id" : data[1]
    			};
   			$.ajax({
   				url : "deleteStatisticAction",
   				data : JSON.stringify(params),
   				dataType : 'json',
   				contentType : 'application/json',
   				type : 'POST',
   				async : true,
   				success : function(res) {
   					
   					if(res.startsWith('success')){
   						tbl.row( selectedRow ).remove().draw( false );
   					}else{
   						alert(res);
   					}
   				 //alert(tbl.row( teo ).data()[1]);
   				}
	   			});
	            
	            //
	        } );
        
        var counter = tbl.data().count()+1;
        $('#addStatistic').click(function () {
        	if(checkInvalidData()){
        		
        		var params = {
        				"id" : "",
        				"date_received": $("#dateReceived").val(),
      		            "invoice_type_id" :	$("#statisticType").val(),
      		            "customer_code_level1" :  $("#cusLevel1").val(),
      		            "customer_code_level2" : $("#cusLevel2").val(),
      		            "product_id" : $("#product").val(),
      		            "total_box" : $("#pro_totalBoxFm").val(),
      		            "quantity" : $("#pro_quantityFm").val(),
      		          	"unit_price" : $("#pro_unitPrice").val(),
        			};
       			$.ajax({
       				url : "addStatisticAction",
       				data : JSON.stringify(params),
       				dataType : 'json',
       				contentType : 'application/json',
       				type : 'POST',
       				async : false,
       				success : function(res) {
       					//alert(res);
       					//if(res.test(new RegExp('^(success;[0-9]+|duplicate;[0-9]+)$'))){
       					if(res.startsWith('success') || res.startsWith('duplicate')){
       						tbl.row.add( [
             	      		                counter,
             	      		             	res.replace(/(success;|duplicate;)/g, ""),
             	      		                $("#dateReceived").val(),
             	      		              	$("#statisticType").val()==1?"Cấp 1":"Cấp 2",
             	      		                $("#cusLevel1").val().replace(/.+#/g, ""),
             	      		                $("#cusLevel2").val().replace(/.+#/g, ""),
             	      		                $("#product").val().replace(/.+#/g, ""),
             	      		                $("#pro_totalBoxFm").val(),
             	      		                $("#pro_quantityFm").val(),
             	      		                $("#total_priceFm").val()
             	      		            ] ).draw(false); 
             		        	tbl.order([0, 'desc']).draw();
             		        	counter++;
             		        	//if(res.test(new RegExp('^(duplicate;[0-9]+)$'))){\
             		        	if(res.startsWith('duplicate')){
             		        		alert('Dữ liệu vừa thêm đã tồn tại, vui lòng kiểm tra!');
             		        	}
       					}else{
       						alert(res);
       					}
       					
       				}
       			});
        	}
        } );
        
        $('#editStatistic').click(function () {
        	if(checkInvalidData()){
        		var params = {
        				"id" : $("#id").val(),
        				"date_received": $("#dateReceived").val(),
      		            "invoice_type_id" :	$("#statisticType").val(),
      		            "customer_code_level1" :  $("#cusLevel1").val(),
      		            "customer_code_level2" : $("#cusLevel2").val(),
      		            "product_id" : $("#product").val(),
      		            "total_box" : $("#pro_totalBoxFm").val(),
      		            "quantity" : $("#pro_quantityFm").val(),
      		            "unit_price" : $("#pro_unitPrice").val(),
        			};
       			$.ajax({
       				url : "addStatisticAction",
       				data : JSON.stringify(params),
       				dataType : 'json',
       				contentType : 'application/json',
       				type : 'POST',
       				async : false,
       				success : function(res) {
       					//alert(res);
       					//if(res.test(new RegExp('^(success;[0-9]+|duplicate;[0-9]+)$'))){
       					if(res.startsWith('success') || res.startsWith('duplicate')){
       						window.location = 'list_statistic.action';
       					}else{
       						alert(res);
       					}
       					
       				}
       			});
        	}
        } );
        
    });
	
	
    function checkInvalidData(){
    	var type = $("#statisticType").val();
    	var dr = $("#dateReceived").val();
    	var cus1 = $("#cusLevel1").val();
        var cus2 = $("#cusLevel2").val();
        var prod = $("#product").val();
        var b = $("#pro_totalBoxFm").val();
        var q = $("#pro_quantityFm").val();
        if(dr == ''){
	       	  alert('Vui lòng nhập thông tin ngày tháng nhận bảng kê');
	       	  $('#dateReceived').eq(0).focus();
	       	  return false;
         }
        if(type == '' || type <= 0){
	       	  alert('Vui lòng chọn loại bảng kê');
	       	  $('#statisticType').eq(0).focus();
	       	  return false;
       }
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