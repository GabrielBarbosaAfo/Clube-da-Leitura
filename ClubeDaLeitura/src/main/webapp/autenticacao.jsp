<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" href="style/style.css">
</head>
<body>
	<c:import url="cabecalho.jsp"/>
	<c:if test="${sessionScope.status != true }">
		<jsp:forward page="login.jsp"/>
	</c:if>
</body>
</html>