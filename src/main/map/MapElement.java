package main.map;

import main.players.Mechanic;
import main.players.Player;
import main.players.SaboteurTeam;

import java.util.List;

/**
 * Ősosztály minden pályaelemhez, elérést biztosít a játékosok számára a különböző metódus hívásokhoz.
 */
public abstract class MapElement {

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
     * @param pipe
     */
    public abstract void detachPipe(Pipe pipe);

    /**
     * Abstract metódus, a cső felcsatolására szolgál a leszármazottaknál.
     * @param pipe
     */
    public abstract boolean attachPipe(Pipe pipe);

    /**
     * Megjavítja az elemet.
     */
    public void heal(){
        setBroken(false);
        System.out.println("Elem megjavítva!");
    }

    /**
     * Tönkre teszi az elemet.
     */
    public void breakElement(){}

    /**
     * Elfogadja a paraméterül kapott játékost.
     * @param player
     */
    public boolean acceptPlayer(Player player){
        addPlayer(player);
        System.out.println("Játékos ráléphet az elemre!");
        return true;
    }

    /**
     * Hozzáadja az elemhez a paraméterül kapott játékost.
     * @param player A hozzáadandó játékos
     */
    public void addPlayer(Player player){
        players.add(player);
        System.out.println("Játékos hozzáadva az elemhez!");
    }

    /**
     * Eltávolítja az elemről a paraméterül kapott játékost.
     * @param player
     */
    public void removePlayer(Player player){
        players.remove(player);
        System.out.println("Játékos eltávolítva az elemről!");
    }

    /**
     * Hozzáadja a csőhőz a paraméterül kapott vízmennyiséget, de nem engedi túlcsordulni.
     * @param water
     * @return Az átvett vízmennyiség
     */
    public int addWater(int water){
        if(isBroken) {
            System.out.println("Az elem el van romolva!");
            /**TODO Pontozás*/
            return 0;
        }
        int waterTaken = this.water + water > capacity ? capacity-this.water : water;
        this.water += waterTaken;
        System.out.println("Az elemhez "+waterTaken+" mennyiségű víz került hozzáadásra!");
        return waterTaken;
    }

    /**
     * Elveszi a paraméterül kapott vízmennyiséget az elem tárolójából, vagy ha a paraméter több, mint ami benne van, akkor kiüríti az elemet.
     * @param water
     * @return A kivett vízmennyiség
     */
    public int removeWater(int water) {
        if(!isBroken) {
            int pumpedWater = this.water < water ? this.water : water;
            this.water -= pumpedWater;
            System.out.println("Az elemből el lett távlítva "+pumpedWater+" mennyiségű víz!");
            return pumpedWater;
        }
        else {
            System.out.println("Az elem el van romolva!");
            return 0;
        }
    }

    /**
     * Beállítja az isBroken értékét a paraméterül kapottra.
     * @param value
     */
    public void setBroken(boolean value){
        isBroken = value;
        System.out.println("MapElement töröttségi állapota beállítva.");
    }

    /**
     * Visszaadja az isBroken értékét.
     * @return isBroken érétke.
     */
    public boolean isBroken(){
        return isBroken;
    }

    /**
     * Eldönti, hogy az elem foglalt-e éppen. Ez a csőnél lényeges igazán, részben, hogy ráléphetnek-e, részben pedig, hogy fellehet-e venni.
     * @return Foglalt-e az elem.
     */
    public boolean isOccupied(){
        return players.size() > 0;
    }

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
}
