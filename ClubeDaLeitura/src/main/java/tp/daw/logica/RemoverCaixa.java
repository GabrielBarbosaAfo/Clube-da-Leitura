package tp.daw.logica;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tp.daw.dao.CaixaDAO;
import tp.daw.modelo.Caixa;

public class RemoverCaixa implements Logica{

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = "mensagem.jsp";
		
		Caixa caixa = new Caixa();
		caixa.setId(Long.parseLong(req.getParameter("id")));
		
		CaixaDAO dao = new CaixaDAO();
		dao.remove(caixa);
		
		req.setAttribute("mensagem", "Caixa removida com sucesso!");
		return url;
	}

}
