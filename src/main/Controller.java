package main;

import main.map.Map;
import main.players.MechanicTeam;
import main.players.Player;
import main.players.SaboteurTeam;

public class Controller {

    //Publikus beállítások a játékhoz
    public static int STICKY_FOR_OPTION = 2;
    public static int SLIPPERY_FOR_OPTION = 2;
    public static int PLAYER_COUNT = 4;
    public static Player CURRENT_PLAYER = null;

    static boolean win = false;
    public static boolean randomDebug = false;
    public static void run(){
        Map.getInstance().loadMap("1");
        SaboteurTeam.getInstance().init();
        MechanicTeam.getInstance().init();
        while(!win) {
            for (int i = 0; i < PLAYER_COUNT /2; i++) {
                CURRENT_PLAYER = SaboteurTeam.getInstance().getSaboteur();
                CURRENT_PLAYER.doAction();
                CURRENT_PLAYER = MechanicTeam.getInstance().getMechanic();
                CURRENT_PLAYER.doAction();
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
