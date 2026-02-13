import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ProgramControllerTest {
    private FileHandler mockFileHandler;
    private Cipher mockCipher;
    private ProgramController programController;

    @BeforeEach
    public void setUp() {
        mockFileHandler = mock(FileHandler.class);
        mockCipher = mock(Cipher.class);
        programController = new ProgramController(mockFileHandler, mockCipher);
    }

    @Test
    public void testListFiles() {
        List<String> files = Arrays.asList("filea.txt", "fileb.txt", "filec.txt");
        when(mockFileHandler.listFiles()).thenReturn(files);

        String result = programController.listFiles();

        assertTrue(result.contains("01 filea.txt"));
        assertTrue(result.contains("02 fileb.txt"));
        assertTrue(result.contains("03 filec.txt"));
    }

    @Test
    public void testGetFileContent_Success() throws IOException {
        List<String> files = Arrays.asList("filea.txt", "fileb.txt");
        String encryptedContent = "KHOOR ZRUOG";
        String decryptedContent = "HELLO WORLD";

        when(mockFileHandler.listFiles()).thenReturn(files);
        when(mockFileHandler.readFile(1)).thenReturn(encryptedContent);
        when(mockCipher.decipher(encryptedContent, "ciphers/key.txt")).thenReturn(decryptedContent);

        String result = programController.getFileContent(1);

        assertEquals(decryptedContent, result);
    }

    @Test
    public void testGetFileContent_InvalidFileNumber() {
        List<String> files = Arrays.asList("filea.txt");
        when(mockFileHandler.listFiles()).thenReturn(files);

        String result = programController.getFileContent(5);

        assertTrue(result.contains("Error"));
        assertTrue(result.contains("Invalid file number"));
    }

    @Test
    public void testGetFileContent_WithCustomKey() throws IOException {
        List<String> files = Arrays.asList("filea.txt");
        String customKey = "ciphers/alternate.txt";

        when(mockFileHandler.listFiles()).thenReturn(files);
        when(mockFileHandler.readFile(1)).thenReturn("ENCRYPTED");
        when(mockCipher.decipher("ENCRYPTED", customKey)).thenReturn("DECRYPTED");

        String result = programController.getFileContent(1, customKey);

        assertEquals("DECRYPTED", result);
        verify(mockCipher).decipher("ENCRYPTED", customKey);
    }
}