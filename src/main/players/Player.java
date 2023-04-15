package main.players;

import main.map.MapElement;
import main.map.Pipe;

public abstract class Player {
    protected MapElement mapElement;
    protected int stepsLeft;

    public void setMapElement(MapElement mapElement) {
        this.mapElement = mapElement;
    }

    public void configurePump(){

    }

    public void configurePump(Pipe p1, Pipe p2){

    }

    public void step(MapElement element){

    }
}
