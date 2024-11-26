<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Clube da Leitura - Empréstimos em Atraso</title>
<style>
    .titulo-pag {
        font-size: 24px;
        font-weight: bold;
        margin-bottom: 20px;
        color: #333;
    }
    .tabela {
        width: 100%;
        border-collapse: collapse;
        margin-bottom: 20px;
    }
    .tabela th, .tabela td {
        border: 1px solid #ddd;
        padding: 8px;
        text-align: left;
    }
    .tabela th {
        background-color: #f4b084;
        color: #333;
    }
    .tabela tr:nth-child(even) {
        background-color: #f9f9f9;
    }
    .tabela tr:hover {
        background-color: #f1f1f1;
    }
</style>
</head>
<body>
    <div class="titulo-pag">Lista de Empréstimos em Atraso</div>

    <!-- Instância do DAO e obtenção dos empréstimos atrasados -->
    <jsp:useBean id="dao" class="tp.daw.dao.EmprestimoDAO" scope="request" />
    <c:set var="emprestimosAtrasados" value="${dao.getEmprestimosAtrasados()}" />

    <!-- Verifica se há empréstimos atrasados -->
    <c:choose>
        <c:when test="${not empty emprestimosAtrasados}">
            <table class="tabela">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Pessoa</th>
                        <th>Revista</th>
                        <th>Data do Empréstimo</th>
                        <th>Prazo de Devolução</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="emprestimo" items="${emprestimosAtrasados}">
                        <tr>
                            <td>${emprestimo.id}</td>
                            <td>${emprestimo.pessoa.nome}</td>
                            <td>${emprestimo.revista.colecao}</td>
                            <td>
                                <fmt:formatDate value="${emprestimo.dataEmprestimo.time}" pattern="dd/MM/yyyy" />
                            </td>
                            <td>
                                <fmt:formatDate value="${emprestimo.dataEmprestimo.time}" pattern="dd/MM/yyyy" />
                                + 10 dias
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:when>
        <c:otherwise>
            <p>Não há empréstimos em atraso no momento.</p>
        </c:otherwise>
    </c:choose>
</body>
</html>
