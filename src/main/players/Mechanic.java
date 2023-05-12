package main.players;

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
        if(pipe.isOccupied() && pipeInHand != null)
            return;
        mapElement.detachPipe(pipe);
        this.pipeInHand = pipe;
    }

    /**
     * A függvény megpróbálja letenni a kezében lévő csövet a pályaelemre amin áll.
     * Ha az if statement törzsében lefutott attachPipe(pipeInHand) visszatérési értéke true,
     * sikerült lerakni a csövet és a mechanic kezében lévő pipe null, lesz.
     */
    public void placePipe(){
        pipeInHand = (mapElement.attachPipe(pipeInHand)) ? null : pipeInHand;
    }

    /**
     * A mechanic felvesz egy pumpát a mapElementen.
     * A givePump() a Cistern -be van implementálva.
     */
    public void pickUpPump(){
       mapElement.givePump(this);
    }

    /**
     * Ezzel a függvénnyel rak le mechanic egy pumpát a csőre.
     * A cut függvény kezeli a pumpa bekötését, új csövek létrehozását.
     * Ezután beállítja a mechanic mapElement pozícióját a pumpára és kiveszi az inventoryból.
     */
    public void placePump(){
        mapElement.cut(pumpsInInventory.get(0));
        this.setMapElement(pumpsInInventory.get(0));
        pumpsInInventory.remove(0);
    }

    /**
     * Ezzel a függvénnyel javítja meg a pályaelemet a mechanic amin áll.
     */
    @Override
    public void repair(){
        if(mapElement.isBroken()) mapElement.heal();
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
}
