package tp.daw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tp.daw.bd.FabricaConexao;
import tp.daw.modelo.Caixa;
import tp.daw.modelo.Revista;

/**
 * Classe responsável por gerenciar os dados da tabela "revista" do banco de dados.
 */
public class RevistaDAO {

    private Connection connection;

    public RevistaDAO() {
        connection = FabricaConexao.getConnection();
    }

    /**
     * Adiciona uma nova revista ao banco de dados.
     * @param revista Revista a ser adicionada.
     */
    public void adicionar(Revista revista) {
        String sql = "INSERT INTO revista (colecao, num_edicao, ano_revista, disponibilidade, idCaixa) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, revista.getColecao());
            stmt.setLong(2, revista.getNumeroEdicao());
            stmt.setInt(3, revista.getAnoRevista());
            stmt.setBoolean(4, revista.getDisponibilidade());
            
            if (revista.getCaixa() != null) {
                stmt.setLong(5, revista.getCaixa().getId());
            } else {
                System.out.println("Aviso: Caixa está nulo");
            }

            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * Remove uma revista do banco de dados pelo ID.
     * @param revista Revista a ser removida.
     */
    public void remover(Revista revista) {
        String sql = "DELETE FROM revista WHERE idRevista = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, revista.getId());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Atualiza os dados de uma revista no banco de dados.
     * @param revista Revista a ser atualizada.
     */
    public void alterar(Revista revista) {
        String sql = "UPDATE revista SET colecao = ?, num_edicao = ?, ano_revista = ?, disponibilidade = ?, idCaixa = ? WHERE idRevista = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, revista.getColecao());
            stmt.setLong(2, revista.getNumeroEdicao());
            stmt.setInt(3, revista.getAnoRevista());
            stmt.setBoolean(4, revista.getDisponibilidade());
            stmt.setLong(5, revista.getCaixa().getId());
            stmt.setLong(6, revista.getId());

            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Obtém uma lista com todas as revistas do banco de dados.
     * @return List<Revista> lista de revistas.
     */
    public List<Revista> getListaRevistas() {
        List<Revista> revistas = new ArrayList<>();
        String sql = "SELECT * FROM revista ORDER BY colecao";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Revista revista = new Revista();
                revista.setId(rs.getLong("idRevista"));
                revista.setColecao(rs.getString("colecao"));
                revista.setNumeroEdicao(rs.getLong("num_edicao"));
                revista.setAnoRevista(rs.getInt("ano_revista"));
                revista.setDisponibilidade(rs.getBoolean("disponibilidade"));
                
                // Supondo que haja uma classe CaixaDAO para buscar dados da caixa
                Caixa caixa = new Caixa(); // ou utilize CaixaDAO para buscar mais detalhes
                caixa.setId(rs.getLong("idCaixa"));
                revista.setCaixa(caixa);

                revistas.add(revista);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return revistas;
    }

    /**
     * Verifica se uma revista específica está disponível.
     * @param idRevista ID da revista.
     * @return true se disponível, false caso contrário.
     */
    public boolean verificaDisponibilidade(Long idRevista) {
        String sql = "SELECT disponibilidade FROM revista WHERE idRevista = ?";
        boolean disponibilidade = false;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, idRevista);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                disponibilidade = rs.getBoolean("disponibilidade");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return disponibilidade;
    }

    /**
     * Obtém uma revista pelo ID.
     * @param id ID da revista a ser buscada.
     * @return Revista com o ID especificado, ou null se não encontrada.
     */
    public Revista getRevistaById(Long id) {
        String sql = "SELECT * FROM revista WHERE idRevista = ?";
        Revista revista = null;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                revista = new Revista();
                revista.setId(rs.getLong("idRevista"));
                revista.setColecao(rs.getString("colecao"));
                revista.setNumeroEdicao(rs.getLong("num_edicao"));
                revista.setAnoRevista(rs.getInt("ano_revista"));
                revista.setDisponibilidade(rs.getBoolean("disponibilidade"));
                
                Caixa caixa = new Caixa();
                caixa.setId(rs.getLong("idCaixa"));
                revista.setCaixa(caixa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return revista;
    }


	public void alterarDisponibilidade(Long id) {
		String sql = "UPDATE revista SET disponibilidade=? WHERE idrevista=?";
		Revista revista = getRevistaById(id);
		
		try(PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setBoolean(1, !revista.getDisponibilidade());
			stmt.setLong(2, id);
			
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
