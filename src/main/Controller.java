package main;

import main.map.Map;
import main.players.MechanicTeam;
import main.players.SaboteurTeam;

public class Controller {

    static boolean win = false;
    public static void run(){
        while(!win){
            for(int i = 0; i < 2; i++){
                SaboteurTeam.getInstance().getSabotuer().doAction();
                MechanicTeam.getInstance().getMechanic().doAction();
            }
            Map.getInstance().update();
            Map.getInstance().control();
        }
        win = SaboteurTeam.getInstance().hasWon() || MechanicTeam.getInstance().hasWon();

    }
}
