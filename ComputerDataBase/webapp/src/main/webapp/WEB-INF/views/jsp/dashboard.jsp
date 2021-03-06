<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="ex" uri="page"%>
<%@ page isELIgnored="false"%>
<%@page session="true"%>
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
<link href="resources/css/main.css" rel="stylesheet" media="screen">
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div>
				<a id="dashboard_button" class="navbar-brand" href="dashboard">
					Application - Computer Database </a>

			</div>
			<div class=" navbar-brand pull-right">
				<c:url var="logoutUrl" value="/logout" />
				<form class="form-inline" action="${logoutUrl}" method="post">
					<input type="submit" class="btn btn-default btn-sm" value="Log out">
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				</form>
			</div>
		</div>

	</header>

	<section id="main">
		<div class="container">
			<h1 id="homeTitle">${model.nbComputers}Computersfound</h1>

			<div id="actions" class="form-horizontal">
				<div class="pull-left">
					<form id="searchForm" action="#" method="GET" class="form-inline">
						<input type="search" id="searchbox" name="search"
							class="form-control" placeholder="Search name"
							value="${model.search}" /> <select class="form-control"
							name="by">
							<option value='cp'
								<c:if test="${model.by == 'cp'}"> selected</c:if>>
								Computers</option>
							<option value='cy'
								<c:if test="${model.by == 'cy'}"> selected</c:if>>
								Companies</option>
						</select> <input type="submit" id="searchsubmit" value="Filter by name"
							class="btn btn-primary" />

					</form>
				</div>
				<div class="pull-right">
					<a class="btn btn-success" id="addComputer" href="add">Add
						Computer</a> <a class="btn btn-default" id="editComputer" href="#"
						onclick="$.fn.toggleEditMode();">Edit</a>
				</div>
			</div>
		</div>

		<form id="deleteForm" action="dashboard" method="POST">
			<input type="hidden" name="selection" value="">
			<input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
		</form>

		<div class="container" style="margin-top: 10px;">
			<table class="table table-striped table-bordered">
				<thead>
					<tr>
						<!-- Variable declarations for passing labels as parameters -->
						<!-- Table header for Computer Name -->

						<th class="editMode" style="width: 60px; height: 22px;"><input
							type="checkbox" id="selectall" /> <span
							style="vertical-align: top;"> - <a href="#"
								id="deleteSelected" onclick="$.fn.deleteSelected();"> <i
									class="fa fa-trash-o fa-lg"></i>
							</a>
						</span></th>
						<th>Computer name</th>
						<th>Introduced date</th>
						<!-- Table header for Discontinued Date -->
						<th>Discontinued date</th>
						<!-- Table header for Company -->
						<th>Company</th>

					</tr>
				</thead>
				<!-- Browse attribute computers -->
				<tbody id="results">

					<c:forEach items="${model.computersDto}" var="computerDto">
						<tr>
							<td class="editMode" style="display: none"><input
								type="checkbox" class="cb"
								value="<c:out value="${computerDto.id}"/>" /></td>
							<td><a id='id'
								href="edit?id=<c:out value="${computerDto.id}"/>"><c:out
										value="${computerDto.name}" /></a></td>
							<td><c:out value="${computerDto.dateIntroduced}" /></td>
							<td><c:out value="${computerDto.dateDiscontinued}" /></td>
							<td><c:out value="${computerDto.cydtoName}" /></td>
						</tr>
					</c:forEach>

				</tbody>
			</table>
		</div>
	</section>

	<footer class="navbar-fixed-bottom">
		<div class="container text-center">
			<ul class="pagination">
				<li id='first' class="pages"><a id="afirst" position="0"
					href='?search=${model.search}&by=${model.by}&page=1&maxObj=${model.maxObj}'
					aria-label='First'><span position="0" aria-hidden='true'>&laquo;&laquo;</span></a></li>
				<ex:Page index="${model.index}" nbPage="${model.nbPages}"
					reSearch="${model.search}" by="${model.by}"
					maxObj="${model.maxObj}"></ex:Page>
				<li id='last' class="pages"><a id="alast"
					position="${model.nbPages}"
					href='?search=${model.search}&by=${model.by}&page=${model.nbPages+1}&maxObj=${model.maxObj}'
					aria-label='Last'><span position="${model.nbPages+1}"
						aria-hidden='true'>&raquo;&raquo;</span></a></li>
			</ul>

			<div class="btn-group btn-group-sm pull-right" role="group">
				<a href='?search=${model.search}&by=${model.by}&page=1&maxObj=10'><button
						type="button" class="max_obj btn btn-default">10</button></a> <a
					href='?search=${model.search}&by=${model.by}&page=1&maxObj=50'><button
						type="button" class="max_obj btn btn-default">50</button></a> <a
					href='?search=${model.search}&by=${model.by}&page=1&maxObj=100'><button
						type="button" class="max_obj btn btn-default">100</button></a>
			</div>
		</div>
	</footer>
	<script src="resources/js/jquery.min.js"></script>
	<script src="resources/js/bootstrap.min.js"></script>
	<script src="resources/js/dashboard.js"></script>

</body>
</html>
