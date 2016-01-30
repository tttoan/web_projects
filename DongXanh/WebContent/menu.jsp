<!DOCTYPE html>
<!-- sidebar menu -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<div id="sidebar-menu" class="main_menu_side hidden-print main_menu">

	<div class="menu_section">
		<h3>General</h3>
		<ul class="nav side-menu">
			<li><a href="index.jsp"><i class="fa fa-home"></i> Trang Chủ
			</a></li>
			<li><a><i class="fa fa-desktop"></i> Khuyến Mãi <span
					class="fa fa-chevron-down"></span></a>
				<ul class="nav child_menu" style="display: none">
					<li><a href="result_promotion.html"> Khái Quát </a></li>
					<li><a href="list_promotion.html"> Danh Sách </a></li>
					<li><a href="create_promotion.html"> Tạo Mới </a></li>
				</ul>
			</li>
			<s:include value="set_user.jsp">
            	<s:param name="role_id"><s:property value="%{user.role.id}"/></s:param>
        	</s:include>
			
			<li><a><i class="fa fa-edit"></i> Khách Hàng <span
					class="fa fa-chevron-down"></span></a>
				<ul class="nav child_menu" style="display: none">
					<li><s:url action="list_customer.action" var="lcURL" /> <s:a
							href="%{lcURL}" theme="bootstrap"> Danh Sách </s:a></li>
					<li><a href="new_customer.jsp"> Tạo Mới </a></li>
					<li><a href="import_customer.jsp">Import Excel</a></li>
				</ul></li>

			<li><a><i class="fa fa-table"></i> Bảng Kê <span
					class="fa fa-chevron-down"></span></a>
				<ul class="nav child_menu" style="display: none">
					<li><a href="list_invoice.html" theme="bootstrap"> Danh
							Sách </a></li>
					<li><a href="new_invoice.jsp"> Tạo Mới </a></li>
					<li><a href="import_invoice.jsp">Import Excel</a></li>

				</ul></li>

			<li><a><i class="fa fa-windows"></i> Lịch công tác <span
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
					aria-expanded="false"> <img src="images/img.jpg" alt="">tttoan
						<span class=" fa fa-angle-down"></span>
				</a>
					<ul
						class="dropdown-menu dropdown-usermenu animated fadeInDown pull-right">
						<li><a href="profile.html"> Profile</a></li>

						<li><a href="login.jsp"><i
								class="fa fa-sign-out pull-right"></i> Log Out</a></li>
					</ul></li>

				<!-- /customer notice -->

				<li role="presentation" class="dropdown"><a href="javascript:;"
					class="dropdown-toggle info-number" data-toggle="dropdown"
					aria-expanded="false"> <i class="fa fa-envelope-o"></i> <span
						class="badge bg-green">6</span>
				</a>
					<ul id="menu1"
						class="dropdown-menu list-unstyled msg_list animated fadeInDown"
						role="menu">
						<li><a> <span class="image"> <img
									src="images/img.jpg" alt="Profile Image" />
							</span> <span> <span>Khách hàng A</span> <span class="time">hôm
										nay</span>
							</span> <span class="message"> Hôm nay là sinh nhật của khách
									hàng A, hãy gửi lời chúc mừng tới họ nhé. </span>
						</a></li>
						<li><a> <span class="image"> <img
									src="images/img.jpg" alt="Profile Image" />
							</span> <span> <span>Khách hàng B</span> <span class="time">cách
										1 ngày</span>
							</span> <span class="message"> Ngày mai là sinh nhật của khách
									hàng B, hãy gửi lời chúc mừng tới họ nhé. </span>
						</a></li>
						<li><a> <span class="image"> <img
									src="images/img.jpg" alt="Profile Image" />
							</span> <span> <span>Khách hàng XYZ</span> <span class="time">còn
										1 tuần</span>
							</span> <span class="message"> Còn 1 tuần là sinh sinh nhật khách
									hàng XYZ, hãy gửi lời chúc mừng tới họ nhé. </span>
						</a></li>
						<li>
							<div class="text-center">
								<a> <strong>Xem tất cả các thông báo</strong> <i
									class="fa fa-angle-right"></i>
								</a>
							</div>
						</li>
					</ul></li>

			</ul>
		</nav>
	</div>

</div>
<!-- /top navigation -->