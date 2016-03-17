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
				<sx:tabbedpanel id="tabContainer">
					<sx:div label="Bảng kê Cấp 1 ">
						<div class="x_panel">
							<div class="x_content">
								<s:form action="import_statistic_level1" method="post"
									theme="bootstrap" enctype="multipart/form-data"
									cssClass="form-horizontal form-label-left">
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
											for="uploadLevel1">Chọn bảng kê <span
											class="required"></span></label>
										<div class="col-md-5 col-sm-6 col-xs-12">
											<input id="uploadLevel1" type="file" name="upload"
												required="required" class="form-control col-md-7 col-xs-12">
										</div>
									</div>
									<div class="ln_solid"></div>
									<div class="form-group">
										<div class="col-md-6 col-md-offset-3">
											<button id="send1" type="submit" class="btn btn-warning">Cập
												nhật bảng kê Cấp 1</button>
										</div>
									</div>
								</s:form>
							</div>
						</div>
					</sx:div>
					<sx:div label="Bảng kê Cấp 2 ">
						<div class="x_panel">
							<div class="x_content">
								<s:form action="import_statistic_level2" method="post"
									theme="bootstrap" enctype="multipart/form-data"
									cssClass="form-horizontal form-label-left">
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
											for="uploadLevel2">Chọn bảng kê <span
											class="required"></span></label>
										<div class="col-md-5 col-sm-6 col-xs-12">
											<input id="uploadLevel2" type="file" name="upload"
												required="required" class="form-control col-md-7 col-xs-12">
										</div>
									</div>
									<div class="ln_solid"></div>
									<div class="form-group">
										<div class="col-md-6 col-md-offset-3">
											<button id="send2" type="submit" class="btn btn-primary">Cập
												nhật bảng kê Cấp 2</button>
										</div>
									</div>
								</s:form>
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