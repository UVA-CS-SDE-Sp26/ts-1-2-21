import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import package_name.Cipher;
import static org.junit.jupiter.api.Assertions.*;

public class CipherTest {

    private Cipher cipher;

    @BeforeEach
    void setUp() throws Exception {
        cipher = new Cipher("ciphers/key.txt");
    }

    @Test
    void testCipherLoads() {
        assertNotNull(cipher);
    }

    @Test
    void testDecipherSingleCharacter() {
        assertEquals("a", cipher.decipher("b"));
    }

    @Test
    void testDecipherWord() {
        assertEquals("0ab", cipher.decipher("abc"));
    }

    @Test
    void testDecipherKeepsUnknownCharacters() {
        assertEquals("a!", cipher.decipher("b!"));
    }
}
