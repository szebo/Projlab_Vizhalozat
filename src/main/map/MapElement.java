package main.map;

import main.players.Player;

import java.util.List;

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
    protected static int capacity;

    /**
     * Abstract metódus, a cső lecsatolásra szolgál a leszármazottaknál.
     * @param pipe
     */
    public abstract void detachPipe(Pipe pipe);

    /**
     * Abstract metódus, a cső felcsatolására szolgál a leszármazottaknál.
     * @param pipe
     */
    public abstract void attachPipe(Pipe pipe);

    /**
     * Abstract metódus, implementáció függő a viselkedése.
     * A játékosok számára biztosít elérhetőséget egy-két különböző akcióhoz.
     */
    public abstract void interact();

    /**
     * Megjavítja az elemet.
     */
    public void heal(){
        setBroken(false);
    }

    /**
     * Tönkre teszi az elemet.
     */
    public void breakElement(){
        setBroken(true);
    }

    /**
     * Elfogadja a paraméterül kapott játékost.
     * @param player
     */
    public void acceptPlayer(Player player){
        addPlayer(player);
    }

    /**
     * Hozzáadja az elemhez a paraméterül kapott játékost.
     * @param player
     */
    public void addPlayer(Player player){
        players.add(player);
    }

    /**
     * Eltávolítja az elemről a paraméterül kapott játékost.
     * @param player
     */
    public void removePlayer(Player player){
        players.remove(player);
    }

    /**
     * Hozzáadja a csőhőz a paraméterül kapott vízmennyiséget, de nem engedi túlcsordulni.
     * @param water
     * @return Az átvett vízmennyiség
     */
    public int addWater(int water){
        if(isBroken) return 0; /**TODO Pontozás*/
        int waterTaken = this.water + water > capacity ? capacity-this.water : water;
        this.water += waterTaken;
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
            return pumpedWater;
        }
        else return 0;
    }

    /**
     * Beállítja az isBroken értékét a paraméterül kapottra.
     * @param value
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
     * Eldönti, hogy az elem foglalt-e éppen. Ez a csőnél lényeges igazán, részben, hogy ráléphetnek-e, részben pedig, hogy fellehet-e venni.
     * @return Foglalt-e az elem.
     */
    public boolean isOccupied(){
        return players.size() > 0;
    }
}
