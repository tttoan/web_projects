<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- Using Struts2 Tags in JSP --%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%-- <s:set var="rId">${param.role_id}</s:set> --%>
<%-- <s:if test="%{#rId == 1}"> --%>
	<li><a><i class="fa fa-user"></i> Nhân Viên <span
			class="fa fa-chevron-down"></span></a>
		<ul class="nav child_menu" style="display: none">
			<li><s:url action="move_to_add_employee" var="maeURL"></s:url> <s:a
					href="%{maeURL}"> Thêm mới </s:a></li>
			<li><s:url action="list_employee" var="leURL" /> <s:a
					href="%{leURL}" theme="bootstrap"> Danh sách </s:a></li>
		</ul></li>
<%-- </s:if> --%>