package main;

import commands.CommandInterpreter;

import java.io.Console;
import java.util.Scanner;

public class Main {
    public static boolean exit = false;

    public static String rootfolder;
    public static void main(String[] args) {
        rootfolder = System.getProperty("user.dir");
        System.out.println(rootfolder);
        while(!exit) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Reading cmd...");
            String cmd = scanner.nextLine();
            System.out.println("Cmd read");
            CommandInterpreter.runCommand(cmd, null);
            scanner.close();
        }

        //Tester.runTest();
        //TestController.runTests();
    }
}