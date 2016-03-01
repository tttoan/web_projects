<!DOCTYPE html>
<!-- sidebar menu -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
	<div class="menu_section">
		<h3>Admin</h3>
		<ul class="nav side-menu">
			<li><a href="index.jsp"><i class="fa fa-home"></i> Trang Chủ
			</a></li>
			<li><a><i class="fa fa-desktop"></i> Khuyến Mãi <span
					class="fa fa-chevron-down"></span></a>
				<ul class="nav child_menu" style="display: none">
					<li><a href="result_promotion.html"> Khái Quát </a></li>
					<li><a href="list_promotion.html"> Danh Sách </a></li>
					<li><a href="create_promotion.html"> Tạo Mới </a></li>
				</ul></li>
			<s:include value="menu_employee.jsp">
				<s:param name="role_id">
					<s:property value="%{user.role.id}" />
				</s:param>
			</s:include>
			<li><a><i class="fa fa-edit"></i> Khách Hàng <span
					class="fa fa-chevron-down"></span></a>
				<ul class="nav child_menu" style="display: none">
					<li><s:url action="list_customer.action" var="lcURL" /> <s:a
							href="%{lcURL}" theme="bootstrap"> Danh Sách </s:a></li>
					<li><s:url action="move_to_add_customer" var="macURL"></s:url>
						<s:a href="%{macURL}"> Tạo Và Chỉnh Sửa </s:a></li>
				</ul></li>
			<li><a><i class="fa fa-table"></i> Bảng Kê <span
					class="fa fa-chevron-down"></span></a>
				<ul class="nav child_menu" style="display: none">
					<li><s:url action="list_statistic" var="liURL" /> <s:a
							href="%{liURL}" theme="bootstrap"> Danh Sách </s:a></li>
					<li><s:url action="move_to_add_statistic.action" var="maiURL">
						</s:url> <s:a href="%{maiURL}"> Thêm Và Chỉnh Sửa </s:a></li>
					<li><a href="import_statistic.jsp">Thao tác Excel</a></li>
				</ul></li>
			<li><a><i class="fa fa-calendar"></i> Lịch công tác <span
					class="fa fa-chevron-down"></span></a>
				<ul class="nav child_menu" style="display: none">
					<li><a href="working_schedule.html">Xem lịch của tôi</a></li>
					<li><a href="create_working_schedule.html">Tạo mới</a></li>

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
				<a id="menu_toggle"><i class="fa fa-bars"></i></a>
			</div>
			<ul class="nav navbar-nav navbar-right">
				<li class=""><a href="javascript:;"
					class="user-profile dropdown-toggle" data-toggle="dropdown"
					aria-expanded="false"> <img src="images/user.png" alt=""><s:property value="%{user.userName}" />
						<span class=" fa fa-angle-down"></span>
				</a>
					<ul
						class="dropdown-menu dropdown-usermenu animated fadeInDown pull-right">
						<li><a href="profile.html"> Thông tin cá nhân</a></li>
						<li><a href="login.jsp"><i
								class="fa fa-sign-out pull-right"></i> Đăng xuất</a></li>
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
									<span> <span><s:property value="%{director}" /></span> <span
										class="time">hôm nay</span>
									</span>
									<span class="message"> Hôm nay là sinh nhật của <s:property value="%{director}" />, hãy
										gửi lời chúc mừng tới họ nhé. </span>
								</s:a></li>
						</s:iterator>
					</ul></li>
			</ul>
		</nav>
	</div>

</div>
<!-- /top navigation -->