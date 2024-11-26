package tp.daw.logica;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tp.daw.dao.RevistaDAO;
import tp.daw.modelo.Revista;

public class RemoverRevista implements Logica{

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = "mensagem.jsp";
		
		Revista revista = new Revista();
		revista.setId(Long.parseLong(req.getParameter("id")));
		
		RevistaDAO dao = new RevistaDAO();
		dao.remover(revista);
		
		req.setAttribute("mensagem", "Revista removida com sucesso!");
		return url;
	}

}
