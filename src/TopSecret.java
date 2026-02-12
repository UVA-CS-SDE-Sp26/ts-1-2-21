/**
 * Commmand Line Utility
 */
import java.util.*;
public class TopSecret {
    public static void main(String[] args) {

        int[] commandLineInfo = commandLineInterface(args);

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

    // takes in command line args, returns number of command line args (index 0), file to read (if applicable, index 0), and cipher to use (if applicable, index 2)
    public static int[] commandLineInterface(String[] info) {
        int[] commandLineArgs = {0, 0, 0};

        switch(info.length) {
            case 0:
                // no command line args
                break;
            case 1:
                // one command line arg, file number
                commandLineArgs[0] = 1;
                commandLineArgs[1] = Integer.parseInt(info[0]);
                break;
            case 2:
                // two command line args, file number and cipher to use for decryption
                commandLineArgs[0] = 2;
                commandLineArgs[1] = Integer.parseInt(info[0]);
                commandLineArgs[2] = Integer.parseInt(info[1]);
                break;
            default:
                // user entered some invalid number of command line args
                System.out.println("Please enter 0-2 command line arguments.");
        }

        return commandLineArgs;



    }
}
