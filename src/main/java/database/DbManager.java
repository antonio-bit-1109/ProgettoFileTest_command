package database;

import database.DTOs.ProdottoDTO;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbManager {

    private static final String connString = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String user = "UTENTEPROVA";
    private static final String password = "SYSTEM";
    private Connection conn;
    
    // costr vuoto
    public DbManager() {
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(getConnString(), getUser(), getPassword());
    }

    public static String getConnString() {
        return connString;
    }

    public static String getUser() {
        return user;
    }

    public static String getPassword() {
        return password;
    }
}
