<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="resources/css/bootstrap.min.css" rel="stylesheet"
	media="screen">
<link href="resources/css/font-awesome.css" rel="stylesheet"
	media="screen">
<link href="resources/css/main.css" rel="stylesheet" media="screen">
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a id="dashboard_button" class="navbar-brand" href="dashboard"> Application - Computer
				Database </a>
		</div>
	</header>

	<section id="main">
		<div class="container">
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 box">
					<h1>Add Computer</h1>
					<form action="add" method="POST">
						<fieldset>
							<div class="form-group ">
								<label for="computerName">Computer name</label> <input
									type="text" class="form-control" name="name" id="computerName"
									placeholder="Computer name">
								
							</div>
							<div class="form-group">
								<label for="introduced">Introduced date</label> <input
									type="date" class="form-control" name="dI" id="introduced"
									placeholder="Introduced date">
							</div>
							<div class="form-group">
								<label for="discontinued">Discontinued date</label> <input
									type="date" class="form-control" name="dD" id="discontinued"
									placeholder="Discontinued date">
							</div>
							<div class="form-group">
								<label for="companyId">Company</label> <select
									class="form-control" name="companyId" id="companyId">
									<option value="0">--</option>
									<c:forEach items="${lcydto}" var="cydto">
										<option value="<c:out value="${cydto.id}"/>">
											<c:out value="${cydto.name}" /></option>
									</c:forEach>
								</select>
							</div>
						</fieldset>
						<c:if test="${success==1}">
								<div id="success" class="alert alert-success pull-left" role="alert">Computer Succesfully Added</div>
						</c:if>
						<c:if test="${error==1}">
							<div id="error" class="alert alert-danger pull-left" role="alert">Computer not Added</div>
						</c:if>
						<div class="actions pull-right">
							<input type="submit" id="submit" value="Add" class="btn btn-primary">
							or <a href="dashboard" class="btn btn-default">Cancel</a>
						</div>
					</form>
				</div>
			</div>
		</div>
	</section>
</body>
<script src="resources/js/jquery.min.js"></script>
<script src="resources/js/bootstrap.min.js"></script>
<script src="resources/js/addComputer.js"></script>
</html>