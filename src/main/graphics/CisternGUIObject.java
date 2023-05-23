package main.graphics;

import main.map.Cistern;

import java.awt.*;

public class CisternGUIObject extends GUIObject{

    private Cistern cistern;
    private Point position;

    public CisternGUIObject(Cistern cistern){
        this.cistern = cistern;
    }

    @Override
    public void onClick() {

    }

    @Override
    public void paintComponent(Graphics g){

    }

    public void drawAtPosition(Point point){

    }

    @Override
    public Point getPosition() {
        return getLocation();
    }
}
