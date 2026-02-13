import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserInterfaceTest {
    private UserInterface ui;

    @BeforeEach
    void setUp() {
        ui = new UserInterface();
    }

    @Test
    void testNoArguments() {
        // user enters 0 command line args
        String[] args = {};
        int[] result = ui.commandLineInterface(args);

        assertArrayEquals(new int[]{0, 0, 0}, result, "Should return all zeros for no arguments");
    }

    @Test
    void testOneArgument() {
        // user enters 1 command line arg, that being the file number
        String[] args = {"5"};
        int[] result = ui.commandLineInterface(args);

        assertEquals(1, result[0], "Index 0 should be 1 indicating one argument");
        assertEquals(5, result[1], "Index 1 should contain the parsed integer");
        assertEquals(0, result[2], "Index 2 should remain 0");
    }

    @Test
    void testInvalidNumberOfArguments() {
        // user entered invalid number of args, 3+
        String[] args = {"1", "2", "3"};
        int[] result = ui.commandLineInterface(args);

        // default case returns the initialized array {0, 0, 0}
        assertArrayEquals(new int[]{0, 0, 0}, result, "Should return zeros for invalid argument counts");
    }

    @Test
    void testNonIntegerInput() {
        // verifies that method throws NumberFormatException for non-numeric strings
        String[] args = {"abc"};
        assertThrows(NumberFormatException.class, () -> {
            ui.commandLineInterface(args);
        }, "Should throw exception when input is not a number");
    }

    @Test
    void commandLineInterface() {
        // tests two command line args, file num and cipher num
        String[] args = {"10", "2"};
        int[] result = ui.commandLineInterface(args);

        assertEquals(2, result[0], "Should indicate 2 arguments were parsed");
        assertEquals(10, result[1]);
        assertEquals(2, result[2]);
    }
}
