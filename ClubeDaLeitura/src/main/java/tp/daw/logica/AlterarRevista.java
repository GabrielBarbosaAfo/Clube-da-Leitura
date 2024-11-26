package tp.daw.logica;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tp.daw.dao.CaixaDAO;
import tp.daw.dao.RevistaDAO;
import tp.daw.modelo.Revista;

public class AlterarRevista implements Logica {

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String colecao = req.getParameter("colecao");
        String id = req.getParameter("id");
        String edicao = req.getParameter("edicao");
        String anoRevista = req.getParameter("anoRevista");
        String caixa = req.getParameter("caixa");
        String mensagem;

        try {
            // Instancia a classe Revista
            CaixaDAO caixaDAO = new CaixaDAO();
            Revista revista = new Revista();
            revista.setId(Long.parseLong(id));
            revista.setColecao(colecao);
            revista.setNumeroEdicao(Long.parseLong(edicao));
            revista.setAnoRevista(Integer.parseInt(anoRevista));
            revista.setDisponibilidade(true);
            revista.setCaixa(caixaDAO.recuperaCaixa(Long.parseLong(caixa)));

            // Instancia a classe RevistaDAO e chama o método de alteração
            RevistaDAO revistaDAO = new RevistaDAO();
            revistaDAO.alterar(revista);

            // Mensagem de sucesso
            mensagem = "Revista alterada com sucesso!";
        } catch (Exception e) {
            // Mensagem de erro em caso de exceção
            mensagem = "Erro ao alterar a revista: " + e.getMessage();
        }

        // Adiciona a mensagem na requisição para ser exibida no JSP
        req.setAttribute("mensagem", mensagem);

        // Retorna o nome do JSP que exibirá a mensagem
        return "mensagem.jsp";
    }
}
