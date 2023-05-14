package main;

import commands.CommandInterpreter;
import main.map.Map;

import java.io.Console;
import java.util.Scanner;

public class Main {
    public static boolean exit = false;

    public static String rootfolder;
    public static void main(String[] args) {
        rootfolder = System.getProperty("user.dir");
        System.out.println(rootfolder);
        Controller.run();
        //Scanner scanner = new Scanner(System.in);
        //while(scanner.hasNextLine()) {
        //   String cmd = scanner.nextLine();
        //   CommandInterpreter.runCommand(cmd, null);
        //}
        //scanner.close();
        //Tester.runTest();
        //TestController.runTests();
    }
}