package main.graphics;

import main.map.Pump;

import java.awt.*;

public class PumpGUIObject extends GUIObject{

    private Pump pump;
    private Point position;

    public PumpGUIObject(Pump pump){
        this.pump = pump;
    }

    @Override
    public void onClick() {

    }

    @Override
    public void paintComponent(Graphics g){

    }

    private void drawWorking(Point point){

    }

    private void drawBroken(Point point){

    }

    @Override
    public Point getPosition() {
        return getLocation();
    }
}
