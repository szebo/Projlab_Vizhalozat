package main.players;

import main.map.MapElement;

import java.util.Objects;

/**
 * A két játékostípus közös működését megvalósító absztrakt osztály
 * **/
public abstract class Player {
    /**
     * Az a MapElement, amelyen a játékos jelenleg áll.
     * **/
    protected MapElement mapElement;

    protected int stepsLeft;

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
     * A játékos lépését valósítja meg
     * @param element A MapElement amelyre lép a játékos
     * **/
    public void step(MapElement element){
        Objects.requireNonNull(element, "Null értékű paramétert kapott a step!");
        if(element.acceptPlayer(this)) {
            getMapElement().removePlayer(this);
            setMapElement(element);
        }

    }
}
