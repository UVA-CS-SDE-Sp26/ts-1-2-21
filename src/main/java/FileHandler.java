import java.io.*;
import java.nio.file.*;
import java.util.*;

public class FileHandler {

    public List<String> listFiles() {
        List<String> fileNames = new ArrayList<>();
        File folder = new File("data");
        File[] listOfFiles = folder.listFiles();

        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                if (file.isFile()) {
                    fileNames.add(file.getName());
                }
            }
        } else {
            System.out.println("Error: Unable to access the data folder.");
        }
        return fileNames;
    }

    public String readFile(int fileNumber) {
        File folder = new File("data");
        File[] listOfFiles = folder.listFiles();

        if (listOfFiles != null && fileNumber > 0 && fileNumber <= listOfFiles.length) {
            File selectedFile = listOfFiles[fileNumber - 1];
            try {
                String content = new String(Files.readAllBytes(selectedFile.toPath()));
                return content;
            } catch (IOException e) {
                return "Error reading the file.";
            }
        } else {
            return "Invalid file number.";
        }
    }
}
