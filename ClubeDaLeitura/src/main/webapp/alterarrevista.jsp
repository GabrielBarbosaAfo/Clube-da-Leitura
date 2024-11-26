<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="tp.daw.modelo.Revista"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   
<%@ include file="autenticacao.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Revista</title>
</head>
<body>
	<form class="container mt-5" action="controladora" method="post">
		<h1>Alterar Revista</h1>
		<%
		Revista revista = (Revista) request.getAttribute("revista");
		if (revista != null) {
		%>
		<input type="hidden" name="id" value="<%=revista.getId()%>">
		Coleção:<input type="text" name="colecao" value="<%=revista.getColecao()%>"> 
		Edição:<input type="text" name="edicao" value="<%=revista.getNumeroEdicao()%>"> 
		Ano revista:<input type="number" name="anoRevista" value="<%=revista.getAnoRevista()%>" required>

		<div>
			<p>Caixa</p>
			<select name="caixa" required="required" class="input-txt">
				<jsp:useBean id="caixaDao" class="tp.daw.dao.CaixaDAO" />
				<c:forEach var="caixa" items="${caixaDao.listaCaixas()}">
					<option value="${caixa.id}">${caixa.cor}</option>
				</c:forEach>
			</select><br />
			<br />
		</div>
		<input type="hidden" hidden="true" name="logica" value="AlterarRevista">
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