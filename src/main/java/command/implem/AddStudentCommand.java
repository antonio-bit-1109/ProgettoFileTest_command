package command.implem;

import command.interf.command;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class AddStudentCommand implements command {

    private Scanner scan;
    private String Test_fileName;
    private boolean corretto;
    private String inputNome;
    private String inputCognome;
    private String inputEta;

    public void setScan(Scanner scan) {
        this.scan = scan;
    }

    public void setTest_fileName(String test_fileName) {
        Test_fileName = test_fileName;
    }

    public void setCorretto(boolean corretto) {
        this.corretto = corretto;
    }

    public void setInputNome(String inputNome) {
        this.inputNome = inputNome;
    }

    public void setInputCognome(String inputCognome) {
        this.inputCognome = inputCognome;
    }

    public void setInputEta(String inputEta) {
        this.inputEta = inputEta;
    }

    //costr
    public AddStudentCommand(String Test_fileName, Scanner scan) {
        setCorretto(false);
        setTest_fileName(Test_fileName);
        setScan(scan);
    }

    public boolean getCorretto() {
        return this.corretto;
    }

    public String getTest_fileName() {
        return Test_fileName;
    }

    public String getInputNome() {
        return inputNome;
    }

    public String getInputCognome() {
        return inputCognome;
    }

    public String getInputEta() {
        return inputEta;
    }

    @Override
    public void Execute() {

        if (getTest_fileName() == null) {
            throw new RuntimeException("nome del file null");
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(getTest_fileName(), true))) {

            getInputUser();

            StringBuilder sb = new StringBuilder();
            bw.write(String.valueOf(sb.append(getInputNome()).append(",").append(getInputCognome()).append(",").append(getInputEta())));
            bw.newLine();
            System.out.println("Utente aggiunto al file nel formato: nome,cognome,età ");
        } catch (IOException ex) {

            throw new RuntimeException("errore durante l'inserimento dei dati nel file." + ex);

        }

    }

    private void getInputUser() {

        do {

            try {
                System.out.println("inserisci nome");
                String inputNome = scan.nextLine();
                setInputNome(inputNome);

                System.out.println("inserisci cognome");
                String inputCognome = scan.nextLine();
                setInputCognome(inputCognome);


                System.out.println("inserisci eta");
                String inputEta = scan.nextLine();

                if (!IsParsable(inputEta)) {
                    throw new RuntimeException("formato età non valido.");
                }

                setInputEta(inputEta);
                setCorretto(true);

            } catch (NoSuchElementException | IllegalStateException ex) {
                System.out.println("premi enter per continuare.");
                scan.nextLine();
                resetInputsUtente();
                throw new RuntimeException("errore durante lettura input." + ex);

            } catch (RuntimeException e) {
                System.out.println("premi enter per continuare.");
                scan.nextLine();
                resetInputsUtente();
                throw new RuntimeException(e.getMessage());

            }
        } while (!getCorretto());


    }

    private boolean IsParsable(String val) {

        try {
            Integer.parseInt(val);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    private void resetInputsUtente() {
        this.setInputNome("");
        this.setInputCognome("");
        this.setInputEta("");
    }
}
