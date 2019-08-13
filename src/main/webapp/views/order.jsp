<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="/user/order" method="post">
    Name <input type="text" name="name" value="${name}" required>
    Surname <input type="text" name="surname" value="${surname}" required>
    Mail <input type="email" name="mail" value="${mail}" required>
    Phone number <input type="text" name="phoneNumber" required>
    Address <input type="text" name="address" required>
    <input type="submit" value="order">
</form>

</body>
</html>
