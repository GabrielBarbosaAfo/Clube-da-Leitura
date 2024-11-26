<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<%@ include file="autenticacao.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Caixa</title>
</head>
<body>
<form class="container mt-5" action="controladora" method="post">
	<div>
		<p>Caixa</p> <select name="caixa" required="required" class="input-txt">
			<jsp:useBean id="caixaDao" class="tp.daw.dao.CaixaDAO" />
			<c:forEach var="caixa" items="${caixaDao.listaCaixas()}">
				<option value="${caixa.id}">${caixa.cor}</option>
			</c:forEach>
		</select><br/><br/>
	</div>
	<input type="text" hidden="true" name="url" value="removercaixa.jsp">
	<input type="hidden" hidden="true" name="logica" value="VerificaIdCaixa">
	<button type="submit">Alterar</button>
</form>
</body>
</html>