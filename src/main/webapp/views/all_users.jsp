<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>Title</title>
</head>
<body>

<button><a href="/admin/product/all">Products</a></button>
<button><a href="/admin/user/add">Add a new user</a></button>
<button><a href="/signout">Exit</a></button>

<table border="1">
    <tr>
        <th>Mail</th>
        <th>Password</th>
    </tr>
    <c:forEach var="user" items="${users}">
        <tr>
            <td>${user.mail}</td>
            <td>${user.password}</td>
            <td>
                <button><a href="/admin/user/edit?id=${user.id}">Update</a></button>
            </td>
            <td>
                <button><a href="/admin/user/delete?id=${user.id}">Delete</a></button>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
