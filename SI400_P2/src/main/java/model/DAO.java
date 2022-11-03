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
    
    public static final String DBURL = "jdbc:mariadb://localhost:3306?allowPublicKeyRetrieval=true&useSSL=false";
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
            } catch (SQLException e) {
                System.err.println("Exception: " + e.getMessage());
            }
        }
        return con;
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
            (DAO.getConnection(user, password)).close();
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
    
    /* Criação de Teste de Implementação */
    protected final boolean createTable() {
        try {
            PreparedStatement stmt;

            stmt = DAO.getConnection(user, password).prepareStatement("CREATE TABLE IF NOT EXISTS Fragmentos( \n"
                    + "groupId INTEGER, \n"
                    + "file VARCHAR, \n"
                    + "line INTEGER, \n"
                    + "text VARCHAR); \n");
            executeUpdate(stmt);

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
}
