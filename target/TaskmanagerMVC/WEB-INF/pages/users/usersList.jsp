<%--
  Created by IntelliJ IDEA.
  User: dona
  Date: 15.06.16
  Time: 10:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Employees List</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <style type="text/css">
        .myrow-container {
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
                <div align="left"><b>Users List</b></div>
                <div align="right"><a href="/users/editUser">Add New user</a></div>
            </h3>
        </div>
        <div class="panel-body">
            <c:if test="${empty usersList}">
                There are no users
            </c:if>
            <c:if test="${not empty usersList}">

                <form action="searchUser">
                    <div class="row">
                        <div class="col-md-4">Search Users: <input type='text' name='searchName' id='searchName'/> </div>
                        <div class="col-md-4"><input class="btn btn-success" type='submit' value='Search'/></div>
                    </div>
                </form>
        </div>
                <table class="table table-hover table-bordered">
                    <thead style="background-color: #bce8f1;">
                    <tr>
                        <th>Id</th>
                        <th>Username</th>
                        <th>Password</th>
                        <th>Is Admin</th>
                        <th>Edit</th>
                        <th>Delete</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${usersList}" var="usr">
                        <tr>
                            <th><c:out value="${usr.id}"/></th>
                            <th><c:out value="${usr.username}"/></th>
                            <th><c:out value="${usr.password}"/></th>
                            <th><c:out value="${usr.isAdmin}"/></th>
                            <th><a href="editUser?id=<c:out value='${usr.id}'/>">Edit</a></th>
                            <th><a href="deleteUser?id=<c:out value='${usr.id}'/>">Delete</a></th>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:if>
        </div>
    </div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>
</html>
