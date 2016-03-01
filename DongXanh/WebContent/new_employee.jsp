
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- Using Struts2 Tags in JSP --%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="header.jsp"%>
<%@ include file="user_profile.jsp"%>
<%@ include file="menu.jsp"%>
<%@ taglib uri="/struts-dojo-tags" prefix="sd"%>
<!-- page content -->
<div class="right_col" role="main">
	<div class="container" style="padding-top: 60px;">
		<h3 class="page-header">Tạo Hồ Sơ Nhân Viên</h3>
		<div class="row">
			<!-- left column -->
			<!-- 				<div class="col-md-4 col-sm-6 col-xs-12"> -->
			<!-- 					<div class="text-center"> -->
			<!-- 						<img src="images/img.jpg" class="avatar img-circle img-thumbnail" -->
			<!-- 							alt="avatar"> -->
			<!-- 						<h6>Upload a different photo...</h6> -->
			<!-- 						<input type="file" class="text-center center-block well well-sm"> -->
			<!-- 					</div> -->
			<!-- 				</div> -->
			<!-- edit form column -->
			<div class="col-md-9 col-sm-6 col-xs-12 personal-info">
				<!-- 				<div class="alert alert-info alert-dismissable"> -->
				<!-- 					<a class="panel-close close" data-dismiss="alert"></a> <i -->
				<!-- 						class="fa fa-coffee"></i> -->
				<!-- 				</div> -->
				<s:form action="add_employee" method="post"
					cssClass="form-horizontal" theme="bootstrap">
					<div class="form-group">
						<label class="col-lg-3 control-label">Họ và tên:</label>
						<div class="col-lg-8">
							<s:textfield id="fullName" name="fullName"
								cssClass="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label">Tên tài khoản:</label>
						<div class="col-lg-8">
							<s:textfield id="userName" name="userName"
								cssClass="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label">Mật khẩu:</label>
						<div class="col-lg-8">
							<s:password id="password" name="password" cssClass="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label">Email:</label>
						<div class="col-lg-8">
							<s:textfield id="email" name="email" type="email"
								cssClass="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label">Ngày sinh:</label>
						<div class="col-lg-8">
							<sd:datetimepicker id="birthDate" name="birthDate" displayFormat="dd-MM-yyyy"
								value="%{'today'}" cssClass="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label">Giới tính:</label>
						<div class="col-lg-8">
							<s:select id="gender" name="gender" list="{'Nam','Nữ'}"
								cssClass="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label">Điện thoại:</label>
						<div class="col-lg-8">
							<s:textfield id="mobilePhone" name="mobilePhone"
								cssClass="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label">Địa chỉ:</label>
						<div class="col-lg-8">
							<s:textarea id="address" name="address" cssClass="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label">Nhóm: </label>
						<div class="col-lg-8">
							<s:select id="roleId" name="roleId" cssClass="form-control"
								list="listRole" listKey="roleId" listValue="roleName" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label"></label>
						<div class="col-md-8">
							<s:reset cssClass="btn btn-default" value="Reset" />
							<s:submit cssClass="btn btn-primary" value="Save" />
						</div>
					</div>
				</s:form>
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