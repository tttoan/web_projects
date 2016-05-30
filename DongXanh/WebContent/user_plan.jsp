<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<%@ include file="user_profile.jsp"%>
<%@ include file="menu.jsp"%>

<!-- page content -->
<div class="right_col" role="main">
	<div class="">

		<div class="row">
			<div class="col-md-12">
				<div class="x_panel">

					<div class="content" id="content">
						<div class="scheduler" id="scheduler">
							<s:property escape="false" value="messageStore.scheduler" />
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

</div>

<script src="js/bootstrap.min.js"></script>

<script src="js/nprogress.js"></script>
<!-- chart js -->
<script src="js/chartjs/chart.min.js"></script>
<!-- bootstrap progress js -->
<script src="js/progressbar/bootstrap-progressbar.min.js"></script>
<script src="js/nicescroll/jquery.nicescroll.min.js"></script>
<!-- icheck -->
<script src="js/icheck/icheck.min.js"></script>

<script src="js/custom.js"></script>

<script src="js/moment.min.js"></script>
<script src="js/calendar/fullcalendar.min.js"></script>

<script>
	function resize() {
		var menu = document.getElementById("sidebar-menu");
		//var header = document.getElementById("header");
		var content = document.getElementById("content");
		var container = document.getElementById("scheduler");
		
		content.style.width = (document.body.offsetWidth - menu.offsetWidth) + "px";
		var height = document.body.offsetHeight ;//- header.offsetHeight + 11;
		content.style.height = height + "px";
		container.style.height = (height - 120) + "px";

		if (scheduler)
			scheduler.setCurrentView();
	};
	window.onload = resize;
	window.onresize = resize;
</script>

<style>
	
		.sample {
			width: 900px;
			height: 80px;
			margin-bottom: 10px;
		}
		.sample .name {
			width: 32%;
			height: 100%;
			font-family: Arial;
			font-size: 24px;
			color: #a54a4a;
			float: left;
			padding: 4px;
			padding-right: 20px;
			box-sizing: border-box;
			background-image: url("./codebase/demo/delimiter.png");
			background-position: right top;
			background-repeat: no-repeat;
		}
		.sample .dsc {
			width: 60%;
			height: 100%;
			font-family: Tahoma;
			font-size: 14px;
			color: #464646;
			float: left;
			padding: 4px;
			padding-left: 0px;
			box-sizing: border-box;
		}
		.scheduler>div {
			border: 1px solid #cecece;
		}
   </style>

</body>
</html>