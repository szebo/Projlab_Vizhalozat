package main.map;

import java.util.ArrayList;
import java.util.List;

public class Map {

    private List<MapElement> mapElements;
    private List<ActiveElement> activeElements;

    public Map(){
        mapElements = new ArrayList<>();
        activeElements = new ArrayList<>();
    }

    /**
     * B Terv Vízmozgatásra
     * @param iterations Megadja, hogy hányszor iteráljon végig a metódus az ActiveElementeken.
     */
    public void waterFlow(int iterations){
        for(int i = 0; i < iterations; i++){
            for(ActiveElement e : activeElements){
                e.pumpWater();
            }
        }
    }

    public void storeNewMapElement(MapElement element){ mapElements.add(element); }

    public void removeElement(MapElement element) { mapElements.remove(element); }

    //--------------------------------------------------------------
    //B Terv
    public void addActive(ActiveElement element) { activeElements.add(element); }

    public void removeActive(ActiveElement element) { activeElements.remove(element); }

    public MapElement getElement(String id){
        for(MapElement mapElement : mapElements){
            if(mapElement.getLogID().equals(id)){
                return mapElement;
            }
        }
        return null;
    }

}
