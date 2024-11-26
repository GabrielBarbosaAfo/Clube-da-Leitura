package tp.daw.logica;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tp.daw.dao.RevistaDAO;
import tp.daw.modelo.Revista;

public class BuscaRevista implements Logica{

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String revistaId = req.getParameter("revistaId");

        if (revistaId != null && !revistaId.isEmpty()) {
            try {
                RevistaDAO revistaDao = new RevistaDAO();
                Revista revistaSelecionada = revistaDao.getRevistaById(Long.parseLong(revistaId));
                req.setAttribute("revistaSelecionada", revistaSelecionada);
            } catch (NumberFormatException e) {
                req.setAttribute("erro", "ID da revista inválido.");
            } catch (Exception e) {
                req.setAttribute("erro", "Erro ao buscar informações da revista.");
            }
        } else {
            req.setAttribute("erro", "ID da revista não informado.");
        }
        
        return "buscarrevista.jsp";
	}

}
