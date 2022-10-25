<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<br>
<a href="/init">init</a></br>
<a href="/foods">foods</a></br>
<a href="/cart">cart</a>

<br>

<c:if test="${sessionScope.id != null}">
    <a href="/logout">logout</a>
</c:if>


</body>
</html>
