package tp.daw.logica;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tp.daw.dao.CaixaDAO;
import tp.daw.modelo.Caixa;

public class AlterarCaixa implements Logica {
    @Override
    public String executa(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String cor = req.getParameter("cor");
        String mensagem;

        try {
            Caixa caixa = new Caixa();
            caixa.setId(Long.parseLong(id));
            caixa.setCor(cor);

            CaixaDAO caixaDAO = new CaixaDAO();
            caixaDAO.altera(caixa);

            mensagem = "Caixa alterada com sucesso!";
        } catch (Exception e) {
            mensagem = "Erro ao alterar a caixa: " + e.getMessage();
        }

        req.setAttribute("mensagem", mensagem);

        return "mensagem.jsp";
    }
}
