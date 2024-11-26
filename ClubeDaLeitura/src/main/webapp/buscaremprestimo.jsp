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
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f4f7f6;
        margin: 0;
        padding: 0;
    }
    h2 {
        color: #333;
    }
    .container {
        width: 80%;
        margin: 20px auto;
        padding: 20px;
        background-color: #fff;
        border-radius: 8px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }
    form {
        display: flex;
        flex-direction: column;
    }
    label {
        font-size: 16px;
        margin-bottom: 5px;
        font-weight: bold;
    }
    select, input[type="text"], input[type="submit"] {
        padding: 10px;
        margin: 10px 0;
        border: 1px solid #ccc;
        border-radius: 4px;
        font-size: 14px;
    }
    input[type="submit"] {
        background-color: #4CAF50;
        color: white;
        cursor: pointer;
    }
    input[type="submit"]:hover {
        background-color: #45a049;
    }
    .input-txt {
        width: 100%;
    }
    .botao {
        background-color: #007BFF;
        color: white;
        font-weight: bold;
        border: none;
        padding: 10px;
        cursor: pointer;
        border-radius: 5px;
    }
    .botao:hover {
        background-color: #0056b3;
    }
    .details p {
        margin: 5px 0;
    }
    .details p strong {
        color: #333;
    }
    .error {
        color: red;
        font-weight: bold;
    }
</style>
</head>
<body>

<div class="container">
    <form action="controladora" method="post">
        <jsp:useBean id="emprestimoDao" class="tp.daw.dao.EmprestimoDAO" />

        <div>
            <label for="emprestimoId">Selecione o Emprestimo:</label>
            <select name="emprestimoId" required="required" class="input-txt revista-nome">
                <c:forEach var="emprestimo" items="${emprestimoDao.getListaEmprestimos()}">
                    <option value="${emprestimo.id}">${emprestimo.pessoa.nome}</option>
                </c:forEach>
            </select>
        </div>

        <input type="hidden" name="logica" value="BuscaEmprestimo"> 
        <input type="submit" value="Buscar" class="botao" />
    </form>

    <c:if test="${not empty emprestimoSelecionado}">
        <div class="details">
            <h2>Detalhes do Emprestimo</h2>
            <p><strong>ID:</strong> ${emprestimoSelecionado.id}</p>
            <p><strong>Pessoa:</strong> ${emprestimoSelecionado.pessoa.nome}</p>
            <p><strong>Revista:</strong> ${emprestimoSelecionado.revista.colecao}</p>
            <p><strong>Data Emprestimo:</strong> 
                <fmt:formatDate value="${emprestimoSelecionado.dataEmprestimo.time}" pattern="dd/MM/yyyy" />
            </p>
            <p><strong>Data Devolução:</strong>
                <c:choose>
                    <c:when test="${empty emprestimoSelecionado.dataDevolucao}">
                        Empréstimo pendente
                    </c:when>
                    <c:otherwise>
                        <fmt:formatDate value="${emprestimoSelecionado.dataDevolucao.time}" pattern="dd/MM/yyyy" />
                    </c:otherwise>
                </c:choose>
            </p>
        </div>
    </c:if>

    <c:if test="${not empty erro}">
        <p class="error">${erro}</p>
    </c:if>
</div>

</body>
</html>
