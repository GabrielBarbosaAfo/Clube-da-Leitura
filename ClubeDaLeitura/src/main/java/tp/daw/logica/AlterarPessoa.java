package tp.daw.logica;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tp.daw.dao.PessoaDAO;
import tp.daw.modelo.Pessoa;

public class AlterarPessoa implements Logica {

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String nome = req.getParameter("nome");
        String telefone = req.getParameter("telefone");
        String mensagem;

        try {
            Pessoa pessoa = new Pessoa();
            pessoa.setId(Long.parseLong(id));
            pessoa.setNome(nome);
            pessoa.setTelefone(telefone);

            PessoaDAO pessoaDAO = new PessoaDAO();
            pessoaDAO.alterar(pessoa);

            // Mensagem de sucesso
            mensagem = "Pessoa alterada com sucesso!";
        } catch (Exception e) {
            // Mensagem de erro em caso de exceção
            mensagem = "Erro ao alterar a pessoa: " + e.getMessage();
        }

        // Adiciona a mensagem na requisição para exibição no JSP
        req.setAttribute("mensagem", mensagem);

        // Retorna o nome do JSP que exibirá a mensagem
        return "mensagem.jsp";
    }
}
