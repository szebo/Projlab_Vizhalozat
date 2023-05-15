package main.map;

import main.Main;
import main.interfaces.IControllable;
import main.interfaces.Updatable;
import main.logging.Logger;
import main.players.SaboteurTeam;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Map implements Updatable {

    private final List<MapElement> mapElements;
    private final List<IControllable> controllableMapElements;
    private final List<Updatable> updatableMapElements;
    private final List<Pipe> pipeList;
    private final List<ActiveElement> activeElements;
    private final List<Spring> springs;
    private static Map instance = null;
    private int startPositionCounter = 0;
    private Map(){
        mapElements = new ArrayList<>();
        controllableMapElements = new ArrayList<>();
        updatableMapElements = new ArrayList<>();
        pipeList = new ArrayList<>();
        activeElements = new ArrayList<>();
        springs = new ArrayList<>();
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
    public void waterFlow(int iterations)
    {
        for(Spring s: springs)
        {
            pumpWater(s);
            Logger.log("console.txt", "[Map]: water is pumped", true);
        }
    }


    public void pumpWater(ActiveElement ae)
    {
        ae.pumpWater();
        List<Pipe> outputPipes = ae.getOutputPipes();
        Logger.log("console.txt", "[ActiveElement]: in water is pumped", false);
        if(outputPipes != null) {
            for (Pipe p : outputPipes) {
                MapElement[] meArray = p.getNeighbours();
                for (MapElement me : meArray) {
                    if (me != null && me != ae) {
                        Logger.log("console.txt", "[ActiveElement]: out water is pumped", false);
                        pumpWater((ActiveElement) me);
                    }
                }
            }
        }
    }

    //--------------------------------------------------------------
    //B Terv
    public void addActive(ActiveElement element) { controllableMapElements.add(element); }
    //--------------------------------------------------------------

    public void update(){
        for(Updatable ume : updatableMapElements)
        {
            ume.update();
        }
    }

    public void create(String type){
        switch (type) {
            case "Pump" -> {
                Pump pump = new Pump();
                mapElements.add(pump);
                controllableMapElements.add(pump);
                activeElements.add(pump);
                Logger.log("console.txt", "[Map]: " + pump.getLogID() + " has been created", true);
            }
            case "Cistern" -> {
                Cistern cistern = new Cistern();
                mapElements.add(cistern);
                controllableMapElements.add(cistern);
                activeElements.add(cistern);
                Logger.log("console.txt", "[Map]: " + cistern.getLogID() + " has been created", true);
            }
            case "Spring" -> {
                Spring spring = new Spring();
                mapElements.add(spring);
                controllableMapElements.add(spring);
                activeElements.add(spring);
                springs.add(spring);
                Logger.log("console.txt", "[Map]: " + spring.getLogID() + " has been created", true);
            }
            case "Pipe" -> {
                Pipe pipe = new Pipe();
                mapElements.add(pipe);
                updatableMapElements.add(pipe);
                pipeList.add(pipe);
                Logger.log("console.txt", "[Map]: " + pipe.getLogID() + " has been created", true);
            }
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
                        switch (splits[0]) {
                            case "Pump" -> {
                                Pump pump = new Pump(Integer.parseInt(splits[1]));
                                mapElements.add(pump);
                                controllableMapElements.add(pump);
                                activeElements.add(pump);
                                Logger.log("console.txt", "[Map]: " + pump.getLogID() + " has been created", true);
                            }
                            case "Cistern" -> {
                                Cistern cistern = new Cistern();
                                mapElements.add(cistern);
                                controllableMapElements.add(cistern);
                                activeElements.add(cistern);
                                Logger.log("console.txt", "[Map]: " + cistern.getLogID() + " has been created", true);
                            }
                            case "Spring" -> {
                                Spring spring = new Spring();
                                mapElements.add(spring);
                                controllableMapElements.add(spring);
                                activeElements.add(spring);
                                springs.add(spring);
                                Logger.log("console.txt", "[Map]: " + spring.getLogID() + " has been created", true);
                            }
                        }
                    } else {
                        Pipe pipe = new Pipe(Integer.parseInt(splits[2]));
                        mapElements.add(pipe);
                        getElement(Integer.parseInt(splits[0])).attachPipe(pipe);
                        //Logger.log("console.txt", splits[3], true);
                        if(!Objects.equals(splits[3], "cors"))
                        {
                            //Logger.log("console.txt", splits[3], true);
                            Pump pum = (Pump) getElement(Integer.parseInt(splits[0]));
                            if(Objects.equals(splits[3], "in_pump"))
                            {
                                pum.setInput(pipe);
                            }
                            else if(Objects.equals(splits[3], "out_pump"))
                            {
                                pum.setOutput(pipe);
                            }
                        }
                        getElement(Integer.parseInt(splits[1])).attachPipe(pipe);
                        if(!Objects.equals(splits[4], "cors")) {
                            Pump pum = (Pump) getElement(Integer.parseInt(splits[1]));
                            if(Objects.equals(splits[4], "in_pump"))
                            {
                                pum.setInput(pipe);
                            }
                            else if(Objects.equals(splits[4], "out_pump"))
                            {
                                pum.setOutput(pipe);
                            }
                        }
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

    public MapElement getStartPosition()
    {
        return pipeList.get(startPositionCounter++);
    }
    
    public void resetAfterTest(){
        activeElements.clear();
        mapElements.clear();
        updatableMapElements.clear();
        controllableMapElements.clear();
        pipeList.clear();
    }
}

/*
DUMP

public void storeNewMapElement(MapElement element){ mapElements.add(element); }

public void removeElement(MapElement element) { mapElements.remove(element); }

B-TERV
public void removeActive(ActiveElement element) { controllableMapElements.remove(element); }

public void addUpdatable(Updatable element) { updatableMapElements.add(element); }

public void control()
    {
        for(IControllable cme : controllableMapElements)
        {
            cme.pumpWater();
        }
    }
 */
