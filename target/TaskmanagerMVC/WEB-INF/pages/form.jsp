<%--
  Created by IntelliJ IDEA.
  User: dona
  Date: 15.06.16
  Time: 14:39
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
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login</title>
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
        <div class="panel panel-success">
            <div class="panel-heading">
                <h3 class="panel-title">
                    Login Form
                </h3>
            </div>
            <div class="panel-body">
                <form:form id="reportRegisterForm" cssClass="form-horizontal" modelAttribute="user" method="post" action="/loginUser">

                    <div class="form-group">
                        <div class="control-label col-xs-3"><form:label path="username">Username</form:label></div>
                        <div class="col-xs-6">
                            <form:input cssClass="form-control" path="username" id="username" name = "username" value=""/>
                            <%--<form:errors path="username" cssClass="alert alert-danger"></form:errors>--%>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="control-label col-xs-3"><form:label path="password">Password</form:label></div>
                        <div class="col-xs-6">
                            <form:input type="password" cssClass="form-control" path="password" id="password" name = "password" value=""/>
                            <%--<form:errors path="password" cssClass="alert alert-danger"></form:errors>--%>
                            <c:forEach items="${errors}" var="err">
                                <div class="alert alert-danger fade in"> <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a><c:out value="${err.defaultMessage}"/></div>
                            </c:forEach>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="row">
                            <div class="col-xs-4">
                            </div>
                            <div class="col-xs-4">
                                <input type="submit" id="loginUser" class="btn btn-primary" value="Login"/>
                            </div>
                            <div class="col-xs-4">
                            </div>
                        </div>
                    </div>

                </form:form>
                <div class="col-xs-6">
                    <a href = "/register"><button type="button" class="btn btn-success" value="Create Test Admin">Register now</button></a>
                </div>
            </div>
        </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>


</body>