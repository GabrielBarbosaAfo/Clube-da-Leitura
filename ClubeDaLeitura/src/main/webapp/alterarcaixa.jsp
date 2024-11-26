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
		<h1>Alterar Caixa</h1>
		<%
		Caixa caixa = (Caixa) request.getAttribute("caixa");
		if (caixa != null) {
		%>
		<input type="hidden" name="id" value="<%=caixa.getId()%>">
		Nome: <input type="text" name="cor" value="<%=caixa.getCor()%>"><br>
		<input type="hidden" name="logica" value="AlterarCaixa">
		<button type="submit">Alterar</button>
		<%
		} else {
		%>
		<p>Caixa não encontrada.</p>
		<%
		}
		%>
</form>
</body>
</html>