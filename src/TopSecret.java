/**
 * Commmand Line Utility
 */
import java.util.*;
public class TopSecret {
    public static void main(String[] args) {

        UserInterface user = new UserInterface();
        int[] commandLineInfo = user.commandLineInterface(args);

        if (commandLineInfo[0] == 0) {
            List<String> files = FileHandler.listFiles();
            for (int i = 0; i < files.size(); i++) {
                System.out.println(String.format("%02d %s", i + 1, files.get(i)));
            }
        }
        else if (commandLineInfo[0] == 1 || commandLineInfo[0] == 2) {
            String content = FileHandler.readFile(commandLineInfo[1]);
            System.out.println(content);
        }
        else {
            System.out.println("Invalid command line arguments.");
        }
    }
}
