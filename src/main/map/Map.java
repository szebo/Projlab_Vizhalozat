package main.map;

import main.Main;
import main.interfaces.IControllable;
import main.interfaces.Updatable;
import main.logging.Logger;
import main.players.SaboteurTeam;

import java.io.*;
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

    public void create(String type){
        switch (type){
            case "Pump":
                Pump pump = new Pump();
                Main.map.storeNewMapElement(pump);
                Main.map.addActive(pump);
                Logger.logToConsole("log.txt", "[Map]: "+pump.getLogID()+" has been created");
                break;
            case "Cistern":
                Cistern cistern = new Cistern();
                Main.map.storeNewMapElement(cistern);
                Main.map.addActive(cistern);
                Logger.logToConsole("log.txt", "[Map]: "+cistern.getLogID()+" has been created");
                break;
            case "Spring":
                Spring spring = new Spring();
                Main.map.storeNewMapElement(spring);
                Main.map.addActive(spring);
                Logger.logToConsole("log.txt", "[Map]: "+spring.getLogID()+" has been created");
                break;
            case "Pipe":
                Pipe pipe = new Pipe();
                Main.map.storeNewMapElement(pipe);
                Main.map.addUpdatable(pipe);
                Logger.logToConsole("log.txt", "[Map]: "+pipe.getLogID()+" has been created");
                break;
        }
    }

    public void loadMap(String id){
        try {
            BufferedReader reader = new BufferedReader(new FileReader("/files/maps/"+id+".txt"));
            List<String> lines = reader.lines().toList();
            boolean readingElements = true;
            for(String line : lines){
                if(line.equals("Pipes")) readingElements = false;
                String[] splits = line.split(",");
                if(readingElements) {
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
                    Main.map.storeNewMapElement(element);
                    Main.map.addActive(element);
                }
                else{
                    Pipe pipe = new Pipe(Integer.parseInt(splits[2]));
                    Main.map.storeNewMapElement(pipe);
                    Main.map.getElement(Integer.parseInt(splits[0])).attachPipe(pipe);
                    Main.map.getElement(Integer.parseInt(splits[1])).attachPipe(pipe);
                    Main.map.addUpdatable(pipe);
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveMap(String id){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("/files/maps/"+id+".txt"));
            writer.append("ActiveElements\n");
            for(ActiveElement element : Map.getInstance().getElement()){ //használj egy while(true)-t meg egy counter és a már meglévő getElementet
                //hogy elért a MapelElementeket. Ha csak az active elementeken akarsz végig menni akkor csinalj arra is egy gettert.
                //TODO FIX!!
                writer.append(element.getLogID()+","+element.getCapacity()+"\n");
            }
            writer.append("Pipes\n");
            //for()

            writer.close();
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
        return null;
    }

    public MapElement getElement(int number){
        if(number < mapElements.size())
            return mapElements.get(number);
        else return null;
    }
}
