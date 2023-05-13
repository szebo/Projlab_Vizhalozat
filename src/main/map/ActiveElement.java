package main.map;

import main.interfaces.IControllable;
import main.logging.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Közös ős minden aktiv elemnek, amik a víz mozgatásáért felelnek.
 */
public abstract class ActiveElement extends MapElement implements IControllable {
   /**
    * Az aktív elemre felcsatolt csövek gyűjteménye
    **/
    protected List<Pipe> pipes;


    /**
     * Beállítja az isBroken értékét a paraméterül kapottra.
     * @param value A beállítandó bináris érték
     */
    @Override
    public void setBroken(boolean value){
        super.setBroken(value);
    }

    /**
     * Lecsatolja “pipe” csövet az elemről, attól függetlenül, hogy ki-/bemeneti cső-e éppen.
     * Ha a cső foglalt, akkor nem lehet lecsatolni.
     * A csőben lévő víz ilyenkor kifolyik.
     * @param pipe A lecsatolandó cső
     * **/
    @Override
    public void detachPipe(Pipe pipe)  {
        Objects.requireNonNull(pipe, "Null értékű paramétert kapott a detachPipe!");
        if(!pipe.isOccupied()) {
            this.pipes.remove(pipe);
            pipe.removeWater(pipe.water);
            pipe.removeElement(this);
            Logger.log("log.txt", "Element detached");
        }
        else
            Logger.log("log.txt", "Element occupied");
    }
    /**
     * Felcsatolja “pipe” csövet az aktív elemre.
     * @param pipe A felcsatolandó cső
    * **/
    @Override
    public boolean attachPipe(Pipe pipe){
        Objects.requireNonNull(pipe, "Null értékű paramétert kapott az attachPipe!");
        boolean hasPlace = pipes.size() < 10;
        if(hasPlace) {
            pipes.add(pipe);
            pipe.addElement(this);
            //TODO Itt kerül majd ki a cső a player inventoryjából
            Logger.logToConsole("console.txt", "["+getLogID()+"]: "+pipe.getLogID()+" has been attached");
        }
        else
            Logger.logToConsole("console.txt", "["+getLogID()+"]: "+pipe.getLogID()+" could not be attached");
        return hasPlace;
    }

    /**
     * Pumpál az elem, az ActiveElement osztály leszármazottai implementálják érdemlegesen.
     */
    public abstract void pumpWater();

    /**
     * Megnézi, hogy ragacsos-e az elem.
     * @return mindig false, az aktív elemek nem lehetnek ragacsosak.
     */
    @Override
    public boolean checkSticky(){ return false; }

    /**
     * Megnézi, hogy csúszós-e az elem.
     * @return mindig false, az aktív elemek nem lehetnek csúszósak.
     */
    @Override
    public boolean checkSlippery(){ return false; }

    /**
     * Visszaadja a csőnek az egyik végét, ActiveElementben nem lesz implementálva.
     */
    @Override
    public ActiveElement getRandomEnd() {
        return null;
    }

    /**
     * Leszármazott osztálytól függően visszaad egy String az osztály nevével és az objektum azonosítójával.
     * @return StringID
     */
    public abstract String getLogID();

    @Override
    public MapElement[] getNeighbours() {
        return pipes.toArray(new MapElement[pipes.size()]);
    }

    //TODO Control függvény megvalósítása
}
