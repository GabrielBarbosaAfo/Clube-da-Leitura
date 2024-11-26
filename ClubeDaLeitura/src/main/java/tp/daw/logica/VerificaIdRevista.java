package tp.daw.logica;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tp.daw.dao.RevistaDAO;
import tp.daw.modelo.Revista;

public class VerificaIdRevista implements Logica{
	@Override
	public String executa(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 String id = req.getParameter("id");
		 String url = req.getParameter("url");
		 String erro = "erro.jsp";
	        try {
	            RevistaDAO revistaDAO = new RevistaDAO();
	            Revista revistaRecuperada = revistaDAO.getRevistaById(Long.parseLong(id));
	            
	            if (revistaRecuperada != null) {
	                req.setAttribute("revista", revistaRecuperada);
	                
	                return url;
	            } else {
	                req.setAttribute("errorMessage", "Revista não encontrada.");
	                return erro;
	            }
	        } catch (NumberFormatException e) {
	            e.printStackTrace();
	            req.setAttribute("errorMessage", "ID inválido.");
	            return erro;
	        } catch (Exception e) {
	            e.printStackTrace();
	            req.setAttribute("errorMessage", "Erro ao recuperar a revista.");
	            return erro;
	        }
	}
}
