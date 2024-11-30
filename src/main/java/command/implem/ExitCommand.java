package command.implem;

import command.interf.command;
import database.DbManager;
import utilityclass.HandleProdotti;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;


public class ExitCommand implements command {

    private HandleProdotti handleProdotti;
    private DbManager dbmanager;

    public void setDbmanager(DbManager dbmanager) {
        this.dbmanager = dbmanager;
    }

    public void setHandleProdotti(HandleProdotti handleProdotti) {
        this.handleProdotti = handleProdotti;
    }

    //costrutt
    public ExitCommand(HandleProdotti handleProd, DbManager dbmanager) {
        setHandleProdotti(handleProd);
        setDbmanager(dbmanager);
    }

    public DbManager getDbmanager() {
        return dbmanager;
    }

    public HandleProdotti getHandleProdotti() {
        return handleProdotti;
    }

    // prima di uscire dall applicazione scrivo su db tutte le transazioni effettuate.
    @Override
    public void Execute() {

        try {

            getHandleProdotti().AddToMap("Uscita applicazione.");
            AddTransactionsTodb();

        } catch (SQLException | RuntimeException ex) {

            System.out.println("Errore durante la registrazione nel db delle transazioni effettuate.");

        }
        System.exit(0);
    }

    private void AddTransactionsTodb() throws SQLException, RuntimeException {

        String insertQuery = "INSERT INTO TRANSAZIONI (ID_TRANS, DESC_TRANS) VALUES (?, ?)";
        int totRow = 0;

        try (Connection conn = getDbmanager().getConnection()) {

            for (Map.Entry<String, String> entry : getHandleProdotti().getMappaTransaz().entrySet()) {

                PreparedStatement pstmt = conn.prepareStatement(insertQuery);
                pstmt.setString(1, entry.getKey());
                pstmt.setString(2, entry.getValue());
                int rowsAffected = pstmt.executeUpdate();
                totRow += rowsAffected;

            }

            if (totRow != getHandleProdotti().getMapSize()) {
                throw new RuntimeException("quantità transazioni registrate diverse da numero effettivo");
            } else {
                System.out.println("transazioni salvate con successo su db.");
                getHandleProdotti().ClearMap();
            }

        }
    }

}
