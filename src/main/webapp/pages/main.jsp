<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Beauty saloon</title>
</head>
<body>
<%@include file="menu.jsp" %>
<h3 class="text-center">Hello, <c:out value="${user.login}"/></h3>
</body>
</html>
