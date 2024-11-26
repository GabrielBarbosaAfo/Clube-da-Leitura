package tp.daw.logica;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tp.daw.dao.UsuarioDAO;
import tp.daw.modelo.Usuario;

public class EfetuaLogin implements Logica{

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String login = req.getParameter("login");
		String senha = req.getParameter("senha");
		String url = "login.jsp";
		UsuarioDAO userDAO = new UsuarioDAO();
		Usuario user = userDAO.validaCredencial(login, senha);
		
		if(user != null) {
			HttpSession sessao = req.getSession();
			sessao.setMaxInactiveInterval(2 * 60);
			sessao.setAttribute("status", true);
			sessao.setAttribute("nome", login);
			url = "menuprincipal.jsp";
		}
		return url;
	}

}
