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
<title>Remoção de Empréstimo</title>
</head>
<body>
    <form class="container mt-5" action="controladora" method="post">
        <h1>Remover Empréstimo</h1>
        <%
        Emprestimo emprestimo = (Emprestimo) request.getAttribute("emprestimo");
        %>
        <input type="hidden" name="id" value="<%=emprestimo.getId()%>">

        Pessoa: <input type="text" name="pessoa" value="<%=emprestimo.getPessoa().getNome()%>" readonly><br>
        Revista: <input type="text" name="revista" value="<%=emprestimo.getRevista().getColecao()%>" readonly><br>
        Data Empréstimo: <input type="text" name="dataEmprestimo" value="<fmt:formatDate value="${emprestimo.dataEmprestimo.time}" pattern="dd/MM/yyyy"/>" readonly /><br>

        <div>
            Data Devolução:
            <c:choose>
                <c:when test="${empty emprestimo.dataDevolucao}">
                    <input type="text" name="dataDevolucao" class="input-txt" value="Empréstimo pendente" readonly />
                </c:when>
                <c:otherwise>
                    <input type="text" name="dataDevolucao" class="input-txt" value="<fmt:formatDate value="${emprestimo.dataDevolucao.time}" pattern="dd/MM/yyyy"/>" readonly />
                </c:otherwise>
            </c:choose>
        </div>
        <input type="hidden" name="logica" value="RemoverEmprestimo">
        <button type="submit">Remover</button>
    </form>
    <br />
    <h6>OBS: Todos os empréstimos relacionados também serão removidos.</h6>
</body>
</html>
