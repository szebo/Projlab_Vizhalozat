package main;

import main.graphics.GUIManager;
import main.map.Map;
import main.map.MapElement;
import main.players.*;
import main.windowing.Window;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller {

    //Publikus beállítások a játékhoz
    public static int STICKY_FOR_OPTION = 2;
    public static int SLIPPERY_FOR_OPTION = 2;
    public static int PLAYER_COUNT = 4;
    public static Player CURRENT_PLAYER = null;

    public static MapElement SELECTED_ELEMENT = null;

    static boolean win = false;
    public static boolean randomDebug = false;

    public static void init(){
        SaboteurTeam.getInstance().init();
        MechanicTeam.getInstance().init();
    }

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
            if(state == GameState.mechanicTurn) {
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

                    case KeyEvent.VK_ENTER -> CURRENT_PLAYER.setCurrentAction(Player.Action.endturn);

                }
            }
            else if (state == GameState.saboteurTurn){
                switch (key) {
                    case KeyEvent.VK_SPACE -> CURRENT_PLAYER.setCurrentAction(Player.Action.step);
                    case KeyEvent.VK_D -> CURRENT_PLAYER.setCurrentAction(Player.Action.breakelement);

                    case KeyEvent.VK_S -> CURRENT_PLAYER.setCurrentAction(Player.Action.configure);

                    case KeyEvent.VK_E -> CURRENT_PLAYER.setCurrentAction(Player.Action.sticky);
                    case KeyEvent.VK_R -> CURRENT_PLAYER.setCurrentAction(Player.Action.slippery);

                    case KeyEvent.VK_ENTER -> CURRENT_PLAYER.setCurrentAction(Player.Action.endturn);
                }
            }
        }

    };

    private enum GameState { saboteurTurn, mechanicTurn}

    public static int turnsEnded = 0;

    private static GameState state = GameState.saboteurTurn;

    public static void run(){
        if(!win) {
            GUIManager.getInstance().repaintGame();

            if(CURRENT_PLAYER == null){
                if(state == GameState.saboteurTurn)
                    CURRENT_PLAYER = SaboteurTeam.getInstance().getSaboteur();
                else if(state == GameState.mechanicTurn)
                    CURRENT_PLAYER = MechanicTeam.getInstance().getMechanic();
            }
            else{
                if(state == GameState.saboteurTurn && CURRENT_PLAYER.getCurrentAction() != Player.Action.nothing) {
                    System.out.println("Saboteur Action");
                    if (CURRENT_PLAYER.getActions() > 0)
                        CURRENT_PLAYER.doAction();
                    if (CURRENT_PLAYER.getActions() <= 0) {
                        CURRENT_PLAYER.resetActions();
                        state = GameState.mechanicTurn;
                        turnsEnded++;
                        CURRENT_PLAYER = null;
                    }
                }
                else if(state == GameState.mechanicTurn && CURRENT_PLAYER.getCurrentAction() !=  Player.Action.nothing) {
                    System.out.println("Mechanic Action");
                    if(CURRENT_PLAYER.getActions() > 0)
                        CURRENT_PLAYER.doAction();
                    if (CURRENT_PLAYER.getActions() <= 0) {
                        CURRENT_PLAYER.resetActions();
                        state = GameState.saboteurTurn;
                        turnsEnded++;
                        CURRENT_PLAYER = null;
                    }
                }
            }

            if(turnsEnded >= PLAYER_COUNT) {
                Map.getInstance().update();
                //Map.getInstance().control();
                Map.getInstance().waterFlow(0);
                SaboteurTeam.getInstance().getPoints();
                MechanicTeam.getInstance().getPoints();
                CURRENT_PLAYER.setCurrentAction(Player.Action.nothing);
                state = GameState.saboteurTurn;
                turnsEnded = 0;
            }
            win = SaboteurTeam.getInstance().hasWon() || MechanicTeam.getInstance().hasWon();
        }
    }
}
