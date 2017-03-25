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
	<div class="">
		<div class="page-title">
			<div class="title_left">
				<h3>Thêm khách hàng</h3>
			</div>
		</div>
		<div class="clearfix"></div>
		<div class="row">
			<div class="col-md-12 col-sm-12 col-xs-12">
				
				<sx:tabbedpanel id="tabContainer">
					<s:form action="add_customer" method="post"
						enctype="multipart/form-data"
						cssClass="form-horizontal form-label-left" theme="bootstrap">
						<sx:div label="Thông Tin Khách Hàng" id="createCustomerInfo1">
							<div class="x_panel">
								<div class="x_content">
									<s:hidden name="custId" value="%{custId}"></s:hidden>
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
									<span class="section"></span>

									<div class="item form-group">
										<label class="control-label col-md-3 col-sm-3 col-xs-12"
											for="createTime">Ngày lập </label>
										<div class="col-md-3 xdisplay_inputx has-feedback">
											<input type="text" class="form-control has-feedback-left"
												id="createTime" name="varCreateTime"
												value="${varCreateTime}"
												disabled="true"
												aria-describedby="inputSuccess2Status"> <span
												class="fa fa-calendar-o form-control-feedback left"
												aria-hidden="true"></span> <span id="inputSuccess2Status"
												class="sr-only">(success)</span>
										</div>
									</div>

									<div class="item form-group">
										<label class="control-label col-md-3 col-sm-3 col-xs-12"
											for="customerCode">Mã khách hàng
										</label>
										<div class="col-md-3 col-sm-6 col-xs-12">
											<input id="customerCode" type="text" name="customerCode"
												 data-validate-length-range="0,20"
												value="${cust.customerCode}"
												class="form-control col-md-7 col-xs-12">
										</div>
									</div>
									<div class="item form-group">
										<label class="control-label col-md-3 col-sm-3 col-xs-12"
											for="statisticName">Tên bảng kê <span
											class="required">*</span>
										</label>
										<div class="col-md-5 col-sm-6 col-xs-12">
											<input id="statisticName" name="cust.statisticName"
												type="text" data-validate-length-range="1,500"
												required="required" value="${cust.statisticName}"
												class="form-control col-md-7 col-xs-12">
										</div>
									</div>
									<div class="item form-group">
										<label class="control-label col-md-3 col-sm-3 col-xs-12"
											for="businessName">Tên doanh nghiệp (cửa hàng) <span
											class="required">*</span>
										</label>
										<div class="col-md-5 col-sm-6 col-xs-12">
											<input id="businessName" type="text" name="cust.businessName"
												required="required" value="${cust.businessName}"
												class="form-control col-md-7 col-xs-12">
										</div>
									</div>
									<div class="item form-group">
										<label class="control-label col-md-3 col-sm-3 col-xs-12"
											for="cityName">Khu vực 
										</label>
										<div class="col-md-5 col-sm-6 col-xs-12">
											<s:select id="cityName" name="varCityCode"
												cssClass="form-control col-md-7 col-xs-12" list="listCity"
												showDownArrow="false" autoComplete="true" headerKey="-1" headerValue="--"
												listKey="cityCode" listValue="cityName"
												value="%{varCityCode}" />
										</div>
									</div>
									<div class="item form-group">
										<label class="control-label col-md-3 col-sm-3 col-xs-12"
											for="grpCustomer_id">Nhóm 
										</label>
										<div class="col-md-5 col-sm-6 col-xs-12">
											<s:select id="grpCustomer_id" name="grpCustomer.id"
												cssClass="form-control col-md-7 col-xs-12" list="listGrpCus"
												showDownArrow="false" autoComplete="true" headerKey="-1" headerValue="--"
												listKey="id" listValue="groupName"
												value="%{cust.groupCustomer.id}" />
										</div>
									</div>
									<div class="item form-group">
										<label class="control-label col-md-3 col-sm-3 col-xs-12"
											for="emp_id">Nhân viên TT 
										</label>
										<div class="col-md-5 col-sm-6 col-xs-12">
											<s:select id="emp_id" name="emp.id"
												cssClass="form-control col-md-7 col-xs-12"
												showDownArrow="false" autoComplete="true" headerKey="-1" headerValue="--"
												list="listEmployee" listKey="id"
												listValue="fullName"
												value="%{cust.user.id}" />
										</div>
									</div>
									
									<div class="item form-group">
										<label class="control-label col-md-3 col-sm-3 col-xs-12"
											for="cusImageScan">Ảnh scan (*.jpg, *.png, *.gif) </label>
										<div class="col-md-5 col-sm-6 col-xs-12">
											<input id="cusImageScan" type="file" name="cusImageScan" multiple=""/>
										</div>
										<s:if test="%{edit}">
											<div id="dvPreview" class="col-md-5 col-sm-6 col-xs-12 divborder">
												<img src="<s:property value="cust.pathDocScan"/>"  width="300" height="250"  />
											</div>
										</s:if>
										<s:else>
											<div id="dvPreview" class="col-md-5 col-sm-6 col-xs-12 divborder">
											</div>
										</s:else>
									</div>
									
									<br>
									
									<div class="ln_solid"></div>
									<div class="form-group">
										<div class="col-md-6 col-md-offset-3">
											<button id="rs" type="reset" class="btn btn-primary">
												Làm mới </button>
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
								</div>
							</div>
						</sx:div>
						
						</s:form>
					
				</sx:tabbedpanel>
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
<script>
	$(document).ready(function() {
		$('#cityName').change(function() {
			var custCode = $("#customerCode").val();
			if (custCode.length > 3)
				custCode = custCode.substr(custCode.length - 3);
			var cityCode = $("#cityName").val();
			$("#customerCode").val(cityCode + "" + custCode);
		});
	});
