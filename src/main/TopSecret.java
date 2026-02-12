/**
 * Commmand Line Utility
 */
public class TopSecret {
    public static void main(String[] args) {
        int[] commandLineInfo = commandLineInterface(args);
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
