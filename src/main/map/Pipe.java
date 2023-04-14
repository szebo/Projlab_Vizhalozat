package main.map;

/**
 * A csövet megvalósító osztály, amelyben a víz folyik két pumpa között.
 * Csak egyetlen játékos állhat rajta
 * **/
public class Pipe extends MapElement {
    /** A csövek kapacitását adja meg **/
    private static int capacity;
    /** Azt adja meg, hogy mennyi víz folyik el, amennyiben a cső lyukas **/
    private int sabotagedWater;

    /** Eltöri a csövet, aminek következményében a
     * **/
    @Override
    public void breakElement(){
        setBroken(true);
    }

    /**
     * Üres függvény
     * @param pipe
     */
    @Override
    public void detachPipe(Pipe pipe) {return;}

    /**
     * Üres függvény
     * @param pipe
     */
    @Override
    public void attachPipe(Pipe pipe) {}

    /**
     * TODO
     */
    @Override
    public void interact() {

    }

    /**
     * Hozzáadja a csőhőz a paraméterül kapott vízmennyiséget, de nem engedi túlcsordulni.
     * @param water
     * @return Az átvett vízmennyiség
     */
    @Override
    public int addWater(int water) {
        if(isBroken) return 0; /**TODO Pontozás*/
        int waterTaken = this.water + water > capacity ? capacity-this.water : water;
        this.water += waterTaken;
        return waterTaken;
    }

    /**
     * Hozzáadja a water vízmennyiséget az objektum vizéhez
     * @param water
     * @return A kivett vízmennyiség
     */
    @Override
    public int removeWater(int water) {
        if(!isBroken) {
            int pumpedWater = this.water < water ? this.water : water;
            this.water -= pumpedWater;
            return pumpedWater;
        }
        else return 0;
    }

    /** Beállítja az objektum töröttségi állapotát (boolean)
     * @param value a beállítandó állapot **/
    @Override
    public void setBroken(boolean value) {
        super.setBroken(value);
    }

    /** Elfelezi a csövet, hogy be lehessen helyezni közéjük egy pumpát **/
    public Pipe[] cut(){
        Pipe uj_pipe_1 = new Pipe();
        Pipe uj_pipe_2 = new Pipe();
        return new Pipe[] {uj_pipe_1, uj_pipe_2};
    }
}
