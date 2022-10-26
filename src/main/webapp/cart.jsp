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

<c:forEach items="${buyMap}" var="map">
    <fmt:message key="${map.key.getName()}"></fmt:message> ( ${map.key.getPrice()} <fmt:message key="원"></fmt:message>) ${map.value}<fmt:message key="개"></fmt:message> = ${map.key.getPrice() * map.value} <br>

<%--    <c:set var="total" value="${total} + ${map.key.getPrice() * map.value}"></c:set>--%>
</c:forEach>

    <fmt:message key="총금액"></fmt:message> <c:out value="${totalPrice}"></c:out>

    <br>
    <br>

<form method="post" action="/pay.do">
    <input value="${totalPrice}" type="hidden" name="totalPrice">
    <button type="submit"><fmt:message key="pay"></fmt:message></button>
</form>

</fmt:bundle>
</body>
</html>
