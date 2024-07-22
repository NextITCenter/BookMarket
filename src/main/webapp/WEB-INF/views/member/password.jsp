<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
    <label>이메일:
        <input type="text" name="email" placeholder="이메일">
    </label>
    <label>패스워드:
        <input type="password" name="password" placeholder="패스워드">
    </label>
    <sec:csrfInput/>
    <button>변경</button>
</form>
</body>
</html>
