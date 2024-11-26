<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Clube da Leitura - Empréstimos</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f4f4f4;
        margin: 0;
        padding: 0;
    }
    
    .titulo-pag {
        text-align: center;
        font-size: 2.5rem;
        margin-top: 20px;
        color: #333;
    }
    
    h3 {
        text-align: center;
        font-size: 1.8rem;
        margin-top: 20px;
        color: #333;
    }

    .container {
        width: 90%;
        max-width: 1200px;
        margin: 0 auto;
        padding: 20px;
    }

    .tabela {
        width: 100%;
        margin-bottom: 30px;
        border-collapse: collapse;
        background-color: #fff;
        box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
    }

    .tabela th, .tabela td {
        padding: 10px;
        text-align: center;
        border: 1px solid #ddd;
    }

    .tabela th {
        background-color: #ffdc8f;
        color: #333;
        font-size: 1.2rem;
    }

    .tabela tr:nth-child(even) {
        background-color: #f9f9f9;
    }

    .tabela tr:hover {
        background-color: #f1f1f1;
    }

    .form-container {
        text-align: center;
    }

    .form-container input[type="submit"] {
        background-color: #4CAF50;
        color: white;
        padding: 10px 20px;
        border: none;
        cursor: pointer;
        font-size: 1rem;
        margin: 5px;
    }

    .form-container input[type="submit"]:hover {
        background-color: #45a049;
    }

    /* Responsividade */
    @media screen and (max-width: 768px) {
        .tabela {
            width: 100%;
        }

        .titulo-pag {
            font-size: 2rem;
        }

        h3 {
            font-size: 1.5rem;
        }
    }
</style>
</head>
<body>
    <c:import url="cabecalho.jsp"/>

    <jsp:useBean id="dao" class="tp.daw.dao.EmprestimoDAO"/>
    
    <div class="container">
        <!-- Exibir empréstimos em aberto -->
        <div>
            <h3>Empréstimos em Aberto</h3>
            <table class="tabela">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Coleção</th>
                        <th>N° Edição</th>
                        <th>Ano</th>
                        <th>Caixa</th>
                        <th>Pessoa</th>
                        <th>Data Empréstimo</th>
                        <th>Ação</th>
                    </tr>
                </thead>
                <c:forEach var="emprestimo" items="${dao.getEmprestimosEmAberto()}" varStatus="id">
                    <tr>
                        <td>${emprestimo.revista.id}</td>
                        <td>${emprestimo.revista.colecao}</td>
                        <td>${emprestimo.revista.numeroEdicao}</td>
                        <td>${emprestimo.revista.anoRevista}</td>
                        <td>${emprestimo.revista.caixa.cor}</td>
                        <td>${emprestimo.pessoa.nome}</td>
                        <td><fmt:formatDate value="${emprestimo.dataEmprestimo.time}" pattern="dd/MM/yyyy"/></td>
                        <td class="form-container">
                            <form action="controladora" method="post">
                                <input type="hidden" name="idEmprestimo" value="${emprestimo.id}">
                                <input type="hidden" name="logica" value="DevolveRevista">
                                <input type="submit" value="Devolver">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>

        <!-- Exibir empréstimos atrasados -->
        <div>
            <h3>Empréstimos Atrasados</h3>
            <table class="tabela">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Coleção</th>
                        <th>N° Edição</th>
                        <th>Ano</th>
                        <th>Caixa</th>
                        <th>Pessoa</th>
                        <th>Data Empréstimo</th>
                        <th>Ação</th>
                    </tr>
                </thead>
                <c:forEach var="emprestimo" items="${dao.getEmprestimosAtrasados()}" varStatus="id">
                    <tr>
                        <td>${emprestimo.revista.id}</td>
                        <td>${emprestimo.revista.colecao}</td>
                        <td>${emprestimo.revista.numeroEdicao}</td>
                        <td>${emprestimo.revista.anoRevista}</td>
                        <td>${emprestimo.revista.caixa.cor}</td>
                        <td>${emprestimo.pessoa.nome}</td>
                        <td><fmt:formatDate value="${emprestimo.dataEmprestimo.time}" pattern="dd/MM/yyyy"/></td>
                        <td class="form-container">
                            <form action="controle" method="post">
                                <input type="hidden" name="idEmprestimo" value="${emprestimo.id}">
                                <input type="hidden" name="logica" value="DevolveRevista">
                                <input type="submit" value="Devolver">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>

</body>
</html>
