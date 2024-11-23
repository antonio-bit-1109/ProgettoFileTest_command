package command.implem;

import command.interf.command;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class SearchStudentCommand implements command {

    private String Test_fileName;
    private String studentName;
    private Scanner scan;

    public void setScan(Scanner scan) {
        this.scan = scan;
    }

    public void setTest_fileName(String test_fileName) {
        Test_fileName = test_fileName;
    }

//    public void setStudentName(String studentName) {
//        this.studentName = studentName;
//    }

    //costr
    public SearchStudentCommand(String fileName, Scanner scan) {
        // setStudentName(studentname);
        setTest_fileName(fileName);
        setScan(scan);
    }

    public String getTest_fileName() {
        return Test_fileName;
    }

    @Override
    public void Execute() {

        if (getTest_fileName() == null) {
            throw new RuntimeException("nome del file null");
        }

        try (BufferedReader br = new BufferedReader(new FileReader(getTest_fileName()))) {

            System.out.println("inserisci nome.");
            String nomeStudente = scan.nextLine();

            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {

                String[] arrStr = line.split(",");
                for (String item : arrStr) {

                    if (item.equals(nomeStudente)) {
                        sb.append(arrStr[0]).append(",").append(arrStr[1]).append(",").append(arrStr[2]);
                    }
                }

            }

            if (!sb.isEmpty()) {
                System.out.println(sb.toString());
            } else {
                System.out.println("nulla da mostrare.");

            }

        } catch (IOException e) {

            throw new RuntimeException("errore durante l'inserimento dei dati nel file.");

        } catch (NoSuchElementException | IllegalStateException ex) {

            throw new RuntimeException("errore durante lettura input.");
        }

    }
}
