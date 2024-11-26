<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="autenticacao.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Verifica Id</title>
</head>
<body>
<form class="container mt-5" action="controladora" method="post">
	Id da pessoa a ser alterada: <input type="text" name="id">
	<input type="text" hidden="true" name="url" value="alterarpessoa.jsp">
	<input type="text" hidden="true" name="logica" value="VerificaIdPessoa">
	<button type="submit">Verificar</button>
</form>
</body>
</html>