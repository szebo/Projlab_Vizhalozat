package main;

import main.map.Map;
import main.players.MechanicTeam;
import main.players.Player;
import main.players.SaboteurTeam;
import tests.ProtoTests.Tester;

public class Controller {

    static boolean win = false;
    public static void run(){
        while(!win) {
            for (int i = 0; i < 2; i++) {
                SaboteurTeam.getInstance().getSabotuer().doAction();
                MechanicTeam.getInstance().getMechanic().doAction();
            }
            Map.getInstance().update();
            Map.getInstance().control();
            win = SaboteurTeam.getInstance().hasWon() || MechanicTeam.getInstance().hasWon();
        }
    }

    /**
     * A tesztek futtatásához, hogy lehessen egy játékossal is csinálni dolgokat, és ne keljen végig várni az egész játékot.
     */
    public static void debug_run(){
        for (int i = 0; i < Tester.testCommands.size(); i++) {
            if(SaboteurTeam.getInstance().getPlayers().size()> 0) SaboteurTeam.getInstance().getSabotuer().doAction();
            if(MechanicTeam.getInstance().getPlayers().size()> 0) MechanicTeam.getInstance().getMechanic().doAction();
            Map.getInstance().update();
            Map.getInstance().control();
        }
    }
}
