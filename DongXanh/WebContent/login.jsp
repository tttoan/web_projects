<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- Using Struts2 Tags in JSP --%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html lang="en">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Meta, title, CSS, favicons, etc. -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Promotion Management</title>

<!-- Bootstrap core CSS -->

<link href="css/bootstrap.min.css" rel="stylesheet">

<link href="fonts/css/font-awesome.min.css" rel="stylesheet">
<link href="css/animate.min.css" rel="stylesheet">

<!-- Custom styling plus plugins -->
<link href="css/custom.css" rel="stylesheet">
<link href="css/icheck/flat/green.css" rel="stylesheet">


<script src="js/jquery.min.js"></script>

<!--[if lt IE 9]>
        <script src="../assets/js/ie8-responsive-file-warning.js"></script>
        <![endif]-->

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
<link rel="shortcut icon" type="image/x-icon" href="./images/logo_dongxanh.png" />
</head>

<body style="background: #F7F7F7;">

	<div class="">
		<a class="hiddenanchor" id="toregister"></a> <a class="hiddenanchor"
			id="tologin"></a>

		<div id="wrapper">
			<div id="login" class="animate form">
				<section class="login_content">
					<s:form action="login" method="post" theme="bootstrap">
						<h1>ĐỒNG XANH</h1>
						<s:if test="hasActionErrors()">
							<div class="errors">
								<s:actionerror escape="true" />
							</div>
						</s:if>
						<div>
							<s:textfield name="userName" cssClass="form-control"
								placeholder="Tên tài khoản" />
						</div>
						<div>
							<s:password name="password" cssClass="form-control"
								placeholder="Mật khẩu" />
						</div>

						<div>
							<s:submit class="btn btn-default submit" value="Đăng nhập" />
<!-- 							<a class="reset_pass" href="#">Quên mật khẩu?</a> -->
						</div>
						<div class="clearfix"></div>
<!-- 						<div class="separator"> -->
<!-- 							<p class="change_link"> -->
<!-- 								New to site? <a href="#toregister" class="to_register"> Tạo -->
<!-- 									tài khoản </a> -->
<!-- 							</p> -->
<!-- 							<div class="clearfix"></div> -->
<!-- 						</div> -->
					</s:form>
					<!-- form -->
				</section>
				<!-- content -->
			</div>
			<div id="register" class="animate form">
				<section class="login_content">
					<s:form theme="bootstrap">
						<h1>ĐỒNG XANH</h1>
						<div>
							<s:textfield name="userName" class="form-control"
								placeholder="Tên tài khoản" required="required" />
						</div>
						<div>
							<s:textfield name="email" type="email" class="form-control"
								placeholder="Email" required="required" />
						</div>
						<div>
							<s:textfield name="password" type="password" class="form-control"
								placeholder="Mật khẩu" required="required" />
						</div>
						<div>
							<s:submit class="btn btn-default submit" value="Đăng ký" />
						</div>
						<div class="clearfix"></div>
						<div class="separator">
							<p class="change_link">
								Đã đăng ký tài khoản ? <a href="#tologin" class="to_register">
									Đăng nhập </a>
							</p>
							<div class="clearfix"></div>
						</div>
					</s:form>
					<!-- form -->
				</section>
				<!-- content -->
			</div>
		</div>
	</div>

</body>

</html>