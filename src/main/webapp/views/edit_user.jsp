<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>

<body>
${passwordError}
<form action="/admin/user/edit?id=${userId}" method="post">
    Name <input type="text" name="name" value="${name}" required>
    Surname<input type="text" name="surname" value="${surname}" required>
    Mail <input type="email" name="mail" value="${mail}" required>
    Password <input type="password" name="password" required>
    Repeat password <input type="password" name="repeatPassword" required>
    <input type="submit" value="edit user">
</form>
</body>
</html>
