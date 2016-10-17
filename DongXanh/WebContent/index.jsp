<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<%@ include file="user_profile.jsp"%>
<%@ include file="menu.jsp"%>


<!-- page content -->
<div class="right_col" role="main">

	<br />
	<div class="">

		<div class="row top_tiles">
			<div class="animated flipInY col-lg-3 col-md-3 col-sm-6 col-xs-12">
				<div class="tile-stats">
					<div class="icon">
						<i class="fa fa-check-circle"></i>
					</div>
					<div id="statistic1_p" class="count">0</div>

					<h3>Khuyến mãi đang diễn ra</h3>
					<p>
						<s:url id="resultURL1" action="listPromotionResultAction">
							<s:param name="type" value="1"></s:param>
						</s:url>
						<s:if test="%{userSes.role.roleId == 1 || userSes.role.roleId == 3}">
							<s:a href="%{resultURL1}" class="btn btn-info btn-xs"> Xem chi tiết... </s:a>
						</s:if>
					</p>
				</div>
			</div>
			<div class="animated flipInY col-lg-3 col-md-3 col-sm-6 col-xs-12">
				<div class="tile-stats">
					<div class="icon">
						<i class="fa fa-clock-o"></i>
					</div>
					<div id="statistic2_p" class="count">0</div>

					<h3>Khuyến mãi sắp diễn ra</h3>
					<p>
						<s:url id="resultURL2" action="listPromotionResultAction">
							<s:param name="type" value="2"></s:param>
						</s:url>
						<s:if test="%{userSes.role.roleId == 1 || userSes.role.roleId == 3}">
							<s:a href="%{resultURL2}" class="btn btn-info btn-xs"> Xem chi tiết... </s:a>
						</s:if>
					</p>
				</div>
			</div>
			<div class="animated flipInY col-lg-3 col-md-3 col-sm-6 col-xs-12">
				<div class="tile-stats">
					<div class="icon">
						<i class="fa fa-ban"></i>
					</div>
					<div id="statistic3_p" class="count">0</div>

					<h3>Khuyến mãi vừa kết thúc</h3>
					<p>
						<s:url id="resultURL3" action="listPromotionResultAction">
							<s:param name="type" value="3"></s:param>
						</s:url>
						<s:if test="%{userSes.role.roleId == 1 || userSes.role.roleId == 3}">
							<s:a href="%{resultURL3}" class="btn btn-info btn-xs"> Xem chi tiết... </s:a>
						</s:if>
					</p>
				</div>
			</div>
			<div class="animated flipInY col-lg-3 col-md-3 col-sm-6 col-xs-12">
				<div class="tile-stats">
					<div class="icon">
						<i class="fa fa-times-circle"></i>
					</div>
					<div id="statistic4_p" class="count">0</div>

					<h3>Khuyến mãi đã kết thúc</h3>
					<p>
						<s:url id="resultURL4" action="listPromotionResultAction">
							<s:param name="type" value="4"></s:param>
						</s:url>
						<s:if test="%{userSes.role.roleId == 1 || userSes.role.roleId == 3}">
							<s:a href="%{resultURL4}" class="btn btn-info btn-xs"> Xem chi tiết... </s:a>
						</s:if>
					</p>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-md-12">
				<div class="x_panel">
					<div class="x_title">
						<h2>Hoạt động</h2>
						<div class="clearfix"></div>
					</div>
					<div class="x_content">

						<div class="col-md-9 col-sm-12 col-xs-12">
							<div>
								<div class="x_title">
									<h2>Thông báo</h2>
									<div class="clearfix"></div>
								</div>
							</div>

							<!-- start recent activity -->
							<ul class="list-unstyled top_profiles scroll-view">
									
									<li class='media event'>
										Không có dữ liệu
									</li>
									
								</ul>
						</div>


						<div class="col-md-3 col-sm-12 col-xs-12">
							<div>
								<div id="divCussBirthday" class="x_title">
									<h2>Chăm sóc khách hàng</h2>
									<div class="clearfix"></div>
								</div>
								<ul id="ulCussBirthday" class="list-unstyled top_profiles scroll-view">
									
									<li class='media event'><a
										class='pull-left border-aero profile_thumb'> <i
											class='fa fa-user aero'></i>
									</a>
										Không có dữ liệu
									</li>
									
								</ul>
							</div>
						</div>

					</div>
				</div>
			</div>
		</div>

		<!-- footer content -->
		<%@ include file="footer.jsp"%>
		<!-- /footer content -->
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
<script src="js/nicescroll/jquery.nicescroll.min.js"></script>

<!-- chart js -->
<script src="js/chartjs/chart.min.js"></script>
<!-- bootstrap progress js -->
<script src="js/progressbar/bootstrap-progressbar.min.js"></script>
<!-- icheck -->
<script src="js/icheck/icheck.min.js"></script>
<!-- daterangepicker -->
<script type="text/javascript" src="js/moment.min2.js"></script>
<script type="text/javascript" src="js/datepicker/daterangepicker.js"></script>
<!-- sparkline -->
<script src="js/sparkline/jquery.sparkline.min.js"></script>

<script src="js/custom.js"></script>

<!-- flot js -->
<!--[if lte IE 8]><script type="text/javascript" src="js/excanvas.min.js"></script><![endif]-->
<script type="text/javascript" src="js/flot/jquery.flot.js"></script>
<script type="text/javascript" src="js/flot/jquery.flot.pie.js"></script>
<script type="text/javascript" src="js/flot/jquery.flot.orderBars.js"></script>
<script type="text/javascript" src="js/flot/jquery.flot.time.min.js"></script>
<script type="text/javascript" src="js/flot/date.js"></script>
<script type="text/javascript" src="js/flot/jquery.flot.spline.js"></script>
<script type="text/javascript" src="js/flot/jquery.flot.stack.js"></script>
<script type="text/javascript" src="js/flot/curvedLines.js"></script>
<script type="text/javascript" src="js/flot/jquery.flot.resize.js"></script>
<!-- pace -->
<script src="js/pace/pace.min.js"></script>

<script src="Scripts/custom/dashboard.js"></script>

</body>

</html>
