/**
 * Commmand Line Utility
 */

import java.io.FileNotFoundException;
import java.util.*;
public class TopSecret {
    public static void main(String[] args) throws FileNotFoundException {
        UserInterface user = new UserInterface();
        user.commandLineInterface(args);
    }
}
