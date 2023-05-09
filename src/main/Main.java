package main;

import main.map.Map;
import main.players.Player;
import tests.ProtoTests.Tester;
import tests.SkeletonTests.TestController;

public class Main {
    public static Player currentPlayer = null;

    public static Map map = null;
    public static void main(String[] args) {

        //Tester.runTest();
        TestController.runTests();
    }
}