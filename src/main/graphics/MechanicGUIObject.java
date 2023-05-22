package main.graphics;

import main.players.Mechanic;

import java.awt.*;

public class MechanicGUIObject extends GUIObject{

    private Mechanic mechanic;
    private Point position;

    public MechanicGUIObject(Mechanic mechanic){
        this.mechanic = mechanic;
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
        return null;
    }
}
