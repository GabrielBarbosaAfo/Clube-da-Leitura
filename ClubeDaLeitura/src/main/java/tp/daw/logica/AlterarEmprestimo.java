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

public class AlterarEmprestimo implements Logica{

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		long idEmprestimo = Long.parseLong(req.getParameter("id"));
		EmprestimoDAO emprestimoDao = new EmprestimoDAO();
		Emprestimo emprestimo = emprestimoDao.getEmprestimoById(idEmprestimo);
		
		
		String url = "mensagem.jsp",
				mensagem = String.format("Empréstimo %d alterado com sucesso!", emprestimo.getId());
		
		//Verifica se o amigo foi alterado e modifica a informação no emprestimo.
		long idPessoa = Long.parseLong(req.getParameter("pessoa"));
		if(idPessoa != emprestimo.getPessoa().getId()) {
			Pessoa pessoa = new Pessoa();
			pessoa.setId(idPessoa);
			
			emprestimo.setPessoa(pessoa);
		}
		
		//Valida e altera as datas.
		try {
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			
			//Data atual do sistema, para comparações.
			Calendar dataAtual = Calendar.getInstance();
			dataAtual.setTime(new Date());
			
			//Data de empréstimo
			Calendar dataEmprestimo = new GregorianCalendar();
			dataEmprestimo.setTime(df.parse(req.getParameter("dataEmprestimo"))); 
			
			//Verifica se a data de emprestimo foi alterada
			if(dataEmprestimo.compareTo(emprestimo.getDataEmprestimo()) != 0) 
				if(dataEmprestimo.compareTo(dataAtual) != 1) { //Evita que a data de empréstimo seja maior que a data atual.
					emprestimo.setDataEmprestimo(dataEmprestimo);
				}else
					throw new Exception();

			//Verifica se o empréstimo está pendente
			if(emprestimo.getDataDevolucao() != null) {
				//Data de devolução
				Calendar dataDevolucao = new GregorianCalendar();
				dataDevolucao.setTime(df.parse(req.getParameter("dataDevolucao"))); 
				
				//Verifica se a data de devolução é maior que a data de empréstimo.
				if(dataEmprestimo.compareTo(dataDevolucao) == -1) { 
					emprestimo.setDataDevolucao(dataDevolucao);
				}else
					throw new Exception();
			}
			
		}catch (Exception e) {
			return "erro.jsp";
		}
		
		//Verifica se a revista foi alterada e modifica a informação no emprestimo.
		long idNovaRevista = Long.parseLong(req.getParameter("revista")),
				idAntigaRevista = emprestimo.getRevista().getId();
		
		if(idNovaRevista != idAntigaRevista) {
			RevistaDAO revistaDao = new RevistaDAO();
			
			Revista revista = revistaDao.getRevistaById(idNovaRevista);

			//Altera a disponibilidade da revista antiga.
			revistaDao.alterarDisponibilidade(idAntigaRevista);
			
			//Altera a disponibilidade da nova revista.
			revistaDao.alterarDisponibilidade(idNovaRevista); 
			
			//Altera a revista no emprestimo.
			emprestimo.setRevista(revista);
		}
		
		emprestimoDao.alterar(emprestimo);
		
		req.setAttribute("mensagem", mensagem);
		return url;
	}

}
