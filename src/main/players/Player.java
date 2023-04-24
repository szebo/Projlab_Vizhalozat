package main.players;

import main.map.MapElement;

import java.util.Objects;
import java.util.Random;

/**
 * A két játékostípus közös működését megvalósító absztrakt osztály
 * **/
public abstract class Player {
    /**
     * Az a MapElement, amelyen a játékos jelenleg áll.
     * **/
    protected MapElement mapElement;

    protected int stepsLeft;

    public void setStuck(int stuck) {
        this.stuck = stuck;
    }

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
     * **/
    public void configurePump() {

    }

    /**
     * Eltöri azt az elemet amelyen áll a játékos.
     */
    public void breakElement(){
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
            setStuck(stuck - 1);
            return;
        }
        if(element.acceptPlayer(this) && this.stepsLeft > 0) {
            getMapElement().removePlayer(this);
            setMapElement(element);
            this.stepsLeft--;
            if (element.checkSticky()) {
                setStuck(5);
                element.makeSticky(0); //TODO: Itt leesik a stickyFor a csőről, de kéne valami, hogy magáltól is leessen, pl amikor a köröket léptetjük.
            }
            if(element.checkSlippery()){            //Itt nem lenne ertelemszerubb egy else if?
                getMapElement().removePlayer(this);
                setMapElement(element.getRandomEnd());
                this.mapElement.addPlayer(this); //A mapElementre, amire átdobódik, is hozzá kell adni a playert
                //TODO: Ugyan az mint a stickyFor esetében, itt is le kell essen majd kör léptetéskor a slippery effect.
            }
        }
    }

    public void repair() {
    }

    public void useSlipperyGoo() {
    }

    public void placePipe() {
    }
}
