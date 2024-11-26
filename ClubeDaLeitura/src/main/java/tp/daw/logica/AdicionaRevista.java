package tp.daw.logica;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tp.daw.dao.CaixaDAO;
import tp.daw.dao.RevistaDAO;
import tp.daw.modelo.Revista;

public class AdicionaRevista implements Logica {
    @Override
    public String executa(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String colecao = req.getParameter("colecao");
        String edicao = req.getParameter("edicao");
        String anoRevista = req.getParameter("anoRevista");
        String caixa = req.getParameter("caixa");
        String mensagem;

        try {
            CaixaDAO caixaDAO = new CaixaDAO();

            // Recupera o objeto caixa associado
            Long caixaId = Long.parseLong(caixa);
            caixaDAO.recuperaCaixa(caixaId);

            Revista revista = new Revista();
            revista.setColecao(colecao);
            revista.setNumeroEdicao(Long.parseLong(edicao));
            revista.setAnoRevista(Integer.parseInt(anoRevista));
            revista.setDisponibilidade(true);
            revista.setCaixa(caixaDAO.recuperaCaixa(caixaId));

            RevistaDAO revistaDAO = new RevistaDAO();
            revistaDAO.adicionar(revista);

            // Mensagem de sucesso
            mensagem = "Revista adicionada com sucesso!";
        } catch (Exception e) {
            // Mensagem de erro caso algo aconteça
            mensagem = "Erro ao adicionar a revista: " + e.getMessage();
        }

        // Adiciona a mensagem na requisição para exibição no JSP
        req.setAttribute("mensagem", mensagem);

        // Retorna o nome do JSP para exibir a mensagem
        return "mensagem.jsp";
    }
}
