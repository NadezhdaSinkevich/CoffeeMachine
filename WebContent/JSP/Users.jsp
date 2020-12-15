<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<html>

<body>

<c:forEach var="temp" items="${users}">
	
	${temp.toString()}<br>
	
</c:forEach>

</body>



</html>