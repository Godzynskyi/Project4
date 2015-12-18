<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <fmt:setLocale value="${language}" />
    <title></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css"/>
</head>
<body>

<form action="${requestScope.get("redirect")}" method="POST">
  Enter username : <input type="text" name="username"> <BR>
  Enter password : <input type="password" name="password"> <BR>
  <input type="submit" />
</form>
</body>
</html>
