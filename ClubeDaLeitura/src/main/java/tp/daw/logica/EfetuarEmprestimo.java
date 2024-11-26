package tp.daw.logica;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tp.daw.dao.EmprestimoDAO;
import tp.daw.dao.RevistaDAO;
import tp.daw.modelo.Emprestimo;
import tp.daw.modelo.Pessoa;
import tp.daw.modelo.Revista;

public class EfetuarEmprestimo implements Logica{

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Pessoa pessoa = new Pessoa();
		pessoa.setId(Long.parseLong(req.getParameter("pessoa")));
		
		Revista	revista = new Revista();
		revista.setId(Long.parseLong(req.getParameter("revista")));
		
		String url = "mensagem.jsp",
				mensagem = "Empréstimo efetuado com sucesso!";
		
		try {
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Calendar dataEmprestimo = new GregorianCalendar();
			dataEmprestimo.setTime(df.parse(req.getParameter("dataEmprestimo"))); 
		
			Calendar dataAtual = Calendar.getInstance();
			dataAtual.setTime(new Date());
			
			if(dataEmprestimo.compareTo(dataAtual) == 1) 
				throw new Exception();
			
			Emprestimo emprestimo = new Emprestimo();
			emprestimo.setPessoa(pessoa);
			emprestimo.setRevista(revista);
			emprestimo.setDataEmprestimo(dataEmprestimo);
			emprestimo.setDataDevolucao(null);
			EmprestimoDAO dao = new EmprestimoDAO();
			dao.adicionar(emprestimo);
			
			RevistaDAO revistaDao = new RevistaDAO();
			revistaDao.alterarDisponibilidade(revista.getId());
		} catch (Exception e) {
			mensagem = "Ocorreu um erro!\nA data de empréstimo deve ser menor ou igual à data atual e seguir o seguinte modelo DD/MM/AAAA.";
		}
		
		req.setAttribute("mensagem", mensagem);
		return url;
	}

}
