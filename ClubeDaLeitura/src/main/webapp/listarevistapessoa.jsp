<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Clube da Leitura - Revistas Emprestadas</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f4f7f6;
        margin: 0;
        padding: 0;
    }
    .titulo-pag {
        font-size: 24px;
        text-align: center;
        margin-top: 30px;
        color: #333;
    }
    table {
        width: 80%;
        margin: 30px auto;
        border-collapse: collapse;
        background-color: #fff;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        border-radius: 8px;
    }
    th, td {
        padding: 12px 15px;
        text-align: center;
        border: 1px solid #ddd;
    }
    th {
        background-color: #ffdc8f;
        color: #333;
        font-weight: bold;
    }
    td {
        color: #555;
    }
    tr:nth-child(even) {
        background-color: #f9f9f9;
    }
    tr:hover {
        background-color: #f1f1f1;
    }
    .data-column {
        width: 150px;
    }
</style>
</head>
<body>

<section class="titulo-pag">Revistas Emprestadas por Pessoa</section><br>

<jsp:useBean id="dao" class="tp.daw.dao.EmprestimoDAO"/>
<table>
    <thead>
        <tr>
            <th>Pessoa</th>
            <th>Revista</th>
            <th class="data-column">Data do Empréstimo</th>
            <th class="data-column">Data de Devolução</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="emprestimo" items="${dao.getListaEmprestimos()}">
            <tr>
                <td>${emprestimo.pessoa.nome}</td>
                <td>${emprestimo.revista.colecao}</td>
                <td><fmt:formatDate value="${emprestimo.dataEmprestimo.time}" pattern="dd/MM/yyyy"/></td>
                <td>
                    <c:choose>
                        <c:when test="${emprestimo.dataDevolucao != null}">
                            <fmt:formatDate value="${emprestimo.dataDevolucao.time}" pattern="dd/MM/yyyy"/>
                        </c:when>
                        <c:otherwise>Não devolvido</c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

</body>
</html>
