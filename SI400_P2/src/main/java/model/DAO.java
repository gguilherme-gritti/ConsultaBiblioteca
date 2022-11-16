/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gritti
 */
public class DAO {

    public static final String DBURL = "jdbc:mariadb://localhost:3306/SI400?allowPublicKeyRetrieval=true&useSSL=false";
    private static Connection con;

    private static String user = "si400_2022";
    private static String password = "si400_2022";

    // Connect to Database
    public static Connection getConnection(String user, String password) {
        if (con == null) {
            try {
                con = DriverManager.getConnection(DBURL, user, password);
                if (con != null) {
                    DatabaseMetaData meta = con.getMetaData();
                }
                System.out.println(con);
            } catch (SQLException e) {
                System.err.println("Exception: " + e.getMessage());
            }
        }
        return con;
    }

    public static boolean getStatusCon() {
        if (con == null) {
            return false;
        }

        return true;
    }

    protected ResultSet getResultSet(String query) {
        Statement s;
        ResultSet rs = null;
        try {
            s = (Statement) con.createStatement();
            rs = s.executeQuery(query);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return rs;
    }

    protected int executeUpdate(PreparedStatement queryStatement) throws SQLException {
        int update;
        update = queryStatement.executeUpdate();
        return update;
    }

    protected int lastId(String tableName, String primaryKey) {
        Statement s;
        int lastId = -1;
        try {
            s = (Statement) con.createStatement();
            ResultSet rs = s.executeQuery("SELECT MAX(" + primaryKey + ") AS id FROM " + tableName);
            if (rs.next()) {
                lastId = rs.getInt("id");
            }
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return lastId;
    }

    public static void close() {
        try {
            if (con == null) {
                return;
            }
            con.close();
            System.out.println("\n conexão encerrada.");
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }

    /* Criação de Teste de Implementação */
    public boolean createTable() {
        try {
            PreparedStatement stmt;
            
            stmt = DAO.getConnection(user, password).prepareStatement("CREATE TABLE IF NOT EXISTS Fragmentos( \n"
                    + "groupId INTEGER, \n"
                    + "file VARCHAR(80), \n"
                    + "line INTEGER, \n"
                    + "text VARCHAR(300)); \n");
            executeUpdate(stmt);
            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
