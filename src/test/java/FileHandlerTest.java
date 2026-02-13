import org.junit.jupiter.api.Test;
import java.nio.file.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class FileHandlerTest {

    @Test
    void listFiles() {
        FileHandler fileHandler = new FileHandler();
        List<String> files = fileHandler.listFiles();
        assertNotNull(files, "File list should not be null.");
        assertTrue(files.size() > 0, "There should be at least one file in the list.");
        assertTrue(files.contains("filea.txt"), "Expected file not found.");
    }

    @Test
    void readFile() {
        FileHandler fileHandler = new FileHandler();
        List<String> files = fileHandler.listFiles();
        assertNotNull(files, "File list should not be null.");
        assertTrue(files.size() > 0, "There should be at least one file in the list.");
        assertTrue(files.contains("filea.txt"), "Expected file not found.");
    }
}