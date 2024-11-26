package tp.daw.logica;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tp.daw.dao.PessoaDAO;
import tp.daw.modelo.Pessoa;

public class BuscaPessoa implements Logica {

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pessoaId = req.getParameter("pessoaId");

        if (pessoaId != null && !pessoaId.isEmpty()) {
            try {
                PessoaDAO pessoaDao = new PessoaDAO();
                Pessoa pessoaSelecionada = pessoaDao.getPessoaById(Integer.parseInt(pessoaId));
                req.setAttribute("pessoaSelecionada", pessoaSelecionada);
            } catch (NumberFormatException e) {
                req.setAttribute("erro", "ID de pessoa inválido.");
            } catch (Exception e) {
                req.setAttribute("erro", "Erro ao buscar informações da pessoa.");
            }
        } else {
            req.setAttribute("erro", "ID de pessoa não informado.");
        }
        
        return "buscarpessoa.jsp";
    }
}

