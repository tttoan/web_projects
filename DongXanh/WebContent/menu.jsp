<!DOCTYPE html>
<!-- sidebar menu -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
	<div class="menu_section">
		<hr />
		<hr />
		<ul class="nav side-menu">
			<li><s:url action="homeAction" var="homeURL" /> <s:a
					href="%{homeURL}" theme="bootstrap"> Trang chủ </s:a></li>
					<s:if test="%{userSes.role.roleId == 1 || userSes.role.roleId == 3}">
			<li><a><i class="fa fa-cubes"></i> Khuyến Mãi <span
					class="fa fa-chevron-down"></span></a>
				<ul class="nav child_menu" style="display: none">
					<li><s:url action="promotionResultAction" var="p_resultURL" />
						<s:a href="%{p_resultURL}" theme="bootstrap"> Kết quả </s:a></li>
					<li><s:url action="promotions" var="promotionURL" /> <s:a
							href="%{promotionURL}" theme="bootstrap"> Danh sách </s:a></li>
					<li><s:url action="gifts" var="giftURL" /> <s:a
							href="%{giftURL}" theme="bootstrap"> Quà tặng </s:a></li>
					<li><s:url action="products" var="productURL" /> <s:a
							href="%{productURL}" theme="bootstrap"> Sản phẩm </s:a></li>
				</ul>
			</li>
			
				<s:include value="menu_employee.jsp">
					<s:param name="role_id">
						<s:property value="%{userSes.role.roleId}" />
					</s:param>
				</s:include>
			</s:if>
			<li><a><i class="fa fa-users"></i> Khách Hàng <span
					class="fa fa-chevron-down"></span></a>
				<ul class="nav child_menu" style="display: none">
					<li><s:url action="move_to_add_customer" var="macURL"></s:url>
						<s:a href="%{macURL}"> Thêm mới </s:a></li>
					<li><s:url action="list_customer.action" var="lcURL" /> <s:a
							href="%{lcURL}"> Danh sách </s:a></li>
					<s:if test="%{userSes.role.roleId == 1 || userSes.role.roleId == 3}">
						<li><s:url action="move_to_assign_customer" var="assignCusURL"></s:url>
							<s:a href="%{assignCusURL}"> Phân công theo dõi </s:a></li>
					</s:if>
				</ul></li>
				<s:if test="%{userSes.role.roleId == 1 || userSes.role.roleId == 3}">
			<li><a><i class="fa fa-book"></i> Bảng Kê <span
					class="fa fa-chevron-down"></span></a>
				<ul class="nav child_menu" style="display: none">
					<li><s:url action="move_to_add_statistic.action" var="maiURL">
						</s:url> <s:a href="%{maiURL}"> Thêm mới </s:a></li>
					<li><s:url action="list_statistic" var="liURL" /> <s:a
							href="%{liURL}" theme="bootstrap"> Danh sách </s:a></li>
					<li><s:url action="move_to_accept_statistic" var="masURL">
						</s:url> <s:a href="%{masURL}">Thao tác excel</s:a></li>
				</ul></li>
			
				<li><a><i class="fa fa-bar-chart"></i> Báo cáo <span
						class="fa fa-chevron-down"></span></a>
					<ul class="nav child_menu" style="display: none">
						<li><s:url action="move_to_compare_statistic" var="csURL" />
							<s:a href="%{csURL}" theme="bootstrap"> So sánh bảng kê </s:a></li>
						<li><s:url action="showReportRevenues1" var="csURL1" /> <s:a
								href="%{csURL1}" theme="bootstrap"> So sánh doanh số cùng kỳ </s:a></li>
						<li><s:url action="showReportRevenues2" var="csURL2" /> <s:a
								href="%{csURL2}" theme="bootstrap"> Doanh số câp1 </s:a></li>
						<li><s:url action="showReportRevenues3" var="csURL3" /> <s:a
								href="%{csURL3}" theme="bootstrap"> Doanh số cấp2 </s:a></li>
						<li><s:url action="showReportRevenues4" var="csURL4" /> <s:a
								href="%{csURL4}" theme="bootstrap"> Doanh số NVTT </s:a></li>
					</ul></li>
			</s:if>
			<li><a><i class="fa fa-calendar"></i> Lịch công tác <span
					class="fa fa-chevron-down"></span></a>
				<ul class="nav child_menu" style="display: none">
					<li><s:url action="UserPlan" var="getUserPlanURL" /> <s:a
							href="%{getUserPlanURL}" theme="bootstrap"> Lên lịch tuần </s:a></li>
					<s:if test="%{userSes.role.roleId == 1 || userSes.role.roleId == 3}">
						<li><s:url action="OpenUserPlanStatisticAction" var="userPlanStatistic" /> <s:a
							href="%{userPlanStatistic}" theme="bootstrap"> Thống kê lịch tiếp xúc </s:a></li>
					</s:if>
					<li><s:url action="OpenUserPlanGeneralAction" var="userPlanGeneral" /> <s:a
							href="%{userPlanGeneral}" theme="bootstrap"> Kết quả công tác tuần </s:a></li>
					<li><s:url action="OpenUserPlanDetailAction" var="userPlanDetail" /> <s:a
							href="%{userPlanDetail}" theme="bootstrap"> Báo cáo chi tiết </s:a></li>
					<s:if test="%{userSes.role.roleId == 1 || userSes.role.roleId == 3}">
						<li><s:url action="PlanHistoryGeneralAction" var="userPlanHistory" /> <s:a
							href="%{userPlanHistory}" theme="bootstrap"> Lịch sử thay đổi</s:a></li>
					</s:if>
				</ul></li>
		</ul>
	</div>

