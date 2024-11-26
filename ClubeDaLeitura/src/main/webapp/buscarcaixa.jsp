<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="tp.daw.modelo.Caixa" %>
<%@ include file="autenticacao.jsp" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<title>Caixa</title>

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

    .select-box {
        margin-top: 5px;
    }

    .botao {
        background-color: #007bff;
        color: white;
        width: 100%;
        padding: 10px;
        font-size: 1.1rem;
        border: none;
        border-radius: 5px;
        cursor: pointer;
    }

    .botao:hover {
        background-color: #0056b3;
    }

    .error-message {
        color: red;
        text-align: center;
    }
</style>

</head>
<body>

<div class="container">
    <h1 class="form-title">Buscar Caixa</h1>
    
    <form action="controladora" method="post">
        <jsp:useBean id="CaixaDao" class="tp.daw.dao.CaixaDAO" />

        <!-- Seleção da Caixa -->
        <div class="form-group">
            <label for="caixaId">Caixa</label>
            <select name="caixaId" id="caixaId" required="required" class="form-control input-txt">
                <jsp:useBean id="caixaDao" class="tp.daw.dao.CaixaDAO" />
                <c:forEach var="caixa" items="${caixaDao.listaCaixas()}">
                    <option value="${caixa.id}">${caixa.cor}</option>
                </c:forEach>
            </select>
        </div>

        <input type="hidden" name="logica" value="BuscaCaixa">
        <button type="submit" class="btn btn-custom">Buscar</button>
    </form>

    <!-- Exibição dos Detalhes da Caixa -->
    <c:if test="${not empty caixaSelecionada}">
        <h2 class="form-title">Detalhes da Caixa</h2>
        <p><strong>ID:</strong> ${caixaSelecionada.id}</p>
        <p><strong>Cor:</strong> ${caixaSelecionada.cor}</p>
    </c:if>

    <!-- Exibição de erros -->
    <c:if test="${not empty erro}">
        <div class="error-message">
            <p>${erro}</p>
        </div>
    </c:if>
</div>

</body>
</html>
