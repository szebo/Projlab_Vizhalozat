package main;

import main.map.Map;
import main.players.MechanicTeam;
import main.players.SaboteurTeam;

public class Controller {

    static boolean win = false;
    public static void run(){
        Map.getInstance().loadMap("1");
        SaboteurTeam.getInstance().init();
        MechanicTeam.getInstance().init();
        while(!win) {
            for (int i = 0; i < 2; i++) {
                SaboteurTeam.getInstance().getSaboteur().doAction();
                MechanicTeam.getInstance().getMechanic().doAction();
            }
            Map.getInstance().update();
            //Map.getInstance().control();
            Map.getInstance().waterFlow(0);
            SaboteurTeam.getInstance().getPoints();
            MechanicTeam.getInstance().getPoints();
            win = SaboteurTeam.getInstance().hasWon() || MechanicTeam.getInstance().hasWon();
        }
        Map.getInstance().saveMap(42);
    }
}
