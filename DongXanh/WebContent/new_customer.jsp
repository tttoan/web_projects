<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/struts-dojo-tags" prefix="sd"%>
<%@ include file="header.jsp"%>
<%@ include file="user_profile.jsp"%>
<%@ include file="menu.jsp"%>

<!-- page content -->
<div class="right_col" role="main">
	<div class="page-title">
		<div class="title_left">
			<h3>Tạo Khách Hàng</h3>
		</div>
	</div>
	<span class="section"></span>
	<%	Calendar d = Calendar.getInstance();
							int yearNow = d.get(Calendar.YEAR); %>

	<%-- Setup the number of columns to be in the table layout for the form. --%>


	<s:bean name="java.util.HashMap" id="qTableLayout">
		<s:param name="tablecolspan" value="%{8}" />
	</s:bean>

	<s:form action="add_invoice" method="post" theme="qxhtml">
		<style type="text/css">
.label {
	color: black;
}
</style>

		<s:textfield required="true" id="order.fromPartyOrderNumber"
			label="SO Number" name="order.fromPartyOrderNumber" key="user.name">
			<s:param name="labelcolspan" value="%{4}" />
			<s:param name="inputcolspan" value="%{4}" />
		</s:textfield>
		<s:select name="salespersonId" label="Salesperson" list="{1}">
			<s:param name="labelcolspan" value="%{2}" />
			<s:param name="inputcolspan" value="%{2}" />
		</s:select>

		<s:textfield label="Trucker" name="order.dutyPrepaid" size="40">
			<s:param name="labelcolspan" value="%{4}" />
			<s:param name="inputcolspan" value="%{4}" />
		</s:textfield>
		<s:textfield label="SO Number" name="order.fromPartyOrderNumber">
			<s:param name="labelcolspan" value="%{4}" />
			<s:param name="inputcolspan" value="%{4}" />
		</s:textfield>
		<s:select name="salespersonId" label="Salesperson" list="{1}">
			<s:param name="labelcolspan" value="%{1}" />
			<s:param name="inputcolspan" value="%{1}" />
		</s:select>

		<s:textfield label="Trucker" name="order.dutyPrepaid" size="40">
			<s:param name="labelcolspan" value="%{2}" />
			<s:param name="inputcolspan" value="%{2}" />
		</s:textfield>
		<tr>
			<th align="center" colspan="8">Line Items</th>
		</tr>

		<tr>
			<th>#</th>
			<th>Product</th>
			<th>Qty</th>
			<th>Unit Price</th>
			<th>Allocation Instructions</th>
			<th>Label Instructions</th>
			<th>Description</th>
			<th>Override Reason</th>
		</tr>
		<s:textfield label="Trucker" name="order.dutyPrepaid" size="40">
			<s:param name="labelcolspan" value="%{4}" />
			<s:param name="inputcolspan" value="%{4}" />
		</s:textfield>
		<s:submit value="Create Sales Order" align="center">
			<s:param name="colspan" value="%{4}" />
			<s:param name="align" value="%{'center'}" />
		</s:submit>
	</s:form>
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