</div>
<!-- /sidebar menu -->

<!-- /menu footer buttons -->
<div class="sidebar-footer hidden-small">
	<a data-toggle="tooltip" data-placement="top" title="Settings"> <span
		class="glyphicon glyphicon-cog" aria-hidden="true"></span>
	</a> <a data-toggle="tooltip" data-placement="top" title="FullScreen">
		<span class="glyphicon glyphicon-fullscreen" aria-hidden="true"></span>
	</a> <a data-toggle="tooltip" data-placement="top" title="Lock"> <span
		class="glyphicon glyphicon-eye-close" aria-hidden="true"></span>
	</a> <a data-toggle="tooltip" data-placement="top" title="Logout"> <span
		class="glyphicon glyphicon-off" aria-hidden="true"></span>
	</a>
</div>
<!-- /menu footer buttons -->
</div>
</div>

<!-- top navigation -->
<div class="top_nav">
	<div class="nav_menu">
		<nav class="" role="navigation">
			<div class="nav toggle">
				<a id="menu_toggle"  style="cursor:pointer"><i class="fa fa-bars"></i></a>
			</div>
			<ul class="nav navbar-nav navbar-right">
				<li class=""><a href="javascript:;" id="profile_username"
					class="user-profile dropdown-toggle" data-toggle="dropdown"
					aria-expanded="false"> <img src="images/user.png" alt="">
						<s:property value="%{userSes.userName}" /> <span
						class=" fa fa-angle-down"></span>
				</a>
					<ul
						class="dropdown-menu dropdown-usermenu animated fadeInDown pull-right">
						<s:url action="move_to_add_employee" var="editUserURL">
							<s:param name="userId" value="%{userSes.id}"></s:param>
						</s:url>
						<li><s:a href="%{editUserURL}"> Thông tin cá nhân</s:a></li>
						<s:url action="logout_wanted.action" var="loURL"></s:url>
						<li><s:a href="%{loURL}">
								<i class="fa fa-sign-out pull-right"></i> Đăng xuất
						</s:a></li>
					</ul></li>

				<!-- /customer notice -->

				<li role="presentation" class="dropdown"><a href="javascript:;"
					class="dropdown-toggle info-number" data-toggle="dropdown"
					aria-expanded="false"> <i class="fa fa-envelope-o"></i> <span
						class="badge bg-green"> <s:property
								value="listBirthCus.size" />

					</span>
				</a>
					<ul id="menu1"
						class="dropdown-menu list-unstyled msg_list animated fadeInDown"
						role="menu">
						<s:iterator value="listBirthCus">
							<li><s:url action="find_customer" var="findURL">
									<s:param name="customerId" value="%{id}"></s:param>
								</s:url> <s:a href="%{findURL}">
									<span class="image"> <img src="images/img.jpg"
										alt="Profile Image" />
									</span>
									<span> <span><s:property value="%{director}" /></span>
										<span class="time">hôm nay</span>
									</span>
									<span class="message"> Hôm nay là sinh nhật của <s:property
											value="%{director}" />, hãy gửi lời chúc mừng tới họ nhé.
									</span>
								</s:a></li>
						</s:iterator>
					</ul></li>
			</ul>
		</nav>
	</div>

</div>
<!-- /top navigation -->