<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<style>
    /* Garante que o conteúdo será centralizado verticalmente na tela inteira */
    .container-center {
        min-height: 100vh;
    }
</style>
</head>
<body>
<form action="controladora" method="post">
    <div class="container container-center d-flex justify-content-center align-items-center">
        <div class="form-signin text-center">
            <div class="mb-4">
                <img class="mb-4" src="imagens/Logo_Leitura.png" alt="Logo" width="400" >
                <h1 class="h3 mb-3 font-weight-normal">Login</h1>
            </div>

            <div class="form-label-group mb-3">
                <input type="text" id="inputEmail" name="login" class="form-control" placeholder="Usuario" required autofocus>
            </div>

            <div class="form-label-group mb-3">
                <input type="password" id="inputPassword" name="senha" class="form-control" placeholder="Senha" required>
            </div>

			<input type="hidden" name="logica" value="EfetuaLogin">
            <button class="btn btn-lg btn-primary btn-block" type="submit">Entrar</button>
            <p class="mt-5 mb-3 text-muted">&copy; <%= java.util.Calendar.getInstance().get(java.util.Calendar.YEAR) %></p>

        </div>
    </div>
</form>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>
