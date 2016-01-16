<!DOCTYPE html>
<!-- sidebar menu -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
	
<div id="sidebar-menu" class="main_menu_side hidden-print main_menu">

	<div class="menu_section">
		<h3>General</h3>
		<ul class="nav side-menu" >
			<li><a href="index.jsp"><i class="fa fa-home"></i> Trang Chủ </a></li>
			<li><a><i class="fa fa-desktop"></i> Khuyến Mãi <span
					class="fa fa-chevron-down"></span></a>
				<ul class="nav child_menu" style="display: none">
					<li><a href="result_promotion.html"> Khái Quát </a></li>
					<li><a href="list_promotion.html"> Danh Sách </a></li>
					<li><a href="create_promotion.html"> Tạo Mới </a></li>
				</ul></li>
			<li><a><i class="fa fa-edit"></i> Khách Hàng <span
					class="fa fa-chevron-down"></span></a>
				<ul class="nav child_menu" style="display: none" >
					<li>
					<s:url action="list_customer.action" var="lcURL" />
					<s:a href="%{lcURL}" theme="bootstrap" > Danh Sách </s:a>
					</li>
						<li><a href="new_customer.jsp"> Tạo Mới </a></li>
					<li><a href="import_customer.html">Import Excel</a></li>
				</ul></li>

			<li><a><i class="fa fa-table"></i> Bảng Kê <span
					class="fa fa-chevron-down"></span></a>
				<ul class="nav child_menu" style="display: none">
					<li><a href="list_invoice.html" theme="bootstrap" > Danh Sách </a></li>
					<li><a href="create_invoice.html"> Tạo Mới </a></li>
					<li><a href="import_invoice.html">Import Excel</a></li>

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

			</ul>
		</nav>
	</div>

</div>
<!-- /top navigation -->