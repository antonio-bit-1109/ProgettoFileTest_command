package command.implem;


import command.interf.command;
import utilityclass.HandleFile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class GetAllStudentCommand implements command {

    private List<String> list;
    private HandleFile handleFileCLass;


    public void setHandleFileCLass(HandleFile handleFileCLass) {
        this.handleFileCLass = handleFileCLass;
    }


    public void setList(List<String> list) {
        this.list = list;
    }

    //costr
    public GetAllStudentCommand(List<String> list, HandleFile handleFileClass) {
        setList(list);
        setHandleFileCLass(handleFileClass);
        // setTest_fileName(fileName);
    }

    public HandleFile getHandleFileCLass() {
        return handleFileCLass;
    }

    @Override
    public void Execute() {

        try (BufferedReader br = new BufferedReader(new FileReader(getHandleFileCLass().GetCompletePath()))) {
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
        sb.append("{").append("\n");
        for (String i : list) {
            sb.append(i).append("\n");
        }
        sb.append("}");
        return sb.toString();
    }
}
