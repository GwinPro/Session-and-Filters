<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: Fly
  Date: 10.11.2019
  Time: 20:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3>Authorization or Registration form</h3>
<form method="POST" action="/auth">
        <input type="hidden"><br>
        <input type="text" required placeholder="Name" name="name"><br>
        <input type="text" required placeholder="Email" name="email"><br>
        <input class="button" type="submit" value="Authorization" name="auth">
    </form>
    <c:out value='${addAuth}'/>
</form>

<form method="POST" action="/add">
    <input type="hidden"><br>
    <input type="text" required placeholder="Name" name="name"><br>
    <input type="text" required placeholder="Email" name="email"><br>
    <input type="text" required placeholder="Phone" name="phone"><br>
    <input class="button" type="submit" value="Registration" name="reg">
</form>
<c:out value='${addResult}'/>
</form>
</body>
</html>
