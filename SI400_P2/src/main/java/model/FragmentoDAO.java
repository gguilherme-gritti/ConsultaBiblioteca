package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/* 
 * Classe FragmentoDAO, manipulação da tabela Fragmentos com o BD
 *
 * @author Grupo C
 * @version 1.0
 * @since 1.0
 */
public class FragmentoDAO extends DAO {

    private static FragmentoDAO instance;

    private FragmentoDAO() {
        getConnection();
    }

    /**
     * Retorna a instância da conexão
     *
     * @return instância
     */
    public static FragmentoDAO getInstance() {
        return (instance == null ? (instance = new FragmentoDAO()) : instance);
    }

    /**
     * Cria um objeto Fragmento
     *
     * @param result resultado da consulta do BD
     * @return objeto fragmento
     */
    private Fragmento createObject(ResultSet result) {
        Fragmento fragmento = null;
        try {
            fragmento = new Fragmento(result.getInt("groupId"), result.getString("file"),
                    result.getInt("line"), result.getString("text"));
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }

        return fragmento;
    }

    /**
     * Executa uma query no banco
     *
     * @param query query SQL
     * @return lista de fragmentos
     */
    public List retrieve(String query) {
        List<Fragmento> fragmentos = new ArrayList();
        ResultSet rs = getResultSet(query);
        try {
            while (rs.next()) {
                System.out.println(rs.getInt("groupId") + "|" + rs.getString("file") + "|" + rs.getInt("line") + "|" + rs.getString("text") + "\n");
                fragmentos.add(createObject(rs));
            }
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return fragmentos;
    }

    /**
     * Executa uma query no BD pelo id do grupo
     *
     * @param groupId resultado da consulta do BD
     * @return Lista de fragmentos
     */
    public List getByGroupId(int groupId) {
        return this.retrieve("SELECT * FROM Fragmentos WHERE groupId = " + groupId
                + " ORDER BY line");
    }

}
