package tp.daw.logica;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tp.daw.dao.CaixaDAO;
import tp.daw.modelo.Caixa;

public class BuscaCaixa implements Logica{

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String caixaId = req.getParameter("caixaId");

        if (caixaId != null && !caixaId.isEmpty()) {
            try {
                CaixaDAO caixaDao = new CaixaDAO();
                Caixa caixaSelecionada = caixaDao.recuperaCaixa(Integer.parseInt(caixaId));
                req.setAttribute("caixaSelecionada", caixaSelecionada);
            } catch (NumberFormatException e) {
                req.setAttribute("erro", "ID da caixa inválido.");
            } catch (Exception e) {
                req.setAttribute("erro", "Erro ao buscar informações da caixa.");
            }
        } else {
            req.setAttribute("erro", "ID da caixa não informado.");
        }
        
        return "buscarcaixa.jsp";
    }

}
