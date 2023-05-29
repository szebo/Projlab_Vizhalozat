package main.players;

import commands.CommandInterpreter;
import main.Main;
import main.logging.Logger;
import main.map.Pipe;
import main.map.Pump;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
        currentAction = Action.nothing;
        Main.window.addKeyListener(key);
    }
    public Pipe getPipeInHand(){return pipeInHand;}

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

    /*
    public void doAction()
    {
        Logger.log("console.txt", getLogID()+"'s turn", true);
        while(numberOfActions > 0) {
            Logger.log("console.txt", numberOfActions+" actions left", true);
            Scanner scanner = new Scanner(System.in);
            String playerInput = scanner.nextLine();
            //scanner.close();
            doCommand(playerInput);
            numberOfActions--;
        }
        numberOfActions = 2;
    }
    */

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

            while(currentAction == Action.nothing) continue;

            switch(currentAction){
                // case step : step(param);
                // TODO onClick paraméternek

                case heal           : mapElement.heal();
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
            }
            numberOfActions--;
        }
        numberOfActions = 2;
    }

    //TODO Ez már nem kell, ugye?
    /*
    public void doCommand(String playerInput){
        switch (playerInput) {
            case "pickup pipe", "pickup pump", "place pipe", "place pump", "configure", "break", "move", "repair", "make_sticky" ->
                    CommandInterpreter.runCommand(playerInput, this);
            default -> Logger.log("console.txt", "[System]: Thats not a valid command", true);
        }
    }
     */

    public static void resetAfterTest(){
        nextID = 1;
    }


}
