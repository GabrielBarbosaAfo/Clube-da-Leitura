package tp.daw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import tp.daw.bd.FabricaConexao;
import tp.daw.modelo.Usuario;

/**
 * Classe responsável por gerenciar os dados da tabela "usuario" do banco de dados.
 */
public class UsuarioDAO {

    private Connection connection;

    public UsuarioDAO() {
        connection = FabricaConexao.getConnection();
    }

    /**
     * Adiciona um novo usuário ao banco de dados.
     * @param usuario Usuário a ser adicionado.
     */
    public void adicionar(Usuario usuario) {
        String sql = "INSERT INTO usuario (usuario, senha) VALUES (?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, usuario.getUsuario());
            stmt.setString(2, usuario.getSenha());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Remove um usuário do banco de dados pelo ID.
     * @param usuario Usuário a ser removido.
     */
    public void remover(Usuario usuario) {
        String sql = "DELETE FROM usuario WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, usuario.getId());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Atualiza os dados de um usuário no banco de dados.
     * @param usuario Usuário a ser atualizado.
     */
    public void alterar(Usuario usuario) {
        String sql = "UPDATE usuario SET usuario = ?, senha = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, usuario.getUsuario());
            stmt.setString(2, usuario.getSenha());
            stmt.setLong(3, usuario.getId());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Valida as credenciais do usuário no banco de dados.
     * @param usuario Nome de usuário.
     * @param senha Senha do usuário.
     * @return Usuario se as credenciais forem válidas, null caso contrário.
     */
    public Usuario validaCredencial(String usuario, String senha) {
        String sql = "SELECT * FROM usuario WHERE usuario = ? AND senha = ?";
        Usuario usuarioRetornado = null;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, usuario);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                usuarioRetornado = new Usuario();
                usuarioRetornado.setUsuario(rs.getString("usuario"));
                usuarioRetornado.setSenha(rs.getString("senha"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuarioRetornado;
    }
    
    /**
     * Obtém um usuário pelo ID.
     * @param id ID do usuário a ser buscado.
     * @return Usuario com o ID especificado, ou null se não encontrado.
     */
    public Usuario getUsuarioById(Long id) {
        String sql = "SELECT * FROM usuario WHERE id = ?";
        Usuario usuario = null;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                usuario = new Usuario();
                usuario.setId(rs.getLong("id"));
                usuario.setUsuario(rs.getString("usuario"));
                usuario.setSenha(rs.getString("senha"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuario;
    }
}
