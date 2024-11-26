<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="tp.daw.modelo.Pessoa"%>
<%@ include file="autenticacao.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Remoção</title>
</head>
<body>
	<form class="container mt-5" action="controladora" method="post">
		<h1>Remover Pessoa</h1>
		<%
		Pessoa pessoa = (Pessoa) request.getAttribute("pessoa");
		%>
		<input type="hidden" name="id" value="<%=pessoa.getId()%>">
		Nome: <input type="text" name="nome" value="<%=pessoa.getNome()%>"
			readonly><br> Telefone: <input type="text"
			name="telefone" value="<%=pessoa.getTelefone()%>" readonly><br>
		<input type="hidden" name="logica" value="RemoverPessoa">
		<button type="submit">remover</button>
	</form>
	<br />
	<br />
	<h6>OBS: Todos os empréstimos também serão removidos.</h6>
</body>
</html>