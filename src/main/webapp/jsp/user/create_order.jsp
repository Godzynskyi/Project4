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

<form action="/page/create_order_handler" method="post">
  Start of rent:
  <input type="date" name="date_from">*
  <br>
  End of rent:
  <input type="date" name="date_to">*
  <br>
  First name:
  <input type="text" name="firstname">
  <br>
  Last name:
  <input type="text" name="lastname">
  <br>
  Phone:
  <input type="text" name="phone">*
  <br>
  Email:
  <input type="text" name="email">
  <br> <br>
  Details: <br>
  <textarea name="details" rows="10" cols="30"></textarea>

  <br>
  <label><input type="checkbox" name="gps">GPS ($10)</label>
  <br>
  <label><input type="checkbox" name="child_chair">Child Chair ($10)</label>

  <br><br>

  <label><input type="checkbox" name="delivery_to">I want your company deliver me a car.</label>
  <div>
    Time: <br>
    <input type="time" name="delivery_to_time">
    <br> Address: <br>
    <textarea name="address_to" rows="10" cols="30"></textarea>
  </div>

  <br>
  <label><input type="checkbox" name="delivery_from">I want your company deliver the car to your office from me.</label>
  <div>
    Time: <br>
    <input type="time" name="delivery_from_time">
    <br> Address: <br>
    <textarea name="address_from" rows="10" cols="30"></textarea>
  </div>

  <input type="hidden" name="carId" value="${carId}">
  <input type="submit" value="Get Price">
</form>

</body>
</html>
