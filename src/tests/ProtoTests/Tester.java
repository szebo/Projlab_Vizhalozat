package tests.ProtoTests;

import commands.CommandInterpreter;
import main.Main;
import main.logging.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Tester {
    public static CommandInterpreter commandInterpreter = new CommandInterpreter();

    public static ArrayList<String> currentTestLog = null;
    public static ArrayList<String> testCommands = new ArrayList<>();
    /**
     * A tesztek során automatikusan kiadott parancsokat fájlból olvasó függvény.
     * @param file A parancsokat soronként tartalmazó szöveges fájl
     * @return Soronként parsolt parancsok
     * **/
    public static ArrayList<String> commandFileReader(String file) {
        ArrayList<String> cmds = new ArrayList<>();
        try {
            File commandsFile = new File(Main.rootfolder+"/files/tests/commands/"+file);
            Scanner Reader = new Scanner(commandsFile);
            while (Reader.hasNextLine()) {
                cmds.add(Reader.nextLine());
            }
            Reader.close();
        } catch (FileNotFoundException e) {
            Logger.log("log.txt","Test not found", true);
        }
        return cmds;
    }

    /**
     * Az összehasonlítást végzi az elvárt és a kapott kimenet között
     * @param expectedOutputFile Az elvárt kimenet előre megírt sorait tartalmazó fájl neve
     * @return Igazat ha az összehasonlítás sikeres volt, ha valamely sor nem illeszkedett, akkor hamisat
     * **/
    public static void outputComparator(String expectedOutputFile) {

        /*Parsing part*/
        ArrayList<String> expOutput = new ArrayList<>();
        try {
            File file = new File(expectedOutputFile);
            Scanner Reader = new Scanner(file);
            while (Reader.hasNextLine()) {
                expOutput.add(Reader.nextLine());
            }
            Reader.close();
        } catch (FileNotFoundException e) {
            Logger.log("log.txt","File not found: "+expectedOutputFile, true);      //TODO ez kiíródik akkoris, ha nincs
        }

        /* Comparing part */
        for (int i = 0; i < currentTestLog.size() && i < expOutput.size(); i++) {
            if(!expOutput.get(i).equals(currentTestLog.get(i))){
                Logger.log("console.txt","Nem egyezo teszt kimenet:" +
                        "\nElvart: " + expOutput.get(i) +
                        "\nAktualis:" + currentTestLog.get(i), true);
                return;
            }
        }
        Logger.log("console.txt","Egyezo kimenet a " + expectedOutputFile + "fajllal!\n---- Sikeres teszt! ----", true);
    }

    /**
     * Lefuttat egy vagy több testet, és összeveti a kimenetüket az elvárt kimenettel.
     * @param files file(ok) neve(i)
     * **/
    public static void runTest(String files){
        ArrayList<String> tests = new ArrayList<>();
        if(files.contains(",")){
            String[] params = files.split(",");
            for(String p : params){
                tests.add(p);
            }
        }
        else
            tests.add(files);

        for(String test : tests) {
            currentTestLog = new ArrayList<>();
            testCommands.clear();
            testCommands = commandFileReader(test);

            for (String s: testCommands){
                CommandInterpreter.runCommand(s, null, null);
            }
            outputComparator(Main.rootfolder+"/files/tests/expected_outputs/"+test);
            currentTestLog.clear();
            currentTestLog = null;
        }
    }
}
