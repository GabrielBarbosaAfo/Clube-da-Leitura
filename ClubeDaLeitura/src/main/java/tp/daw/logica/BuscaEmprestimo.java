package tp.daw.logica;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tp.daw.dao.EmprestimoDAO;
import tp.daw.modelo.Emprestimo;

public class BuscaEmprestimo implements Logica{

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String emprestimoId = req.getParameter("emprestimoId");

        if (emprestimoId != null && !emprestimoId.isEmpty()) {
            try {
            	EmprestimoDAO emprestimoDao = new EmprestimoDAO();
            	Emprestimo emprestimoSelecionado = emprestimoDao.getEmprestimoById(Integer.parseInt(emprestimoId));
            	req.setAttribute("emprestimoSelecionado", emprestimoSelecionado);

            } catch (NumberFormatException e) {
                req.setAttribute("erro", "ID do emprestimo inválido.");
            } catch (Exception e) {
                req.setAttribute("erro", "Erro ao buscar informações do emprestimo.");
            }
        } else {
            req.setAttribute("erro", "ID do emprestimo não informado.");
        }
        
        return "buscaremprestimo.jsp";
	}

}
