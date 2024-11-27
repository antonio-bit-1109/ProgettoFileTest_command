package utilityclass;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class HandleFile {

    private final String rootDir = System.getProperty("user.dir");
    private final String foldersPath = "src/main/java/resources/fileFolder";
    private final Path completePath = Paths.get(rootDir, foldersPath, getFileName());

    //costr vuoto

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
}
