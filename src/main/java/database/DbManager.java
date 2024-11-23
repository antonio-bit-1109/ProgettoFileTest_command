package database;

import database.DTOs.ProdottoDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbManager {

    private static final String connString = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String user = "C##UTENTEPROVA";
    private static final String password = "SYSTEM";
    private Connection conn;
    private List<ProdottoDTO> listProdotti;

    public void setListProdotti(List<ProdottoDTO> listProdotti) {
        this.listProdotti = listProdotti;
    }

    public DbManager() {
        setListProdotti(new ArrayList<>());
    }

    public List<ProdottoDTO> getListProdotti() {
        return listProdotti;
    }

    private boolean OpenConnection() throws SQLException {
        DriverManager.getConnection(connString, user, password);
        return true;
    }

    private boolean closeConnection() throws SQLException {

        if (conn != null && !conn.isClosed()) {
            conn.close();
            return true;
        }
        return false;
    }

    public void getProdotto() throws SQLException {
        try {

            if (OpenConnection()) {
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT IDPRODOTTO , NOME_PRODOTTO , QTA FROM PRODOTTI");

                while (rs.next()) {
                    int idProd = rs.getInt("IDPRODOTTO");
                    String NomeProd = rs.getString("NOME_PRODOTTO");
                    float Qta = rs.getFloat("QTA");

                    ProdottoDTO prodDTO = new ProdottoDTO(idProd, NomeProd, Qta);
                    listProdotti.add(prodDTO);
                    //System.out.println(" dati: " + idProd + "-" + NomeProd + "-" + Qta);
                }


            } else {
                throw new SQLException("apertura connessione fallita.");
            }


        } catch (SQLException e) {
            System.out.println("Errore durante la GET dei prodotti " + e);

        } finally {
            closeConnection();
        }
    }
}
