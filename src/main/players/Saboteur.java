package main.players;

import commands.CommandInterpreter;
import main.Controller;
import main.Main;
import main.graphics.GUIManager;
import main.graphics.GUIObject;
import main.logging.Logger;
import main.map.Pipe;

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
    }

    public String getLogID(){
        return "Saboteur"+ID;
    }

    /**
     * Csúszóssá teszi a csövet, amin áll a játékos.
     */
    @Override
    public void useSlipperyGoo(){
        mapElement.makeSlippery(Controller.SLIPPERY_FOR_OPTION);
        numberOfActions--;
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

    public void doAction()
    {
        Logger.log("log.txt", getLogID()+"'s turn", false);
        if(numberOfActions > 0) {
            Logger.log("log.txt", numberOfActions+" actions left", false);

            switch(currentAction){
                case step : Controller.SELECTED_ELEMENT.acceptPlayer(this);
                break;

                case breakelement   : mapElement.breakElement();
                break;

                case configure      :
                    if(Controller.SECOND_SELECTED_ELEMENT != null)
                        configurePump((Pipe)Controller.SELECTED_ELEMENT, (Pipe)Controller.SECOND_SELECTED_ELEMENT);
                    Controller.SECOND_SELECTED_ELEMENT = null;
                    break;

                case sticky         : useStickyGoo();
                break;
                case slippery       : useSlipperyGoo();
                break;

                case endturn:
                    Controller.turnsEnded++;
                    numberOfActions = 0;
                    break;
            }
            if(stuck > 0 ) {
                stuck--;
            }
            Controller.SELECTED_ELEMENT = null;
            currentAction = Action.nothing;
        }
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
