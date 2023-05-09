package tests.ProtoTests;

import commands.CommandInterpreter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Tester {
    public static CommandInterpreter commandInterpreter = new CommandInterpreter();

    public static List<String> commandFileReader(String filename) {
        List<String> cmds = new ArrayList<String>();
        try {
            File commandsFile = new File(filename);
            Scanner Reader = new Scanner(commandsFile);
            while (Reader.hasNextLine()) {
                cmds.add(Reader.nextLine());
            }
            Reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred");
        }
        return cmds;
    }

    public static boolean outputComparator(String expedtedOutputFile, ArrayList<String> testOutput /*vagy ezt is fájlból*/) {
        ArrayList<String> expOutput = new ArrayList<String>();
        try {
            File file = new File(expedtedOutputFile);
            Scanner Reader = new Scanner(file);
            while (Reader.hasNextLine()) {
                expOutput.add(Reader.nextLine());
            }
            Reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred");
        }

        /* Comparing part */

        for (int i = 0; i < testOutput.size() && i < expOutput.size(); i++) {
            if(!expOutput.get(i).equals(testOutput.get(i))){
                System.out.println("Nem egyező teszt kimenet:" +
                        "\nElvárt: " + expOutput.get(i) +
                        "\nAktuális:" + testOutput.get(i));
                return false;
            }
        }
        System.out.println("Egyező kimenet a " + expedtedOutputFile + "fájllal!\tSikeres teszt!");
        return true;
    }

    public static void runTest(String[] cmds){
        for (String s: cmds ) commandInterpreter.runCommand(s);
    }
}
