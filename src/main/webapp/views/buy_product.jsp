<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
</head>
<body>

<button><a href="/basket">Basket</a></button>

${productCounter}

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
                <form action="/user/product/buy?id=${product.id}" method="post">
                    <input type="submit" value="buy product"/>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
