<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>Processing <s:property value="%{processIndexExcel}" /> of <s:property value="%{totalRecordExcel}" /> dòng</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="css/font-awesome-4.1.0.min.css">
<script src="js/jquery.js"></script>
<script src="js/bootstrap.min.js"></script>
<meta http-equiv="refresh" content="2;<s:url />" />
</head>
<body>
	<div class="container" style="padding-top: 60px;">
		<div class="row">
			<div class="item form-group">
				<label class="control-label col-md-5 col-sm-3 col-xs-12" for=""><span
					class="required"></span> </label>
				<div class="col-md-5 col-sm-6 col-xs-12">
					<img alt="processing" src="images/loading.gif"
						class="col-md-2 col-xs-12">
				</div>
			</div>
			<br>
			<div class="item form-group">
				<label class="control-label col-md-4 col-sm-3 col-xs-12" for=""><span
					class="required"></span> </label>
				<div class="col-md-7 col-sm-6 col-xs-12">
					<label class="col-md-7 col-xs-12">Vui lòng chờ xử lý dữ
						liệu. . . </label>
				</div>
			</div>
		</div>
	</div>
</body>
</html>