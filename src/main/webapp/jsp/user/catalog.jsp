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

  <c:if test="${message ne null}">
    <div class="message">${message}</div>
  </c:if>
  <c:if test="${error ne null}">
    <div class="error">${error}</div>
  </c:if>
  <div style="overflow-x:auto;">
<table>
  <c:forEach items="${cars}" var="car">
    <tr>
      <td><a href="/page/car?id=${car.id}">Get CAR</a></td>
      <td>${car.model.brand} ${car.model.model}</td>
      <td>${car.engine}</td>
      <td>
        <c:forEach items="${car.options}" var="option">
          <table>
            <tr>${option}</tr>
          </table>
        </c:forEach>
      </td>
    </tr>
  </c:forEach>
</table>
</div>

</body>
</html>
