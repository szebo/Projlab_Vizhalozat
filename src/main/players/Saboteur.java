package main.players;

import commands.CommandInterpreter;

/**
 * Olyan játékos, amely feladata, hogy a csöveken és pumpákon haladva akadályozza a víz folyását.
 * Ezt a csövek kilyukasztásával teheti meg, illetve a pumpák forgatására is van lehetősége.
 * **/
public class Saboteur extends Player {

    private static int nextID = 0;

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
            String playerInput = System.console().readLine();
            switch (playerInput) {
                case "break":
                    numberOfActions--;
                    break;
                case "move":
                    numberOfActions--;
                    break;
                case "make_slippery":
                    numberOfActions--;
                    break;
                case "make_sticky":
                    numberOfActions--;
                    break;
                default:
                    System.out.println("thats not a valid command");
                    break;
            }
        }
        numberOfActions = 2;
    }
}
