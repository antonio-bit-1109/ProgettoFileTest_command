package command.implem;

import command.interf.IHandleFile;
import command.interf.ProdottoDAO;
import database.DTOs.ProdottoDTO;
import database.DbManager;
import command.interf.command;
import utilityclass.HandleFile;
import utilityclass.HandleProdotti;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class ProdottoDAOImplem implements ProdottoDAO<StringBuilder>, command, IHandleFile {

    private DbManager dbManager;
    private Scanner scan;
    // private List<ProdottoDTO> listaProdotti;
    private boolean finito;
    private HandleFile handleFileCLass;
    private HandleProdotti handleProdotti;

    public void setHandleFileCLass(HandleFile handleFileCLass) {
        this.handleFileCLass = handleFileCLass;
    }

    // public void setListaProdotti(List<ProdottoDTO> listaProdotti) {
//        this.listaProdotti = listaProdotti;
//    }


    public void setHandleProdotti(HandleProdotti handleProdotti) {
        this.handleProdotti = handleProdotti;
    }

    public void setDbManager(DbManager dbManager) {
        this.dbManager = dbManager;
    }

    public void setScan(Scanner scan) {
        this.scan = scan;
    }

    public void setFinito(boolean finito) {
        this.finito = finito;
    }

    //costru
    public ProdottoDAOImplem(DbManager dbMan, Scanner scan, HandleProdotti handleProd, HandleFile handlefileclass) {
        setFinito(false);
        setDbManager(dbMan);
        setScan(scan);
        setHandleProdotti(handleProd);
        //  setListaProdotti(listaProdotti);
        setHandleFileCLass(handlefileclass);
        // setNomeFile(nomeFile);
    }

    public HandleProdotti getHandleProdotti() {
        return handleProdotti;
    }

    public HandleFile getHandleFileCLass() {
        return handleFileCLass;
    }

    public boolean getFinito() {
        return this.finito;
    }

    public Scanner getScan() {
        return scan;
    }

    public void StartInteractDb() throws SQLException {

        do {

            System.out.println("*****************");
            System.out.println("interfaccia interazione database:");
            System.out.println("scegli cosa fare:");
            System.out.println("1- GetAllProdotti");
            System.out.println("2- Modifica nome prodotto selezionato");
            System.out.println("3- Copia Prodotti su file (richiede prima una get di dati dal database.)");
            System.out.println("4- Get prodotto By Nome");
            System.out.println("5- Aggiungi nuovo Prodotto");
            System.out.println("6- Indietro");
            System.out.println("*****************");

            String scelta = getScan().nextLine();
            switch (scelta) {
                case "1":
                    getAllProdotti();
                    break;
                case "2":
                    UpdateProdottoByNome();
                    break;
                case "3":
                    WriteOnFile();
                    break;
                case "4":
                    getProdottoByNome();
                    break;
                case "5":
                    AddNewProdotto();
                    break;
                case "6":
//                    comeBack();
                    setFinito(true);
                    break;
            }

        } while (!finito);

    }

    @Override
    public void Execute() {
        try {

            StartInteractDb();

        } catch (SQLException ex) {
            System.out.println("Errore con il Database: " + ex.getMessage());
        }
    }

    // ricava tutti i prodotti dla db e salvali in una lista presente in Shell.java
    @Override
    public void getAllProdotti() throws SQLException {

        String query = "SELECT IDPRODOTTO,NOME_PRODOTTO,QTA FROM PRODOTTI";
        //List<ProdottoDTO> listaProd = new ArrayList<>();

        // try with resources  --> "using"
        try (Connection conn = dbManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet res = stmt.executeQuery(query)) {

            while (res.next()) {
                ProdottoDTO prod = new ProdottoDTO(
                        res.getInt("IDPRODOTTO"),
                        res.getString("NOME_PRODOTTO"),
                        res.getFloat("QTA")
                );

                getHandleProdotti().AddToListProdotti(prod);  //listaProdotti.add(prod);

            }
            getHandleProdotti().getMappaTransaz().put(1, "estrapolati tutti i prodotti dal db.");

            System.out.println("prodotti correttamente reperiti dal database.");
        }

    }

    @Override
    public void UpdateProdottoByNome() throws SQLException {

        String vecchioNome = InteractUserNomeProd();
        String nuovoNome = InteractUserModificaNomeProd();

        String query = "UPDATE PRODOTTI SET NOME_PRODOTTO = ? WHERE NOME_PRODOTTO = ?";

        try (Connection conn = dbManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, nuovoNome);
            pstmt.setString(2, vecchioNome);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("prodotto aggiornato con successo.");
                getHandleProdotti().getMappaTransaz().put(2, "update nome prodotto  da :" + vecchioNome + " a : " + nuovoNome);

            } else {
                //  System.out.println("prodotto non trovato. Riprovare.");
                throw new SQLException("prodotto non trovato.");
            }
        }
    }

    @Override
    public void AddNewProdotto() {

    }

    // prende un prodotto tramite il nome da db e lo salva in una lista "listaprodotti"
    @Override
    public void getProdottoByNome() throws SQLException {

        System.out.println("Inserisci nome prodotto da trovare.");
        String nomeProd = scan.nextLine();

        String query = "SELECT IDPRODOTTO,NOME_PRODOTTO,QTA FROM PRODOTTI WHERE NOME_PRODOTTO = ?";
        try (Connection conn = dbManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, nomeProd);

            try (ResultSet res = pstmt.executeQuery()) {
                if (res.next()) {
                    StringBuilder sb = new StringBuilder();
                    System.out.println(sb.append(res.getInt("IDPRODOTTO"))
                            .append(",")
                            .append(res.getString("NOME_PRODOTTO"))
                            .append(",")
                            .append(res.getFloat("QTA")));

                    ProdottoDTO p = new ProdottoDTO(
                            res.getInt("IDPRODOTTO"),
                            res.getString("NOME_PRODOTTO"),
                            res.getFloat("QTA")
                    );
                    getHandleProdotti().AddToListProdotti(p);
                    getHandleProdotti().getMappaTransaz().put(3, "Estrapolato singolo prodotto da db. nome prodotto estrapolato: " + p.getNome());
                    // listaProdotti.add(p);
                } else {
                    throw new SQLException("nessun record corrispondente per il nome fornito");
                }
            }

        }
    }

    @Override
    public boolean DeleteProdotto() {
        return false;
    }

    private String InteractUserNomeProd() {
        System.out.println("inserisci nome prodotto");
        return getScan().nextLine();
    }

    private String InteractUserModificaNomeProd() {
        System.out.println("Inserisci nuovo nome prodotto.");
        return getScan().nextLine();
    }


    @Override
    public void WriteOnFile() {

        if (handleProdotti.getListaProdotti().isEmpty()) {
            System.out.println("la lista dati è vuota. fai prima una get da database");
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(getHandleFileCLass().GetCompletePath(), true))) {

            List<String> listaStringhe = retriveDataFromObject();

            for (String str : listaStringhe) {

                String[] subArr = str.split(",");

                StringBuilder sb = new StringBuilder();
                bw.write(String.valueOf(sb.append(subArr[0]).append(",").append(subArr[1]).append(",").append(subArr[2])));
                bw.newLine();
            }

            handleProdotti.getListaProdotti().clear();
            getHandleProdotti().getMappaTransaz().put(4, "scrittura su file della lista prodotti ");

            // listaProdotti.clear();

        } catch (IOException e) {
            throw new RuntimeException("errore durante l'inserimento dei dati nel file.");
        }

    }

    private List<String> retriveDataFromObject() {

        List<String> StrList = new ArrayList<>();

        for (int i = 0; i < handleProdotti.getListaSize(); i++) {
            ProdottoDTO obj = handleProdotti.getListaProdotti().get(i);

            StringBuilder sb = new StringBuilder();
            sb.append(obj.getId()).append(",").append(obj.getNome()).append(",").append(obj.getQuantita()).append("\n");
            StrList.add(i, sb.toString());
        }
        return StrList;
    }

    @Override
    public void ReadFromFile() {

    }
}

