package shellcontroller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import command.implem.*;
import command.interf.command;
import database.DTOs.ProdottoDTO;
import database.DbManager;
import utilityclass.HandleFile;

public class Shell {

    private Scanner scan;
    private HashMap<String, command> CommandMap;
    // private String nomeFile;
    private List<String> list;
    private DbManager dbmanager;
    private List<ProdottoDTO> listaProdotti;
    private HandleFile handleFileCLass;

    public void setHandleFileCLass() {
        this.handleFileCLass = new HandleFile();
    }

    public void setListaProdotti() {
        this.listaProdotti = new ArrayList<>();
    }

    public void setScan() {
        this.scan = new Scanner(System.in);
    }

    public void setList() {
        this.list = new ArrayList<>();
    }

    public void setDbmanager() {
        this.dbmanager = new DbManager();
    }

    public void setCommandMap() {
        CommandMap = new HashMap<>();
        CommandMap.put("1", new AddStudentCommand(getScan(), getHandleFileCLass()));
        CommandMap.put("2", new GetAllStudentCommand(getList(), getHandleFileCLass()));
        CommandMap.put("3", new SearchStudentCommand(getHandleFileCLass(), getScan()));
        CommandMap.put("4", new ProdottoDAOImplem(getDbmanager(), getScan(), getListaProdotti(), getHandleFileCLass()));
        CommandMap.put("5", new StoricoTransazioniCommand());
        CommandMap.put("6", new ExitCommand());
    }

    // costr
    public Shell() {
        setHandleFileCLass();
        setListaProdotti();
        setDbmanager();
        setScan();
        setList();
        setCommandMap();
    }

    public HandleFile getHandleFileCLass() {
        return handleFileCLass;
    }

    public List<ProdottoDTO> getListaProdotti() {
        return listaProdotti;
    }

    public Scanner getScan() {
        return scan;
    }

    public List<String> getList() {
        return list;
    }

    public DbManager getDbmanager() {
        return dbmanager;
    }

    public void StartPoint() {
        System.out.println("**************************");
        System.out.println("MAIN");
        System.out.println("**************************");
        System.out.println("1- aggiungi uno studente");
        System.out.println("2- visualizza elenco studenti");
        System.out.println("3- cerca uno studente per nome");
        System.out.println("4- interagisci con Database");
        System.out.println("5- Storico transazioni");
        System.out.println("6- Esci dal programma");
        System.out.println("**************************");

        String scelta = scan.nextLine();
        this.HandleScelta(scelta);

    }

    private void HandleScelta(String scelta) {

        try {
            command c = CommandMap.get(scelta);
            c.Execute();
        } catch (Exception ex) {

            System.out.println("ERROR: " + ex.getMessage());
        }

    }

}