</script>
<!-- daterangepicker -->
<script type="text/javascript" src="js/moment.min2.js"></script>
<script type="text/javascript" src="js/datepicker/daterangepicker.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$('#createTime,#certificateDate,#directorBirthday').daterangepicker({
			singleDatePicker : true,
			calender_style : "picker_2",
			format : 'DD/MM/YYYY',
			showDropdowns : true
		}, function(start, end, label) {
			console.log(start.toISOString(), end.toISOString(), label);
		});
	});
</script>


<script src="js/jquery.min.js"></script>
<script type="text/javascript">
$(function () {
    $("#cusImageScan").change(function () {
        $("#dvPreview").html("");
        var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.jpg|.jpeg|.gif|.png|.bmp)$/;
        alert($(this)[0].files.length);
        if (regex.test($(this).val().toLowerCase())) {
            if (false){//$.browser.msie && parseFloat(jQuery.browser.version) <= 9.0) {
            	alert("huhuhu1");
                $("#dvPreview").show();
                $("#dvPreview")[0].filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = $(this).val();
            }
            else {
                if (typeof (FileReader) != "undefined") {
                	alert("huhuhu2");
                    $("#dvPreview").show();
                  	//for every file...
                    for (var x = 0; x < $(this)[0].files.length; x++) {
                    	$("#dvPreview").append("<img />");
                        var reader = new FileReader();
                        reader.onload = function (e) {
                            $("#dvPreview img").attr("src", e.target.result).width(300).height(200);;
                        }
                        reader.readAsDataURL($(this)[x].files[x]);
                    }
                } else {
                    alert("This browser does not support FileReader.");
                }
            }
        } else {
            alert("Please upload a valid image file.");
        }
    });
});
</script>

<style>
	.divleft {
	    float: left;
	    width: 300;
	    height: 150;
	    border: 3px solid #73AD21;
	    padding: 5px;
	}
	.divright {
	    float: right;
	    width: 300;
	    height: 150;
	    border: 3px solid #73AD21;
	    padding: 5px;
	}
	.divborder {
	    border: 3px solid #73AD21;
	    padding: 5px;
	}
</style>
</body>
</html>