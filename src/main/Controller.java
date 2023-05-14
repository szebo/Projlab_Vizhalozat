package main;

import main.map.Map;
import main.players.*;
import tests.ProtoTests.Tester;

public class Controller {

    static boolean win = false;
    public static void run(){
        while(!win) {
            for (int i = 0; i < 2; i++) {
                SaboteurTeam.getInstance().getSaboteur().doAction();
                MechanicTeam.getInstance().getMechanic().doAction();
            }
            Map.getInstance().update();
            Map.getInstance().control();
            Map.getInstance().waterFlow(2);
            win = SaboteurTeam.getInstance().hasWon() || MechanicTeam.getInstance().hasWon();
        }
    }
}
