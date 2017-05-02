<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="ex" uri="page"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
<!-- Bootstrap -->
<link href="resources/css/bootstrap.min.css" rel="stylesheet"
	media="screen">
<link href="resources/css/font-awesome.css" rel="stylesheet"
	media="screen">
<link href="resources/css/login.css" rel="stylesheet"
	media="screen">
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a id="dashboard_button" class="navbar-brand" href="dashboard">
				Application - Computer Database </a>
		</div>
	</header>


<div  id="login-modal"  role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: block;">
    	  <div class="modal-dialog">
				<div class="loginmodal-container">
					<h1>Login to Your Account</h1><br>
				  <form action="<c:url value='j_spring_security_check' />" method='POST'>
					<input type="text" name="username" placeholder="Username"/>
					<input type="password" name="password" placeholder="Password"/>
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
					<input type="submit" name="login" class="login loginmodal-submit" value="Login">
				  </form>
					
				  <div class="login-help">
					<a href="#">Register</a> - <a href="#">Forgot Password</a>
				  </div>
				</div>
			</div>
		  </div>

</body>
</html>
