<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div>
    <div id="title-string">CAR RENT</div>
    <div>
        ${admin}
    </div>
    <table>
        <tr>
            <td>
                <a href="/page/admin/register">Register new Employee</a>
            </td>
            <td>
                <a href="/page/admin/repair">Repair car</a>
            </td>
            <td>
                <a href="/page/admin/orders?admin=null">Free orders</a>
            </td>
            <td>
                <a href="/page/admin/orders">My orders</a>
            </td>
            <td>
                <a href="/page/admin/create_car">Add car</a>
            </td>
            <td>
                <a href="/page/admin/logout">Logout</a>
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