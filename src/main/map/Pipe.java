package main.map;

import main.interfaces.Updatable;
import main.logging.Logger;
import main.players.Player;
import main.players.SaboteurTeam;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Egy olyan eleme a hálózatnak, amely a belevezetett vizet továbbítja a másik vége felé,
 * az ott elhelyezkedő pumpába. A játékosok képesek rajta állni és különböző akciókat is végrehajtani,
 * attól függően, milyen játékosról beszélünk.
 * Egyetlen játékos állhat csak rajta
 **/
public class Pipe extends MapElement implements Updatable {

    private static int nextID = 1;

    /**
     * Ennyi ?kör? ideig ragacsos a cső.
     */
    private int stickyFor = 0;

    /**
     * Ennyi ?kör? ideig csúszós a cső.
     */
    private int slipperyFor = 0;

    /**
     * A cső két végén elhelyezkedő pumpákat tároló lista
     * **/
    private List<ActiveElement> elements;

    /**
     * A cső osztály def. konstruktora.
     * Beállítja a kapacitást és az elemeket alapértelmezettre.
     * **/
    public Pipe(){
        this.capacity = 20;
        elements = new ArrayList<>();
        players = new ArrayList<>();
        this.ID = nextID++;
    }

    /**
     * A cső osztály konstruktora.
     * Beállítja a kapacitást a paraméterben kapottra és az elemeket alapértelmezettre.
     * @param capacity A beállítandó kapacitás
     * **/
    public Pipe(int capacity){
        this.capacity = capacity;
    }

    /** Eltöri a csövet, aminek következményében a víz elfolyik majd.
     * **/
    @Override
    public void breakElement(){
        if(checkUnbreakable()) Logger.logToConsole("log.txt", "["+getLogID()+"]: can't break");
        else if(!this.isBroken()) {
            setBroken(true);
            Logger.logToConsole("log.txt", "["+getLogID()+"]: broken");
        }
    }

    /**
     * Örökölt függvény, amit ez az osztály nem valósít meg
     * @param pipe not used
     */
    @Override
    public void detachPipe(Pipe pipe) {}

    /**
     * Örökölt függvény, amit ez az osztály nem valósít meg
     * @param pipe not used
     */
    @Override
    public boolean attachPipe(Pipe pipe) {return false;}

    /**
     * Elfelezi a csövet, hogy be lehessen helyezni közéjük egy pumpát
     * @param pumpPlaced A cső közepére elhelyezni kívánt pumpa
     */
    public void cut(Pump pumpPlaced){
        Pipe uj_pipe_1 = new Pipe();
        Pipe uj_pipe_2 = new Pipe();
        Logger.log("log.txt","Pipe cut in two");

        ActiveElement end1 = elements.get(0);
        ActiveElement end2 = null;
        if(elements.size() > 1) end2 =  elements.get(1);
        Logger.log("log.txt","Pipe ends saved");

        end1.detachPipe(this);
        end1.attachPipe(uj_pipe_1);
        if(end2 != null) {
            end2.detachPipe(this);
            end2.attachPipe(uj_pipe_2);
        }
        pumpPlaced.attachPipe(uj_pipe_1);
        pumpPlaced.attachPipe(uj_pipe_2);
        Logger.log("log.txt","New pipes attached to placed pump");
    }

    /**
     * A csőre lépést engedélyező függvény, amely ellenőrzi, hogy szabad-e a játékosnak a csőre lépni.
     * Amennyiben igen, akkor fogadja a játékost.
     * @param player A csőre lépni kívánó játékos
     * **/
    @Override
    public boolean acceptPlayer(Player player){
        boolean accepted = false;
        if(!this.isOccupied()) {
            Logger.logToConsole("console.txt", "["+getLogID()+"]: "+player.getLogID()+" moved");
            if(checkSlippery()){
                player.step(getRandomEnd());
            }
            else if(checkSticky()){
                player.setStuck(stickyFor);
                addPlayer(player);
                accepted = true;
            }
        }
        else{
            Logger.logToConsole("console.txt", "["+getLogID()+"]: "+player.getLogID()+" could not move");
        }
        return accepted;
    }

