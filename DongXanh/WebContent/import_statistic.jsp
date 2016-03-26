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
				<s:set name="importType" value="%{chooseSubTab}" />
				<sx:tabbedpanel id="tabContainer" selectedTab="%{chooseTab}">
					<sx:div label="Bảng kê Cấp I" id="tab1">
						<div class="x_panel">
							<div class="x_content">
								<sx:tabbedpanel id="tabContainer1" selectedTab="%{chooseSubTab}">
									<sx:div label="Cập nhật" id="subtab1_1">
										<s:form action="import_statistic_level_one" namespace="/"
											method="post" theme="bootstrap" enctype="multipart/form-data"
											cssClass="form-horizontal form-label-left">
											<s:if test="%{#importType=='subtab1_1'}">
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
											<div class="ln_solid"></div>
											<div class="item form-group">
												<label class="col-md-3 col-sm-3 col-xs-12"
													style="text-align: right;" for="uploadLevel1">Chọn
													bảng kê <span class="required"></span>
												</label>
												<div class="col-md-5 col-sm-6 col-xs-12">
													<input id="uploadLevel1" type="file" name="upload"
														required="required" class="col-md-7 col-xs-12">
												</div>
											</div>
											<div class="ln_solid"></div>
											<div class="form-group">
												<div class="col-md-6 col-md-offset-3">
													<button id="send1" type="submit" class="btn btn-warning">Cập
														nhật bảng kê Cấp I</button>
												</div>
											</div>
										</s:form>
									</sx:div>
									<sx:div label="Kết xuất" id="subtab1_2">
										<div class="col-md-12 col-sm-12 col-xs-12">
											<div class="x_panel">
												<div class="x_content"></div>
											</div>
										</div>
									</sx:div>
								</sx:tabbedpanel>
							</div>
						</div>
					</sx:div>
					<sx:div label="Bảng kê Cấp II" id="tab2">
						<div class="x_panel">
							<div class="x_content">
								<sx:tabbedpanel id="tabContainer2" selectedTab="%{chooseSubTab}">
									<sx:div label="Cập nhật" id="subtab2_1">
										<s:form action="import_statistic_level_two" namespace="/"
											method="post" theme="bootstrap" enctype="multipart/form-data"
											cssClass="form-horizontal form-label-left">
											<s:if test="%{#importType=='subtab2_1'}">
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
											<div class="ln_solid"></div>
											<div class="item form-group">
												<label class="col-md-3 col-sm-3 col-xs-12"
													style="text-align: right;" for="uploadLevel2">Chọn
													bảng kê <span class="required"></span>
												</label>
												<div class="col-md-5 col-sm-6 col-xs-12">
													<input id="uploadLevel2" type="file" name="upload"
														required="required" class="col-md-7 col-xs-12">
												</div>
											</div>
											<div class="ln_solid"></div>
											<div class="form-group">
												<div class="col-md-6 col-md-offset-3">
													<button id="send2" type="submit" class="btn btn-primary">Cập
														nhật bảng kê Cấp II</button>
												</div>
											</div>
										</s:form>
									</sx:div>
									<sx:div label="Kết xuất" id="subtab2_2">
										<div class="col-md-12 col-sm-12 col-xs-12">
											<div class="x_panel">
												<div class="x_content">
													<s:url var="fileDownload" action="export_statistic"></s:url>
													<s:form id="formExport" action="export_statistic" theme="bootstrap"
														method="post" cssClass="form-horizontal form-label-left">
														<s:if test="%{#importType=='subtab2_2'}">
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
															<label class="control-label col-md-3 col-sm-3 col-xs-12"
																for="fromDate">Từ ngày <span class="required">*</span>
															</label>
															<div class="col-md-6 col-sm-6 col-xs-12">
																<sx:datetimepicker id="fromDate"
																	name="sttCustom.fromDate"
																	cssClass="form-control col-md-7 col-xs-12"
																	value="%{'today'}" displayFormat="dd-MM-yyyy" />
															</div>
														</div>
														<div class="item form-group">
															<label class="control-label col-md-3 col-sm-3 col-xs-12"
																for="toDate">Đến ngày <span class="required">*</span>
															</label>
															<div class="col-md-6 col-sm-6 col-xs-12">
																<sx:datetimepicker id="toDate" name="sttCustom.toDate"
																	cssClass="form-control col-md-7 col-xs-12"
																	value="%{'today'}" displayFormat="dd-MM-yyyy" />
															</div>
														</div>
														<div class="item form-group">
															<label class="control-label col-md-3 col-sm-3 col-xs-12"
																for="emp.id">Nhân viên TT <span class="required">*</span>
															</label>
															<div class="col-md-6 col-sm-6 col-xs-12">
																<s:select id="emp_id" name="sttCustom.empId"
																	cssClass="form-control col-md-7 col-xs-12"
																	headerKey="-1"
																	headerValue="-- Chọn nhân viên thị trường --"
																	showDownArrow="false" autoComplete="true"
																	list="listEmployee" value="%{stat.user.id}"
																	listKey="id" listValue="fullName +' - '+ userName" />
															</div>
														</div>
														<div class="item form-group">
															<label class="control-label col-md-3 col-sm-3 col-xs-12"
																for=cusLevel2.id>Tên cấp II <span
																class="required">*</span>
															</label>
															<div class="col-md-6 col-sm-6 col-xs-12">
																<s:url id="url" value="/add_statistic.action" />
																<s:select id="cusLevel2.id"
																	name="sttCustom.custLevel2Id" headerKey="-1"
																	headerValue="-- Chọn khách hàng cấp 2 --"
																	cssClass="form-control col-md-7 col-xs-12"
																	value="%{stat.customerByCustomerCodeLevel2.id}"
																	showDownArrow="false" autoComplete="true"
																	list="listCustomer" listKey="id"
																	listValue="director +' - '+ customerCode" />
															</div>
														</div>
														<div class="item form-group">
															<label class="control-label col-md-3 col-sm-3 col-xs-12"
																for="cusLevel1.id">Tên cấp I <span
																class="required">*</span>
															</label>
															<div class="col-md-6 col-sm-6 col-xs-12">
																<s:select id="cusLevel1.id"
																	name="sttCustom.custLevel1Id" showDownArrow="false"
																	autoComplete="true"
																	value="%{stat.customerByCustomerCodeLevel1.id}"
																	cssClass="form-control col-md-7 col-xs-12"
																	list="listCustomer" listKey="id" headerKey="-1"
																	headerValue="-- Chọn khách hàng cấp 1 --"
																	listValue="director +' - '+ customerCode" />
															</div>
														</div>

														<div class="ln_solid"></div>
														<div class="form-group">
															<div class="col-md-6 col-md-offset-3">
																<img id="loadingImage" src="images/loading.gif" style="display: none" />
																<button type="reset" class="btn btn-success">Reset</button>
																<button id="send" type="submit" class="btn btn-primary">Kết xuất bảng kê Cấp II</button>
															</div>
														</div>
													</s:form>
												</div>
											</div>
										</div>
									</sx:div>
								</sx:tabbedpanel>
							</div>
						</div>
					</sx:div>
				</sx:tabbedpanel>
			
			</div>
		</div>
	</div>
</div>

<!-- footer content -->
<s:include value="footer.jsp"></s:include>
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
<!-- dropzone -->
<script src="js/dropzone/dropzone.js"></script>

</body>

</html>