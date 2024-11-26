package tp.daw.logica;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tp.daw.dao.EmprestimoDAO;
import tp.daw.dao.PessoaDAO;
import tp.daw.dao.RevistaDAO;
import tp.daw.modelo.Emprestimo;
import tp.daw.modelo.Pessoa;

public class RemoverPessoa implements Logica{

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = "mensagem.jsp";
		
		Pessoa pessoa = new Pessoa();
		pessoa.setId(Long.parseLong(req.getParameter("id")));
		
		//Verifica se a pessoa possui alguma pendência (revista emprestada) e realiza a devolução da mesma.
		PessoaDAO dao = new PessoaDAO();
		long pendencia = dao.verificaPendencia(pessoa);
		if(pendencia != -1L) {
			EmprestimoDAO emprestimoDao = new EmprestimoDAO();
			Emprestimo emprestimo = emprestimoDao.getEmprestimoById(pendencia);
			
			RevistaDAO revistaDAO = new RevistaDAO();
			revistaDAO.alterarDisponibilidade(emprestimo.getRevista().getId());
		}
			
		dao.remover(pessoa);
		
		req.setAttribute("mensagem", "Amigo removido com sucesso!");
		
		return url;
	}

}
