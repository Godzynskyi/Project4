<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="my" uri="/WEB-INF/mytag.tld" %>

<div>
    <div id="title-string"><a href="/jsp/index.jsp">CAR RENT</a></div>
    <table>
        <tr>
            <c:if test="${sessionScope.get(admin) ne null}">
                <td>
                    <a href="/page/admin/register">Register new Employee</a>
                </td>
                <td>
                    <a href="/page/catalog">Catalog</a>
                </td>
                <td>
                    <a href="/conditions.jsp">Conditions</a>
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
            </c:if>
            <c:if test="${sessionScope.get(admin) eq null}">
                <td>
                    <a href="/page/catalog">Catalog</a>
                </td>
                <td>
                    <a href="/conditions.jsp">Conditions</a>
                </td>
                <td>
                    <a href="/page/login">Login</a>
                </td>

            </c:if>
        </tr>

    </table>

    <my:printMessageIfNotNullTag var="${message}" id="message"/>
    <my:printMessageIfNotNullTag var="${error}" id="error"/>
</div>