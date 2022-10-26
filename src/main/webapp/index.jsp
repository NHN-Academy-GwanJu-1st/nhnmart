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
    <br>
    <a href="/init.do"> <fmt:message key="init"/></a></br>
    <a href="/foods.do"><fmt:message key="foods"/></a></br>
    <a href="/cartList.do"><fmt:message key="cart"/></a></br>

<br>

<c:if test="${sessionScope.id != null}">
    <c:set var="user" value="${user}"></c:set>
    <a href="/logout.do"><fmt:message key="Logout"/></a><br>
    <p><fmt:message key="money"></fmt:message> : <c:out value="${user.getMoney()}"></c:out></p>
</c:if>

<c:if test="${sessionScope.id == null}">
    <a href="/loginForm.do"><fmt:message key="Login"></fmt:message></a>
</c:if>

</fmt:bundle>

<br>
<br>
<br>

<form method="post" action="/local.do">
    <input type="submit" name="locale" value="ko">
    <input type="submit" name="locale" value="en">
</form>

<%--<form method="post" action="/local.do">--%>
<%--</form>--%>


</body>
</html>
