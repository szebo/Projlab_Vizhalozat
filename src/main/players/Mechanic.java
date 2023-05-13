package main.players;

import commands.CommandInterpreter;
import main.logging.Logger;
import main.map.Pipe;
import main.map.Pump;
import java.util.*;

public class Mechanic extends Player {

    private Pipe pipeInHand;
    private final List<Pump> pumpsInInventory = new ArrayList<>() ;
    /**
     * Az osztályból a következő létrehozott objektum által kapható ID.
     */
    private static int nextID = 0;
    public Mechanic(){
        ID = nextID++;
    }

    /**
     * Ezzel a függvénnyel veszi fel a mechanic a kezébe a csövet.
     * Megvizsgálja, hogy állnak-e a csövön, és van-e már a szerelő kezében cső.
     * Ha nem, akkor felveszi a csövet a kezébe, feltéve, ha az nem null, egyébként NullPointerExceptiont dob,
     * hogy ne próbáljunk meg később ezzel dolgozni.
     * Ezután lecsatolja a csövet pályaelemről.
     * @param pipe a cső, amit velvesz a mechanic a kezébe
     */
    public void pickUpPipe(Pipe pipe){
        Objects.requireNonNull(pipe, "Null értékű paramétert kapott a pickUpPipe!");
        if(pipe.isOccupied() && pipeInHand != null){
            Logger.logToConsole("log.txt", getLogID()+": "+pipe.getLogID()+" could not be picked up");
            return;
        }
        Logger.logToConsole("log.txt", getLogID()+": "+pipe.getLogID()+" picked up");
        mapElement.detachPipe(pipe);
        this.pipeInHand = pipe;
    }

    /**
     * A függvény megpróbálja letenni a kezében lévő csövet a pályaelemre amin áll.
     * Ha az if statement törzsében lefutott attachPipe(pipeInHand) visszatérési értéke true,
     * sikerült lerakni a csövet és a mechanic kezében lévő pipe null, lesz.
     */
    public void placePipe(){
        String pipeString = pipeInHand.getLogID();
        pipeInHand = (mapElement.attachPipe(pipeInHand)) ? null : pipeInHand;
        if(pipeInHand == null) Logger.logToConsole("log.txt", getLogID()+": "+pipeString+" placed");
        else Logger.logToConsole("log.txt", getLogID()+": "+pipeString+" could not be placed");
    }

    /**
     * A mechanic felvesz egy pumpát a mapElementen.
     * A givePump() a Cistern -be van implementálva.
     */
    public void pickUpPump(){
       mapElement.givePump(this);
       Logger.logToConsole("log.txt", getLogID()+": "+pumpsInInventory.get(0).getLogID()+" picked up");
    }

    /**
     * Ezzel a függvénnyel rak le mechanic egy pumpát a csőre.
     * A cut függvény kezeli a pumpa bekötését, új csövek létrehozását.
     * Ezután beállítja a mechanic mapElement pozícióját a pumpára és kiveszi az inventoryból.
     */
    public void placePump(){
        mapElement.cut(pumpsInInventory.get(0));
        this.setMapElement(pumpsInInventory.get(0));
        Logger.logToConsole("log.txt", getLogID()+": "+pumpsInInventory.get(0).getLogID()+" placed");
        pumpsInInventory.remove(0);
    }

    /**
     * Ezzel a függvénnyel javítja meg a pályaelemet a mechanic amin áll.
     */
    @Override
    public void repair(){
        if(mapElement.isBroken()) {
            mapElement.heal();
            Logger.logToConsole("log.txt", mapElement.getLogID()+": repaired");
        }
        else
            Logger.logToConsole("log.txt", mapElement.getLogID()+": repair not needed");
    }

    /**
     * A függvény az átadott pumpát teszi bele a mechanic inventory-ába.
     * @param pump A pumpa ami bekerül a szerelő "inventory"-ába.
     */
    public void addPumpToInventory(Pump pump){
        pumpsInInventory.add(pump);
    }

    public void setPipeInHand(Pipe pipeInHand) {
        this.pipeInHand = pipeInHand;
    }

    /**
     * Ragacsossá teszi a csövet, amin áll a játékos.
     */
    @Override
    public void useStickyGoo() {
        mapElement.makeSticky(2);
    }

    public String getLogID(){
        return "Mechanic"+ID;
    }

    /**
     * Létrehoz egy stringet a szerelő információival
     * @return info
     */
    public String printInfo(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("["+getLogID()+"]");
        stringBuilder.append("\nElement: "+mapElement.getLogID());
        stringBuilder.append("Inventory: "+(pumpsInInventory.size() < 1? " -" : pumpsInInventory.get(0).getLogID())+"\n");
        stringBuilder.append("Hand: "+(pipeInHand != null ? pipeInHand.getLogID() : " -")+"\n");
        stringBuilder.append("Steps left: "+stepsLeft+"\n");
        return stringBuilder.toString();
    }

    public void doAction()
    {
        while(numberOfActions > 0) {
            String playerInput = System.console().readLine();
            switch (playerInput) {
                case "break":
                    numberOfActions--;
                    breakElement();
                    break;
                case "move":
                    numberOfActions--;
                    CommandInterpreter.runCommand("move", this);
                    break;
                case "repair":
                    numberOfActions--;
                    repair();
                    break;
                case "make_sticky":
                    numberOfActions--;
                    useStickyGoo();
                    break;
                case "pickup pipe":
                case "pickup pump":
                    numberOfActions--;
                    CommandInterpreter.runCommand("pickup", this);
                    break;
                case "place pipe":
                case "place pump":
                    numberOfActions--;
                    CommandInterpreter.runCommand("place", this);
                    break;
                case "configure":
                    numberOfActions--;
                    CommandInterpreter.runCommand("configure", this);
                    break;
                default:
                    System.out.println("thats not a valid command");
                    break;
            }
        }
        numberOfActions = 2;
    }
}
