<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="tp.daw.modelo.Revista"%>
<%@ include file="autenticacao.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Revista</title>
</head>
<body>
	<form class="container mt-5" action="controladora" method="post">
		<h1>Remover Revista</h1>
		<%
		Revista revista = (Revista) request.getAttribute("revista");
		%>
		<input type="hidden" name="id" value="<%=revista.getId()%>">
		Colecao: <input type="text" name="colecao" value="<%=revista.getColecao()%>" readonly><br>
		Edicao: <input type="text" name="edicao" value="<%=revista.getNumeroEdicao()%>" readonly><br>
		Ano: <input type="text" name="ano" value="<%=revista.getAnoRevista()%>" readonly><br>
		Caixa: <input type="text" name="caixa" value="<%=revista.getCaixa().getId()%>" readonly><br>
		<input type="hidden" name="logica" value="RemoverCaixa">
		<button type="submit">remover</button>
	</form>
	<br />
	<br />
	<h6>OBS: Todos os empréstimos também serão removidos.</h6>
</body>
</html>