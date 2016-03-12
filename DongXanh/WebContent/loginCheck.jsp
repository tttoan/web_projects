<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:if test="#session.logined != 'true'">
<%-- 	<jsp:forward page="login.jsp"></jsp:forward> --%>
</s:if>