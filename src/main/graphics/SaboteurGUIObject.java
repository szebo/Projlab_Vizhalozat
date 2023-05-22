package main.graphics;

import main.players.Saboteur;

import java.awt.*;

public class SaboteurGUIObject extends GUIObject{

    private Saboteur saboteur;
    private Point position;

    public SaboteurGUIObject(Saboteur saboteur){
        this.saboteur = saboteur;
    }
    @Override
    public void onClick() {

    }

    @Override
    public void draw() {

    }

    public void drawAtPosition(Point point){

    }

    @Override
    public Point getPosition() {
        return position;
    }
}
