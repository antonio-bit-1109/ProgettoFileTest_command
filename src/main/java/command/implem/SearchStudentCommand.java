package command.implem;

import command.interf.command;
import utilityclass.HandleFile;
import utilityclass.HandleProdotti;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class SearchStudentCommand implements command {

    //  private String Test_fileName;
    private Scanner scan;
    private HandleFile handleFileCLass;
    private HandleProdotti handleProdotti;


    public void setHandleProdotti(HandleProdotti handleProdotti) {
        this.handleProdotti = handleProdotti;
    }

    public void setScan(Scanner scan) {
        this.scan = scan;
    }

    public void setHandleFileCLass(HandleFile handleFileCLass) {
        this.handleFileCLass = handleFileCLass;
    }

    //costr
    public SearchStudentCommand(HandleFile handlefileclass, Scanner scan, HandleProdotti handleProd) {
        // setStudentName(studentname);
        setHandleFileCLass(handlefileclass);
        setScan(scan);
        setHandleProdotti(handleProd);
    }

    public HandleProdotti getHandleProdotti() {
        return handleProdotti;
    }

    public HandleFile getHandleFileCLass() {
        return handleFileCLass;
    }


    @Override
    public void Execute() {

        try (BufferedReader br = new BufferedReader(new FileReader(getHandleFileCLass().GetCompletePath()))) {

            System.out.println("inserisci nome.");
            String nomeStudente = scan.nextLine().trim().toLowerCase();

            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {

                String[] arrStr = line.split(",");
                for (String item : arrStr) {

                    if (item.equals(nomeStudente)) {
                        System.out.println("trovata corrispondenza.");
                        sb.append(arrStr[0]).append(",").append(arrStr[1]).append(",").append(arrStr[2]);
                        break;
                    }
                }

            }

            if (!sb.isEmpty()) {
                System.out.println(sb);
                getHandleProdotti().AddToMap("effettuata ricerca su file del nome: " + nomeStudente + "." + " | Esito positivo.");
            } else {
                System.out.println("nulla da mostrare.");
                getHandleProdotti().AddToMap("effettuata ricerca su file del nome: " + nomeStudente + "." + " | Esito negativo.");
            }

        } catch (IOException e) {

            throw new RuntimeException("errore durante l'inserimento dei dati nel file.");

        } catch (NoSuchElementException | IllegalStateException ex) {

            throw new RuntimeException("errore durante lettura input.");
        }

    }
}
