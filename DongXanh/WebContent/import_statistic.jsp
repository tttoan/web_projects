<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- Using Struts2 Tags in JSP --%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/struts-jquery-tags" prefix="sj"%>
<%@ include file="header.jsp"%>
<%@ include file="user_profile.jsp"%>
<%@ include file="menu.jsp"%>
<!-- page content -->
<div class="right_col" role="main">
	<div class="">
		<div class="clearfix"></div>
		<div class="row">
			<div class="col-md-12 col-sm-12 col-xs-12">
				<div class="x_panel">
					<div class="x_content">
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
						<s:set name="importType" value="%{chooseSubTab}" />
						
						<div class="" role="tabpanel" data-example-id="togglable-tabs">
							<ul id="myTab" class="nav nav-tabs bar_tabs" role="tablist">
								<li role="presentation" class="active"><a
									href="#tab_content1" id="home-tab" role="tab" data-toggle="tab"
									aria-expanded="true">Bảng kê Cấp 1</a></li>
								<li role="presentation" class=""><a href="#tab_content2"
									role="tab" id="profile-tab" data-toggle="tab"
									aria-expanded="false">Bảng kê Cấp 2</a></li>
							</ul>
							<div id="myTabContent" class="tab-content">
								<div role="tabpanel" class="tab-pane fade active in"
									id="tab_content1" aria-labelledby="home-tab">
									<div class="x_panel">
										<div class="x_content">
											<div class="item form-group">
												<table style="width: 100%">
													<tr>
														<td><a href="#openImportStatisticLevel1"
															class="btn btn-primary"><b>Import bảng kê cấp 1</b></a> 
															<a href="#openImportBalanceLevel1" 
															class="btn btn-success"><b>Import số dư đầu kỳ</b></a></td>
													</tr>
												</table>
											</div>
										</div>
									</div>
								</div>
								<div role="tabpanel" class="tab-pane fade" id="tab_content2"
									aria-labelledby="profile-tab">
									<div class="x_panel">
										<div class="x_content">
											<div class="item form-group">
												<table style="width: 100%">
													<tr>
														<td><a href="#openImportStatisticLevel2"
															class="btn btn-primary"><b>Import bảng kê cấp 2</b></a></td>
													</tr>
												</table>
											</div>
										</div>
									</div>
									<div class="x_panel">
											<div class="x_content">
												<s:url var="fileDownload" action="export_statistic"></s:url>
												<s:form id="formExport" action="export_statistic"
													theme="bootstrap" method="post"
													cssClass="form-horizontal form-label-left">
													<s:if test="%{#importType=='exportInvoiceLevel2'}">
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
													</s:if>
													<div class="item form-group">
														<label
															class="control-label col-md-3 col-sm-3 col-xs-12"
															for="fromDate">Từ ngày <span class="required">*</span>
														</label>
														<div class="col-md-3 xdisplay_inputx has-feedback">
															<input type="text"
																class="form-control has-feedback-left" id="fromDate"
																name="varFromDate" value="${varFromDate}"
																aria-describedby="inputSuccess2Status"> <span
																class="fa fa-calendar-o form-control-feedback left"
																aria-hidden="true"></span> <span
																id="inputSuccess2Status" class="sr-only">(success)</span>
														</div>
													</div>
													<div class="item form-group">
														<label
															class="control-label col-md-3 col-sm-3 col-xs-12"
															for="toDate">Đến ngày <span class="required">*</span>
														</label>
														<div class="col-md-3 xdisplay_inputx has-feedback">
															<input type="text"
																class="form-control has-feedback-left" id="toDate"
																name="varToDate" value="${varToDate}"
																aria-describedby="inputSuccess2Status"> <span
																class="fa fa-calendar-o form-control-feedback left"
																aria-hidden="true"></span> <span
																id="inputSuccess2Status" class="sr-only">(success)</span>
														</div>
													</div>
													<div class="item form-group">
														<label
															class="control-label col-md-3 col-sm-3 col-xs-12"
															for="emp.id">Nhân viên TT <span
															class="required">*</span>
														</label>
														<div class="col-md-6 col-sm-6 col-xs-12">
															<s:select id="emp_id" name="sttCustom.empId"
																cssClass="form-control col-md-7 col-xs-12"
																headerKey="-1" headerValue="--" showDownArrow="false"
																autoComplete="true" list="listEmployee"
																value="%{stat.user.id}" listKey="id"
																listValue="fullName" />
														</div>
													</div>
													<div class="item form-group">
														<label
															class="control-label col-md-3 col-sm-3 col-xs-12"
															for=cusLevel2.id>Tên cấp 2 <span
															class="required">*</span>
														</label>
														<div class="col-md-6 col-sm-6 col-xs-12">
															<s:url id="url" value="/add_statistic.action" />
															<s:select id="cusLevel2.id"
																name="sttCustom.custLevel2Id" headerKey="-1"
																headerValue="--"
																cssClass="form-control col-md-7 col-xs-12"
																value="%{stat.customerByCustomerCodeLevel2.id}"
																showDownArrow="false" autoComplete="true"
																list="listCustomerLevel2" listKey="id"
																listValue="statisticName +' - '+ customerCode" />
														</div>
													</div>
													<div class="item form-group">
														<label
															class="control-label col-md-3 col-sm-3 col-xs-12"
															for="cusLevel1.id">Tên cấp 1 <span
															class="required">*</span>
														</label>
														<div class="col-md-6 col-sm-6 col-xs-12">
															<s:select id="cusLevel1.id"
																name="sttCustom.custLevel1Id" showDownArrow="false"
																autoComplete="true"
																value="%{stat.customerByCustomerCodeLevel1.id}"
																cssClass="form-control col-md-7 col-xs-12"
																list="listCustomerLevel1" listKey="id" headerKey="-1"
																headerValue="--"
																listValue="statisticName +' - '+ customerCode" />
														</div>
													</div>

													<div class="ln_solid"></div>
													<div class="form-group">
														<div class="col-md-6 col-md-offset-3">
															<img id="loadingImage" src="images/loading.gif"
																style="display: none" />
															<button id="send" type="submit"
																class="btn btn-success">Kết xuất bảng kê cấp 2</button>
														</div>
													</div>
												</s:form>
											</div>
										</div>
								</div>
							</div>
						</div>
						
					</div>
				</div>

			</div>
		</div>
	</div>
	<s:include value="import_balance_level1.jsp" />
	<s:include value="import_statistic_level1.jsp" />
	<s:include value="import_statistic_level2.jsp" />
	<!-- footer content -->
	<s:include value="footer.jsp"></s:include>
	<!-- /footer content -->
</div>

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
<!-- Datatables -->
<script src="js/jquery.dataTables.min.js"></script>
<!-- daterangepicker -->
<script type="text/javascript" src="js/moment.min2.js"></script>
<script type="text/javascript" src="js/datepicker/daterangepicker.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#fromDate,#toDate').daterangepicker({
			singleDatePicker : true,
			calender_style : "picker_2",
			format : 'DD/MM/YYYY',
			showDropdowns : true
		}, function(start, end, label) {
			console.log(start.toISOString(), end.toISOString(), label);
		});
	});
</script>
</body>

</html>