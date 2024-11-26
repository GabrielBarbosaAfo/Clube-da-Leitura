<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="tp.daw.modelo.Emprestimo"%>
<%@ include file="autenticacao.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Emprestimo</title>
</head>
<body>
	<form class="container mt-5" action="controladora" method="post">
		<h1>Alterar Emprestimo</h1>
		<%
		Emprestimo emprestimo = (Emprestimo) request.getAttribute("emprestimo");
		if (emprestimo != null) {
		%>
		<input type="hidden" name="id" value="<%=emprestimo.getId()%>">
		<div>
			<p>Pessoa</p>
			<select name="pessoa" required="required" class="input-txt">
				<jsp:useBean id="pessoaDao" class="tp.daw.dao.PessoaDAO" />
				<c:forEach var="pessoa" items="${pessoaDao.getListaPessoas()}">
					<option value="${pessoa.id}">${pessoa.nome}</option>
				</c:forEach>
			</select><br /> <br />
		</div>

		<div>
			<p>Revista</p>
			<select name="revista" required="required" class="input-txt">
				<jsp:useBean id="revistaDao" class="tp.daw.dao.RevistaDAO" />
				<c:forEach var="revista" items="${revistaDao.getListaRevistas()}">
					<option value="${revista.id}">${revista.colecao}</option>
				</c:forEach>
			</select><br /> <br />
		</div>
		<p>Data Empréstimo</p>
		<input type="text" name="dataEmprestimo" class="input-txt"
			required="required"
			value="<fmt:formatDate value="${emprestimo.dataEmprestimo.time}" pattern="dd/MM/yyyy"/>" />

		<p>Data Devolução</p>

		<c:choose>
			<c:when test="${empty emprestimo.dataDevolucao}">
				<input type="text" class="input-txt" value="Empréstimo pendente"
					readonly />
			</c:when>
			<c:otherwise>
				<input type="text" class="input-txt"
					value="<fmt:formatDate value="${emprestimo.dataDevolucao.time}" pattern="dd/MM/yyyy"/>" />
			</c:otherwise>
		</c:choose>

		<input type="hidden" hidden="true" name="logica"
			value="AlterarEmprestimo">
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