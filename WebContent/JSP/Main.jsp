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

<div class="prettyTitle">
<fmt:message key="label.title"/>
</div>
<br>
<br>
<div class="beverageList">

</div>
<c:if test="${curUser == null}">
<div class="link">
<a href="Controller?command=signinshowform"><fmt:message key="label.signin"/></a>
<a href="Controller?command=signupshowform"><fmt:message key="label.signup"/></a>
</div>

</c:if>
<c:if test="${curUser != null}">
<div class="link">
<a href="Controller?command=exit"><fmt:message key="label.exit"/></a>
</div>
<div class="userInfo">
<fmt:message key="label.firstname"/>${curUser.first_name}
<fmt:message key="label.bill"/>${curUser.bill}
</div>

</c:if>

</body>


</html>