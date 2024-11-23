package shellcontroller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import command.implem.AddStudentCommand;
import command.implem.ExitCommand;
import command.implem.GetAllStudentCommand;
import command.implem.SearchStudentCommand;
import command.interf.command;

public class Shell {

    private Scanner scan;
    private HashMap<String, command> CommandMap;
    private String nomeFile;
    private List<String> list;

    public void setScan() {
        this.scan = new Scanner(System.in);
    }

    public void setNomeFile(String nomeFile) {
        this.nomeFile = nomeFile;
    }

    public void setList() {
        this.list = new ArrayList<>();
    }

    public void setCommandMap() {
        CommandMap = new HashMap<>();
        CommandMap.put("1", new AddStudentCommand(nomeFile, scan));
        CommandMap.put("2", new GetAllStudentCommand(list, nomeFile));
        CommandMap.put("3", new SearchStudentCommand(nomeFile, scan));
        CommandMap.put("4", new ExitCommand());
    }


    // costr
    public Shell(String nomefile) {
        setScan();
        setNomeFile(nomefile);
        setList();
        setCommandMap();
    }

    public void StartPoint() {
        System.out.println("**************************");
        System.out.println("1- aggiungi uno studente");
        System.out.println("2- visualizza elenco studenti");
        System.out.println("3- cerca uno studente per nome");
        System.out.println("4- Esci dal programma");
        System.out.println("**************************");

        String scelta = scan.nextLine();
        this.HandleScelta(scelta);

    }

    private void HandleScelta(String scelta) {

        try {
            command c = CommandMap.get(scelta);
            c.Execute();
        } catch (RuntimeException ex) {

            System.out.println("ERROR: " + ex.getMessage());
        }

    }

}
