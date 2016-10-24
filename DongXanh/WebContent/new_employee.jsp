<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- Using Struts2 Tags in JSP --%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/struts-dojo-tags" prefix="sx"%>
<%@ include file="header.jsp"%>
<%@ include file="user_profile.jsp"%>
<%@ include file="menu.jsp"%>
<!-- page content -->
<div class="right_col" role="main">

	<div class="">
		<div class="page-title">
			<div class="title_left">
				<s:if test="%{edit}">
					<h3>Cập nhật thông tin Nhân viên</h3>
				</s:if>
				<s:else>
					<h3>Thêm nhân viên</h3>
				</s:else>
			</div>
		</div>
		<div class="clearfix"></div>

		<div class="row">
			<div class="col-md-12 col-sm-12 col-xs-12">
				<div class="x_panel">
					<div class="x_content">
						<s:form action="add_employee" method="post" theme="bootstrap"
							cssClass="form-horizontal form-label-left">
							<s:hidden name="id" value="%{userId}"></s:hidden>
							<s:hidden name="edit" value="%{edit}"></s:hidden>
							<s:if test="hasActionErrors()">
								<div class="errors">
									<s:actionerror escape="true" />
								</div>
							</s:if>
							<s:elseif test="hasActionMessages()">
								<div class="message">
									<s:actionmessage escape="true" />
								</div>
							</s:elseif>
							<span class="section"></span>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="fullName">Họ và tên <span class="required">*</span>
								</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<input id="fullName" type="text" name="fullName"
										data-validate-length-range="1,100" value="${user.fullName}"
										class="form-control col-md-7 col-xs-12">
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="userName">Tên tài khoản <span class="required">*</span>
								</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<input id="userName" type="text" name="userName" readonly
										data-validate-length-range="1,20" value="${user.userName}"
										class="form-control col-md-7 col-xs-12">
								</div>
							</div>
							<div class="item form-group">
								<label for="password" class="control-label col-md-3">Mật
									khẩu </label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<input id="password" type="password" name="password"
										data-validate-length="6,100" value="${user.password}"
										class="form-control col-md-7 col-xs-12" required="required">
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="email">Email <span class="required">*</span>
								</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<input type="email" id="email" name="email" required="required"
										data-validate-length="1,100" value="${user.email}"
										class="form-control col-md-7 col-xs-12">
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="birthDate">Ngày sinh <span class="required">*</span>
								</label>
								<div class="col-md-3 xdisplay_inputx has-feedback">
									<input type="text" class="form-control has-feedback-left"
										id="birthDate" name="varbirthDate" value="${varbirthDate}"
										aria-describedby="inputSuccess2Status"> <span
										class="fa fa-calendar-o form-control-feedback left"
										aria-hidden="true"></span> <span id="inputSuccess2Status"
										class="sr-only">(success)</span>
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="gender">Giới tính <span class="required">*</span>
								</label>
								<div class="col-md-2 col-sm-6 col-xs-12">
									<select id="gender" name="gender" required="required"
										class="form-control col-md-7 col-xs-12">
										<option selected="selected">Nam</option>
										<option>Nữ</option>
									</select>
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="mobilePhone">Điện thoại <span class="required">*</span>
								</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<input id="mobilePhone" type="text" name="mobilePhone"
										data-validate-length-range="10,11" value="${user.mobilePhone}"
										class="form-control col-md-7 col-xs-12">
								</div>
							</div>

							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="address">Địa chỉ <span class="required">*</span>
								</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<textarea id="address" name="address" required="required"
										class="form-control col-md-7 col-xs-12">${user.address}</textarea>
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="roleId">Nhóm <span class="required">*</span>
								</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<s:select id="roleId" name="roleId" showDownArrow="false"
										autoComplete="true" value="%{user.role.roleId}"
										cssClass="form-control col-md-7 col-xs-12" list="listRole"
										disabled="%{userSes.role.roleId != 1}"
										listKey="roleId" listValue="roleName" />
								</div>
							</div>
							<div class="ln_solid"></div>
							<div class="form-group">
								<div class="col-md-6 col-md-offset-3">
									<button type="reset" class="btn btn-primary">Làm mới</button>
									<button id="send" type="submit" class="btn btn-success">
										<s:if test="%{edit}">
											Cập nhật
										</s:if>
										<s:else>
											Thêm
										</s:else>
									</button>
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
		$('#birthDate').daterangepicker({
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
	$(document).ready(function() {
		$('#fullName').change(function() {
			var varFullName = {
				"varFullName" : $("#fullName").val()
			};
			$.ajax({
				url : "generateEmpUserName",
				data : JSON.stringify(varFullName),
				dataType : 'json',
				contentType : 'application/json',
				type : 'POST',
				async : true,
				success : function(res) {
					$('#userName').val(res);
				}
			});
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

</body>

</html>