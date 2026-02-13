import java.util.List;

public class ProgramController {
    private FileHandler fileHandler;
    private Cipher cipher;
    private static final String DEFAULT_KEY = "ciphers/key.txt";

    public ProgramController(FileHandler fileHandler, Cipher cipher) {
        this.fileHandler = fileHandler;
        this.cipher = cipher;
    }

    public String listFiles() {
        List<String> files = fileHandler.listFiles();

        if (files == null || files.isEmpty()) {
            return "No files available.";
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < files.size(); i++) {
            result.append(String.format("%02d %s%n", i + 1, files.get(i)));
        }

        return result.toString().trim();
    }

    public String getFileContent(int fileNumber, String keyFile) {
        // Validate file number
        List<String> files = fileHandler.listFiles();

        if (fileNumber < 1 || fileNumber > files.size()) {
            return "Error: Invalid file number.";
        }

        // Read file content
        String content = fileHandler.readFile(fileNumber);

        if (content == null) {
            return "Error: Could not read file.";
        }

        // Decipher
        if (cipher != null) {
            String key = (keyFile != null) ? keyFile : DEFAULT_KEY;
            try {
                content = cipher.decipher(content, key);
            } catch (Exception e) {
                return "Error: Could not decipher file. " + e.getMessage();
            }
        }

        return content;
    }

    public String getFileContent(int fileNumber) {
        return getFileContent(fileNumber, null);
    }
}

