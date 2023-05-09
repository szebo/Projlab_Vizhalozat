package main.map;

import main.interfaces.IControllable;
import main.interfaces.Updatable;

import java.util.ArrayList;
import java.util.List;

public class Map {

    private List<MapElement> mapElements;
    private List<IControllable> controllableMapElements;
    private List<Updatable> updatableMapElements;

    public Map(){
        mapElements = new ArrayList<>();
        controllableMapElements = new ArrayList<>();
        updatableMapElements = new ArrayList<>();
    }

    /**
     * B Terv Vízmozgatásra
     * @param iterations Megadja, hogy hányszor iteráljon végig a metódus az ActiveElementeken.
     */
    public void waterFlow(int iterations){
        for(int i = 0; i < iterations; i++){
            for(IControllable e : controllableMapElements){
                e.pumpWater();
            }
        }
    }

    public void storeNewMapElement(MapElement element){ mapElements.add(element); }

    public void removeElement(MapElement element) { mapElements.remove(element); }

    //--------------------------------------------------------------
    //B Terv
    public void addActive(ActiveElement element) { controllableMapElements.add(element); }

    public void removeActive(ActiveElement element) { controllableMapElements.remove(element); }

    //--------------------------------------------------------------

    public void addUpdatable(Updatable element) { updatableMapElements.add(element); }

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

    public MapElement getElement(int number){
        if(number < mapElements.size())
            return mapElements.get(number);
        else return null;
    }
}
