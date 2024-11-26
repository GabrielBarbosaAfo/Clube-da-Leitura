package tp.daw.logica;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tp.daw.dao.EmprestimoDAO;
import tp.daw.dao.RevistaDAO;
import tp.daw.modelo.Emprestimo;

public class DevolveRevista implements Logica{

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = "listarevistasemprestadas.jsp";
		
		EmprestimoDAO emprestimoDao = new EmprestimoDAO();
		Emprestimo emprestimo = emprestimoDao.getEmprestimoById(Long.parseLong(req.getParameter("idEmprestimo")));
		
		try {
			//Data atual do sistema, para devolução.
			Calendar dataAtual = Calendar.getInstance();
			dataAtual.setTime(new Date());
		
			//Adicionando a data de devolução ao empréstimo.
			emprestimo.setDataDevolucao(dataAtual);
		
		} catch (Exception e) {
			url = "erro.jsp";
		}
		
		//Alterando a disponibilidade da revista emprestada.
		RevistaDAO revistaDao = new RevistaDAO();
		revistaDao.alterarDisponibilidade(emprestimo.getRevista().getId());
		
		//Salvando as alterações no banco de dados.
		emprestimoDao.alterar(emprestimo);
		
		return url;
	}

}
