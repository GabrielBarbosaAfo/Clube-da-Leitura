<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="autenticacao.jsp" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<title>Empréstimo</title>

<!-- Adicionando o Bootstrap -->
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-pzjw8f+ua7Kw1TIq0v8Fq/ATKgp7gO7x59r0w5FfnU2Ez4vZUl4ITyptRO88w7y3" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zyYPt6g9C6A8blf9Xppg5g5zd7y2Fw5J1W9k1bI9" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-EAXkAik/4+FC8MvUSklHtZ0NzjB8s9hZh+l9/c7l6P/x6fD6sdybT7J7mZGvxLtx" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-w1CzZmf+4ftHYB2r7MFw0bRjfdgwkC5b7OEv3k51Plbz4jS3nqQ7D5RgM2JzPiJy" crossorigin="anonymous"></script>

<style>
    body {
        background-color: #f8f9fa;
        font-family: 'Arial', sans-serif;
    }

    .container {
        margin-top: 50px;
        max-width: 600px;
    }

    .form-title {
        font-size: 2rem;
        text-align: center;
        margin-bottom: 30px;
        color: #007bff;
    }

    .form-group label {
        font-weight: bold;
    }

    .input-txt {
        border: 1px solid #ced4da;
        border-radius: 4px;
        padding: 10px;
        width: 100%;
        margin-top: 5px;
    }

    .btn-custom {
        background-color: #28a745;
        color: white;
        width: 100%;
        padding: 10px;
        font-size: 1.1rem;
        border: none;
        border-radius: 5px;
        cursor: pointer;
    }

    .btn-custom:hover {
        background-color: #218838;
    }

    .readonly-input {
        background-color: #e9ecef;
        cursor: not-allowed;
    }

    .error-message {
        color: red;
        font-weight: bold;
        text-align: center;
    }
</style>

</head>
<body>

<div class="container">
    <h1 class="form-title">Empréstimo</h1>
    
    <form action="controladora" method="post">
        <jsp:useBean id="pessoaDao" class="tp.daw.dao.PessoaDAO" />
        
        <!-- Campo para Seleção de Pessoa -->
        <div class="form-group">
            <label for="pessoa">Pessoa</label>
            <select name="pessoa" id="pessoa" class="form-control input-txt" required="required">
                <c:forEach var="pessoa" items="${pessoaDao.getListaPessoas()}">
                    <c:if test="${pessoaDao.verificaPendencia(pessoa) eq -1}">
                        <option value="${pessoa.id}">${pessoa.nome}</option>
                    </c:if>
                </c:forEach>
            </select>
        </div>

        <!-- Campo para Seleção de Revista -->
        <div class="form-group">
            <label for="revista">Revista</label>
            <select name="revista" id="revista" class="form-control input-txt" required="required">
                <jsp:useBean id="revistaDao" class="tp.daw.dao.RevistaDAO" />
                <c:forEach var="revista" items="${revistaDao.getListaRevistas()}">
                    <c:if test="${revista.disponibilidade}">
                        <option value="${revista.id}">${revista.colecao} - N° ${revista.numeroEdicao}</option>
                    </c:if>
                </c:forEach>
            </select>
        </div>

        <!-- Campo para Data de Empréstimo -->
        <jsp:useBean id="dataAtual" class="java.util.Date" />
        <div class="form-group">
            <label for="dataEmprestimo">Data Empréstimo</label>
            <input type="text" id="dataEmprestimo" name="dataEmprestimo" class="form-control input-txt" required="required" 
                   value="<fmt:formatDate value="${dataAtual}" pattern="dd/MM/yyyy"/>" />
        </div>

        <!-- Campo para Data de Devolução -->
        <div class="form-group">
            <label for="dataDevolucao">Data Devolução</label>
            <input type="text" id="dataDevolucao" class="form-control readonly-input" value="10 dias após a data de empréstimo" readonly />
        </div>

        <!-- Campo Oculto para Lógica -->
        <input type="hidden" name="logica" value="EfetuarEmprestimo">

        <!-- Botão de Submissão -->
        <button type="submit" class="btn btn-custom">Gravar</button>
    </form>
</div>

</body>
</html>
