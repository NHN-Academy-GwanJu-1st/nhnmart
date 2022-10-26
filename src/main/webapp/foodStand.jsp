<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<c:if test="${sessionScope.locale == null}">
    <fmt:setLocale value="ko"></fmt:setLocale>
    <fmt:setBundle basename="message" var="message"></fmt:setBundle>
</c:if>

<c:if test="${sessionScope.locale != null}">
    <fmt:setLocale value="${sessionScope.locale}"></fmt:setLocale>
    <fmt:setBundle basename="message" var="message"></fmt:setBundle>
</c:if>

<fmt:bundle basename="message">

<form method="post" action="/cart.do">
<c:forEach var="food" items="${foodList}">
    <fmt:message key="${food.getName()}">
<%--    <c:out value="${food.getName()}"></c:out>--%>
    </fmt:message>
    <c:out value="${food.getPrice()}"></c:out><fmt:message key="원"></fmt:message>
    <input type="text" name="${food.getName()}"></br>
</c:forEach>
    <button type="submit">
        <fmt:message key="PutInCart"></fmt:message>
    </button>
</form>

</fmt:bundle>

</body>
</html>
