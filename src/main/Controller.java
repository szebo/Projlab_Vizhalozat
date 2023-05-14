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

    /**
     * A tesztek futtatásához, hogy lehessen egy játékossal is csinálni dolgokat, és ne keljen végig várni az egész játékot.
     */
    public static void debug_run(){
        /*for (int i = 0; i < Tester.testCommands.size(); i++) {
            if(SaboteurTeam.getInstance().getPlayers().size()> 0){
                Saboteur s = SaboteurTeam.getInstance().getSaboteur();
                s.doCommand(Tester.testCommands.get(i));
                s.doCommand(Tester.testCommands.get(i));
            }
            if(MechanicTeam.getInstance().getPlayers().size()> 0){
                Mechanic m = MechanicTeam.getInstance().getMechanic();
                m.doCommand(Tester.testCommands.get(i));
                m.doCommand(Tester.testCommands.get(i));
            }
            Map.getInstance().update();
            Map.getInstance().control();
        }*/
    }
}
