<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="tp.daw.modelo.Caixa"%>
<%@ include file="autenticacao.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Caixa</title>
</head>
<body>
	<form class="container mt-5" action="controladora" method="post">
		<h1>Remover Caixa</h1>
		<%
		Caixa caixa = (Caixa) request.getAttribute("caixa");
		%>
		<input type="hidden" name="id" value="<%=caixa.getId()%>">
		Cor: <input type="text" name="nome" value="<%=caixa.getCor()%>"readonly>
		<br>
		<input type="hidden" name="logica" value="RemoverCaixa">
		<button type="submit">remover</button>
	</form>
	<br />
	<br />
	<h6>OBS: Todas as revistas que estão na caixa também serão
		removidas.</h6>
</body>
</html>