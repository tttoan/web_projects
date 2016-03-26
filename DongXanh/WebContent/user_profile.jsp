<!-- menu prile quick info -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="profile">
	<div class="profile_pic">
		<img src="images/user.png" alt="..." class="img-circle profile_img">
	</div>
	<div class="profile_info">
		<span>Xin chào,</span>
		<h2 id="profile_fullname"><s:property value="%{userSes.fullName}" /> </h2>
	</div>
</div>
<!-- /menu prile quick info -->

<br />