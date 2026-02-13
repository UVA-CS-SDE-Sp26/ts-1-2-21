package java;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.crypto.Cipher;
import java.io.File;
import java.io.FileWriter;

import static org.junit.jupiter.api.Assertions.*;

public class CipherTest {
    private Cipher cipher;

    @BeforeEach
    void setUp() throws Exception {
        File file = File.createTempFile("key", ".txt");
        FileWriter writer = new FileWriter(file);
        writer.write("abc\n");
        writer.write("xyz\n");
        writer.close();

        var keyPath = file.getAbsolutePath();
        cipher = new Cipher(keyPath);
    }

    @Test
    void testCipherLoadsKey() {
        assertNotNull(cipher, "Cipher should be created with a valid key file");
    }

    @Test
    void testDecipherSimpleWord() {
        String result = cipher.decipher("xyz");
        assertEquals("abc", result, "xyz should map back to abc");
    }

    @Test
    void testDecipherKeepsUnknownCharacters() {
        String result = cipher.decipher("x!y?z");
        assertEquals("a!b?c", result, "Non-key characters should remain unchanged");
    }

    @Test
    void testInvalidKeyLengthThrowsError() throws Exception {
        File file = File.createTempFile("badkey", ".txt");
        FileWriter writer = new FileWriter(file);
        writer.write("abcd\n");
        writer.write("xyz\n");
        writer.close();

        assertThrows(IllegalArgumentException.class, () -> {
            new Cipher(file.getAbsolutePath());
        }, "Should throw error when key lines are different lengths");
    }
}
