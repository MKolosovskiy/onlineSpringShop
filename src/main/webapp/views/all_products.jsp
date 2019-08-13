<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<button><a href="/admin/user/all">Users</a></button>
<button><a href="/admin/product/add">Add a new product</a></button>
<button><a href="/signout">Exit</a></button>

<table border="1">
    <tr>
        <th>Name</th>
        <th>Description</th>
        <th>Price</th>
    </tr>
    <c:forEach var="product" items="${products}">
        <tr>
            <td>${product.name}</td>
            <td>${product.description}</td>
            <td>${product.price}</td>
            <td>
                <button><a href="/admin/product/edit?id=${product.id}">Update</a></button>
            </td>
            <td>
                <button><a href="/admin/product/delete?id=${product.id}">Delete</a></button>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
