package tp.daw.logica;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tp.daw.dao.EmprestimoDAO;
import tp.daw.dao.RevistaDAO;
import tp.daw.modelo.Emprestimo;

public class RemoverEmprestimo implements Logica{

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = "mensagem.jsp";
		
		EmprestimoDAO emprestimoDao = new EmprestimoDAO();
		Emprestimo emprestimo = emprestimoDao.getEmprestimoById(Long.parseLong(req.getParameter("id")));
		
		if(emprestimo.getDataDevolucao() == null) {
			RevistaDAO revistaDao = new RevistaDAO();
			revistaDao.alterarDisponibilidade(emprestimo.getRevista().getId());
		}
		
		emprestimoDao.remover(emprestimo);
		
		req.setAttribute("mensagem", String.format("Empréstimo %d removido com sucesso!", emprestimo.getId()));
		
		return url;
	}

}
