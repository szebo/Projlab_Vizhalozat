package main.map;

import main.logging.Logger;
import main.players.Mechanic;
import main.players.Player;

import java.util.List;

/**
 * Ősosztály minden pályaelemhez, elérést biztosít a játékosok számára a különböző metódus hívásokhoz.
 */
public abstract class MapElement {

    protected int ID;
    protected int unbreakableFor = 0;

    /**
     * Mutatja, hogy törött-e a cső
     */
    protected boolean isBroken;
    /**
     * Tárolja a játékosokat az elemen.
     */
    protected List<Player> players;
    /**
     * Tárolja az elemben levő víz mennyiséget.
     */
    protected int water;
    /**
     * Statikus tag, tárolja, hogy a mennyi a kapacitása az elemeknek.
     */
    protected int capacity;

    /**
     * Abstract metódus, a cső lecsatolásra szolgál a leszármazottaknál.
     * @param pipe a cső amit leválasztunk a MapElementről.
     */
    public abstract void detachPipe(Pipe pipe);

    /**
     * Abstract metódus, a cső felcsatolására szolgál a leszármazottaknál.
     * @param pipe a cső amit hozzákötünk a MapElementhez.
     */
    public abstract boolean attachPipe(Pipe pipe);

    /**
     * Megjavítja az elemet.
     */
    public void heal(){
        setBroken(false);
        Logger.log("log.txt", "Element repaired", false);
    }

    /**
     * Tönkre teszi az elemet.
     */
    public void breakElement(){}

    /**
     * Elfogadja a paraméterül kapott játékost.
     * @param player a játékos, aki rálép a pályaelemre.
     */
    public boolean acceptPlayer(Player player){
        Logger.log("console.txt", "["+getLogID()+"]: "+player.getLogID()+" moved", true);
        return true;
    }

    /**
     * Hozzáadja az elemhez a paraméterül kapott játékost.
     * @param player A hozzáadandó játékos
     */
    public void addPlayer(Player player){
        players.add(player);
        Logger.log("log.txt", "["+getLogID()+"]: "+player.getLogID()+" added", false);
    }

    /**
     * Eltávolítja az elemről a paraméterül kapott játékost.
     * @param player a játékos, aki lekerül az elemről.
     */
    public void removePlayer(Player player){
        players.remove(player);
        Logger.log("log.txt","["+getLogID()+"]: "+player.getLogID()+" removed", false);
    }

    /**
     * Hozzáadja a csőhőz a paraméterül kapott vízmennyiséget, de nem engedi túlcsordulni.
     * @param water víz mennyisége egész számmal kifejezve.
     * @return Az átvett vízmennyiség
     */
    public int addWater(int water){
        int waterTaken = this.water + water > capacity ? capacity-this.water : water;
        this.water += waterTaken;
        Logger.log("log.txt", "["+getLogID()+"]: "+waterTaken+" water added", false);
        return waterTaken;
    }

    /**
     * Elveszi a paraméterül kapott vízmennyiséget az elem tárolójából, vagy ha a paraméter több, mint ami benne van, akkor kiüríti az elemet.
     * @param water víz mennyisége, egész számmal kifejezve.
     * @return A kivett vízmennyiség
     */
    public int removeWater(int water) {
        if(!isBroken) {
            int pumpedWater = Math.min(this.water, water);
            this.water -= pumpedWater;
            Logger.log("log.txt","["+getLogID()+"]: "+pumpedWater+" water removed", false);
            return pumpedWater;
        }
        else {
            Logger.log("log.txt", "["+getLogID()+"]: pipe is not functional", false);
            return 0;
        }
    }

    /**
     * Beállítja az isBroken értékét a paraméterül kapottra.
     * @param value true = törött, false = üzemképes.
     */
    public void setBroken(boolean value){
        isBroken = value;
    }

    /**
     * Visszaadja az isBroken értékét.
     * @return isBroken érétke.
     */
    public boolean isBroken(){
        return isBroken;
    }

    /**
     * Eldönti, hogy az elem foglalt-e éppen. Ez a csőnél lényeges igazán, részben, hogy ráléphetnek-e,
     * részben pedig, hogy fellehet-e venni.
     * @return Foglalt-e az elem.
     */
    public boolean isOccupied(){
        return players.size() > 0;
    } //itt akkor is true értéket ad, ha egyébként pumpa az elem.

    /**
     * A játékos számára elérhetővé teszi a Pipe cut metódusát, minden más fajta leszármazottra nem csinál semmit.
     * @param pumpPlaced A lerakni kívánt pumpa
     */
    public void cut(Pump pumpPlaced){}

    /**
     * A Cistern givePump metódusát elérhetővé teszi a játékos számára, más leszármazotton nem csinál semmit.
     * @param mechanic a mechanic-ot átadjuk aki átveszi a pumpát.
     */
    public void givePump(Mechanic mechanic){}

    /**
     * Ragacsossá teszi a csövet.
     * A függvény csak a Pipe osztályban van megvalósítva.
     */
    public void makeSticky(int value){}

    /**
     * Megnézi, hogy ragacsos-e a cső.
     * A függvény csak a Pipe osztályban van megvalósítva.
     */
    public abstract boolean checkSticky();

    /**
     * Ragacsossá teszi a csövet.
     * A függvény csak a Pipe osztályban van megvalósítva.
     */
    public void makeSlippery(int value){}

    /**
     * Megnézi, hogy ragacsos-e a cső.
     * A függvény csak a Pipe osztályban van megvalósítva.
     */
    public abstract boolean checkSlippery();

    /**
     * Visszaadja a csőnek egy véletlenszerű végét. (Csak a csövön van implementálva.)
     * @return ActiveElement
     */
    public abstract ActiveElement getRandomEnd();

    /**
     * Leszármazott osztálytól függően visszaad egy String az osztály nevével és az objektum azonosítójával.
     * @return StringID
     */
    public abstract String getLogID();

    /**
     * Visszaadja az objektum azonosító számát.
     * @return
     */
    public int getID(){
        return this.ID;
    }

    public int getCapacity(){ return capacity; }

    /**
     * A leszármazott osztálytól függően visszaad egy Stringet az objektum összes elérhető információjáról.
     * @return
     */
    public abstract String printInfo();

    public abstract MapElement[] getNeighbours();

    /**
     * Javítás esetén törhetetlenné teszi a csövet pár körig.
     */
    public void makeUnbreakable(){this.unbreakableFor = 2;}

    /**
     * Megvizsgálja, hogy el lehet-e törni az elemet.
     * @return True, ha az unbreakableFor nagyobb mint 0.
     */
    public boolean checkUnbreakable(){return unbreakableFor > 0;}

    public void newPipe(){}

    public void setOutput(Pipe output) {}

    public void setInput(Pipe input) {}
}
