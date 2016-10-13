<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<%@ include file="user_profile.jsp"%>
<%@ include file="menu.jsp"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>


<!-- page content -->
<div class="right_col" role="main">

	<div class="clearfix"></div>
	<s:url action="export_event" var="exportEventURL">
		<%-- 		<s:param name="custId" value="%{id}"></s:param> --%>
	</s:url>
	<table style="width: 1080px">
		<tr>
			<%
				Calendar d = Calendar.getInstance();
				int weekOfYear = d.get(Calendar.WEEK_OF_YEAR);
				if(weekOfYear + 12 > 52){
					weekOfYear = weekOfYear + 12 - 52;
				}else{
					weekOfYear = weekOfYear+12;
				}
			%>
			<td width="300px" valign="middle"><div id="div_userplan_WeekOfYear"><h3>Lịch Công Tác Tuần <%=weekOfYear%></h3></div></td>
			<td width="110px" valign="middle"><label for="emp_id">Nhân
					viên TT : </label></td>
			<td valign="middle"><s:form name="userPlanForm"
					class="form-horizontal form-label-left">
					<s:select id="emp_id" name="emp_id" style="padding-left:10px"
						onchange="onUserPlanChange()" showDownArrow="false"
						autoComplete="true" disabled="%{isPermissionAccept}"
						list="listEmployee" listKey="id"
						listValue="fullName +' - '+ userName"
						value="getSelectedUserPlan().id" />
				</s:form></td>
			<td align="right"><s:a href="%{exportEventURL}"
					cssClass="btn btn-info btn-xs">
					<i class="fa fa-exchange"></i> Kết xuất </s:a></td>
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
.scheduler>div {
	border: 2px solid #cecece;
}

.dhx_cal_today_button {
	height: 20px;
}

.dhx_cal_tab {
	height: 20px;
}

.dhx_cal_tab.active {
	height: 20px;
}
</style>


<script src="js/bootstrap.min.js"></script>
<script src="js/nprogress.js"></script>
<!-- bootstrap progress js -->
<script src="js/progressbar/bootstrap-progressbar.min.js"></script>
<script src="js/nicescroll/jquery.nicescroll.min.js"></script>
<script src="js/custom.js"></script>
<!-- daterangepicker -->
<script type="text/javascript" src="js/moment.min2.js"></script>


<script>
	scheduler.attachEvent("onBeforeViewChange",
			function(old_mode, old_date, new_mode, new_date) {
				if (new_mode == "log_detail") {
					scheduler.clearAll();
					scheduler.load("events?emp_id="
							+ document.getElementById('emp_id').value
							+ "&curTabName=log_detail");
				} else if (old_mode == "log_detail") {
					scheduler.clearAll();
					scheduler.load("events?emp_id="
							+ document.getElementById('emp_id').value
							+ "&curTabName=");
				}
				return true;
			});
	scheduler.attachEvent("onViewChange", function (new_mode , new_date){
		if (new_mode == "log_detail") {
			setTimeout(function(){
				var x = document.getElementsByClassName("dhx_grid_v_border");
				if(x.length > 0 && x.length < 6){
					x[0].style.left = 163+"px";
					x[1].style.left = 347+"px";
					x[2].style.left = 530+"px";
					x[3].style.left = 630+"px";
					x[4].style.left = 715+"px";
				}

			},100); 
		}
	});
	
	function resize() {
		var menu = document.getElementById("sidebar-menu");
		var header = document.getElementById("header");
		var content = document.getElementById("content");
		var container = document.getElementById("scheduler");

		content.style.width = (document.body.offsetWidth - menu.offsetWidth)
				+ "px";
		var height = document.body.offsetHeight;//- header.offsetHeight + 11;
		content.style.height = height + "px";
		container.style.height = (height - 120) + "px";

		
		if (scheduler)
			scheduler.setCurrentView();
	};

	window.onload = resize;
	window.onresize = resize;


		
	
	scheduler.templates.lightbox_header = function(start, end, ev) {
		return "Tạo công tác";
	};
	scheduler.templates.event_bar_date = function(start,end,ev){
		if(ev.typeOfDay == "1"){
		return "S : ";
			}
		 return "C : ";
	};
</script>

<script type="text/javascript">
	function onUserPlanChange() {
				var type = "UserPlan?emp_id="+document.getElementById('emp_id').value + "&curTabName=";
				document.userPlanForm.action = type;
				document.userPlanForm.submit();
// 		scheduler.clearAll();
// 		scheduler.load("events?emp_id="
// 				+ document.getElementById('emp_id').value + "&curTabName=");
	}
</script>

</body>
</html>