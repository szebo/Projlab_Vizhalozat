package main;

import commands.CommandInterpreter;
import main.graphics.GUIManager;
import main.windowing.Window;

import javax.swing.*;
import java.util.Scanner;

public class Main {
    //public static boolean exit = false;

    public static String rootfolder;

    public static Window window;

    public static void main(String[] args) {
        rootfolder = System.getProperty ("user.dir");
        System.out.println(rootfolder);
        window = new Window();
        window.setVisible(true);
    }
}