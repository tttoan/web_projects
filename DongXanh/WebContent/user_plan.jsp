<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<%@ include file="user_profile.jsp"%>
<%@ include file="menu.jsp"%>

<!-- page content -->
<div class="right_col" role="main">
	<s:url action="export_event" var="exportEventURL">
<%-- 		<s:param name="custId" value="%{id}"></s:param> --%>
	</s:url>
	<s:a href="%{exportEventURL}" cssClass="btn btn-info btn-xs">
		<i class="fa fa-exchange"></i> Kết xuất </s:a>
	<div class="content" id="content">
		<div class="scheduler" id="scheduler">
			<s:property escape="false" value="messageStore.scheduler" />
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
<!-- bootstrap progress js -->
<script src="js/progressbar/bootstrap-progressbar.min.js"></script>
<script src="js/nicescroll/jquery.nicescroll.min.js"></script>
<script src="js/custom.js"></script>

<script>
	function resize() {
		var menu = document.getElementById("sidebar-menu");
		var header = document.getElementById("header");
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
</body>
</html>