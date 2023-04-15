package main.map;

import main.players.Player;

import java.util.List;

/**
 * A csövet megvalósító osztály, amelyben a víz folyik két pumpa között.
 * Csak egyetlen játékos állhat rajta
 * **/
public class Pipe extends MapElement {

    private List<ActiveElement> elements;
    public Pipe(){
        this.capacity = 20;
    }
    public Pipe(int capacity){
        this.capacity = capacity;
    }
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

    @Override
    public void acceptPlayer(Player player){
        if(!this.isOccupied()) addPlayer(player);
    }

    public void addElement(ActiveElement element){
        this.elements.add(element);
    }
}
