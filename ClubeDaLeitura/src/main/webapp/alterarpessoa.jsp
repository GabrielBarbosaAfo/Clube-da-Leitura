<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="tp.daw.modelo.Pessoa"%>
<%@ include file="autenticacao.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Alterar</title>
</head>
<body>
	<form class="container mt-5" action="controladora" method="post">
		<h1>Alterar Pessoa</h1>
		<%
		Pessoa pessoa = (Pessoa) request.getAttribute("pessoa");
		if (pessoa != null) {
		%>
		<input type="hidden" name="id" value="<%=pessoa.getId()%>">
		Nome: <input type="text" name="nome" value="<%=pessoa.getNome()%>"><br>
		Telefone: <input type="text" name="telefone"
			value="<%=pessoa.getTelefone()%>"><br> <input
			type="hidden" name="logica" value="AlterarPessoa">
		<button type="submit">Alterar</button>
		<%
		} else {
		%>
		<p>Pessoa não encontrada.</p>
		<%
		}
		%>
	</form>
</body>
</html>