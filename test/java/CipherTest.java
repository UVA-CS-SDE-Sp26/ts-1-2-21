import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;

import static org.junit.jupiter.api.Assertions.*;

public class CipherTest {

    private Cipher cipher;

    @BeforeEach
    void setUp() throws Exception {
        cipher = new Cipher("ciphers/key.txt");
    }

    @Test
    void testCipherLoadsSuccessfully() {
        assertNotNull(cipher, "Cipher should initialize with a valid key file");
    }

    @Test
    void testDecipherSingleCharacter() {
        assertEquals("a", cipher.decipher("b"),
                "Encoded 'b' should map back to 'a'");
    }

    @Test
    void testDecipherMultipleCharacters() {
        assertEquals("0ab", cipher.decipher("abc"),
                "Should correctly decipher multiple characters in sequence");
    }

    @Test
    void testDecipherNumbers() {
        assertEquals("012", cipher.decipher("123"),
                "Numbers should rotate back correctly");
    }

    @Test
    void testDecipherMixedString() {
        String result = cipher.decipher("bC1!");
        assertEquals("aB0!", result,
                "Should decipher letters and numbers but keep unknown characters");
    }

    @Test
    void testDuplicateCharacterInKeyThrowsException() throws Exception {
        File badKey = File.createTempFile("badkey", ".txt");
        FileWriter writer = new FileWriter(badKey);
        writer.write("aabc\n");   // duplicate 'a'
        writer.write("bcde\n");
        writer.close();

        assertThrows(IllegalArgumentException.class, () -> {
            new Cipher(badKey.getAbsolutePath());
        }, "Duplicate characters in key should throw exception");
    }
}
