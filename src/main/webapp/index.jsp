<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<br>
<a href="/init.do">init</a></br>
<a href="/foods.do">foods</a></br>
<a href="/cartList.do">cart</a>

<br>

<c:if test="${sessionScope.id != null}">
    <a href="/logout.do">logout</a>
</c:if>


</body>
</html>
