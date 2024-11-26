<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ include file="autenticacao.jsp" %>   

<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<title>Cadastro de Revista</title>

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
</style>
</head>
<body>

<div class="container">
    <h2 class="form-title">Cadastrar Revista</h2>
    <form action="controladora" method="post">

        <!-- Campo Coleção -->
        <div class="form-group">
            <label for="colecao">Coleção</label>
            <input type="text" class="form-control input-txt" name="colecao" id="colecao" placeholder="Digite a coleção" required>
        </div>

        <!-- Campo Edição -->
        <div class="form-group">
            <label for="edicao">Edição</label>
            <input type="text" class="form-control input-txt" name="edicao" id="edicao" placeholder="Digite a edição" required>
        </div>

        <!-- Campo Ano -->
        <div class="form-group">
            <label for="anoRevista">Ano da Revista</label>
            <input type="number" class="form-control input-txt" name="anoRevista" id="anoRevista" required>
        </div>

        <!-- Caixa -->
        <div class="form-group">
            <label for="caixa">Caixa</label>
            <select name="caixa" id="caixa" class="form-control select-box" required>
                <jsp:useBean id="caixaDao" class="tp.daw.dao.CaixaDAO" />
                <c:forEach var="caixa" items="${caixaDao.listaCaixas()}">
                    <option value="${caixa.id}">${caixa.cor}</option>
                </c:forEach>
            </select>
        </div>

        <!-- Campo escondido para lógica -->
        <input type="hidden" name="logica" value="AdicionaRevista">

        <!-- Botão de enviar -->
        <button type="submit" class="btn btn-custom mt-3">Cadastrar</button>
    </form>
</div>

</body>
</html>
