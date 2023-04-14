package main.players;

import main.map.MapElement;

public abstract class Player {
    protected MapElement mapElement;
    protected int stepsLeft;

    public void setMapElement(MapElement mapElement) {
        this.mapElement = mapElement;
    }

    public void configurePump(){

    }

    public void step(MapElement element){

    }
}
