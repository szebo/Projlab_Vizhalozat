package main.graphics;

import main.map.Cistern;
import main.map.Pipe;
import main.map.Pump;
import main.map.Spring;
import main.players.Mechanic;
import main.players.Saboteur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class GUIManager {

    private static GUIManager instance = null;
    private String guiMessage;
    private List<GUIObject> guiObjects;

    private JPanel graphics = null;

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

    public void createPumpGUIObject(Pump pump, Point point){
        guiObjects.add(new PumpGUIObject(pump, point));
    }

    public void createSpringGUIObject(Spring spring, Point point){
        guiObjects.add(new SpringGUIObject(spring, point)); //TODO IDE HELYES SZÁM KELL
    }

    public void createCisternGUIObject(Cistern cistern, Point position){
        guiObjects.add(new CisternGUIObject(cistern, position));
    }

    public void createSaboteurGUIObject(Saboteur saboteur){
        guiObjects.add(0, new SaboteurGUIObject(saboteur));
    }

    public void createMechanicGUIObject(Mechanic mechanic){
        guiObjects.add(0, new MechanicGUIObject(mechanic));
    }

    public void draw(Graphics2D g2){
        for (int i = guiObjects.size()-1; i >= 0; i--) {
            guiObjects.get(i).draw(g2);
        }
    }

    public void clickObjects(MouseEvent e){
        for(GUIObject object : guiObjects){
            object.onClick(e);
        }
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

    public void setGraphics(JPanel panel){
        graphics = panel;
    }

    public void repaintGame(){
        graphics.repaint();
    }
}
