import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class CipherTest {

    private Cipher cipher;

    @BeforeEach
    void setUp() throws FileNotFoundException {
        cipher = new Cipher();
    }

    // testing the loadKey method, making sure the logic is correct if a missing file is identified.
    @Test
    void testLoadKeyFileNotFound() {
        assertThrows(FileNotFoundException.class, () -> {
            cipher.decipher("message", "ciphers/nonexistent.txt");
        }, "loadKey should throw FileNotFoundException for missing paths.");
    }

    @Test
    void testValidateKey() throws Exception {
        Method loadMethod = Cipher.class.getDeclaredMethod("loadKey", String.class);
        loadMethod.setAccessible(true);
        loadMethod.invoke(cipher, "ciphers/key.txt");

        Method validateMethod = Cipher.class.getDeclaredMethod("validateKey");
        validateMethod.setAccessible(true);

        assertDoesNotThrow(() -> validateMethod.invoke(cipher), "validateKey should pass once a valid key file has been loaded.");
    }

    @Test
    void testCipherLoads() {
        assertNotNull(cipher, "Cipher should initialize with a valid key file.");
    }

    @Test
    void testDecipherSingleCharacter() throws FileNotFoundException {
        assertEquals("a", cipher.decipher("b", "ciphers/key.txt"), "Decipher 'b' should return 'a'.");
    }

    @Test
    void testDecipherMixedCharacters() throws FileNotFoundException {
        assertEquals("aAB3", cipher.decipher("bBC4", "ciphers/key.txt"), "Should correctly decipher mixed characters.");
    }
}
