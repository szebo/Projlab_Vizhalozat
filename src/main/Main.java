package main;

import commands.CommandInterpreter;
import main.map.Map;
import main.players.Player;
import tests.ProtoTests.Tester;
import tests.SkeletonTests.TestController;

public class Main {
    public static Player currentPlayer = null;

    public static Map map = null;
    public static void main(String[] args) {
        boolean exit = false;
        while(!exit) {
            String cmd = System.console().readLine();
            CommandInterpreter.runCommand(cmd, null);
        }

        //Tester.runTest();
        TestController.runTests();
    }
}