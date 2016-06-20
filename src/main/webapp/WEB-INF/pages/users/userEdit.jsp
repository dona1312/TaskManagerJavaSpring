<%--
  Created by IntelliJ IDEA.
  User: dona
  Date: 15.06.16
  Time: 10:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="author" content="Ranga Reddy">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Employee Information</title>
    <!-- Bootstrap CSS -->
    <%-- <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet"> --%>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <style type="text/css">
        .myrow-container{
            margin: 20px;
        }
    </style>
</head>
<body class=".container-fluid">
<div class="container myrow-container">
    <jsp:include page="../shared/menu.jsp" />
    <div class="panel panel-success">
        <div class="panel-heading">
            <h3 class="panel-title">
                User Details
            </h3>
        </div>
        <div class="panel-body">
            <form:form id="employeeRegisterForm" cssClass="form-horizontal" modelAttribute="user" method="post" action="saveUser">

                <div class="form-group">
                    <form:label path="fullName" cssClass="control-label col-xs-3">Full name</form:label>
                    <div class="col-xs-6">
                        <form:hidden path="id" value="${userObject.id}"/>
                        <form:input cssClass="form-control" path="fullName" value="${userObject.fullName}"/>
                    </div>
                </div>

                <div class="form-group">
                    <form:label path="username" cssClass="control-label col-xs-3">Username</form:label>
                    <div class="col-xs-6">
                        <form:input cssClass="form-control" path="username" value="${userObject.username}"/>
                    </div>
                </div>

                <div class="form-group">
                    <div class="control-label col-xs-3"><form:label path="password">Password</form:label></div>
                    <div class="col-xs-6">
                        <form:input cssClass="form-control" path="password" value="${userObject.password}"/>
                    </div>
                </div>

                <div class="form-group">
                    <div class="control-label col-xs-3"><form:label path="">Is Admin</form:label></div>
                    <div class="col-xs-6">
                        <form:input cssClass="form-control" path="isAdmin" value="${userObject.isAdmin}"/>
                    </div>
                </div>

                <div class="form-group">
                    <div class="row">
                        <div class="col-xs-4">
                        </div>
                        <div class="col-xs-4">
                            <input type="submit" id="saveEmployee" class="btn btn-primary" value="Save"/>
                        </div>
                        <div class="col-xs-4">
                            <div class="col-xs-6">
                                <a href = "/users/getAll"><button type="button" class="btn btn-info" value="logout">Back</button></a>
                            </div>
                        </div>
                    </div>
                </div>

            </form:form>
        </div>
    </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

</body>
</html>
