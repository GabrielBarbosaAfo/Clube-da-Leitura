<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="autenticacao.jsp" %>
<!DOCTYPE html>
<html>
<head>
<title>Clube da Leitura - Devolver Revista</title>
</head>
<body>
	<div class="container">
		<h1>Devolução de Revista</h1>

		<!-- Exibe mensagens de sucesso ou erro -->
		<c:if test="${not empty mensagemSucesso}">
			<div class="alert alert-success">${mensagemSucesso}</div>
		</c:if>
		<c:if test="${not empty mensagemErro}">
			<div class="alert alert-danger">${mensagemErro}</div>
		</c:if>

		<!-- Formulário para devolução -->
		<form action="controladora" method="post">
			<div class="form-group">
				<label for="idEmprestimo">ID do Empréstimo:</label> <input
					type="text" id="idEmprestimo" name="idEmprestimo"
					class="form-control" required>
			</div>
			<input type="hidden" name="logica" value="DevolveRevista">
			<button type="submit" class="btn btn-primary">Devolver
				Revista</button>
		</form>

		<h2>Empréstimos em Aberto</h2>
		<c:if test="${not empty emprestimosEmAberto}">
			<table class="table table-striped">
				<thead>
					<tr>
						<th>ID</th>
						<th>Pessoa</th>
						<th>Revista</th>
						<th>Data de Empréstimo</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="emprestimo" items="${emprestimosEmAberto}">
						<tr>
							<td>${emprestimo.id}</td>
							<td>${emprestimo.pessoa.nome}</td>
							<td>${emprestimo.revista.colecao}- Edição
								${emprestimo.revista.numeroEdicao}</td>
							<td><fmt:formatDate
									value="${emprestimo.dataEmprestimo.time}" pattern="dd/MM/yyyy" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
		<c:if test="${empty emprestimosEmAberto}">
			<div class="alert alert-info">Nenhum empréstimo em aberto no
				momento.</div>
		</c:if>

	</div>
</body>
</html>
