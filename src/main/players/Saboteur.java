package main.players;

import commands.CommandInterpreter;
import main.Main;
import main.logging.Logger;
import main.map.MapElement;

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

    public void doAction()
    {
        while(numberOfActions > 0) {
            Scanner scanner = new Scanner(System.in);
            String playerInput = scanner.nextLine();
            scanner.close();
            doCommand(playerInput);
            numberOfActions--;
        }
        numberOfActions = 2;
    }

    public void doCommand(String playerInput){
        switch (playerInput) {
            case "break", "move", "make_slippery", "make_sticky":
                CommandInterpreter.runCommand(playerInput,  null, this);
                break;
            default:
                Logger.log("console.txt","[System]: Thats not a valid command", true);
                break;
        }
    }
}
