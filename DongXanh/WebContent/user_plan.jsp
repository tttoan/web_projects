<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<%@ include file="user_profile.jsp"%>
<%@ include file="menu.jsp"%>

<!-- page content -->
<div class="right_col" role="main">

	<div class="page-title">
		<div class="title_left">
			<h3>Lịch Công Tác Tuần</h3>
		</div>
	</div>
	<div class="clearfix"></div>
	<s:url action="export_event" var="exportEventURL">
		<%-- 		<s:param name="custId" value="%{id}"></s:param> --%>
	</s:url>

	<table style="width: 600px">
		<tr>
			<td width="200px" valign="middle"><s:a href="%{exportEventURL}"
					cssClass="btn btn-info btn-xs">
					<i class="fa fa-exchange"></i> Kết xuất </s:a></td>
			<td width="100px" valign="middle"><label for="emp_id">Nhân
					viên TT: </label></td>
			<td valign="top"><s:form name="userPlanForm"
					class="form-horizontal form-label-left">
					<s:select id="emp_id" name="emp_id" style="padding-left:10px"
						onchange="onUserPlanChange()" showDownArrow="false"
						autoComplete="true" disabled="%{isPermissionAccept}"
						list="listEmployee" listKey="id"
						listValue="fullName +' - '+ userName"
						value="getSelectedUserPlan().id" />
				</s:form></td>
		</tr>
	</table>

	<div class="content" id="content">
		<div class="scheduler" id="scheduler">
			<s:property escapeHtml="false" value="messageStore.scheduler" />
		</div>
	</div>

	<!-- footer content -->
	<%@ include file="footer.jsp"%>
	<!-- /footer content -->
</div>
</div>

</div>

<style>
.scheduler > div {
    border: 2px solid #cecece;
}
</style>


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

<script type="text/javascript">
	function onUserPlanChange() {
		var type = "UserPlan?emp_id="+document.getElementById('emp_id').value;
		//alert(type);
		document.userPlanForm.action = type;
		document.userPlanForm.submit();
	}
</script>

</body>
</html>