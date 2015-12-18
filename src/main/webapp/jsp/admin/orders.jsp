<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CAR RENT Orders</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css"/>
</head>
<body>
<%@include file="menu_admin.jspx" %>

<%--Searching--%>
<form action="/page/admin/orders" method="post">
    Filter by Car Id:
    <input type="text" name="car" placeholder="carId">
    <input type="submit" value="Filter...">

    <input type="hidden" name="admin" value="${admin}">

</form>

<table>
    <caption>Orders</caption>
    <tr>
        <th>Client</th>
        <th>Car</th>
        <th>Start Date</th>
        <th>End Date</th>
        <th>Child Chair</th>
        <th>GPS</th>
        <th>Price</th>
        <th>Details</th>
    </tr>
    <c:forEach items="${orderList}" var="order">
        <tr>
            <td>${order.clientId}</td>
            <td>${order.car.id}</td>
            <td>${order.startString}</td>
            <td>${order.endString}</td>
            <td>${order.childChair}</td>
            <td>${order.gps}</td>
            <td>${order.price}</td>
            <td>${order.details}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
