package main;

import commands.CommandInterpreter;
import main.windowing.Window;

import java.util.Scanner;

public class Main {
    //public static boolean exit = false;

    public static String rootfolder;
    public static void main(String[] args) {
        rootfolder = System.getProperty("user.dir");
        System.out.println(rootfolder);
        Window window = new Window();
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextLine()) {
           String cmd = scanner.nextLine();
           CommandInterpreter.runCommand(cmd, null);
        }
        scanner.close();
        //Tester.runTest();
        //TestController.runTests();
    }
}