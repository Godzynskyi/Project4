<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <fmt:setLocale value="${language}" />
  <title>Car RENT Add Defect</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css"/>
</head>
<body>
<%@include file="menu_admin.jsp"%>

<form action="/page/admin/add_defect_handler" method="post">
  <input type="hidden" name="orderId" value="${requestScope.get("orderId")}">
  <input type="text" name

</form>

</body>
</html>
