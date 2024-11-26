package tp.daw.logica;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tp.daw.dao.CaixaDAO;
import tp.daw.modelo.Caixa;

public class VerificaIdCaixa implements Logica{
	
	@Override
	public String executa(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 String id = req.getParameter("caixa");
		 String url = req.getParameter("url");
		 String erro = "erro.jsp";
	        try {
	            CaixaDAO caixaDAO = new CaixaDAO();
	            Caixa caixaRecuperado = caixaDAO.recuperaCaixa(Long.parseLong(id));
	            
	            if (caixaRecuperado != null) {
	                req.setAttribute("caixa", caixaRecuperado);
	                
	                return url;
	            } else {
	                req.setAttribute("errorMessage", "Caixa não encontrada.");
	                return erro;
	            }
	        } catch (NumberFormatException e) {
	            e.printStackTrace();
	            req.setAttribute("errorMessage", "ID inválido.");
	            return erro;
	        } catch (Exception e) {
	            e.printStackTrace();
	            req.setAttribute("errorMessage", "Erro ao recuperar a caixa.");
	            return erro;
	        }
	}
}
