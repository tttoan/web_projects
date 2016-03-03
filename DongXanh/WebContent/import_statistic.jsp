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
				<div class="panel with-nav-tabs panel-default">
					<div class="panel-heading">
						<ul class="nav nav-tabs">
							<li class="active"><a href="#tab1default" data-toggle="tab">Cấp
									1</a></li>
							<li><a href="#tab2default" data-toggle="tab">Cấp 2</a></li>
						</ul>
					</div>
					<div class="panel-body">
						<div class="tab-content">
							<div class="tab-pane fade in active" id="tab1default">
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
							</div>
							<div class="tab-pane fade" id="tab2default">
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
							</div>
						</div>
					</div>
					<!--                         <div class="col-md-12 col-sm-12 col-xs-12"> -->
					<!--                             <div class="x_panel"> -->
					<!--                                 <div class="x_title"> -->
					<!--                                     <h2>Dropzone multiple file uploader</h2> -->
					<!--                                     <ul class="nav navbar-right panel_toolbox"> -->
					<!--                                         <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a> -->
					<!--                                         </li> -->
					<!--                                         <li class="dropdown"> -->
					<!--                                             <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="fa fa-wrench"></i></a> -->
					<!--                                             <ul class="dropdown-menu" role="menu"> -->
					<!--                                                 <li><a href="#">Settings 1</a> -->
					<!--                                                 </li> -->
					<!--                                                 <li><a href="#">Settings 2</a> -->
					<!--                                                 </li> -->
					<!--                                             </ul> -->
					<!--                                         </li> -->
					<!--                                         <li><a class="close-link"><i class="fa fa-close"></i></a> -->
					<!--                                         </li> -->
					<!--                                     </ul> -->
					<!--                                     <div class="clearfix"></div> -->
					<!--                                 </div> -->
					<!--                                 <div class="x_content"> -->

					<!--                                     <p>Drag multiple files to the box below for multi upload or click to select files. This is for demonstration purposes only, the files are not uploaded to any server.</p> -->
					<%--                                     <s:form action="list_statistic" cssClass="dropzone" style="border: 1px solid #e5e5e5; height: 300px; " theme="bootstrap" > --%>
					<%-- 									<s:div cssClass="fallback"> --%>
					<%--     									<s:file name="upload" label="select a file" ></s:file> --%>
					<%--  									 </s:div> --%>
					<%--                                     </s:form> --%>

					<!--                                     <br /> -->
					<!--                                     <br /> -->
					<!--                                     <br /> -->
					<!--                                     <br /> -->
					<!--                                 </div> -->
					<!--                             </div> -->
					<!--                         </div> -->
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