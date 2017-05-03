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
<link href="resources/css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="resources/css/font-awesome.css" rel="stylesheet" media="screen">
<link href="resources/css/main.css" rel="stylesheet" media="screen">
</head>
<body>
    <header class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <a id="dashboard_button" class="navbar-brand" href="dashboard"> Application - Computer Database </a>
        </div>
    </header>
    <section id="main">
        <div class="container">
            <div class="row">
                <div class="col-xs-8 col-xs-offset-2 box">
                    <div class="label label-default pull-right">
                        id: ${computer.id}
                    </div>
                    <h1>Edit Computer</h1>

                    <form action="edit" method="POST">
                        <input type="hidden" name="id" value="${computer.id}" id="id"/>
                        <fieldset>
                            <div class="form-group">
                                <label for="computerName">Computer name</label>
                                <input type="text" class="form-control" name="name" id="computerName" placeholder="Computer name" 
                                value="${computer.name}">
                            </div>
                            <div class="form-group">
                                <label for="introduced">Introduced date</label>
                                <input type="date" class="form-control" name="dateIntroduced" id="introduced" placeholder="Introduced date" 
                                value="${computer.dateIntroduced}">
                            </div>
                            <div class="form-group">
                                <label for="discontinued">Discontinued date</label>
                                <input type="date" class="form-control" name="dateDiscontinued" id="discontinued" placeholder="Discontinued date" 
                                value="${computer.dateDiscontinued}">
                            </div>
                            <div class="form-group">
                                <label for="companyId">Company</label>
                                <select name="cydtoId" class="form-control" id="companyId" >
                                	<option value='0'> -- </option>
                                    <c:forEach items="${companiesDto}" var="companydto">
										<option value="<c:out value="${companydto.id}"/>" 
										<c:if test="${computer.cydtoId == companydto.id}"> selected="selected" </c:if>>
											<c:out value="${companydto.name}" /></option>
									</c:forEach>
                                </select>
                            </div>
                            <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>            
                        </fieldset>
                        <c:if test="${success==1}">
								<div class="alert alert-success pull-left" role="alert">Computer Succesfully Added</div>
						</c:if>
						<c:if test="${error==1}">
							<div class="alert alert-danger pull-left" role="alert">Computer not Added</div>
						</c:if>
                        <div class="actions pull-right">
                            <input id="submit" type="submit" value="Edit" class="btn btn-primary">
                            or
                            <a href="dashboard.html" class="btn btn-default">Cancel</a>
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