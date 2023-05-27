package main.graphics;

import main.map.Cistern;
import main.map.Pipe;
import main.map.Pump;
import main.map.Spring;
import main.players.Mechanic;
import main.players.Saboteur;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GUIManager {

    private static GUIManager instance = null;
    private String guiMessage;
    private List<GUIObject> guiObjects;

    private GUIManager(){
        guiObjects = new ArrayList<>();
    }

    public static synchronized GUIManager getInstance(){
        if (instance == null)
                instance = new GUIManager();

        return instance;
    }


    public void createPipeGUIObject(Pipe pipe){
        guiObjects.add(new PipeGUIObject(pipe));
    }

    public void createPumpGUIObject(Pump pump){
        guiObjects.add(new PumpGUIObject(pump));
    }

    public void createSpringGUIObject(Spring spring){
        guiObjects.add(new SpringGUIObject(spring, new Point(10, 10))); //TODO IDE HELYES SZÁM KELL
    }

    public void createCisternGUIObject(Cistern cistern, Point position){
        guiObjects.add(new CisternGUIObject(cistern, position));
    }

    public void createSaboteurGUIObject(Saboteur saboteur){
        guiObjects.add(new SaboteurGUIObject(saboteur));
    }

    public void createMechanicGUIObject(Mechanic mechanic){
        guiObjects.add(new MechanicGUIObject(mechanic));
    }

    public void draw(JPanel view){
        for (GUIObject object: guiObjects) {
            object.draw(view.getGraphics());
        }
        view.repaint();
    }

    public GUIObject getGUIObjectByID(String id){
        for(GUIObject object : guiObjects){
            if(object.getElement().getLogID().equals(id))
                return object;
        }
        return null;
    }

    public GUIObject getGUIPlayerByID(String id){
        for(GUIObject object : guiObjects){
            if(object.getPlayer().getLogID().equals(id))
                return object;
        }
        return null;
    }



}
