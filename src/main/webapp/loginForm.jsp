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

<form method="post" action="/login.do">
  <input type="text" name="id" placeholder="id = admin"></br>
  <input type="text" name="password" placeholder="password = 1234"></br>
  <button type="submit"><fmt:message key="Login"/></button>
</form>

</fmt:bundle>
</body>
</html>
