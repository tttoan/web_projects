<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- Using Struts2 Tags in JSP --%>
<%@ taglib uri="/struts-tags" prefix="s"%>
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
					<sx:div label="Cấp 1 ">
						<div class="x_panel">
							<div class="x_content">
								<s:form action="import_statistic_level1" method="post"
									enctype="multipart/form-data"
									cssClass="form-horizontal form-label-left">
									<div class="item form-group">
										<div class="col-md-6 col-sm-6 col-xs-12">
											<s:file name="upload"></s:file>
										</div>
									</div>
									<div class="item form-group">
										<div class="col-md-6 col-md-offset-3">
											<s:submit id="sends" value="Submit" class="btn btn-success" />
										</div>
									</div>
								</s:form>
							</div>
						</div>
					</sx:div>
					<sx:div label="Cấp 2 ">
						<div class="x_panel">
							<div class="x_content">
								<s:form action="import_statistic_level2" method="post"
									enctype="multipart/form-data"
									cssClass="form-horizontal form-label-left">
									<div class="item form-group">
										<div class="col-md-6 col-sm-6 col-xs-12">
											<s:file name="upload"></s:file>
										</div>
									</div>
									<div class="item form-group">
										<div class="col-md-6 col-md-offset-3">
											<s:submit id="sends" value="Submit" class="btn btn-info" />
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