<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2024-07-19
  Time: 12:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>패스워드 변경</title>
</head>
<body>
<form action="/password" method="post">
    <input type="text" name="email">
    <input type="password" name="password">
    <button>변경</button>
</form>
</body>
</html>