    /**
     * Hozzáad a cső egyik végéhez egy aktív elemet, amennyiben nincsen mindkét végéhez hozzárendelve aktív elem
     * @param element A csőhöz kapcsolni kívánt aktív elem
     * **/
    public void addElement(ActiveElement element){
        if(elements.size()<2) {
            this.elements.add(element);
            Logger.log("log.txt", "["+getLogID()+"]: Element added");
        }
    }

    /**
     * Kivesz egy elemet az elements listából.
     * @param element a kivenni való aktív elem
     */
    public void removeElement(ActiveElement element){
        elements.remove(element);
    }

    /**
     * Beállítja a ragacsosság értékét.
     * @param value mennyi ideig ragacsos a cső
     */
    @Override
    public void makeSticky(int value){
        if(stickyFor <= 0) {
            this.stickyFor = value;
            Logger.logToConsole("console.txt", "["+getLogID() + "]: made sticky for " + value + " turns");
        }
        else{
            Logger.logToConsole("console.txt", "["+getLogID()+"]: can't become sticky");
        }
    }

    /**
     * Megvizsgálja, hogy ragacsos-e a cső.
     * @return true, ha a stickyFor értéke nagyobb mint 0.
     */
    @Override
    public boolean checkSticky(){return stickyFor > 0;}

    /**
     * Csúszóssá teszi a csövet.
     * @param value mennyi ideig csúszós a cső
     */
    @Override
    public void makeSlippery(int value){
        if(slipperyFor <= 0) {
            this.slipperyFor = value;
            Logger.logToConsole("console.txt", "["+getLogID() + "]: made slippery for " + value + " turns");
        }
        else{
            Logger.logToConsole("console.txt", "["+getLogID()+"]: can't become slippery");
        }
    }

    /**
     * Megvizsgálja, hogy csúszós-e a cső.
     * @return true, ha a slipperyFor értéke nagyobb mint 0.
     */
    @Override
    public boolean checkSlippery() {
        return slipperyFor > 0;
    }






    /**
     * A csőnek véletlenszerűen sorsolja ki az egyik végét, és visszaadja azt.
     * @return ActiveElement: Véletlenszerűen visszakapott vége a csőnek.
     */
    @Override
    public ActiveElement getRandomEnd() {
        if(elements.size() == 1)
            return elements.get(0);
        Random rand = new Random();
        int randomInt = rand.nextInt(2);
        if(randomInt == 0)
            return elements.get(0);
        else
            return elements.get(1);
    }

    public String getLogID(){
        return "Pipe"+this.ID;
    }

    public void update(){
        if(this.slipperyFor!=0) this.slipperyFor -= 1;
        if(this.stickyFor!=0) this.stickyFor -= 1;
        if(this.unbreakableFor!=0) this.unbreakableFor -=1;
    }

    @Override
    public int addWater(int water){
        if(isBroken || elements.size() < 2){
            SaboteurTeam.getInstance().addPoints(water);
            return water;
        }
        else{
            return super.addWater(water);
        }
    }

    @Override
    public void heal(){
        setBroken(false);
        makeUnbreakable();
    }

    @Override
    public MapElement[] getNeighbours(){
        return elements.toArray(new MapElement[2]);
    }

    /**
     * Létrehoz egy stringet a cső információival
     * @return info
     */
    public String printInfo(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("["+getLogID()+"]");
        stringBuilder.append("\nPlayer: ");
        if(players.size()>0) stringBuilder.append(players.get(0).getLogID());
        else stringBuilder.append(" -");
        stringBuilder.append("\nEnds: ");
        if(elements.size()<1) stringBuilder.append(" -");
        else {
            for (int i = 0; i < players.size(); i++) {
                if (i == 0) stringBuilder.append(elements.get(i).getLogID());
                else stringBuilder.append(", " + elements.get(i).getLogID());
            }
        }
        stringBuilder.append(isBroken ? "\nBroken\n" : "\nNot Broken\n");
        stringBuilder.append("Sticky for: "+stickyFor+" turn\n");
        stringBuilder.append("Slippery for: "+slipperyFor+" turn\n");
        return stringBuilder.toString();
    }
}
