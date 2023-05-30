package main;

import main.graphics.GUIManager;
import main.map.Map;
import main.players.MechanicTeam;
import main.players.Player;
import main.players.SaboteurTeam;
import main.windowing.Window;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller {

    //Publikus beállítások a játékhoz
    public static int STICKY_FOR_OPTION = 2;
    public static int SLIPPERY_FOR_OPTION = 2;
    public static int PLAYER_COUNT = 4;
    public static Player CURRENT_PLAYER = null;

    static boolean win = false;
    public static boolean randomDebug = false;

    public static void init(){
        SaboteurTeam.getInstance().init();
        MechanicTeam.getInstance().init();
    }

    public static int team = 0;

    public static KeyListener key = new KeyListener() {
        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {


        }

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            if(team%2 == 1) {
                switch (key) {
                    case KeyEvent.VK_SPACE -> CURRENT_PLAYER.setCurrentAction(Player.Action.step);
                    case KeyEvent.VK_A -> CURRENT_PLAYER.setCurrentAction(Player.Action.heal);
                    case KeyEvent.VK_D -> CURRENT_PLAYER.setCurrentAction(Player.Action.breakelement);

                    case KeyEvent.VK_S -> CURRENT_PLAYER.setCurrentAction(Player.Action.configure);

                    case KeyEvent.VK_Q -> CURRENT_PLAYER.setCurrentAction(Player.Action.pipepickup);
                    case KeyEvent.VK_W -> CURRENT_PLAYER.setCurrentAction(Player.Action.pipeplace);
                    case KeyEvent.VK_E -> CURRENT_PLAYER.setCurrentAction(Player.Action.sticky);

                    case KeyEvent.VK_R -> CURRENT_PLAYER.setCurrentAction(Player.Action.pumppickup);
                    case KeyEvent.VK_F -> CURRENT_PLAYER.setCurrentAction(Player.Action.pumpplace);

                }
            }
            else{
                switch (key) {
                    case KeyEvent.VK_SPACE -> CURRENT_PLAYER.setCurrentAction(Player.Action.step);
                    case KeyEvent.VK_D -> CURRENT_PLAYER.setCurrentAction(Player.Action.breakelement);

                    case KeyEvent.VK_S -> CURRENT_PLAYER.setCurrentAction(Player.Action.configure);

                    case KeyEvent.VK_E -> CURRENT_PLAYER.setCurrentAction(Player.Action.sticky);
                    case KeyEvent.VK_R -> CURRENT_PLAYER.setCurrentAction(Player.Action.slippery);

                }
            }
        }

    };

    public static void run(){
        if(!win) {
            GUIManager.getInstance().repaintGame();
            if(team%2 == 0) {
                System.out.println("Saboteur");
                CURRENT_PLAYER = SaboteurTeam.getInstance().getSaboteur();
                CURRENT_PLAYER.doAction();
                Main.window.playerActed();
                team = 1;
            }
            else {
                System.out.println("Mechanic");
                CURRENT_PLAYER = MechanicTeam.getInstance().getMechanic();
                CURRENT_PLAYER.doAction();
                Main.window.playerActed();
                team = 0;
            }
            if(CURRENT_PLAYER.getCurrentAction() == Player.Action.endturn) {
                Map.getInstance().update();
                //Map.getInstance().control();
                Map.getInstance().waterFlow(0);
                SaboteurTeam.getInstance().getPoints();
                MechanicTeam.getInstance().getPoints();
                CURRENT_PLAYER.setCurrentAction(Player.Action.nothing);
            }
            win = SaboteurTeam.getInstance().hasWon() || MechanicTeam.getInstance().hasWon();
        }
    }
}
