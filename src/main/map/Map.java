package main.map;

import main.Main;
import main.interfaces.IControllable;
import main.interfaces.Updatable;
import main.logging.Logger;
import main.players.SaboteurTeam;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Map implements Updatable {

    private List<MapElement> mapElements;
    private List<IControllable> controllableMapElements;
    private List<Updatable> updatableMapElements;
    private List<Pipe> pipeList;
    private List<ActiveElement> activeElements;
    private static Map instance = null;
    private Map(){
        mapElements = new ArrayList<>();
        controllableMapElements = new ArrayList<>();
        updatableMapElements = new ArrayList<>();
        pipeList = new ArrayList<>();
        activeElements = new ArrayList<>();
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
        Logger.log("console.txt", "[Map]: water is pumped", true);
    }

    public void storeNewMapElement(MapElement element){ mapElements.add(element); }

    public void removeElement(MapElement element) { mapElements.remove(element); }

    //--------------------------------------------------------------
    //B Terv
    public void addActive(ActiveElement element) { controllableMapElements.add(element); }

    public void removeActive(ActiveElement element) { controllableMapElements.remove(element); }

    //--------------------------------------------------------------

    public void addUpdatable(Updatable element) { updatableMapElements.add(element); }

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
            cme.pumpWater();
        }
    }

    public void create(String type){
        switch (type){
            case "Pump":
                Pump pump = new Pump();
                mapElements.add(pump);
                controllableMapElements.add(pump);
                activeElements.add(pump);
                Logger.log("console.txt", "[Map]: "+pump.getLogID()+" has been created", true);
                break;
            case "Cistern":
                Cistern cistern = new Cistern();
                mapElements.add(cistern);
                controllableMapElements.add(cistern);
                activeElements.add(cistern);
                Logger.log("console.txt", "[Map]: "+cistern.getLogID()+" has been created", true);
                break;
            case "Spring":
                Spring spring = new Spring();
                mapElements.add(spring);
                controllableMapElements.add(spring);
                activeElements.add(spring);
                Logger.log("console.txt", "[Map]: "+spring.getLogID()+" has been created", true);
                break;
            case "Pipe":
                Pipe pipe = new Pipe();
                mapElements.add(pipe);
                updatableMapElements.add(pipe);
                pipeList.add(pipe);
                Logger.log("console.txt", "[Map]: "+pipe.getLogID()+" has been created", true);
                break;
        }
    }

    public void loadMap(String id){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(Main.rootfolder+"/files/maps/"+id+".txt"));
            List<String> lines = reader.lines().toList();
            boolean readingElements = true;
            for(String line : lines) {
                if (line.contains("Pipes")) readingElements = false;
                if (!line.contains("ActiveElements") && !line.contains("Pipes")) {
                    String[] splits = line.split(",");
                    if (readingElements) {
                        ActiveElement element = null;
                        switch (splits[0]) {
                            case "Cistern":
                                element = new Cistern();
                                break;

                            case "Spring":
                                element = new Spring();
                                break;

                            case "Pump":
                                element = new Pump(Integer.parseInt(splits[1]));
                                break;
                        }
                        mapElements.add(element);
                        controllableMapElements.add(element);
                    } else {
                        Pipe pipe = new Pipe(Integer.parseInt(splits[2]));
                        mapElements.add(pipe);
                        getElement(Integer.parseInt(splits[0])).attachPipe(pipe);
                        getElement(Integer.parseInt(splits[1])).attachPipe(pipe);
                        updatableMapElements.add(pipe);
                        pipeList.add(pipe);
                    }
                }
            }
            reader.close();
            Logger.log("console.txt", "[Map]: map loaded", true);
        } catch (FileNotFoundException e) {
            Logger.log("console.txt", "[Map]: map not found", true);
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveMap(int id){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(Main.rootfolder+"/files/maps/"+id+".txt"));
            writer.append("ActiveElements\n");
            for(ActiveElement element : activeElements){
                writer.append(element.getLogID()+","+element.getCapacity()+"\n");
            }
            writer.append("Pipes\n");
            for(Pipe pipe : pipeList){
                MapElement[] neighbours = pipe.getNeighbours();
                writer.append(activeElements.indexOf(neighbours[0])+","+activeElements.indexOf(neighbours[1])+","+pipe.getCapacity()+"\n");
            }

            writer.close();
            Logger.log("console.txt", "[Map]: map saved as "+id, true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public MapElement getElement(String id){
        for(MapElement mapElement : mapElements){
            if(mapElement.getLogID().equals(id)){
                return mapElement;
            }
        }
        Logger.log("console.txt", "[System]: Element doesn't exist", true);
        return null;
    }

    public MapElement getElement(int number){
        if(number < mapElements.size())
            return mapElements.get(number);
        else{
            Logger.log("console.txt", "[System]: Element doesn't exist", true);
            return null;
        }
    }

    public Pipe getPipe(String id){
        for(Pipe pipe : pipeList){
            if(pipe.getLogID().equals(id)){
                return pipe;
            }
        }
        Logger.log("console.txt", "[System]: Element doesn't exist", true);
        return null;
    }
    public void resetAfterTest(){
        activeElements.clear();
        mapElements.clear();
        updatableMapElements.clear();
        controllableMapElements.clear();
        pipeList.clear();
    }
}
