package main.graphics;

import main.map.Cistern;
import main.map.Pipe;
import main.map.Pump;
import main.map.Spring;
import main.players.Mechanic;
import main.players.Saboteur;

import java.util.ArrayList;
import java.util.List;

public class GUIManager {

    private GUIManager instance = null;
    private String guiMessage;
    private List<GUIObject> guiObjects;

    private GUIManager(){
        guiObjects = new ArrayList<>();
    }

    public GUIManager getInstance(){
        if (instance == null)
                instance = new GUIManager();

        return instance;
    }

    public String getGUIMessage(){
        return guiMessage;
    }

    public void addGUIMessage(String guiMessage){
        this.guiMessage = guiMessage;
    }

    public void createPipeGUIObject(Pipe pipe){
        guiObjects.add(new PipeGUIObject(pipe));
    }

    public void createPumpGUIObject(Pump pump){
        guiObjects.add(new PumpGUIObject(pump));
    }

    public void createSpringGUIObject(Spring spring){
        guiObjects.add(new SpringGUIObject(spring));
    }

    public void createCisternGUIObject(Cistern cistern){
        guiObjects.add(new CisternGUIObject(cistern));
    }

    public void createSaboteurGUIObject(Saboteur saboteur){
        guiObjects.add(new SaboteurGUIObject(saboteur));
    }

    public void createMechanicGUIObject(Mechanic mechanic){
        guiObjects.add(new MechanicGUIObject(mechanic));
    }

    public void draw(){

    }

}
