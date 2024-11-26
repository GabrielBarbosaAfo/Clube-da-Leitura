package tp.daw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import tp.daw.bd.FabricaConexao;
import tp.daw.modelo.Caixa;

public class CaixaDAO {
    private Connection connection;

    public CaixaDAO() {
        this.connection = FabricaConexao.getConnection();
    }

    public void adiciona(Caixa caixa) {
        String sql = "insert into caixa (cor) values (?)";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, caixa.getCor());
            stm.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void altera(Caixa caixa) {
        String sql = "update caixa set cor=? where idcaixa=?";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, caixa.getCor());
            stm.setLong(2, caixa.getId());
            stm.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void remove(Caixa caixa) {
        String sql = "delete from caixa where idcaixa=?";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setLong(1, caixa.getId());
            stm.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Caixa> listaCaixas() {
        List<Caixa> caixas = new ArrayList<>();
        String sql = "select * from caixa";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Caixa caixa = new Caixa();
                caixa.setId(rs.getLong("idcaixa"));
                caixa.setCor(rs.getString("cor"));
                caixas.add(caixa);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return caixas;
    }

    public Caixa recuperaCaixa(long id) {
        String sql = "select * from caixa where idcaixa=?";
        Caixa caixa = null;
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setLong(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
            	caixa = new Caixa();
                caixa.setId(rs.getLong("idcaixa"));
                caixa.setCor(rs.getString("cor"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return caixa;
    }
}
