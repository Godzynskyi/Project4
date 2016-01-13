<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div>
    <div id="title-string"><a href="/index.jsp">CAR RENT</a></div>
    <table>
        <tr>
            <td>
                <a href="/page/catalog">Catalog</a>
            </td>
            <td>
                <a href="/conditions.jsp">Conditions</a>
            </td>
            <td>
                <a href="/page/login">Login</a>
            </td>

        </tr>
    </table>

    <c:if test="${message ne null}">
        <div class="message">${message}</div>
    </c:if>
    <c:if test="${error ne null}">
        <div class="error">${error}</div>
    </c:if>
</div>