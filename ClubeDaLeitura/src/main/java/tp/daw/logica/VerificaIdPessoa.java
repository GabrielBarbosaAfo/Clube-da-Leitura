package tp.daw.logica;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tp.daw.dao.PessoaDAO;
import tp.daw.modelo.Pessoa;

public class VerificaIdPessoa implements Logica{
	@Override
	public String executa(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 String id = req.getParameter("id");
		 String url = req.getParameter("url");
		 String erro = "erro.jsp";
	        try {
	            Pessoa pessoa = new Pessoa();
	            pessoa.setId(Long.parseLong(id));
	            
	            PessoaDAO pessoaDAO = new PessoaDAO();
	            Pessoa pessoaRecuperado = pessoaDAO.recuperaPessoa(pessoa);
	            
	            if (pessoaRecuperado != null) {
	                req.setAttribute("pessoa", pessoaRecuperado);
	                
	                return url;
	            } else {
	                req.setAttribute("errorMessage", "Contato não encontrado.");
	                return erro;
	            }
	        } catch (NumberFormatException e) {
	            e.printStackTrace();
	            req.setAttribute("errorMessage", "ID inválido.");
	            return erro;
	        } catch (Exception e) {
	            e.printStackTrace();
	            req.setAttribute("errorMessage", "Erro ao recuperar o contato.");
	            return erro;
	        }
	}
}
