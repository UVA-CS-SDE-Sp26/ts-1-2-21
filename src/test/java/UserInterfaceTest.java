import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertTrue;

class UserInterfaceTest {
    private UserInterface ui;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        ui = new UserInterface();
        // Redirect System.out to capture the program's response
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void testCommandLineInterface_NoArgs() throws FileNotFoundException {
        String[] args = {};
        ui.commandLineInterface(args);

        // Verifies it calls pc.listFiles() logic
        // Should contain "No files available" or a file list
        assertTrue(outputStreamCaptor.toString().trim().length() > 0);
    }

    @Test
    void testCommandLineInterface_InvalidArgs() throws FileNotFoundException {
        String[] args = {"1", "key.txt", "extra"};
        ui.commandLineInterface(args);

        // Verifies the 'default' switch case
        assertTrue(outputStreamCaptor.toString().contains("Please enter 0-2 command line arguments."));
    }

    @Test
    void testCommandLineInterface_OneArg_InvalidFile() throws FileNotFoundException {
        String[] args = {"999"}; // A file number that likely doesn't exist
        ui.commandLineInterface(args);

        // Verifies it passes 999 to getFileContent which should return an error
        assertTrue(outputStreamCaptor.toString().contains("Error"));
    }
}