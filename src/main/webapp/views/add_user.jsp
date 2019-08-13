<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
${passwordError}
<form action="/admin/user/add" method="post">
    Name<input type="text" name="name" value="${name}" required>
    <br>
    Surname<input type="text" name="surname" value="${surname}" required>
    <br>
    Mail <input type="email" name="mail" value="${mail}" required>
    <br>
    User<input type="radio" name="role" value="ROLE_USER">
    Admin<input type="radio" name="role" value="ROLE_ADMIN">
    <br>
    Password<input type="password" name="password" required>
    <br>
    Repeat password<input type="password" name="repeatPassword" required>
    <br>
    <input type="submit" value="add user">
</form>
</body>
</html>
