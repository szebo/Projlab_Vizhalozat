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

    /** Beállítja az objektum töröttségi állapotát (boolean)
     * @param b a beállítandó állapot **/
    @Override
    public void setBroken(boolean b) {
        super.setBroken(b);
    }

    /** Elfelezi a csövet, hogy be lehessen helyezni közéjük egy pumpát **/
    public void cut(){}
}
