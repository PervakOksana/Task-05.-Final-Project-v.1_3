<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<p align="center">
		<b><c:out value="ERROR" /></b></br>
		<c:out value="${requestScope.message_error}" />
	</p>

</body>
</html>