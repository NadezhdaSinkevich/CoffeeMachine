<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<c:set var="theLocale" value="${not empty param.theLocale ? param.theLocale : pageContext.request.locale}" scope="session"/>
<fmt:setLocale value="${param.theLocale}"/>
<fmt:setBundle basename="com.i18n.default"/>
<html>
<head>
<meta charset="utf-8">
</head>
<body>

<div class="prettyForm">
<form action="Controller" method="POST">
<fmt:message key="label.login"/>
<input type="text" class="inputLabel" name="login" required>
<fmt:message key="label.password"/>
<input type="password" class="inputLabel" name="password" required>
<input type="hidden" name="command" value="signin">
<input type="submit" value="OK" class="prettyButton">
</form>
</div>
</body>
</html>