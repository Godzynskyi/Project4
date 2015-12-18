<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>New car</title>
    <c:url var="cssUrl" value="${pageContext.request.contextPath}/css/new_car.css" />
</head>
<body>
<%@include file="menu_admin.jspx" %>

<c:if test="${message ne null}">
    <div class="message">${message}</div>
</c:if>
<c:if test="${error ne null}">
    <div class="error">${error}</div>
</c:if>

<div id="registration_form">
    <h1>New car</h1>
    <form method="post" action="/page/admin/create_car">
        <div>
            <input name="brand" placeholder="Brand"><br>
            <input name="model" placeholder="Model"><br>
        </div>
        <input type="text" name="year" placeholder="Year"><br>
        <input type="text" name="color" placeholder="Color"><br>
        <input type="text" name="engine" placeholder="Engine"><br>
        <input type="text" name="expenditure" placeholder="Expenditure"><br>
        <label>Automatic</label><input type="checkbox" name="automat" placeholder="automat"><br>
        <input type="text" name="price" placeholder="Price"><br>
        Details: <br>
        <textarea name="details" rows="10" cols="30" placeholder="Details"></textarea>
        <input type="submit" value="Submit">
    </form>
</div>
</body>
</html>
