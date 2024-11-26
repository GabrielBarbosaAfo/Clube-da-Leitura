package tp.daw.logica;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tp.daw.dao.EmprestimoDAO;
import tp.daw.modelo.Emprestimo;

public class VerificaIdEmprestimo implements Logica{

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		String url = req.getParameter("url");
		String erro = "erro.jsp";
        try {
            EmprestimoDAO emprestimoDAO = new EmprestimoDAO();
            Emprestimo emprestimoRecuperado = emprestimoDAO.getEmprestimoById(Long.parseLong(id));
            
            if (emprestimoRecuperado != null) {
                req.setAttribute("emprestimo", emprestimoRecuperado);
                
                return url;
            } else {
                req.setAttribute("errorMessage", "emprestimo não encontrado.");
                return erro;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            req.setAttribute("errorMessage", "ID inválido.");
            return erro;
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("errorMessage", "Erro ao recuperar o emprestimo.");
            return erro;
        }
	}

}
