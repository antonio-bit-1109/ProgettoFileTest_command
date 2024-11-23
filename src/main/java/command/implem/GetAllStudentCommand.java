package command.implem;


import command.interf.command;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class GetAllStudentCommand implements command {

    private List<String> list;
    private String Test_fileName;


    public void setTest_fileName(String test_fileName) {
        Test_fileName = test_fileName;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    //costr
    public GetAllStudentCommand(List<String> list, String fileName) {
        setList(list);
        setTest_fileName(fileName);
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
            String line;
            while ((line = br.readLine()) != null) {

                if (!list.contains(line)) {
                    list.add(line);
                }

            }
            System.out.println(this.toString());
        } catch (IOException e) {
            throw new RuntimeException("errore durante la lettura del file");
        }
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (String i : list) {
            sb.append(i).append(" | ").append("\n");
        }
        sb.append("}");
        return sb.toString();
    }
}
