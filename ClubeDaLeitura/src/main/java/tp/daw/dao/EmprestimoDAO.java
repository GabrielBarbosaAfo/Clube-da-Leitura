package tp.daw.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import tp.daw.bd.FabricaConexao;
import tp.daw.modelo.Emprestimo;
import tp.daw.modelo.Pessoa;
import tp.daw.modelo.Revista;

/**
 * Classe responsável por gerenciar os dados da tabela "emprestimo" do banco de dados.
 */
public class EmprestimoDAO {

    private Connection connection;

    public EmprestimoDAO() {
        connection = FabricaConexao.getConnection();
    }

    /**
     * Adiciona um novo empréstimo ao Banco de Dados.
     * @param emprestimo Emprestimo
     */
    public void adicionar(Emprestimo emprestimo) {
        String sql = "INSERT INTO emprestimo (idpessoa, idrevista, dataEmprestimo, dataDevolucao) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, emprestimo.getPessoa().getId());
            stmt.setLong(2, emprestimo.getRevista().getId());
            stmt.setDate(3, new Date(emprestimo.getDataEmprestimo().getTimeInMillis()));
            stmt.setDate(4, emprestimo.getDataDevolucao() != null ? 
                             new Date(emprestimo.getDataDevolucao().getTimeInMillis()) : null);

            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Remove um empréstimo do Banco de Dados.
     * @param emprestimo Emprestimo
     */
    public void remover(Emprestimo emprestimo) {
        String sql = "DELETE FROM emprestimo WHERE idemprestimo = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, emprestimo.getId());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Altera os dados de um empréstimo no Banco de Dados.
     * @param emprestimo Emprestimo
     */
    public void alterar(Emprestimo emprestimo) {
        String sql = "UPDATE emprestimo SET idpessoa = ?, idrevista = ?, dataEmprestimo = ?, dataDevolucao = ? WHERE idemprestimo = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, emprestimo.getPessoa().getId());
            stmt.setLong(2, emprestimo.getRevista().getId());
            stmt.setDate(3, new Date(emprestimo.getDataEmprestimo().getTimeInMillis()));
            stmt.setDate(4, emprestimo.getDataDevolucao() != null ? 
                             new Date(emprestimo.getDataDevolucao().getTimeInMillis()) : null);
            stmt.setLong(5, emprestimo.getId());

            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Obtém uma lista com todos os empréstimos do Banco de Dados.
     * @return List<Emprestimo> lista com todos os empréstimos.
     */
    public List<Emprestimo> getListaEmprestimos() {
        List<Emprestimo> emprestimos = new ArrayList<>();
        String sql = "SELECT * FROM emprestimo ORDER BY dataEmprestimo";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Emprestimo emprestimo = new Emprestimo();
                emprestimo.setId(rs.getLong("idemprestimo"));

                Pessoa pessoa = new PessoaDAO().getPessoaById(rs.getLong("idpessoa"));
                Revista revista = new RevistaDAO().getRevistaById(rs.getLong("idrevista"));

                emprestimo.setPessoa(pessoa);
                emprestimo.setRevista(revista);

                Calendar dataEmprestimo = Calendar.getInstance();
                dataEmprestimo.setTime(rs.getDate("dataEmprestimo"));
                emprestimo.setDataEmprestimo(dataEmprestimo);

                Date dataDevolucaoDate = rs.getDate("dataDevolucao");
                if (dataDevolucaoDate != null) {
                    Calendar dataDevolucao = Calendar.getInstance();
                    dataDevolucao.setTime(dataDevolucaoDate);
                    emprestimo.setDataDevolucao(dataDevolucao);
                }

                emprestimos.add(emprestimo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return emprestimos;
    }

    /**
     * Busca um empréstimo pelo ID no Banco de Dados.
     * @param id ID do empréstimo
     * @return Emprestimo com o ID fornecido ou null se não encontrado.
     */
    public Emprestimo getEmprestimoById(long id) {
        String sql = "SELECT * FROM emprestimo WHERE idemprestimo = ?";
        Emprestimo emprestimo = null;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                emprestimo = new Emprestimo();
                emprestimo.setId(rs.getLong("idemprestimo"));

                Pessoa pessoa = new PessoaDAO().getPessoaById(rs.getLong("idpessoa"));
                Revista revista = new RevistaDAO().getRevistaById(rs.getLong("idrevista"));

                emprestimo.setPessoa(pessoa);
                emprestimo.setRevista(revista);

                Calendar dataEmprestimo = Calendar.getInstance();
                dataEmprestimo.setTime(rs.getDate("dataEmprestimo"));
                emprestimo.setDataEmprestimo(dataEmprestimo);

                Date dataDevolucaoDate = rs.getDate("dataDevolucao");
                if (dataDevolucaoDate != null) {
                    Calendar dataDevolucao = Calendar.getInstance();
                    dataDevolucao.setTime(dataDevolucaoDate);
                    emprestimo.setDataDevolucao(dataDevolucao);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return emprestimo;
    }
    
    
    
    public List<Emprestimo> getEmprestimosEmAberto() {
        List<Emprestimo> emprestimos = new ArrayList<>();
        String sql = "SELECT * FROM emprestimo WHERE dataDevolucao IS NULL ORDER BY dataEmprestimo";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Emprestimo emprestimo = new Emprestimo();
                emprestimo.setId(rs.getLong("idemprestimo"));

                Pessoa pessoa = new PessoaDAO().getPessoaById(rs.getLong("idpessoa"));
                Revista revista = new RevistaDAO().getRevistaById(rs.getLong("idrevista"));

                emprestimo.setPessoa(pessoa);
                emprestimo.setRevista(revista);

                Calendar dataEmprestimo = Calendar.getInstance();
                dataEmprestimo.setTime(rs.getDate("dataEmprestimo"));
                emprestimo.setDataEmprestimo(dataEmprestimo);

                emprestimos.add(emprestimo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return emprestimos;
    }
    
    /**
     * Obtém uma lista de empréstimos que estão atrasados (mais de 10 dias sem devolução).
     * @return List<Emprestimo> lista de empréstimos atrasados.
     */
    public List<Emprestimo> getEmprestimosAtrasados() {
        List<Emprestimo> emprestimos = new ArrayList<>();
        String sql = "SELECT * FROM emprestimo WHERE dataDevolucao IS NULL AND dataEmprestimo < current_date - INTERVAL '10' DAY";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Emprestimo emprestimo = new Emprestimo();
                emprestimo.setId(rs.getLong("idemprestimo"));

                Pessoa pessoa = new PessoaDAO().getPessoaById(rs.getLong("idpessoa"));
                Revista revista = new RevistaDAO().getRevistaById(rs.getLong("idrevista"));

                emprestimo.setPessoa(pessoa);
                emprestimo.setRevista(revista);

                Calendar dataEmprestimo = Calendar.getInstance();
                dataEmprestimo.setTime(rs.getDate("dataEmprestimo"));
                emprestimo.setDataEmprestimo(dataEmprestimo);

                emprestimos.add(emprestimo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return emprestimos;
    }

}
