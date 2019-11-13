<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%--
  Created by IntelliJ IDEA.
  User: prokopovich
  Date: 22.10.2019
  Time: 11:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        <%@include file="/css/horizontal.css"%>
    </style>
</head>

<body>
<div class="outer">
    <div class="inner inner1">
        <h2 align="center">List of Users</h2>
        <table>
            <tr>
                <th>Name</th>
                <th>Email</th>
                <th>Phone</th>
                <th>Role</th>
                <th>Choose action</th>
            </tr>

            <c:forEach var="user" items="${listUser}">
                <tr>
                    <td><c:out value="${user.name}"/></td>
                    <td><c:out value="${user.email}"/></td>
                    <td><c:out value="${user.phone}"/></td>
                    <td><c:out value="${user.role}"/></td>
                    <td>
                        <a href="update?id=<c:out value='${user.id}' />">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="delete?id=<c:out value='${user.id}' />">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <div class="inner inner2">
        <h3>Add User</h3>
        <form name="list" method="POST" action="/userAdd">
            <input type="hidden"><br>
            <input type="text" required placeholder="Name" name="name"><br>
            <input type="text" required placeholder="Email" name="email"><br>
            <input type="text" required placeholder="Phone" name="phone"><br>
            <select required name="role">
                <option disabled selected value="">Выберите роль</option>
                <option value="admin">admin</option>
                <option value="user">user</option>
            </select><br>
            <input class="button" type="submit" value="Add">
        </form>
        <p><c:out value='${addResult}'/></p>
    </div>
    <div class="inner inner3">
        <h3>Update User</h3>
        <form method="POST" action="/update">
            <input type="hidden" name="id" value="<c:out value='${user.id}'/>"><br>
            <input type="text" required placeholder="Name" name="name" value="<c:out value='${user.name}'/>"><br>
            <input type="text" required placeholder="Email" name="email" value="<c:out value='${user.email}'/>"><br>
            <input type="text" required placeholder="Phone" name="phone" value="<c:out value='${user.phone}'/>"><br>
            <select name="role">
                <option disabled selected>Выберите роль</option>
                <c:choose>
                    <c:when test="${user.role =='admin'}">
                        <option selected value="<c:out value='${user.role}'/>"><c:out value='${user.role}'/></option>
                        <option value="user">user</option>
                    </c:when>

                    <c:when test="${user.role =='user'}">
                        <option selected value="<c:out value='${user.role}'/>"><c:out value='${user.role}'/></option>
                        <option value="admin">admin</option>
                    </c:when>

                    <c:otherwise>
                        <option value="user">user</option>
                        <option value="admin">admin</option>
                    </c:otherwise>

                </c:choose>
            </select><br>
            <input class="button" type="submit" value="Update">
        </form>
        <c:out value='${UpdateResult}'/>
    </div>
</div>
<div>
    <a href="/logout">Logout</a>
</div>

</body>
</html>