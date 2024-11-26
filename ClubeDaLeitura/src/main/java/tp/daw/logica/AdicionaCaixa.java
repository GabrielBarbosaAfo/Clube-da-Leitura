package tp.daw.logica;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tp.daw.dao.CaixaDAO;
import tp.daw.modelo.Caixa;

public class AdicionaCaixa implements Logica {

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cor = req.getParameter("cor");
        String mensagem;

        try {
            Caixa caixa = new Caixa();
            caixa.setCor(cor);

            CaixaDAO caixaDAO = new CaixaDAO();
            caixaDAO.adiciona(caixa);

            mensagem = "Caixa adicionada com sucesso!";
        } catch (Exception e) {
            mensagem = "Erro ao adicionar a caixa: " + e.getMessage();
        }

        req.setAttribute("mensagem", mensagem);

        // Retorna o nome da página JSP para exibir a mensagem
        return "mensagem.jsp";
    }
}
