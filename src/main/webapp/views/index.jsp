<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Log In</title>
</head>
<body>
${message}
<form action="/signin" method="post">
    Mail <input type="text" name="mail"  value="${mail}" required>  <%// change type  %>
    Password <input type="password" name="password" required>
    <input type="submit" value="login">
</form>
</body>
</html>
