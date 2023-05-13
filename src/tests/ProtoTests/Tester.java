package tests.ProtoTests;

import commands.CommandInterpreter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Tester {
    public static CommandInterpreter commandInterpreter = new CommandInterpreter();

    /**
     * A tesztek során automatikusan kiadott parancsokat fájlból olvasó függvény.
     * @param filename A parancsokat soronként tartalmazó szöveges fájl
     * @return Soronként parsolt parancsok
     * **/
    public static List<String> commandFileReader(int file) {
        List<String> cmds = new ArrayList<String>();
        try {
            File commandsFile = new File("/files/tests/command/"+file);
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

    /**
     * Az összehasonlítást végzi az elvárt és a kapott kimenet között
     * @param expectedOutputFile Az elvárt kimenet előre megírt sorait tartalmazó fájl neve
     * @param testOutput Az aktuálisan végrehajtott teszt kimenete soronként külön tárolva
     * @return Igazat ha az összehasonlítás sikeres volt, ha valamely sor nem illeszkedett, akkor hamisat
     * **/
    public static boolean outputComparator(String expectedOutputFile, ArrayList<String> testOutput /*vagy ezt is fájlból*/) {

        /*Parsing part*/
        ArrayList<String> expOutput = new ArrayList<String>();
        try {
            File file = new File(expectedOutputFile);
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
                System.out.println("Nem egyezo teszt kimenet:" +
                        "\nElvart: " + expOutput.get(i) +
                        "\nAktualis:" + testOutput.get(i));
                return false;
            }
        }
        System.out.println("Egyezo kimenet a " + expectedOutputFile + "fajllal!\n---- Sikeres teszt! ----");
        return true;
    }

    /**
     * Futtatja a paraméterben megadott parancsokat sorban.
     * @param cmds A kiadandó parancsok soronként szedve
     * @return hamis, ha valamely parancsnak nem volt sikeres a végrehajtása
     * **/
    public static void runTest(int file){
        ArrayList<String> cmds = (ArrayList<String>) commandFileReader(file);
        for (String s: cmds ) commandInterpreter.runCommand(s);
    }
}
