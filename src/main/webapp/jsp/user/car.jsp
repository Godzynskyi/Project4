<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <fmt:setLocale value="${language}" />
  <title></title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css"/>
</head>
<body>
<%@include file="menu-user.jspx"%>

${car.id}
${car.model.brand}
${car.model.model}
${car.year}
${car.color}
${car.engine}
${car.expenditure}
<c:if test="${car.automat}" var="AUTOMAT">AUTOMAT</c:if>
<c:if test="${car.automat eq false}" var="MANUAL">MANUAL</c:if>
<br>
<c:forEach items="${car.options}" var="option">
  ${option}
</c:forEach>

<form action="/page/create_order" method="post">
  <input type="hidden" name="carId" value="${car.id}">
  <input type="submit" value="CREATE ORDER">
</form>

</body>
</html>
