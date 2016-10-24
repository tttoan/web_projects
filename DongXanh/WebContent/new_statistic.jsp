
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
					<h3>Cập nhật thông tin hóa đơn</h3>
				</s:if>
				<s:else>
					<h3>Thêm hóa đơn</h3>
				</s:else>
			</div>
		</div>
		<div class="clearfix"></div>
		<div class="row">
			<div class="col-md-12 col-sm-12 col-xs-12">
				<div class="x_panel">
					<div class="x_content">
						<s:form action="add_statistic" theme="bootstrap" method="post"
							cssClass="form-horizontal form-label-left">
							<s:hidden name="stat.id" value="%{statId}"></s:hidden>
							<s:hidden name="edit" value="%{edit}"></s:hidden>
							<span class="section"></span>
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
									for="emp.id">Nhân viên TT <span class="required">*</span>
								</label>

								<div class="col-md-6 col-sm-6 col-xs-12">
									<s:select id="emp_id" name="emp.id"
										cssClass="form-control col-md-7 col-xs-12" headerKey="-1"
										headerValue="---" showDownArrow="false" autoComplete="true"
										list="listEmployee" value="%{stat.user.id}" listKey="id"
										listValue="fullName +' - '+ userName" />
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for=cusLevel2.id>Tên cấp 2 <span class="required">*</span>
								</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<s:url id="url" value="/add_statistic.action" />
									<s:select id="cusLevel2.id" name="cusLevel2.id" headerKey="-1"
										headerValue="---" cssClass="form-control col-md-7 col-xs-12"
										value="%{stat.customerByCustomerCodeLevel2.id}"
										showDownArrow="false" autoComplete="true" list="listCustomerLevel2"
										listKey="id" listValue="director +' - '+ customerCode" />
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="cusLevel1.id">Tên cấp 1 <span class="required">*</span>
								</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<s:select id="cusLevel1.id" name="cusLevel1.id"
										showDownArrow="false" autoComplete="true"
										value="%{stat.customerByCustomerCodeLevel1.id}"
										cssClass="form-control col-md-7 col-xs-12" list="listCustomerLevel1"
										listKey="id" headerKey="-1" headerValue="---"
										listValue="director +' - '+ customerCode" />
								</div>
							</div>

							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="pro_id">Sản phẩm <span class="required">*</span>
								</label>

								<div class="col-md-6 col-sm-6 col-xs-12">
									<s:select id="pro_id" name="pro.id" value="%{stat.product.id}"
										cssClass="form-control col-md-7 col-xs-12" list="listProduct"
										headerKey="-1" headerValue="---" listKey="id"
										listValue="productName +' - '+ productCode" />
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="unitPriceFm">Đơn giá <span class="required">*</span>
								</label>
								<div class="col-md-3 col-sm-6 col-xs-12">
									<s:hidden id="unitPrice" name="unitPrice"
										value="%{stat.product.unitPrice}"></s:hidden>
									<s:set var="varUnitPrice"
										value="%{getText('format.money',{stat.product.unitPrice})}"></s:set>
									<input type="text" id="unitPriceFm" name="unitPriceFm" readonly
										required="required" data-validate-minmax="1,1000000000"
										value="${varUnitPrice}"
										class="form-control col-md-7 col-xs-12">
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="totalBox">Số thùng <span class="required">*</span>
								</label>
								<div class="col-md-3 col-sm-6 col-xs-12">
									<input type="number" id="totalBox" name="totalBox"
										value="${stat.totalBox}" required="required"
										data-validate-minmax="0,100000"
										class="form-control col-md-7 col-xs-12">
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="quantity">Số lượng <span class="required">*</span>
								</label>
								<div class="col-md-3 col-sm-6 col-xs-12">
									<input type="number" id="quantity" name="quantity"
										value="${stat.quantity}" required="required"
										data-validate-minmax="0,100000"
										class="form-control col-md-7 col-xs-12">
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="total">Thành tiền <span class="required">*</span>
								</label>
								<div class="col-md-3 col-sm-6 col-xs-12">
									<s:hidden id="total" name="total" value="%{stat.total}"></s:hidden>
									<s:set var="varTotal"
										value="%{getText('format.money',{stat.total})}"></s:set>
									<input type=text id="totalFm" name="totalFm"
										required="required" value="${varTotal}" readonly
										data-validate-minmax="0,1000000000"
										class="form-control col-md-7 col-xs-12">

								</div>
							</div>

							<div class="ln_solid"></div>
							<div class="form-group">
								<div class="col-md-6 col-md-offset-3">
									<button type="reset" class="btn btn-primary">Làm mới</button>
									<button id="send" type="submit" class="btn btn-success"><s:if test="%{edit}">
											Cập nhật
										</s:if>
										<s:else>
											Thêm
										</s:else></button>
								</div>
							</div>
						</s:form>
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

<script>
	$(document).ready(function() {
		$('#quantity').change(function() {
			var unitPrice = $("#unitPrice").val();
			var quantity = $("#quantity").val();
			$('#total').val((unitPrice * quantity));
			$('#totalFm').val(((unitPrice * quantity)+"").replace(/(\d)(?=(\d{3})+(?!\d))/g, "$1,"));
		});
	});
</script>

<script>
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
					// 															for (var i = 0; i < res.length; i++) {
					// 																$('#district')
					// 																		.append(
					// 																				'<option value=' + res[i] + '>'
					// 																						+ res[i]
					// 																						+ '</option>');
					// 															}
				}
			});
		});
	});
</script>

</body>

</html>