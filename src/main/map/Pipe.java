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
     * @param water
     * @return
     */
    @Override
    public int addWater(int water) {
        this.water += water;
        return water;
    }

    /**
     * Hozzáadja a water vízmennyiséget az objektum vizéhez
     * @param water
     * @return A vízszint új értéke
     */
    @Override
    public int removeWater(int water) {
    return addWater(-1*water);
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
