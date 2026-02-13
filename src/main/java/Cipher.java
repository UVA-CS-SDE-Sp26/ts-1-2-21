import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Cipher {

    private String normalChars;
    private String encodedChars;

    public Cipher(String path) throws FileNotFoundException {
        loadKey(path);
    }

    private void loadKey(String path) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(path));

        if (!scanner.hasNextLine()) {
            scanner.close();
            throw new IllegalArgumentException("Key missing first line.");
        }
        normalChars = scanner.nextLine();

        if (!scanner.hasNextLine()) {
            scanner.close();
            throw new IllegalArgumentException("Key missing second line.");
        }
        encodedChars = scanner.nextLine();

        scanner.close();
        validateKey();
    }

    private void validateKey() {
        if (normalChars.length() != encodedChars.length()) {
            throw new IllegalArgumentException("Key lines must be the same length.");
        }

        for (int i = 0; i < normalChars.length(); i++) {
            char normal = normalChars.charAt(i);
            char encoded = encodedChars.charAt(i);

            if (normalChars.indexOf(normal) != i) {
                throw new IllegalArgumentException("Duplicate character in first line.");
            }

            if (encodedChars.indexOf(encoded) != i) {
                throw new IllegalArgumentException("Duplicate character in second line.");
            }
        }
    }

    public String decipher(String message, String keyPath) throws FileNotFoundException {
        loadKey(keyPath);

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < message.length(); i++) {
            char currentChar = message.charAt(i);
            int position = encodedChars.indexOf(currentChar);

            if (position != -1) {
                result.append(normalChars.charAt(position));
            } else {
                result.append(currentChar);
            }
        }

        return result.toString();
    }
}
