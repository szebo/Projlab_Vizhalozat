package main.players;

import main.logging.Logger;
import main.map.MapElement;
import main.map.Pipe;
import main.map.Pump;

import java.util.Objects;

/**
 * A két játékostípus közös működését megvalósító absztrakt osztály
 * **/
public abstract class Player {
    /**
     * Az a MapElement, amelyen a játékos jelenleg áll.
     * **/
    protected MapElement mapElement;

    /**
     * Az objektum azonosítója.
     */
    protected int ID;
    protected int stepsLeft = 2;
    protected int numberOfActions = 2;

    public void setStuck(int stuck) {
        this.stuck = stuck;
    }

    public int getStuck(){return stuck;}

    protected int stuck;

    /**
     * A paraméterben kapott mapElementre lépteti a játékost
     * @param mapElement Az elem amelyre léptetni kell a játékost
     * **/
    public void setMapElement(MapElement mapElement) {this.mapElement = mapElement;}

    public MapElement getMapElement(){return mapElement;}

    /**
    * A játékost aktuális helyzete kérdezhető le
     * @return mapElement attribútum
    * */
    public String getMapElementAsString(){ return this.mapElement.toString(); }

    /**
     * A játékos ebben a függvényben állítja be az új ki- és bemeneti csöveket
     * @param pipe1 A bemeneti cső referenciája
     * @param pipe2 A kimeneti cső referenciája
     * **/
    public void configurePump(Pipe pipe1, Pipe pipe2) {
        Pump p = (Pump)mapElement;
        p.setInput(pipe1); //itt castolva javitottam vagy csináljátok meg rendesen a származtatást meg a tárolást vagy ez van
        p.setInput(pipe2);
        Logger.log("console.txt", "["+mapElement.getLogID()+"]: input is "+pipe1.getLogID()+", output is "+pipe2.getLogID(), true);
    }

    /**
     * Eltöri azt az elemet amelyen áll a játékos.
     */
    public void breakElement(){
        if(!mapElement.checkUnbreakable())
            mapElement.breakElement();
    }

    /**
     * Ragacsossá teszi a csövet, amin áll a játékos.
     */
    public void useStickyGoo() {
        mapElement.makeSticky(2);
    }

    /**
     * A játékos lépését valósítja meg.
     * Megvizsgálja, hogy be van-e ragadva a játékos, ha igen, nem lép.
     * @param element A MapElement amelyre lép a játékos
     * **/
    public void step(MapElement element){
        Objects.requireNonNull(element, "Null értékű paramétert kapott a step!");
        if(stuck != 0){
            stuck--;
            return;
        }
        if(element.acceptPlayer(this) && this.stepsLeft > 0) {
            mapElement.removePlayer(this);
            mapElement = element;
            element.addPlayer(this);
            this.stepsLeft--;
        }
        if(stepsLeft <= 0) stepsLeft = 2;
    }

    public void repair() {}
    public void useSlipperyGoo() {}
    public void placePipe() {}
    public void placePump(){}
    public void pickUpPipe(Pipe pipe){}
    public void pickUpPump(){}

    /**
     * Leszármazott osztálytól függően visszaad egy stringet az osztály nevével és az osbejtum azonosítójával
     * @return StringID
     */
    public abstract String getLogID();

    /**
     * Leszármazott osztálytól függően létrehoz egy stringet az objektum információval.
     * @return string
     */
    public abstract String printInfo();
}
