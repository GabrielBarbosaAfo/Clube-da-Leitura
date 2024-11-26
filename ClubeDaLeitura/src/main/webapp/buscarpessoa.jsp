<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="tp.daw.modelo.Pessoa"%>
<%@ include file="autenticacao.jsp" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<title>Buscar Pessoa</title>

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

    .error-message {
        color: red;
        font-weight: bold;
        text-align: center;
    }

    .result-details {
        background-color: #e9ecef;
        padding: 20px;
        border-radius: 5px;
        margin-top: 20px;
    }

    .result-details p {
        font-size: 1.1rem;
    }
</style>

</head>
<body>

<div class="container">
    <h1 class="form-title">Buscar Pessoa</h1>
    
    <form action="controladora" method="post">
        <jsp:useBean id="pessoaDao" class="tp.daw.dao.PessoaDAO" />
        
        <!-- Campo para Seleção da Pessoa -->
        <div class="form-group">
            <label for="pessoaId">Pessoa</label>
            <select name="pessoaId" id="pessoaId" class="form-control input-txt" required="required">
                <c:forEach var="pessoa" items="${pessoaDao.getListaPessoas()}">
                    <option value="${pessoa.id}">${pessoa.nome}</option>
                </c:forEach>
            </select>
        </div>
        
        <!-- Campo oculto para a lógica -->
        <input type="hidden" name="logica" value="BuscaPessoa">
        
        <!-- Botão de Submissão -->
        <button type="submit" class="btn btn-custom">Buscar</button>
    </form>
    
    <!-- Detalhes da Pessoa -->
    <c:if test="${not empty pessoaSelecionada}">
        <div class="result-details">
            <h2>Detalhes da Pessoa</h2>
            <p><strong>ID:</strong> ${pessoaSelecionada.id}</p>
            <p><strong>Nome:</strong> ${pessoaSelecionada.nome}</p>
            <p><strong>Telefone:</strong> ${pessoaSelecionada.telefone}</p>
        </div>
    </c:if>

    <!-- Mensagem de erro -->
    <c:if test="${not empty erro}">
        <p class="error-message">${erro}</p>
    </c:if>
</div>

</body>
</html>
