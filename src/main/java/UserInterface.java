public class UserInterface {
    // takes in command line args, returns number of command line args (index 0), file to read (if applicable, index 0), and cipher to use (if applicable, index 2)
    public void commandLineInterface(String[] info) {
        // for program controller
        int fileToBeRead;
        String keyFile;

        ProgramController pc = new ProgramController(new FileHandler(), new Cipher());

        switch(info.length) {
            case 0:
                // no command line args
                pc.listFiles();
            case 1:
                // one command line arg, file number
                fileToBeRead = Integer.parseInt(info[0]);
                pc.getFileContent(fileToBeRead);
                break;
            case 2:
                // two command line args, file number and cipher to use for decryption
                fileToBeRead = Integer.parseInt(info[0]);
                keyFile = info[1];
                break;
            default:
                // user entered some invalid number of command line args
                System.out.println("Please enter 0-2 command line arguments.");
        }
    }
}