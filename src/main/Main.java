package main;

import commands.CommandInterpreter;
import main.graphics.GUIManager;
import main.windowing.Window;

import javax.swing.*;
import java.util.Scanner;

public class Main {
    //public static boolean exit = false;

    public static String rootfolder;
    public static void main(String[] args) {
        rootfolder = System.getProperty("user.dir");
        System.out.println(rootfolder);
        Window window = new Window();
        while(window.isActive()){
            try {
                GUIManager.getInstance().wait();
                CommandInterpreter.runCommand(GUIManager.getInstance().getGUIMessage(), null);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        /*Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextLine()) {
           String cmd = scanner.nextLine();
           CommandInterpreter.runCommand(cmd, null);
        }
        scanner.close();*/
        //Tester.runTest();
        //TestController.runTests();
    }
}