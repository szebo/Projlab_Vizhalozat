package main.map;

import main.interfaces.IControllable;
import main.interfaces.Updatable;
import main.players.SaboteurTeam;

import java.util.ArrayList;
import java.util.List;

public class Map implements Updatable{

    private List<MapElement> mapElements;
    private List<IControllable> controllableMapElements;
    private List<Updatable> updatableMapElements;
    private static Map instance = null;
    private Map(){
        mapElements = new ArrayList<>();
        controllableMapElements = new ArrayList<>();
        updatableMapElements = new ArrayList<>();
    }

    public static synchronized Map getInstance()
    {
        if (instance == null)
            instance = new Map();
        return instance;
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

    public void update(){
        for(Updatable ume : updatableMapElements)
        {
            ume.update();
        }
    }

    public void control()
    {
        for(IControllable cme : controllableMapElements)
        {
            cme.control();
        }
    }

    public MapElement getElement(int number){
        if(number < mapElements.size())
            return mapElements.get(number);
        else return null;
    }
}
