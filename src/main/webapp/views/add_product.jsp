<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/admin/product/add" method="post">
    Name<input type="text" name="name" required>
    <br>
    Description<input type="text" name="description" required>
    <br>
    Price<input type="number" step="0.01" name="price" required>
    <br>
    <input type="submit" value="add product">
</form>
</body>
</html>
