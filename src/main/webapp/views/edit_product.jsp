<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="/admin/product/edit?id=${productId}" method="post">
    Name<input type="text" name="name" value="${name}" required>
    Description<input type="text" name="description" value="${description}" required>
    Price<input type="number" step="0.01" name="price" value="${price}" required>
    <input type="submit" value="edit product">
</form>

</body>
</html>
