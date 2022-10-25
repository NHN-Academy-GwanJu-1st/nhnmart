<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<c:forEach items="${buyMap}" var="map">
    ${map.key.getName()} ( ${map.key.getPrice()} 원) 총 ${map.value}개 = ${map.key.getPrice() * map.value} <br>

    <c:set var="total" value="${total} + ${map.key.getPrice() * map.value}"></c:set>
</c:forEach>

총 금액 <c:out value="${totalPrice}"></c:out>

</body>
</html>
