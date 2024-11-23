package manager;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentManager {

    private String Test_file;
    private List<String> list;

    public void setTest_file(String test_file) {
        Test_file = test_file;
    }

    public void setList() {
        this.list = new ArrayList<>();
    }

    // costruct
    public StudentManager(String test_file) {
        setTest_file(test_file);
        setList();
    }


    public String getTest_fileName() {
        return this.Test_file;
    }

    // aggiungere le stringhe passate al metodo all interno del file.
    // scrivere su file
    public void addStudent(String nome, String cognome, int eta) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(getTest_fileName(), true))) {

            StringBuilder sb = new StringBuilder();
            bw.write(String.valueOf(sb.append(nome).append(",").append(cognome).append(",").append(eta)));
            bw.newLine();

        } catch (IOException e) {
            throw new RuntimeException("errore durante l'inserimento dei dati nel file.");
        }

    }

    // leggere da un file e inserire i valori dentro la lista ??
    public List<String> getAllStudents() {

        try (BufferedReader br = new BufferedReader(new FileReader(getTest_fileName()))) {
            String line;
            while ((line = br.readLine()) != null) {
                list.add(line);
            }
            return list;
        } catch (IOException e) {
            throw new RuntimeException("errore durante la lettura del file");
        }

    }


    // leggere dal file
    public String searchStudent(String studentName) {
        try (BufferedReader br = new BufferedReader(new FileReader(getTest_fileName()))) {
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {

                String[] arrStr = line.split(",");
                for (String item : arrStr) {

                    if (item.equals(studentName)) {
                        sb.append(arrStr[0]).append(",").append(arrStr[1]).append(",").append(arrStr[2]);
                    }
                }

            }

            if (!sb.isEmpty()) {
                return sb.toString();
            } else {
                return null;
            }

        } catch (IOException ex) {
            throw new RuntimeException("errore durante la lettura del file.");
        }

    }

}
