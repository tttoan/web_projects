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
			<li><a href="index.jsp"><i class="fa fa-home"></i> Trang Chủ
			</a></li>
			<li><a><i class="fa fa-cubes"></i> Khuyến Mãi <span
					class="fa fa-chevron-down"></span></a>
				<ul class="nav child_menu" style="display: none">
					<li><s:url action="promotionResultAction" var="prURL" /> <s:a
							href="%{prURL}" theme="bootstrap"> Kết quả </s:a></li>
					<li><a href="promotion.jsp"> Danh sách </a></li>
					<li><a href="gift.jsp"> Quà tặng </a></li>
					<li><a href="product.jsp"> Sản phẩm </a></li>
				</ul></li>
			<s:include value="menu_employee.jsp">
				<s:param name="role_id">
					<s:property value="%{userSes.role.roleId}" />
				</s:param>
			</s:include>
			<li><a><i class="fa fa-users"></i> Khách Hàng <span
					class="fa fa-chevron-down"></span></a>
				<ul class="nav child_menu" style="display: none">
					<li><s:url action="list_customer.action" var="lcURL" /> <s:a
							href="%{lcURL}" theme="bootstrap"> Danh sách </s:a></li>
					<li><s:url action="move_to_add_customer" var="macURL"></s:url>
						<s:a href="%{macURL}"> Tạo và chỉnh Sửa </s:a></li>
					<li><s:url action="move_to_accept_customer" var="macURL">
						</s:url> <s:a href="%{macURL}">Thao tác excel</s:a></li>
				</ul></li>
			<li><a><i class="fa fa-book"></i> Bảng Kê <span
					class="fa fa-chevron-down"></span></a>
				<ul class="nav child_menu" style="display: none">
					<li><s:url action="list_statistic" var="liURL" /> <s:a
							href="%{liURL}" theme="bootstrap"> Danh sách </s:a></li>
					<li><s:url action="move_to_add_statistic.action" var="maiURL">
						</s:url> <s:a href="%{maiURL}"> Tạo và chỉnh sửa </s:a></li>
					<li><s:url action="move_to_accept_statistic" var="masURL">
						</s:url> <s:a href="%{masURL}">Thao tác excel</s:a></li>
				</ul></li>
			<li><a><i class="fa fa-bar-chart"></i> Báo cáo <span
					class="fa fa-chevron-down"></span></a>
				<ul class="nav child_menu" style="display: none">
					<li><a href="invoice_comparison.jsp"> So sánh bảng kê</a></li>
					<li><a href="revenues_comparison.jsp"> So sánh doanh số cùng kỳ </a></li>
					<li><a href="revenues_cus1.jsp"> Doanh số câp1 </a></li>
					<li><a href="revenues_cus2.jsp"> Doanh số cấp2</a></li>
					<li><a href="revenues_sellman.jsp"> Doanh số NVTT</a></li>
			</ul></li>
			<li><a><i class="fa fa-calendar"></i> Lịch công tác <span
					class="fa fa-chevron-down"></span></a>
				<ul class="nav child_menu" style="display: none">
					<li><a href="user_plan.jsp">Xem lịch của tôi</a></li>
					<li><a href="user_plan.jsp">Tạo mới</a></li>

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
				<li class=""><a href="javascript:;" id="profile_username"
					class="user-profile dropdown-toggle" data-toggle="dropdown"
					aria-expanded="false"> <img src="images/user.png" alt="">
						<s:property value="%{userSes.userName}" /> <span
						class=" fa fa-angle-down"></span>
				</a>
					<ul
						class="dropdown-menu dropdown-usermenu animated fadeInDown pull-right">
						<li><a href="#"> Thông tin cá nhân</a></li>
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