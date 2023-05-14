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
    private static int nextID = 1;
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
            Logger.log("console.txt", "["+getLogID()+"]: "+pipe.getLogID()+" could not be picked up", true);
            return;
        }
        Logger.log("console.txt", "["+getLogID()+"]: "+pipe.getLogID()+" picked up", true);
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
        if(pipeInHand == null) Logger.log("console.txt", "["+getLogID()+"]: "+pipeString+" placed", true);
        else Logger.log("console.txt", "["+getLogID()+"]: "+pipeString+" could not be placed", true);
    }

    /**
     * A mechanic felvesz egy pumpát a mapElementen.
     * A givePump() a Cistern -be van implementálva.
     */
    public void pickUpPump(){
       mapElement.givePump(this);
       Logger.log("console.txt", "["+getLogID()+"]: "+pumpsInInventory.get(0).getLogID()+" picked up", true);
    }

    /**
     * Ezzel a függvénnyel rak le mechanic egy pumpát a csőre.
     * A cut függvény kezeli a pumpa bekötését, új csövek létrehozását.
     * Ezután beállítja a mechanic mapElement pozícióját a pumpára és kiveszi az inventoryból.
     */
    public void placePump(){
        mapElement.cut(pumpsInInventory.get(0));
        this.setMapElement(pumpsInInventory.get(0));
        Logger.log("console.txt", "["+getLogID()+"]: "+pumpsInInventory.get(0).getLogID()+" placed", true);
        pumpsInInventory.remove(0);
    }

    /**
     * Ezzel a függvénnyel javítja meg a pályaelemet a mechanic amin áll.
     */
    @Override
    public void repair(){
        if(mapElement.isBroken()) {
            mapElement.heal();
            Logger.log("console.txt", "["+mapElement.getLogID()+"]: repaired", true);
        }
        else
            Logger.log("console.txt", "["+mapElement.getLogID()+"]: repair not needed", true);
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
            Scanner scanner = new Scanner(System.in);
            String playerInput = scanner.nextLine();
            scanner.close();
            switch (playerInput) {
                case "break":
                    numberOfActions--;
                    CommandInterpreter.runCommand("break", this);
                    break;
                case "move":
                    numberOfActions--;
                    CommandInterpreter.runCommand("move", this);
                    break;
                case "repair":
                    numberOfActions--;
                    CommandInterpreter.runCommand("repair", this);
                    break;
                case "make_sticky":
                    numberOfActions--;
                    CommandInterpreter.runCommand("make_sticky", this);
                    break;
                case "pickup pipe", "pickup pump", "place pipe", "place pump":
                    numberOfActions--;
                    CommandInterpreter.runCommand(playerInput, this);
                    break;
                case "configure":
                    numberOfActions--;
                    CommandInterpreter.runCommand("configure", this);
                    break;
                default:
                    Logger.log("console.txt","[System]: Thats not a valid command", true);
                    break;
            }
        }
        numberOfActions = 2;
    }
}
