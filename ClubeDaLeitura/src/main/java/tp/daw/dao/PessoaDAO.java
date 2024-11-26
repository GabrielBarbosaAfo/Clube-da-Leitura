package tp.daw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tp.daw.bd.FabricaConexao;
import tp.daw.modelo.Pessoa;

/**
 * Classe responsável por gerenciar os dados da tabela "pessoa" do banco de dados.
 */
public class PessoaDAO {

	private Connection connection;

	public PessoaDAO() {
		connection = FabricaConexao.getConnection();
	}

	/**
	 * Adiciona os dados de uma nova pessoa ao Banco de Dados.
	 * @param pessoa Pessoa
	 */
	public void adicionar(Pessoa pessoa) {
		String sql = "INSERT INTO pessoa (nome, telefone) VALUES (?, ?)";

		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, pessoa.getNome());
			stmt.setString(2, pessoa.getTelefone());

			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Remove os dados de uma pessoa do Banco de Dados.
	 * @param pessoa Pessoa
	 */
	public void remover(Pessoa pessoa) {
		String sql = "DELETE FROM pessoa WHERE idpessoa=?";

		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setLong(1, pessoa.getId());
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Altera os dados de uma pessoa do Banco de Dados.
	 * @param pessoa Pessoa
	 */
	public void alterar(Pessoa pessoa) {
		String sql = "UPDATE pessoa SET nome=?, telefone=? WHERE idpessoa=?";

		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, pessoa.getNome());
			stmt.setString(2, pessoa.getTelefone());
			stmt.setLong(3, pessoa.getId());

			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Obtém os dados de todas as pessoas existentes no Banco de Dados.
	 * @return List<Pessoa> lista com todas as pessoas.
	 */
	public List<Pessoa> getListaPessoas() {
		List<Pessoa> pessoas = new ArrayList<>();
		String sql = "SELECT * FROM pessoa ORDER BY nome";

		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Pessoa pessoa = new Pessoa();
				pessoa.setId(rs.getLong("idpessoa"));
				pessoa.setNome(rs.getString("nome"));
				pessoa.setTelefone(rs.getString("telefone"));
				pessoas.add(pessoa);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return pessoas;
	}

    /**
     * Procura no banco de dados uma pessoa com o valor da coluna "idpessoa" correspondente ao parâmetro "id" informado.
     * @param id "idpessoa" da pessoa.
     * @return Objeto Pessoa com os dados ou null caso não exista.
     */
    public Pessoa getPessoaById(long id) {
        String sql = "SELECT * FROM pessoa WHERE idpessoa=?";
        Pessoa pessoa = null;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                pessoa = new Pessoa();
                pessoa.setId(rs.getLong("idpessoa"));
                pessoa.setNome(rs.getString("nome"));
                pessoa.setTelefone(rs.getString("telefone"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pessoa;
    }
    
    /**
     * Retorna uma lista com todas as pessoas que não possuem pendências de devolução de empréstimo.
     * @return List<Pessoa> pessoas sem pendência.
     */
    public List<Pessoa> getListaPessoasSemPendencia() {
        List<Pessoa> pessoasSemPendencia = new ArrayList<>();
        List<Pessoa> pessoas = new PessoaDAO().getListaPessoas(); 

        for (Pessoa pessoa : pessoas) {
            if (verificaPendencia(pessoa) == -1L) {
                pessoasSemPendencia.add(pessoa);
            }
        }

        return pessoasSemPendencia;
    }

    /**
     * Verifica se a pessoa possui algum empréstimo pendente.
     * @param pessoa Pessoa
     * @return ID do empréstimo pendente ou -1 caso não haja nenhum.
     */
    public long verificaPendencia(Pessoa pessoa) {
        String sql = "SELECT * FROM emprestimo WHERE idpessoa = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, pessoa.getId());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                if (rs.getDate("datadevolucao") == null) { // Verifica se a data de devolução é nula
                    return rs.getLong("idemprestimo");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }
    
    /**
     * Recupera uma pessoa do banco de dados com base no ID fornecido.
     * @param pessoa Objeto Pessoa com o ID a ser buscado.
     * @return Objeto Pessoa com os dados completos ou null caso não exista.
     */
    public Pessoa recuperaPessoa(Pessoa pessoa) {
        String sql = "SELECT * FROM pessoa WHERE idpessoa=?";
        Pessoa resultadoPessoa = null;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, pessoa.getId());
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                resultadoPessoa = new Pessoa();
                resultadoPessoa.setId(rs.getLong("idpessoa"));
                resultadoPessoa.setNome(rs.getString("nome"));
                resultadoPessoa.setTelefone(rs.getString("telefone"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultadoPessoa;
    }
}

