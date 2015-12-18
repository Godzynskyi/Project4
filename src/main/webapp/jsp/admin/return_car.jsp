<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Returning car</title>
    <c:url var="cssUrl" value="${pageContext.request.contextPath}/css/main.css" />
</head>
<body>
<%@include file="menu_admin.jspx" %>

<div>
<h3>${order.client.firstName} ${order.client.lastName}</h3>
    <h3>${order.client.phone}</h3>
    <div>
        <h2>Car info:</h2>
        <div>
            <h3>${order.car.model.brand}</h3>
            <h3>${order.car.model.model}</h3>
            <h3>${order.car.year}</h3>
            <h3>${order.car.color}</h3>
            <h3>${order.car.engine}</h3>
            <h3>${order.car.expenditure}</h3>
            <h3>${order.car.transmission}</h3>
            <c:if test="${order.gps}">
                <h3>GPS</h3>
            </c:if>
            <c:if test="${order.childChair}">
                <h3>Child chair</h3>
            </c:if>
        </div>
        <h2>Options of car order:</h2>
        <div>
            <c:forEach items="${order.car.options}" var="option">
                <h3>${option}</h3>
            </c:forEach>
        </div>
        <h3>Total price: ${order.price}</h3>
        <div>
            <h3>Who get car: ${order.admin}</h3>
        </div>
        <div>
            <h3>Start of ordering: ${order.start.getTime()}</h3>
            <h3>End of ordering: ${order.end.getTime()}</h3>
        </div>
    </div>
    <div>
        <div>
            <form method="post" action="/page/admin/confirm_returning">
                <label>Defects exists</label><input type="checkbox" name="existsDefects">
                <input type="text" name="description" placeholder="Description of defect"><br>
                <input type="text" name="price" placeholder="Price"><br>
                <input type="date" name="date" placeholder="Date of occurrence"><br>
                <input type="hidden" name="carId" value="${order.car.id}">
                <input type="hidden" name="clientId" value="${order.clientId}">
                <input type="submit">
            </form>
        </div>
    </div>
</div>
</body>
</html>
