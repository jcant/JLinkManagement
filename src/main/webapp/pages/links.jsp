<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>--jCant-- Link list</title>
</head>
<body>
    <div align="center">
        <h1>Your login is: ${login}, your roles are:</h1>
        <c:forEach var="s" items="${roles}">
            <h3><c:out value="${s}" /></h3>
        </c:forEach>

		<ul>
			<c:forEach var="link" items="${links}">
				<li><c:out value="${link}" /></li>
			</c:forEach>
		</ul>

        <c:url value="/logout" var="logoutUrl" />
        <p>Click to logout: <a href="${logoutUrl}">LOGOUT</a></p>
    </div>
</body>
</html>
