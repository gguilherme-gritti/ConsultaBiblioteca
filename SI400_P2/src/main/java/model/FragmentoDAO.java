package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gritti
 */
public class FragmentoDAO extends DAO {

    private static FragmentoDAO instance;

    private FragmentoDAO() {
        getConnection();
    }

    public static FragmentoDAO getInstance() {
        return (instance == null ? (instance = new FragmentoDAO()) : instance);
    }

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

    public List getByGroupId(int groupId) {
        return this.retrieve("SELECT * FROM Fragmentos WHERE groupId = " + groupId
                + " ORDER BY line");
    }

}
