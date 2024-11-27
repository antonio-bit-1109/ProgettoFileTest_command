package command.implem;

import command.interf.command;
import utilityclass.HandleFile;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class AddStudentCommand implements command {

    private Scanner scan;
    //private String Test_fileName;
    private boolean corretto;
    private String inputNome;
    private String inputCognome;
    private String inputEta;
    private HandleFile handleFileCLass;


    public void setScan(Scanner scan) {
        this.scan = scan;
    }

    public void setHandleFileCLass(HandleFile handleFileCLass) {
        this.handleFileCLass = handleFileCLass;
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
    public AddStudentCommand(Scanner scan, HandleFile handlefileclass) {
        setCorretto(false);
        // setTest_fileName(Test_fileName);
        setHandleFileCLass(handlefileclass);
        setScan(scan);
    }

    public HandleFile getHandFile() {
        return handleFileCLass;
    }

    public boolean getCorretto() {
        return this.corretto;
    }

//    public String getTest_fileName() {
//        return Test_fileName;
//    }

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
    public void Execute() throws Exception {


        if (!getHandFile().FileAlreadyExist()) {
            getHandFile().CreatePath();
            getHandFile().CreateFile();
        }


        try (BufferedWriter bw = new BufferedWriter(new FileWriter(getHandFile().GetCompletePath(), true))) {

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
