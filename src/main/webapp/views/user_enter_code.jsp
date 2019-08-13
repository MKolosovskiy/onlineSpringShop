<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
${codeMassage}
<form action="/code/enter" method="post">
    Code:<input type="text" name="code">
    <input type="submit" value="enter code">
</form>
</body>
</html>
