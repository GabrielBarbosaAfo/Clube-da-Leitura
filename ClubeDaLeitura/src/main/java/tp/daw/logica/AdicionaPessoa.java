package tp.daw.logica;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tp.daw.dao.PessoaDAO;
import tp.daw.modelo.Pessoa;

public class AdicionaPessoa implements Logica {

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nome = req.getParameter("nome");
        String telefone = req.getParameter("telefone");
        String mensagem;

        try {
            Pessoa pessoa = new Pessoa();
            pessoa.setNome(nome);
            pessoa.setTelefone(telefone);
            
            PessoaDAO pessoaDAO = new PessoaDAO();
            pessoaDAO.adicionar(pessoa);

            mensagem = "Pessoa adicionada com sucesso!";
        } catch (Exception e) {
            mensagem = "Erro ao adicionar a pessoa: " + e.getMessage();
        }

        // Adiciona a mensagem como atributo da requisição
        req.setAttribute("mensagem", mensagem);

        // Retorna o nome da página JSP que exibirá a mensagem
        return "mensagem.jsp";
    }

}
