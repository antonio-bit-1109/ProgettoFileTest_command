package utilityclass;

import command.interf.IHandleFile;
import database.DTOs.ProdottoDTO;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class HandleFile implements IHandleFile {

    private final String rootDir = System.getProperty("user.dir");
    private final String foldersPath = "src/main/java/resources/fileFolder";
    private final Path completePath = Paths.get(rootDir, foldersPath, getFileName());
    private HandleProdotti handleProdotti;

    public void setHandleProdotti(HandleProdotti handleProdotti) {
        this.handleProdotti = handleProdotti;
    }

    //costr vuoto
    public HandleFile(HandleProdotti handProd) {
        setHandleProdotti(handProd);
    }

    public HandleProdotti getHandleProdotti() {
        return handleProdotti;
    }

    public String getFileName() {
        return "/file.txt";
    }

    public boolean FileAlreadyExist() {
        Path filePath = Paths.get(completePath.toString());
        return Files.exists(filePath);
    }


    public void CreatePath() throws IOException {
        Files.createDirectories(Paths.get(rootDir, foldersPath));
    }

    public void CreateFile() throws IOException {
        Files.createFile(Paths.get(completePath.toString()));
    }

    public String GetCompletePath() {
        return completePath.toString();
    }

    @Override
    public void WriteOnFile() {

        if (handleProdotti.getListaProdotti().isEmpty()) {
            System.out.println("la lista dati è vuota. fai prima una get da database");
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(GetCompletePath(), true))) {

            List<String> listaStringhe = retriveDataFromObject();

            for (String str : listaStringhe) {

                String[] subArr = str.split(",");

                StringBuilder sb = new StringBuilder();
                bw.write(String.valueOf(sb.append(subArr[0]).append(",").append(subArr[1]).append(",").append(subArr[2])));
                bw.newLine();
            }

            handleProdotti.getListaProdotti().clear();
            // this.getHandleProdotti().getMappaTransaz().put(4, "scrittura su file della lista prodotti ");
            this.getHandleProdotti().AddToMap("scrittura su file della lista prodotti ");
            // listaProdotti.clear();

        } catch (IOException e) {
            throw new RuntimeException("errore durante l'inserimento dei dati nel file.");
        }

    }


    @Override
    public void ReadFromFile() {

    }

    private List<String> retriveDataFromObject() {

        List<String> StrList = new ArrayList<>();

        for (int i = 0; i < handleProdotti.getListaSize(); i++) {
            ProdottoDTO obj = handleProdotti.getListaProdotti().get(i);

            StringBuilder sb = new StringBuilder();
            sb.append(obj.getId()).append(",").append(obj.getNome()).append(",").append(obj.getQuantita()).append("\n");
            StrList.add(i, sb.toString());
        }
        return StrList;
    }
}
