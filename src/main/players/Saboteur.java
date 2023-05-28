package main.players;

import commands.CommandInterpreter;
import main.logging.Logger;

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
        Logger.log("log.txt", getLogID()+"'s turn", false);
        while(numberOfActions > 0) {
            Logger.log("log.txt", numberOfActions+" actions left", false);

            //Waiting for input
            while(currentAction == Action.nothing) continue;

            switch(currentAction){
                //case step : step(param);
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
