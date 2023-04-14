package main.players;

import main.map.MapElement;

public abstract class Player {
    private MapElement mapElement;


    public void setMapElement(MapElement mapElement) {
        this.mapElement = mapElement;
    }
}
