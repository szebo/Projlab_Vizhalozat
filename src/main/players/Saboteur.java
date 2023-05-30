package main.players;

import commands.CommandInterpreter;
import main.Main;
import main.graphics.GUIManager;
import main.graphics.GUIObject;
import main.logging.Logger;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Scanner;

/**
 * Olyan játékos, amely feladata, hogy a csöveken és pumpákon haladva akadályozza a víz folyását.
 * Ezt a csövek kilyukasztásával teheti meg, illetve a pumpák forgatására is van lehetősége.
 * **/
public class Saboteur extends Player {

    private static int nextID = 1;

    public Saboteur(){
        ID = nextID++;
        Main.window.addKeyListener(key);
    }

    public String getLogID(){
        return "Saboteur"+ID;
    }

    /**
     * Csúszóssá teszi a csövet, amin áll a játékos.
     */
    @Override
    public void useSlipperyGoo(){
        mapElement.makeSlippery(2);
    }

    /**
     * Létrehoz egy stringet a szabotőr információival
     * @return info
     */
    public String printInfo(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("["+getLogID()+"]");
        stringBuilder.append("\nElement: "+mapElement.getLogID());
        stringBuilder.append("\nSteps left: "+stepsLeft+"\n");
        return stringBuilder.toString();
    }

    KeyListener key = new KeyListener() {
        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {


        }

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_SPACE -> currentAction = Action.step;
                case KeyEvent.VK_A -> currentAction = Action.heal;
                case KeyEvent.VK_D -> currentAction = Action.breakelement;

                case KeyEvent.VK_S -> currentAction = Action.configure;

                case KeyEvent.VK_Q -> currentAction = Action.pipepickup;
                case KeyEvent.VK_W -> currentAction = Action.pipeplace;
                case KeyEvent.VK_E -> currentAction = Action.sticky;

                case KeyEvent.VK_R -> currentAction = Action.pumppickup;
                case KeyEvent.VK_F -> currentAction = Action.pumpplace;

            }
        }
    };

    public void doAction()
    {
        Logger.log("log.txt", getLogID()+"'s turn", false);
        while(numberOfActions > 0) {
            Logger.log("log.txt", numberOfActions+" actions left", false);

            //Waiting for input
            while(currentAction == Action.nothing) break;

            switch(currentAction){
                //case step : step();
                // TODO onClick paraméternek

                case breakelement   : mapElement.breakElement();

                //case configure      : configurePump(param);
                // TODO onClick 2x paraméternek. Itt amúgy nem kéne inkább a függvénybe bekérni?

                //case pipepickup     : pickUpPipe(param);
                // TODO onClick ből paraméter. Szintén nem egyszerűbb?
                // TODO Vagy ez már összemossa a grafikus részt a működéssel?

                case pipeplace      : placePipe();

                case pumppickup     : pickUpPump();
                case pumpplace      : placePump();

                case sticky         : useStickyGoo();
                case slippery       : useSlipperyGoo();
            }
            numberOfActions--;
        }
        numberOfActions = 2;
    }
/*
    public void doCommand(String playerInput){
        switch (playerInput) {
            case "break", "move", "make_slippery", "make_sticky" -> CommandInterpreter.runCommand(playerInput, this);
            default -> Logger.log("console.txt", "[System]: Thats not a valid command", true);
        }
    }
*/

    public static void resetAfterTest(){
        nextID = 1;
    }
}
