
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- Using Struts2 Tags in JSP --%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<%@ include file="header.jsp"%>
<%@ include file="user_profile.jsp"%>
<%@ include file="menu.jsp"%>
<%@ taglib uri="/struts-dojo-tags" prefix="sd"%>
<script>
	function change() {
		var el1 = document.getElementById("quantiy");
		var el2 = document.getElementById("price");
		var el3 = document.getElementById("total");
		el3.value = el1.value * el2.value;
	}
</script>
<!-- page content -->
<div class="right_col" role="main">
	<div class="">
		<div class="page-title">
			<div class="title_left">
				<h3>Thêm Hóa Đơn</h3>
			</div>

		</div>
		<div class="clearfix"></div>
		<div class="row">
			<div class="col-md-12 col-sm-12 col-xs-12">
				<div class="x_panel">
					<div class="x_content">
						<s:form action="add_invoice" theme="bootstrap"
							cssClass="form-horizontal form-label-left">
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="dateReceived">Ngày nhận <span class="required">*</span>
								</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<sd:datetimepicker id="dateReceived" name="dateReceived"
										cssClass="form-control col-md-7 col-xs-12"
										displayFormat="dd-MM-yyyy" />
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="customerCodeLevel2">Mã cấp 2 <span
									class="required">*</span>
								</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<sd:autocompleter id="customerCodeLevel2"
										name="customerCodeLevel2"
										cssClass="form-control col-md-7 col-xs-12"
										showDownArrow="false" autoComplete="true"
										list="{'Arsenal','Leicester City','Man City','Tottenham','Man United','West Ham',
										'Stoke City','Crystal Palace','Liverpool','Southampton','Everton','Watford','West Bromwich','Chelsea','Bournemouth'}" />
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="customerNameLevel2">Tên cấp 2 <span
									class="required">*</span>
								</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<sd:autocompleter id="customerNameLevel2"
										name="customerNameLevel2"
										cssClass="form-control col-md-7 col-xs-12"
										showDownArrow="false" autoComplete="true"
										list="{'Arsenal','Leicester City','Man City','Tottenham','Man United','West Ham',
										'Stoke City','Crystal Palace','Liverpool','Southampton','Everton','Watford','West Bromwich','Chelsea','Bournemouth'}" />
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="customerCodeLevel1">Mã cấp 1 <span
									class="required">*</span>
								</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<sd:autocompleter id="customerCodeLevel1"
										name="customerCodeLevel1"
										cssClass="form-control col-md-7 col-xs-12"
										showDownArrow="false" autoComplete="true"
										list="{'Arsenal','Leicester City','Man City','Tottenham','Man United','West Ham',
										'Stoke City','Crystal Palace','Liverpool','Southampton','Everton','Watford','West Bromwich','Chelsea','Bournemouth'}" />
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="customerNameLevel1">Tên cấp 1 <span
									class="required">*</span>
								</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<sd:autocompleter id="customerNameLevel1"
										name="customerNameLevel1"
										cssClass="form-control col-md-7 col-xs-12"
										showDownArrow="false" autoComplete="true"
										list="{'Arsenal','Leicester City','Man City','Tottenham','Man United','West Ham',
										'Stoke City','Crystal Palace','Liverpool','Southampton','Everton','Watford','West Bromwich','Chelsea','Bournemouth'}" />
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="quantity">Số lượng <span class="required">*</span>
								</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<s:textfield id="quantity" name="quantity" onchange="change()"
										cssClass="form-control col-md-7 col-xs-12" value="0"
										required="required" type="number" />
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="unitPrice">Giá <span class="required">*</span>
								</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<s:textfield id="unitPrice" name="unitPrice"
										required="required" value="%{getText('format.money',{0})}"
										cssClass="form-control col-md-7 col-xs-12" />
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="total">Thành tiền <span class="required">*</span>
								</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<s:textfield id="total" name="total" required="required"
										value="%{getText('format.money',{0})}"
										cssClass="form-control col-md-7 col-xs-12" />
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="employeeName">NVTT <span class="required">*</span>
								</label>
								<div class="col-md-4 col-sm-6 col-xs-12">
									<sd:autocompleter id="employeeName" name="employeeName"
										cssClass="form-control col-md-7 col-xs-12"
										showDownArrow="false" autoComplete="true"
										list="listEmployeeName" />
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-6 col-md-offset-3">
									<s:reset class="btn btn-primary" value="Reset" />
									<s:submit id="send" value="Submit" class="btn btn-success" />
								</div>
							</div>
						</s:form>
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
<!-- form validation -->
<script src="js/validator/validator.js"></script>
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

	/* FOR DEMO ONLY */
	$('#vfields').change(function() {
		$('form').toggleClass('mode2');
	}).prop('checked', false);

	$('#alerts').change(function() {
		validator.defaults.alerts = (this.checked) ? false : true;
		if (this.checked)
			$('form .alert').remove();
	}).prop('checked', false);
</script>

</body>

</html>