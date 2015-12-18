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

<table>
  <tr>
    <td>
      ${car.model.brand}
    </td>
    <td>
      ${car.model.model}
    </td>
  </tr>
  <tr>
    <td>
      Phone: ${phone}
    </td>
    <td>
      e-mail: ${email}
    </td>
  </tr>
  <tr>

  </tr>
</table>

</body>
</html>
