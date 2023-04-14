package main.map;

/**
 * A csövet megvalósító osztály, amelyben a víz folyik két pumpa között.
 * Csak egyetlen játékos állhat rajta
 * **/
public class Pipe extends MapElement {
    /** A csövek kapacitását adja meg **/
    private static int capacity;
    /**  **/
    private int sabotagedWater;

    /** Eltöri a csövet, aminek következményében a
     * **/
    public void _break(){
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
     *
     */
    @Override
    public void interact() {

    }

    /**
     * @param water
     * @return
     */
    @Override
    public int addWater(int water) {
        return 0;
    }

    /**
     * @param water
     * @return
     */
    @Override
    public int removeWater(int water) {
        return 0;
    }

    /** Beállítja az objektum töröttségi állapotát (boolean)
     * @param value a beállítandó állapot **/
    @Override
    public void setBroken(boolean value) {
        super.setBroken(value);
    }

    /** Elfelezi a csövet, hogy be lehessen helyezni közéjük egy pumpát **/
    public void cut(){}
}
