<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="autenticacao.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Menu Principal</title>
<!-- Bootstrap CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
    <h1 class="text-center mb-4">Menu Principal</h1>
    
    <!-- Menu de Revistas -->
    <div class="card mb-3">
        <div class="card-header bg-primary text-white">
            <h4>Revistas</h4>
        </div>
        <div class="list-group">
            <a href="cadastrarrevista.jsp" class="list-group-item list-group-item-action">Cadastrar Revista</a>
            <a href="buscarrevista.jsp" class="list-group-item list-group-item-action">Buscar Revista</a>
            <a href="verificaidrevistaremocao.jsp" class="list-group-item list-group-item-action">Remover Revista</a>
            <a href="verificaidrevistaalteracao.jsp" class="list-group-item list-group-item-action">Alterar Revista</a>
        </div>
    </div>
    
    <!-- Menu de Caixas -->
    <div class="card mb-3">
        <div class="card-header bg-success text-white">
            <h4>Caixas</h4>
        </div>
        <div class="list-group">
            <a href="cadastrarcaixa.jsp" class="list-group-item list-group-item-action">Cadastrar Caixa</a>
            <a href="buscarcaixa.jsp" class="list-group-item list-group-item-action">Buscar Caixa</a>
            <a href="verificaidcaixaremocao.jsp" class="list-group-item list-group-item-action">Remover Caixa</a>
            <a href="verificaidcaixaalteracao.jsp" class="list-group-item list-group-item-action">Alterar Caixa</a>
        </div>
    </div>
    
    <!-- Menu de Pessoas -->
    <div class="card mb-3">
        <div class="card-header bg-warning text-white">
            <h4>Pessoas</h4>
        </div>
        <div class="list-group">
            <a href="cadastrarpessoa.jsp" class="list-group-item list-group-item-action">Cadastrar Pessoa</a>
            <a href="buscarpessoa.jsp" class="list-group-item list-group-item-action">Buscar Pessoa</a>
            <a href="verificaidpessoaremocao.jsp" class="list-group-item list-group-item-action">Remover Pessoa</a>
            <a href="verificaidpessoaalteracao.jsp" class="list-group-item list-group-item-action">Alterar Pessoa</a>
        </div>
    </div>
    
    <!-- Menu de Empréstimos -->
    <div class="card mb-3">
        <div class="card-header bg-danger text-white">
            <h4>Empréstimos</h4>
        </div>
        <div class="list-group">
            <a href="efetuaremprestimo.jsp" class="list-group-item list-group-item-action">Efetuar Empréstimo</a>
            <a href="buscaremprestimo.jsp" class="list-group-item list-group-item-action">Buscar Empréstimo</a>
            <a href="verificaidemprestimoremocao.jsp" class="list-group-item list-group-item-action">Remover Empréstimo</a>
            <a href="verificaidemprestimoalteracao.jsp" class="list-group-item list-group-item-action">Alterar Empréstimo</a>
        </div>
    </div>
    
    <!-- Menu de Listagem -->
    <div class="card mb-3 margin">
        <div class="card-header bg-info text-white">
            <h4>Listagem</h4>
        </div>
        <div class="list-group ">
            <a href="listarevistasemprestadas.jsp" class="list-group-item list-group-item-action">Empréstimos</a>
            <a href="listarevistapessoa.jsp" class="list-group-item list-group-item-action">Revistas Emprestadas</a>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
