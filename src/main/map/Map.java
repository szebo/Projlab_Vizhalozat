package main.map;

import main.interfaces.IControllable;
import main.interfaces.Updatable;

import java.util.ArrayList;
import java.util.List;

public class Map {

    private List<MapElement> mapElements;
    private List<IControllable> controllableElements;
    private List<Updatable> updatableMapElements;

    public Map(){
        mapElements = new ArrayList<>();
        controllableElements = new ArrayList<>();
    }

    /**
     * B Terv Vízmozgatásra
     * @param iterations Megadja, hogy hányszor iteráljon végig a metódus az ActiveElementeken.
     */
    public void waterFlow(int iterations){
        for(int i = 0; i < iterations; i++){
            for(IControllable e : controllableElements){
                e.pumpWater();
            }
        }
    }

    public void storeNewMapElement(MapElement element){ mapElements.add(element); }

    public void removeElement(MapElement element) { mapElements.remove(element); }

    //--------------------------------------------------------------
    //B Terv
    public void addActive(ActiveElement element) { controllableElements.add(element); }

    public void removeActive(ActiveElement element) { controllableElements.remove(element); }

    //--------------------------------------------------------------

    public MapElement getElement(String id){
        for(MapElement mapElement : mapElements){
            if(mapElement.getLogID().equals(id)){
                return mapElement;
            }
        }
        return null;
    }

    public void updateMap(){

        //TODO Minden elemre iterálva meghívjuk az update-ot. Ezt hívja meg a controller.
    }

}
