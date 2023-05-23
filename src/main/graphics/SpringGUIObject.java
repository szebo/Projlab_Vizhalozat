package main.graphics;

import main.map.Spring;

import java.awt.*;

public class SpringGUIObject extends GUIObject{

    private Spring spring;
    private Point position;

    public SpringGUIObject(Spring spring){
        this.spring = spring;
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
