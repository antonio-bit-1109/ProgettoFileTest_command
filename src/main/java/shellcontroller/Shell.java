package shellcontroller;

import command.interf.Istudent;
import command.interf.impl.*;

import java.util.HashMap;
import java.util.Scanner;

public class Shell {

    private Scanner scan;
    private HashMap<String, Command<String, Integer>> map;

    public void setScan() {
        this.scan = new Scanner(System.in);
    }

//    public void setMap() {
//        this.map = new HashMap<>();
//        this.map.put("1", new AddStudentCommand());
//        this.map.put("2", new GetAllStudentCommand());
//        this.map.put("3", new SearchStudentCommand());
//        this.map.put("4", new ExitCommand());
//    }

    // costr
    public Shell() {
        setScan();
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

        // chiamata ricorsiva startPoint
        StartPoint();
    }

    private void HandleScelta(String scelta) {

        switch (scelta) {
            case "1":
                map.get("1");
                break;
            case "2":
                map.get("2");
                break;
            case "3":
                map.get("3");
                break;
            case "4":
                map.get("4");
                break;
        }

    }

//    @Override
//    public void addStudent(Object nome, Object cognome, Object eta) {
//
//    }
//
//    @Override
//    public List getAllStudents() {
//        return List.of();
//    }
//
//    @Override
//    public Object searchStudent(Object studentName) {
//        return null;
//    }
}
