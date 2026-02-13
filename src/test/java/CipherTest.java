import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class CipherTest {

    private Cipher cipher;

    @BeforeEach
    void setUp() throws FileNotFoundException {
        cipher = new Cipher();
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
